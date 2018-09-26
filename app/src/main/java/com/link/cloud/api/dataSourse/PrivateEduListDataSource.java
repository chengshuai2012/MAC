package com.link.cloud.api.dataSourse;

import com.link.cloud.User;
import com.link.cloud.api.ApiFactory;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.api.request.PageRequest;
import com.link.cloud.api.response.PageResponse;
import com.shizhefei.mvc.IAsyncDataSource;
import com.shizhefei.mvc.RequestHandle;
import com.shizhefei.mvc.ResponseSender;
import com.zitech.framework.data.network.response.ApiResponse;
import com.zitech.framework.data.network.subscribe.PagedListConsumer;

import java.util.List;

import rx.Subscription;


public class PrivateEduListDataSource implements IAsyncDataSource<List<PrivateEduBean>> {

    private int mPage = 1;
    private boolean hasMore = true;

    @Override
    public RequestHandle refresh(ResponseSender<List<PrivateEduBean>> sender) throws Exception {
        mPage = 1;
        return load(sender, mPage);
    }

    @Override
    public RequestHandle loadMore(ResponseSender<List<PrivateEduBean>> sender) throws Exception {
        return load(sender, mPage);
    }

    @Override
    public boolean hasMore() {
        return hasMore;
    }


    private RequestHandle load(final ResponseSender<List<PrivateEduBean>> sender, final int page) throws Exception {

        PageRequest request = new PageRequest();
        request.setToken(User.get().getToken());
        request.setPageSize(10);
        request.setPageNum(mPage);
        final Subscription subscription = ApiFactory.privateEduList(request).subscribe(new PagedListConsumer<ApiResponse<PageResponse<PrivateEduBean>>>() {

            @Override
            protected void accept(ApiResponse<PageResponse<PrivateEduBean>> response) {

//                List<PrivateEduBean> items = response.getData().getRows();
//
//                if (mPage < response.getData().getPages()) {
//                    mPage++;
//                    hasMore = true;
//                    sender.sendData(items);
//                } else if (mPage <= response.getData().getPages()) {
//                    hasMore = false;
//                    sender.sendData(items);
//                } else {
//                    hasMore = false;
//                    sender.sendData(items);
//                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                sender.sendError(new Exception(e));
            }
        });

        return new RequestHandle() {
            @Override
            public void cancle() {
                subscription.unsubscribe();
            }

            @Override
            public boolean isRunning() {
                return false;
            }
        };
    }

}
