package com.horse.sso.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : Gopan
 * @mail : 15923508369@163.com
 * @date : 2017/4/5 13:39
 * @desc :
 */
public class Base64Utils {
	
	private static Logger log = LoggerFactory.getLogger(Base64Utils.class);

	public Base64Utils() {}
	
	/**
	 * base64加密字符串
	 * @param str
	 * @return
	 */
	public static String encodeBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
	}

    /**
     * base64加密 byte[]
     * @param content
     * @return
     */
    public static String encodeBase64(byte[] content) {
        String encodedStr = Base64.encodeBase64String(content);
        if(StringUtils.isEmpty(encodedStr)){
            encodedStr = encodedStr.replaceAll("\r|\n", "");
        }
        return encodedStr;
    }


	/**
	 * base64解密
	 * @param str
	 * @return
	 */
	public static String decodeBase64(String str) {
        byte[] b = null;
        String result = null;
        if (str != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(str);
                result = new String(b, "UTF-8");
                result = URLDecoder.decode(result, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
	}

}
