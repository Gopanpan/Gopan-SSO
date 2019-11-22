package com.horse.sso.common.response;



import com.horse.sso.common.enums.ResponseCode;

import java.io.Serializable;

/**
 * @crea :Created by intelliJ IDEA 16.1.3
 * @auth :Gopan 15923508369@163.com
 * @date :2017/1/17 18:58
 * @desc :系统接口返回通用类
 */
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 355207958304927788L;


    private int    code;            // 执行结果状态码
    private String message;         // 执行结果消息摘要
    private String detailMessage;   // 执行结果详细信息
    private T data;                 // 数据

    private Long timestamp = System.currentTimeMillis();

    public Response() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getDescription();
        this.detailMessage = ResponseCode.SUCCESS.getDetailMessage();
    }

    public Response(ResponseCode response) {
        this.code = response.getCode();
        this.message = response.getDescription();
        this.detailMessage = response.getDetailMessage();
    }


    public Response(ResponseCode response, String detailMessage) {
        this.code = response.getCode();
        this.message = response.getDescription();
        this.detailMessage = detailMessage;
    }


    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(int code, String message, String detailMessage) {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
    }



    public Response(T data) {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getDescription();
        this.detailMessage = ResponseCode.SUCCESS.getDetailMessage();
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", detailMessage='" + detailMessage + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
