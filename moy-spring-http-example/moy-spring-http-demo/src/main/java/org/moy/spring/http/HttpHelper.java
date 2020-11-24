package org.moy.spring.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class HttpHelper {

    private static final CloseableHttpClient closeableHttpClient;
    private static final int DEFAULT_TIMEOUT = 3000;
    private static final String DEFAULT_CHARSET = "UTF-8";

    static {
        RequestConfig requestConfig = getRequestConfig(DEFAULT_TIMEOUT);
        closeableHttpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig).build();
    }

    private static RequestConfig getRequestConfig(int timeout) {
        return RequestConfig.custom().
                setConnectionRequestTimeout(timeout).
                setConnectTimeout(timeout).
                setSocketTimeout(timeout).
                build();
    }

    public static String get(String url) throws IOException {
        return get(url, null);
    }

    public static String getDownload(String url, String filepath) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        HttpEntity entity = closeableHttpClient.execute(httpGet).getEntity();
        InputStream is = entity.getContent();
        File file = new File(filepath);
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        IOUtils.copy(is, fileOutputStream);
        return EntityUtils.toString(entity);
    }


    public static String get(String url, Map<String, String> headMap) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        setHttpHeader(headMap, httpGet);
        HttpEntity entity = closeableHttpClient.execute(httpGet).getEntity();
        return EntityUtils.toString(entity);
    }


    public static String post(String url) throws IOException {
        return post(url, null, null);
    }


    public static String postUpload(String url, String name, InputStream inputStream) throws IOException {
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.addBinaryBody(name, inputStream);
        HttpEntity httpFileEntity = entityBuilder.build();
        return post(url, null, httpFileEntity);
    }

    public static String postForm(String url, Map<String, String> formMap) throws IOException {
        Map<String, String> jsonHeadMap = new HashMap<>();
        jsonHeadMap.put(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");
        UrlEncodedFormEntity encodedFormEntity = null;
        if (null != formMap && !formMap.isEmpty()) {
            List<BasicNameValuePair> pairList = new ArrayList<>();
            Set<Map.Entry<String, String>> entrySet = formMap.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                pairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            encodedFormEntity = new UrlEncodedFormEntity(pairList, DEFAULT_CHARSET);
        }
        return post(url, jsonHeadMap, encodedFormEntity);
    }

    public static String postTextBody(String url, String requestTextBody) throws IOException {
        Map<String, String> textHeadMap = new HashMap<>();
        textHeadMap.put(HTTP.CONTENT_TYPE, "application/text");
        StringEntity stringEntity = new StringEntity(requestTextBody, DEFAULT_CHARSET);
        return post(url, textHeadMap, stringEntity);
    }

    public static String postJsonBody(String url, String requestJsonBody) throws IOException {
        Map<String, String> jsonHeadMap = new HashMap<>();
        jsonHeadMap.put(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
        StringEntity stringEntity = new StringEntity(requestJsonBody, DEFAULT_CHARSET);
        return post(url, jsonHeadMap, stringEntity);
    }

    public static String post(String url, Map<String, String> headMap, HttpEntity httpEntity) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        setHttpHeader(headMap, httpPost);
        setHttpBody(httpEntity, httpPost);
        HttpEntity entity = closeableHttpClient.execute(httpPost).getEntity();
        return EntityUtils.toString(entity);
    }

    private static void setHttpBody(HttpEntity httpEntity, HttpPost httpPost) {
        if (null != httpEntity) {
            httpPost.setEntity(httpEntity);
        }
    }

    private static void setHttpHeader(Map<String, String> headMap, HttpRequestBase httpRequestBase) {
        if (null != headMap && !headMap.isEmpty()) {
            Set<Map.Entry<String, String>> headEntrySet = headMap.entrySet();
            for (Map.Entry<String, String> eachHead : headEntrySet) {
                httpRequestBase.setHeader(eachHead.getKey(), eachHead.getValue());
            }
        }
    }
}
