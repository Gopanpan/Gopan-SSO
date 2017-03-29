package bing.Pan.sso.manage.test;


import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import java.io.*;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/3/29 9:08
 * @desc :
 */
public class AxisTest {

    public static void main(String[] agrs) throws AxisFault {
        testOne();
        testTwo();
    }

    private static void testTwo() throws AxisFault {


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

    private static void testOne() {
        try{
            RPCServiceClient client = new RPCServiceClient();
            Options options = client.getOptions();
            String url = "http://124.133.255.187:8090/Seal/services/AssistSealService?wsdl";
            EndpointReference end = new EndpointReference(url);
            options.setTo(end);

            Object[] obj = new Object[]{"test_001"};
            Class<?>[] classes = new Class[] { String.class };

            QName qname = new QName("http://impl.ws.service.front.seal.cfca", "getSealInfo");
            String result = (String) client.invokeBlocking(qname, obj,classes)[0];
            System.out.println(result);
        }catch(AxisFault e){
            e.printStackTrace();
        }

    }
}
