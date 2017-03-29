package bing.Pan.sso.manage.test;


import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/3/29 9:08
 * @desc :
 */
public class AxisTest {

    public static void main(String[] agrs) throws AxisFault {
        axis2CleintTest();
    }

    private static void axis2CleintTest() {
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
