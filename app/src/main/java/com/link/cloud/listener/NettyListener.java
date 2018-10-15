package com.link.cloud.listener;

/**
 * Created by 49488 on 2018/10/14.
 */

public interface NettyListener {
    void onNettySuccess();
    void onNettyFail(String msg);
    void onNettyLoss(String msg);
    void onMessageReceive(String msg);

}
