package com.link.cloud.utils;
import android.content.Context;
import com.link.cloud.R;
import com.link.cloud.veune.MdUsbService;
import com.link.cloud.veune.ModelImgMng;
import com.orhanobut.logger.Logger;

/**
 * Created by 49488 on 2018/10/15.
 */

public class Venueutils {
    public MdUsbService.MyBinder mdDeviceBinder;
    private byte[] img;
    Context context;
    VenueCallBack callBack;
    private boolean bOpen = false;//设备是否打开
    private int[] pos = new int[1];
    private float[] score = new float[1];
    private boolean ret;
    private ModelImgMng modelImgMng = new ModelImgMng();
    private int[] tipTimes = {0, 0};//后两次次建模时用了不同手指或提取特征识别时，最多重复提醒限制3次
    private int lastTouchState = 0;//记录上一次的触摸状态
    private int modOkProgress = 0;
    boolean isWorkFinsh = false;
    boolean isIdentyFinsh = false;
    int time = 0;
    private final static float IDENTIFY_SCORE_THRESHOLD = 0.63f;
    private final static float MODEL_SCORE_THRESHOLD = 0.4f;
    public interface VenueCallBack{
        void modelMsg(int state,String msg);
        void identifyMsg(String msg,String uid);
    };
    public  void initVenue(MdUsbService.MyBinder mdDeviceBinder, Context context, VenueCallBack callBack,  Boolean bOpen){
        this.bOpen=bOpen;
        this.context=context;
        this.mdDeviceBinder=mdDeviceBinder;
        this.callBack=callBack;
    }
    public int getState() {
        if (!bOpen) {
            modOkProgress = 0;
            modelImgMng.reset();
            bOpen = mdDeviceBinder.openDevice(0);//开启指定索引的设备
            if (bOpen) {
                Logger.e( "open device success");
            } else {
                Logger.e("open device failed,stop identifying and modeling.");

            }
        }
        int state = mdDeviceBinder.getDeviceTouchState(0);
        if (state != 3) {
            if (lastTouchState != 0) {
                mdDeviceBinder.setDeviceLed(0, MdUsbService.getFvColorRED(), true);
            }
            lastTouchState = 0;
        }
        if (state == 3) {
            //返回值state=3表检测到了双Touch触摸,返回1表示仅指腹触碰，返回2表示仅指尖触碰，返回0表示未检测到触碰
            if (lastTouchState == 3) {
                return 4;
            }
            lastTouchState = 3;
            mdDeviceBinder.setDeviceLed(0, MdUsbService.getFvColorGREEN(), false);
            img = mdDeviceBinder.tryGrabImg(0);
            if (img == null) {
                Logger.e("get img failed,please try again");
            }
        }
        return state;
    }

    public void workModel() {
        float[] quaScore = {0f, 0f, 0f, 0f};
        int quaRtn = MdUsbService.qualityImgEx(img, quaScore);
        String oneResult = ("quality return=" + quaRtn) + ",result=" + quaScore[0] + ",score=" + quaScore[1] + ",fLeakRatio=" + quaScore[2] + ",fPress=" + quaScore[3];
        int quality = (int) quaScore[0];
        if (quality != 0) {
            callBack.modelMsg(0,context.getString(R.string.move_finger));
        }
        byte[] feature = MdUsbService.extractImgModel(img, null, null);
        if (feature == null) {
            callBack.modelMsg(0,context.getString(R.string.move_finger));
        } else {
            modOkProgress++;
            if (modOkProgress == 1) {//first model
                tipTimes[0] = 0;
                tipTimes[1] = 0;
                modelImgMng.setImg1(img);
                modelImgMng.setFeature1(feature);
                callBack.modelMsg(1,context.getString(R.string.again_finger));
            } else if (modOkProgress == 2) {//second model
                ret = MdUsbService.fvSearchFeature(modelImgMng.getFeature1(), 1, img, pos, score);
                if (ret && score[0] > MODEL_SCORE_THRESHOLD) {
                    feature = MdUsbService.extractImgModel(img, null, null);//无须传入第一张图片，第三次混合特征值时才同时传入3张图；
                    if (feature != null) {
                        tipTimes[0] = 0;
                        tipTimes[1] = 0;
                        modelImgMng.setImg2(img);
                        modelImgMng.setFeature2(feature);
                        callBack.modelMsg(1,context.getString(R.string.again_finger));
                    } else {//第二次建模从图片中取特征值无效
                        modOkProgress = 1;
                        if (++tipTimes[0] <= 3) {
                            callBack.modelMsg(2,context.getString(R.string.same_finger));

                        } else {//连续超过3次放了不同手指则忽略此次建模重来
                            modOkProgress = 0;
                            modelImgMng.reset();
                            callBack.modelMsg(2,context.getString(R.string.same_finger));
                        }
                    }
                } else {
                    modOkProgress = 1;
                    if (++tipTimes[0] <= 3) {
                        callBack.modelMsg(2,context.getString(R.string.same_finger));
                    } else {//连续超过3次放了不同手指则忽略此次建模重来
                        modOkProgress = 0;
                        modelImgMng.reset();
                        callBack.modelMsg(2,context.getString(R.string.same_finger));
                    }
                }
            } else if (modOkProgress == 3) {//third model
                ret = MdUsbService.fvSearchFeature(modelImgMng.getFeature2(), 1, img, pos, score);
                if (ret && score[0] > MODEL_SCORE_THRESHOLD) {
                    feature = MdUsbService.extractImgModel(modelImgMng.getImg1(), modelImgMng.getImg2(), img);
                    if (feature != null) {//成功生成一个3次建模并融合的融合特征数组
                        tipTimes[0] = 0;
                        tipTimes[1] = 0;
                        modelImgMng.setImg3(img);
                        modelImgMng.setFeature3(feature);
                        modelImgMng.reset();
                        mdDeviceBinder.closeDevice(0);
                        callBack.modelMsg(3,context.getString(R.string.same_finger));
                        bOpen = false;
                    } else {//第三次建模从图片中取特征值无效
                        modOkProgress = 2;
                        if (++tipTimes[1] <= 3) {
                            callBack.modelMsg(2,context.getString(R.string.same_finger));
                        }
                    }
                } else {
                    modOkProgress = 2;
                    if (++tipTimes[1] <= 3) {
                        callBack.modelMsg(2,context.getString(R.string.same_finger));
                    } else {//连续超过3次放了不同手指则忽略此次建模重来
                        modOkProgress = 0;
                        modelImgMng.reset();
                        callBack.modelMsg(2,context.getString(R.string.same_finger));
                    }
                }
            } else {
                modOkProgress = 0;
                modelImgMng.reset();
            }
        }

    }
}
