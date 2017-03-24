package bing.Pan.sso.common.exception;

import bing.Pan.sso.common.enums.ResponseCode;
import bing.Pan.sso.common.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/2/6 20:39
 * @desc :
 */

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);



    /**
     * 400 - Bad Request  参数解析失败
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,HttpServletRequest request) {
        writeLog(ex, request);
        return new Response<>(ResponseCode.CLIENT_PARAM_ERR);
    }

    /**
     * 405 - Method Not Allowed  不支持当前请求方法
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex,HttpServletRequest request) {
        writeLog(ex, request);
        return new Response<>(ResponseCode.CLIENT_NO_SUPPORT_METHOD);
    }

    /**
     * 415 - Unsupported Media Type 不支持当前媒体类型
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Object handleHttpMediaTypeNotSupportedException(Exception ex,HttpServletRequest request) {
        writeLog(ex, request);
        return new Response<>(ResponseCode.CLIENT_NO_SUPPORT_TYPE);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Object handleMissingServletRequestParameterException(MissingServletRequestParameterException ex,
                                                                HttpServletRequest request) {
        writeLog(ex, request);
        return new Response<>(ResponseCode.CLIENT_PARAM_ERR);
    }

    @ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
    public Object handleRequestParameterException(UnsatisfiedServletRequestParameterException exception) {
        Response<Object> cp = new Response<>();
        cp.setCode("50501");
        cp.setMessage("参数错误!" + exception.getMessage());
        return cp;
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        writeLog(ex, request);
        return new Response<>(ResponseCode.SERVE_UNKNOWN_ERROR);
    }

    @ExceptionHandler(ServiceException.class)
    public Object serviceExceptionHandle(ServiceException ex, HttpServletRequest request,
                                         HttpServletResponse response) {

        Response<Object> cp = new Response<>();
        cp.setCode(ex.getErrorCode());
        cp.setMessage(ex.getMessage());
        writeLog(ex, request);
        return cp;
    }

    private void writeLog(Exception ex, HttpServletRequest request) {
        String url = MessageFormat.format("Exception :{0}?{1}", request.getRequestURL(), request.getQueryString());
        log.error(url, ex);
    }
}
