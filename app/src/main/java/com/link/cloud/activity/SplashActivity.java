package com.link.cloud.activity;

import android.content.Intent;

import com.link.cloud.R;
import com.link.cloud.User;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.BaseProgressSubscriber;
import com.link.cloud.api.dataSourse.UserList;
import com.link.cloud.api.request.GetUserPages;
import com.link.cloud.base.AppBarActivity;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.NoProgressSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.realm.Realm;

/**
 * Created by 49488 on 2018/10/20.
 */

public class SplashActivity extends AppBarActivity {
    Realm realm;

    @Override
    protected void initViews() {
        hideToolbar();
        realm = Realm.getDefaultInstance();
        getToken();

    }

    int pageNum = 1;
    int total = 0;

    private void getTotal() {
        GetUserPages getUserPages = new GetUserPages();
        getUserPages.setContent("HJKF");
        getUserPages.setPageNo(1);
        getUserPages.setPageSize(100);
        ApiFactory.getUsers(getUserPages).subscribe(new BaseProgressSubscriber<ApiResponse<UserList>>(this) {
            @Override
            public void onNext(ApiResponse<UserList> apiResponse) {
                final UserList userList = apiResponse.getData();
                total = userList.getTotal();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealm(userList.getData());
                    }
                });

            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                getAllData();
            }
        });
    }

    private void getAllData() {
        int totalPage = total / pageNum + 1;
        ExecutorService executorService = Executors.newFixedThreadPool(totalPage);
        List<Future<Boolean>> futures = new ArrayList();
        if (totalPage >= 1) {
            for (int i = 1; i < totalPage; i++) {
                Callable<Boolean> task = new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        GetUserPages getUserPages = new GetUserPages();
                        getUserPages.setContent("HJKF");
                        getUserPages.setPageNo(1);
                        getUserPages.setPageSize(100);
                        ApiFactory.getUsers(getUserPages).subscribe(new NoProgressSubscriber<ApiResponse<UserList>>(SplashActivity.this) {
                            @Override
                            public void onNext(ApiResponse<UserList> apiResponse) {
                                final UserList userList = apiResponse.getData();
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        realm.copyToRealm(userList.getData());
                                    }
                                });
                            }
                        });
                        return true;
                    }
                };

                futures.add(executorService.submit(task));
            }
        }
        for (Future<Boolean> future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    private void getToken() {
        ApiFactory.appLogin().subscribe(new BaseProgressSubscriber<ApiResponse>(this) {
            @Override
            public void onNext(ApiResponse response) {
                super.onNext(response);
                User.get().setToken((String) response.getData());
                getTotal();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}
