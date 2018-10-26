package com.link.cloud;


import com.zitech.framework.SP;

/**
 * Created by ymh on 2016/7/1 0001.
 */
public class User {

    private static final String TOKEN = "token";
    private static final String POSITION = "Position";

    private SP sp;

    private String token;
    private int position;


    public static User get() {
        return MacApplication.getInstance().getUser();
    }

    public User() {
        super();
        sp = new SP("USER_DATA");
        token = sp.getString(TOKEN, "");
        position = sp.getInt(POSITION, -1);


    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        sp.putInt(POSITION, position);
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
