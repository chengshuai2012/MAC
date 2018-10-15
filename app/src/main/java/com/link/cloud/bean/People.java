package com.link.cloud.bean;

import io.realm.RealmObject;

/**
 * Created by 49488 on 2018/10/15.
 */

public class People extends RealmObject{
    String uid;

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    String feature;
}
