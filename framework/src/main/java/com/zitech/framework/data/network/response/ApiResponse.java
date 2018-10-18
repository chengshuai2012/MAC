package com.zitech.framework.data.network.response;


/**
 * @author makk
 * @version V1.0
 * @Project: PersonalAccount
 * @Package
 * @Description: 响应基类
 * @date 2016/5/17 9:47
 */
public class ApiResponse<T> {




    /**
     * code : 0
     * msg : ok
     */
    T data;
    private String code;
    private String message;
    private String secondMessage;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public String getSecondMessage() {
        return secondMessage;
    }

    public void setSecondMessage(String secondMessage) {
        this.secondMessage = secondMessage;
    }
}
