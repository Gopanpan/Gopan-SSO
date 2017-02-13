package bing.Pan.sso.common.exception;

import bing.Pan.sso.common.enums.ResponseCode;

import java.io.Serializable;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/2/6 20:46
 * @desc :
 */
public class ServiceException extends Exception implements Serializable {

    private static final long serialVersionUID = -1695036681341844113L;

    private String errorCode;

    private String errorMsg;

    public ServiceException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public ServiceException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ServiceException(ResponseCode responseCode) {

        super(responseCode.getDescription());
        this.errorCode = responseCode.getCode();
        this.errorMsg = responseCode.getDescription();
    }

    public ServiceException(ServiceException e) {
        super(e);
        this.errorCode = e.getErrorCode();
        this.errorMsg = e.getMessage();
    }

    public ServiceException(Exception e) {
        super(e);
        this.errorCode = ResponseCode.SERVE_UNKNOWN_ERROR.getCode();
        this.errorMsg = ResponseCode.SERVE_UNKNOWN_ERROR.getDescription();
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
