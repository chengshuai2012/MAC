package com.link.cloud.activity;

import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import com.link.cloud.MacApplication;
import com.link.cloud.R;
import com.link.cloud.User;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.BaseProgressSubscriber;
import com.link.cloud.api.bean.APPVersionBean;
import com.link.cloud.api.bean.DeviceBean;
import com.link.cloud.api.dataSourse.UserList;
import com.link.cloud.api.request.GetUserPages;
import com.link.cloud.base.AppBarActivity;
import com.link.cloud.bean.AllUser;
import com.link.cloud.bean.DeviceInfo;
import com.link.cloud.utils.TTSUtils;
import com.link.cloud.utils.Utils;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.NoProgressSubscriber;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.link.cloud.utils.Utils.writeFile;

/**
 * Created by 49488 on 2018/10/20.
 */

public class SplashActivity extends AppBarActivity {

    private RealmResults allLocal;
    private DeviceInfo deviceInfo;

    @Override
    protected void initViews() {
        hideToolbar();
        getToken();
        TTSUtils.getInstance().speak("");
    }

    int pageNum = 100;
    int total = 0;
    int local = 0;

    private void getTotal() {
        GetUserPages getUserPages = new GetUserPages();
        getUserPages.setContent(deviceInfo.getDeviceId());
        getUserPages.setPageNo(1);
        getUserPages.setPageSize(pageNum);
        ApiFactory.getUsers(getUserPages).subscribe(new BaseProgressSubscriber<ApiResponse<UserList>>(this) {
            @Override
            public void onStart() {
                super.onStart();
                dismissProgressDialog();
            }

            @Override
            public void onNext(ApiResponse<UserList> apiResponse) {
                final UserList userList = apiResponse.getData();
                total = userList.getTotal();
                Log.e("onNext: ", local + ">>>" + total);
                if (local != total) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            allLocal.deleteAllFromRealm();
                            realm.copyToRealm(userList.getData());
                        }
                    });

                }else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }

            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                if (local != total) {
                    getAllData();
                }
            }
        });
    }

    private void getAllData() {
        int totalPage = total / pageNum + 1;
        ExecutorService executorService = Executors.newFixedThreadPool(totalPage);
        List<Future<Boolean>> futures = new ArrayList();
        if (totalPage >= 2) {
            for (int i = 2; i < totalPage; i++) {
                final int finalI = i;
                Callable<Boolean> task = new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        GetUserPages getUserPages = new GetUserPages();
                        getUserPages.setContent(deviceInfo.getDeviceId());
                        getUserPages.setPageNo(finalI);
                        getUserPages.setPageSize(pageNum);
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
    private void getAppVersion(){
        ApiFactory.getAppVersion().subscribe(new BaseProgressSubscriber<ApiResponse<APPVersionBean>>(this) {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onNext(ApiResponse<APPVersionBean> appVersionBeanApiResponse) {
                super.onNext(appVersionBeanApiResponse);
                int version = Utils.getVersion(MacApplication.getInstance());
                if(version<Integer.parseInt(appVersionBeanApiResponse.getData().getVersion())){
                    final String filePath = Environment.getExternalStorageDirectory()+"/lingxi.apk";
                    ApiFactory.getApiService().getApp("app","downloadApp",1,User.get().getToken()).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                            .map(new Func1<ResponseBody, InputStream>() {
                                @Override
                                public InputStream call(ResponseBody responseBody) {
                                    return responseBody.byteStream();
                                }
                            }).observeOn(Schedulers.computation()).
                            doOnNext(new Action1<InputStream>() {
                        @Override
                        public void call(InputStream inputStream) {
                            writeFile(inputStream, filePath);
                        }
                    })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<InputStream>() {
                                @Override
                                public void onCompleted() {
                                    Log.e("onCompleted: ","" );
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.e("onError: ","" );
                                }

                                @Override
                                public void onNext(InputStream inputStream) {
                                    Log.e("InputStream: ","" );
                                }
                            });
                }
            }
        });
    }
    private void getToken() {

        RealmResults<DeviceInfo> all = realm.where(DeviceInfo.class).findAll();
        final RealmResults<AllUser> peopleIn = realm.where(AllUser.class).equalTo("isIn",1).findAll();
        for(int x=0;x<peopleIn.size();x++){
            final int finalX = x;
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    AllUser person = peopleIn.get(finalX);
                    person.setIsIn(0);
                }
            });
        }
        if (all.size() != 0) {
            deviceInfo = all.get(0);
            ApiFactory.appLogin(deviceInfo.getDeviceId().trim(), deviceInfo.getPsw()).subscribe(new BaseProgressSubscriber<ApiResponse<DeviceBean>>(this) {
                @Override
                public void onStart() {
                    super.onStart();
                    dismissProgressDialog();/**/
                }

                @Override
                public void onNext(ApiResponse<DeviceBean> response) {
                    super.onNext(response);
                    User.get().setToken(response.getData().getToken());
                    allLocal = realm.where(AllUser.class).findAll();
                    local = allLocal.size();
                    getTotal();
                    getAppVersion();
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    skipActivity(SettingActivity.class);
                }
            });
        } else {
            skipActivity(SettingActivity.class);
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }



}
