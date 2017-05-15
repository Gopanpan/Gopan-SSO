package bing.Pan.sso.manage.controller;

import bing.Pan.sso.common.enums.ResponseCode;
import bing.Pan.sso.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/5/13 10:49
 * @desc :
 */
public class BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 页面传参校验
     * @param result
     * @throws ServiceException
     */
    protected void checkValid(BindingResult result) throws ServiceException {

        if(result.hasErrors()) {
            StringBuilder sBuilder = new StringBuilder();
            List<ObjectError> allErrors = result.getAllErrors();

            for (ObjectError error:allErrors) {
                sBuilder.append(String.format("%s%s",error.getDefaultMessage(),"<br/>"));
            }
            throw new ServiceException(ResponseCode.CLIENT_PARAM_ERR,sBuilder.toString());
        }
    }
}
