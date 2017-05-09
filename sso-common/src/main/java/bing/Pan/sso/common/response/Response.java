package bing.Pan.sso.common.response;

import bing.Pan.sso.common.enums.ResponseCode;

import java.io.Serializable;

/**
 * @crea :Created by intelliJ IDEA 16.1.3
 * @auth :bing.Pan 15923508369@163.com
 * @date :2017/1/17 18:58
 * @desc :系统接口返回通用类
 */
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 355207958304927788L;

    // 状态码
    private String code;
    // 消息
    private String message;
    // 数据
    private T result;

    private Long timestamp = System.currentTimeMillis();

    public Response() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getDescription();
    }

    public Response(ResponseCode response) {
        this.code = response.getCode();
        this.message = response.getDescription();
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(T result) {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getDescription();
        this.result = result;
    }



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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
