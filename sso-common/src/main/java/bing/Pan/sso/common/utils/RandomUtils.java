package bing.Pan.sso.common.utils;

import java.util.Random;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/7/25 14:46
 * @desc :
 */
public class RandomUtils {

    /**
     * 生成介于两者之间的随机数
     * @param max       最大值
     * @param min       最小值
     * @return
     */
    public static int getBetweenRandom(int max,int min){
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }

}
