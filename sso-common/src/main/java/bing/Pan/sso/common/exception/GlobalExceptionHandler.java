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
import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;

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
        Response<Object> response = new Response<>(ResponseCode.CLIENT_PARAM_PARSE_ERROR);
        writeLog(ex, request,"", response);
        return response;
    }

    /**
     * 405 - Method Not Allowed  不支持当前请求方法
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex,HttpServletRequest request) {
        Response<Object> response = new Response<>(ResponseCode.CLIENT_NO_SUPPORT_METHOD);
        writeLog(ex, request,"", response);
        return response;
    }

    /**
     * 415 - Unsupported Media Type 不支持当前媒体类型
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Object handleHttpMediaTypeNotSupportedException(Exception ex,HttpServletRequest request) {
        Response<Object> response = new Response<>(ResponseCode.CLIENT_NO_SUPPORT_TYPE);
        writeLog(ex, request,"", response);
        return response;
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Object handleMissingServletRequestParameterException(MissingServletRequestParameterException ex,
                                                                HttpServletRequest request) {
        Response<Object> response = new Response<>(ResponseCode.CLIENT_PARAM_PARSE_ERROR);
        writeLog(ex, request,"", response);
        return response;
    }

    @ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
    public Object handleRequestParameterException(UnsatisfiedServletRequestParameterException ex,HttpServletRequest request) {

        Response<Object> response = new Response<>(ResponseCode.CLIENT_PARAM_ERROR);
        writeLog(ex, request,"", response);
        return response;
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception ex, HttpServletRequest request) {

        Response<Object> response = new Response<>(ResponseCode.SERVE_UNKNOWN_ERROR);
        writeLog(ex, request,"", response);
        return response;
    }

    @ExceptionHandler(ServiceException.class)
    public Object serviceExceptionHandle(ServiceException ex, HttpServletRequest request) {

        Response<Object> rs = new Response<>();
        rs.setCode(ex.getErrorCode());
        rs.setMessage(ex.getMessage());
        rs.setDetailMessage(ex.getDetailErrorMsg());
        writeLog(ex, request,ex.getDetailErrorMsg(),rs);
        return rs;
    }

    private void writeLog(Exception ex, HttpServletRequest request, String detailMessage, Response<Object> rs) {

        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder sBuilder = new StringBuilder();
        Set<String> keySet = parameterMap.keySet();
        for (String key:keySet) {
            String[] strings = parameterMap.get(key);
            StringBuilder valueSBuilder = new StringBuilder();
            for (String single: strings) {
                valueSBuilder.append(single);
            }
            sBuilder.append(String.format("%s%s%s%s",key,"=",valueSBuilder.toString(), " "));
        }

        log.error(String.format("%s%s","请求地址：",MessageFormat.format("{0}", request.getRequestURL())));
        log.error(String.format("%s%s","请求参数：",sBuilder.toString()));
        log.error(String.format("%s%s","错误摘要：",detailMessage));
        log.error(String.format("%s%s","请求地址：",request.getRemoteAddr()));
        log.error(String.format("%s%s","请求方式：",request.getMethod()));
        log.error(String.format("%s%s","接口返回：",rs.toString()));
        log.error("堆栈信息：", ex);
    }
}
