package com.zitech.framework.data.network.response;


/**
 * @author makk
 * @version V1.0
 * @Project: PersonalAccount
 * @Package com.zitech.personalaccount.data.network.response
 * @Description: 响应基类
 * @date 2016/5/17 9:47
 */
public class ApiResponse<T> {
    T data;

    /**
     * code : 0
     * msg : ok
     */
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
