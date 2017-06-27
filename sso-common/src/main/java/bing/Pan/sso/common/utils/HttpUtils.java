package bing.Pan.sso.common.utils;


import com.google.common.collect.Maps;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/6/26 17:42
 * @desc :
 */
public class HttpUtils {

    private static Logger logger =  LoggerFactory.getLogger(HttpUtils.class);

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;

    static {
        connMgr = new PoolingHttpClientConnectionManager();
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);

        requestConfig = configBuilder.build();
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     * @param url
     * @return
     */
    public static String doGet(String url) throws IOException {
        return doGet(url, new HashMap<>());
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     * @param url
     * @param params
     * @return
     */
    private static String doGet(String url, Map<String, Object> params) throws IOException {

        try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
            String buildUrl = buildUrl(url, params);
            logger.info("请求地址为：{}",buildUrl);
            HttpGet httpGet = new HttpGet(buildUrl);
            httpGet.setConfig(requestConfig);
            String response = httpclient.execute(httpGet, responseHandler());
            logger.info("响应结果为：{}",response);
            httpGet.abort();
            return response;
        }

    }

    private static ResponseHandler<String> responseHandler() {
        return response -> {
            int code = response.getStatusLine().getStatusCode();
            if(code >= HttpStatus.SC_OK && code < HttpStatus.SC_MULTIPLE_CHOICES){
                HttpEntity entity = response.getEntity();
                return entity !=null? EntityUtils.toString(entity, Consts.UTF_8):null;
            }else
                throw new ClientProtocolException("Unexpected response status: " + code);
        };
    }

    private static String buildUrl(String url, Map<String, Object> params) {
        if(StringUtils.isEmpty(url)) return null;
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if(!StringUtils.isEmpty(params) && params.size()>= 1){
            params.entrySet().forEach(entry ->
                urlBuilder.append(entry.getKey())
                          .append("=")
                          .append(entry.getValue())
                          .append("&"));

        }
        urlBuilder.setLength(urlBuilder.length() -1);
        return urlBuilder.toString();
    }




    /**
     * 发送 POST 请求（HTTP），不带输入数据
     * @param url
     * @return
     */
    public static String doPost(String url) throws IOException {
        return doPost(url, new HashMap<>());
    }

    /**
     * 发送 POST 请求（HTTP），K-V形式
     * @param url API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPost(String url, Map<String, String> params) throws IOException {

        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            return doMapPost(httpClient,url,params);
        }
    }


    private static HttpEntity buildFormParams(Map<String, String> params) {
        List<NameValuePair> formParams = new ArrayList<>();
        if (params != null) {
            params.entrySet().forEach(entry ->
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue())));
        }
        return new UrlEncodedFormEntity(formParams, Consts.UTF_8);
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     * @param url
     * @param json json对象
     * @return
     */
    public static String doPost(String url, Object json) throws IOException {

        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            return doJsonPost(httpClient,url,json);
        }



    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     * @param url API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPostSSL(String url, Map<String, String> params) throws IOException {

        try(CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build()) {
            return doMapPost(httpClient,url,params);
        }
    }



    /**
     * 发送 SSL POST 请求（HTTPS），JSON形式
     * @param url API接口URL
     * @param json JSON对象
     * @return
     */
    public static String doPostSSL(String url, Object json) throws IOException {

        try(CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build()){
          return doJsonPost(httpClient,url,json);
        }



    }

    private static String doJsonPost(CloseableHttpClient httpClient, String url, Object json) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        logger.info("请求地址为：{}",url);
        httpPost.setConfig(requestConfig);
        StringEntity stringEntity = new StringEntity(json.toString(), Consts.UTF_8);// 解决中文乱码问题
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        String response = httpClient.execute(httpPost, responseHandler());
        logger.info("响应结果为：{}",response);
        return response;
    }


    private static String doMapPost(CloseableHttpClient httpClient, String url, Map<String, String> params) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        logger.info("请求地址为：{}",url);
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(buildFormParams(params));
        String response = httpClient.execute(httpPost, responseHandler());
        logger.info("响应结果为：{}",response);
        httpPost.abort();
        return response;
    }

    /**
     * 创建SSL安全连接
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }


    public static void main(String[] args){
        try {
            Map<String,String> map = Maps.newHashMap();
            map.put("loginName","bing.Pan");
            map.put("password","25d55ad283aa400af464c76d713c07ad");
            String s = HttpUtils.doPost("https://127.0.0.1:4444/loginSysUser",map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}