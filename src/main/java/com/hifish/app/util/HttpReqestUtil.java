/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: HTTP请求工具类
 * @author: yinhong
 * @date: 2016年11月29日 下午3:14:59
 * @version: V1.0
 */
package com.hifish.app.util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;


/**
 * @Description: HTTP请求工具类
 */
public class HttpReqestUtil {

    public static final String HTTP_REQUESTMETHOD_GET = "GET";

    public static final String HTTP_REQUESTMETHOD_POST = "POST";

    /**
     * @Description: 根据参数构建请求URL
     * @return: String
     */
    public static String buildURL(String reqUrl, Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        Set<String> set = params.keySet();
        for (String key : set) {
            query.append(String.format("%s=%s&", key, params.get(key)));
        }
        return reqUrl + "?" + query.toString();
    }

    /**
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     * @Description: Get请求共用方法
     * @return: String
     */
    public static String sendGet(String reqUrl, Map<String, String> params)
            throws URISyntaxException, ClientProtocolException, IOException {

        String reponseResult = null;
        InputStream inputStream = null;

        CloseableHttpClient client = HttpClients.createDefault();
        String url = buildURL(reqUrl, params);

        HttpGet request = new HttpGet();
        request.setHeader("Accept-Encoding", "gzip");

        try {
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            inputStream = response.getEntity().getContent();
            reponseResult = getStrFromGzipHttp(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (request != null) {
                request.releaseConnection();
            }
            if (client != null) {
                client.close();
            }
        }

        return reponseResult;
    }

    /**
     * @throws IOException
     * @Description: Post请求共用方法
     * @return: String
     */
    @SuppressWarnings("deprecation")
    public static String sendPostByMap(String reqUrl, Map<String, String> params) throws IOException {

        String responseResult = null;
        InputStream inputStream = null;

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(reqUrl);
        request.setHeader("Accept-Encoding", "gzip");

        Set<String> set = params.keySet();
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String key : set) {
            list.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
            if (list.size() > 0) {
                request.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
            }
            HttpResponse response = client.execute(request);
            inputStream = response.getEntity().getContent();
            responseResult = getStrFromGzipHttp(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (request != null) {
                request.releaseConnection();
            }
            if (client != null) {
                client.close();
            }
        }

        return responseResult;
    }

    /**
     * @throws IOException
     * @Description: Post公共请求方法
     * @return: String
     */
    @SuppressWarnings("deprecation")
    public static String sendPostByString(String requestUrl, String params) throws IOException {

        String reponseResult = null;

        // params参数为"key1=value1&key2=value2"的字符串
        StringEntity se = new StringEntity(params, HTTP.UTF_8);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(requestUrl);
        request.setEntity(se);

        try {
            HttpResponse httpResponse = client.execute(request);
            reponseResult = EntityUtils.toString(httpResponse.getEntity());
        } finally {
            if (request != null) {
                request.releaseConnection();
            }
            if (client != null) {
                client.close();
            }
        }

        return reponseResult;
    }

    /**
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @Description: https请求公用方法
     * @return: JSONObject
     */
    @SuppressWarnings("deprecation")
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr)
            throws IOException, NoSuchAlgorithmException, NoSuchProviderException,
            KeyManagementException {

        JSONObject jsonObject = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        InputStream inputStream = null;
        HttpsURLConnection conn = null;

        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new HttpsX509TrustManager()};

            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes(HTTP.UTF_8));
                outputStream.close();
            }
            // 从输入流读取返回内容
            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, HTTP.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            jsonObject = new JSONObject(buffer.toString());
        } finally {
            // 释放资源
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return jsonObject;
    }

    /**
     * @throws IOException
     * @Description: 通过Http请求返回输入流数据，解析具体内容
     * @return: String
     */
    @SuppressWarnings("deprecation")
    private static String getStrFromGzipHttp(InputStream is) throws IOException {

        String resultStr = null;
        BufferedInputStream bis = null;
        InputStreamReader reader = null;

        try {
            bis = new BufferedInputStream(is);
            bis.mark(2);
            // 取前两个字节
            byte[] header = new byte[2];
            int result = bis.read(header);
            // reset 输入流到开始位置
            bis.reset();
            // 判断是否是 GZIP 格式
            int headerData = getShort(header);
            // Gzip 流 的前两个字节是 0x1f8b
            if (result != -1 && headerData == 0x1f8b) {
                is = new GZIPInputStream(bis);
            } else {
                is = bis;
            }
            reader = new InputStreamReader(is, HTTP.UTF_8);
            char[] data = new char[100];
            int readSize;
            StringBuffer sb = new StringBuffer();
            while ((readSize = reader.read(data)) > 0) {
                sb.append(data, 0, readSize);
            }
            resultStr = sb.toString();
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (reader != null) {
                reader.close();
            }
        }

        return resultStr;
    }

    private static int getShort(byte[] data) {
        return (data[0] << 8) | data[1] & 0xFF;
    }

}
