package Gopan.sso;

import com.horse.sso.common.utils.AESEncryptUtils;

/**
 * @crea : Created by intelliJ IDEA 17.2
 * @auth : Gopan
 * @mail : 15923508369@163.com
 * @date : 01 29 2018 13:01
 * @desc :
 */
public class ASETest {

    public static void main(String[] args) throws Exception {
        for (int x = 0; x < 10; x ++){
            String s = AESEncryptUtils.aesEncrypt("11111111");
            System.out.println(s);

            String s1 = AESEncryptUtils.aesDecrypt(s);
            System.out.println(s1);

        }


    }
}
