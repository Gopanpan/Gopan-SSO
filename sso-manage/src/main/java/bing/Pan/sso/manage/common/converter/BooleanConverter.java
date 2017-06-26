package bing.Pan.sso.manage.common.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/6/26 14:47
 * @desc :
 */

@Component
public class BooleanConverter implements Converter<String,Boolean> {
    @Override
    public Boolean convert(String source) {

        if(StringUtils.isEmpty(source))  return null;
        if(Boolean.getBoolean(source))   return true;
        else return false;
    }
}
