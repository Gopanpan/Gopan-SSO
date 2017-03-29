package bing.Pan.sso.manage.test;

import java.io.*;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/3/29 10:50
 * @desc : 文件转字节数组，自己数组转文件测试
 */
public class ConversionEachOther {

    public  static void main(String[] args){

        // 文件转bytep[]
        byte[] buffer = null;
        try {
            File file = new File("D:/测试合同.pdf");
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(buffer);



        //byte[]转文件
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file =  new File("D:/测试合同_convert.pdf");
        try {
            file.createNewFile();
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }


    }
}
