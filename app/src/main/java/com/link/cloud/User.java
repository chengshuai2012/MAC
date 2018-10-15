package com.link.cloud;


import com.zitech.framework.SP;

/**
 * Created by ymh on 2016/7/1 0001.
 */
public class User {

    private static final String TOKEN = "token";

    private SP sp;

    private String token;

    public static User get() {
        return MacApplication.getInstance().getUser();
    }

    public User() {
        super();
        sp = new SP("USER_DATA");
        token = sp.getString(TOKEN, "");


    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        sp.putString(TOKEN, token);
    }


    public void clear() {

        sp.remove(TOKEN);
        token = "";



    }
}
