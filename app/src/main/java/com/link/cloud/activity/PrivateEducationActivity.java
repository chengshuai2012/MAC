package com.link.cloud.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.link.cloud.Constants;
import com.link.cloud.MacApplication;
import com.link.cloud.R;
import com.link.cloud.api.bean.PriceLevelBean;
import com.link.cloud.api.bean.PrivateEduBean;
import com.link.cloud.base.BaseActivity;
import com.link.cloud.bean.AllUser;
import com.link.cloud.fragment.BuyPrivateEduFragment;
import com.link.cloud.fragment.PrivateEduListFragment;
import com.link.cloud.listener.DialogCancelListener;
import com.link.cloud.utils.DialogUtils;
import com.link.cloud.utils.RxTimerUtil;
import com.link.cloud.widget.BottomBuyDialog;
import com.link.cloud.widget.PublicTitleView;
import com.zitech.framework.utils.ToastMaster;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * @author qianlu
 * @date 2018/9/26.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
@SuppressLint("Registered")
public class PrivateEducationActivity extends BaseActivity implements DialogCancelListener {

    private PublicTitleView publicTitle;
    private RxTimerUtil rxTimerUtil;
    private int minute;
    private int second;
    private long payRest;
    private DialogUtils dialogUtils;


    @Override
    protected void initViews() {

        setTitle(R.drawable.handy_logo);
        showFragment(PrivateEduListFragment.class);
        publicTitle = (PublicTitleView) findViewById(R.id.publicTitle);
        publicTitle.setItemClickListener(new PublicTitleView.onItemClickListener() {
            @Override
            public void itemClickListener() {
                finish();
            }
        });
        dialogUtils = DialogUtils.getDialogUtils(this, this);
        publicTitle.setTags(getResources().getString(R.string.select_class), getResources().getString(R.string.select_time), getResources().getString(R.string.sure_pay), getResources().getString(R.string.pay_success));

        rxTimerUtil = new RxTimerUtil();
    }


    private void finger() {
        rxTimerUtil.interval(2000, new RxTimerUtil.IRxNext() {
            @Override
            public void doNext(long number) {
                int state = MacApplication.getVenueUtils().getState();
                if (state == 3) {
                    RealmResults<AllUser> users = realm.where(AllUser.class).findAll();
                    List<AllUser> peoples = new ArrayList<>();
                    peoples.addAll(realm.copyFromRealm(users));
                    String uid = MacApplication.getVenueUtils().identifyNewImg(peoples);
                    if (null != uid && !TextUtils.isEmpty(uid)) {

                    } else {
                        ToastMaster.shortToast(getResources().getString(R.string.cheack_fail)+getResources().getString(R.string.again_finger));
                    }
                }}
        }     );
    }


    public void showPay(String money) {
//        minute = payRestTime.getMinute();
//        second = payRestTime.getSecond();
        payRest = minute * 60;
        View handy_pay = View.inflate(this, R.layout.pay_conform_dialog, null);
        dialogUtils.showHanyPayDialog(handy_pay, "￥" + money);
        finger();
    }


    public void showDate(PriceLevelBean priceLevelBean, PrivateEduBean rentTimeBean) {
        final Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.FragmentExtra.BEAN, rentTimeBean);
        bundle.putSerializable(Constants.FragmentExtra.PRICELEVELBEAN, priceLevelBean);
        publicTitle.nextPosition();
        BottomBuyDialog dialog = new BottomBuyDialog(this);
        dialog.setCanceledOnTouchOutside(false);


        dialog.setOnCancelButtonClickListener(new BottomBuyDialog.OnCancelButtonClickListener() {
            @Override
            public void onClick(Dialog dialog) {
                publicTitle.lastPosition();
                dialog.dismiss();
            }
        });
        dialog.setOnPositiveButtonClickListener(new BottomBuyDialog.OnPositiveButtonClickListener() {
            @Override
            public void onClick(Dialog dialog) {
                publicTitle.nextPosition();
                showFragment(BuyPrivateEduFragment.class, bundle);
                dialog.dismiss();
            }
        });
        dialog.show();

    }


    public void showPosition() {
        publicTitle.lastPosition();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_privateeducation;
    }


    @Override
    public void dialogCancel() {

    }

    @Override
    public void onVenuePay() {

    }
}
