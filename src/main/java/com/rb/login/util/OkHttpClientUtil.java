package com.rb.login.util;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpClientUtil {
    public OkHttpClient client = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).build();

    /**
     * post请求xml入参
     * @param url
     * @param data
     * @return
     * @throws IOException
     */
    public  String doPostXml(String url,String data) throws IOException {
        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, data);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    /**
     * post请求json入参
     * @param url
     * @return
     * @throws IOException
     */
    public  String doPostJson(String url,String data)throws IOException{
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,data);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * get请求
     * @param url
     * @return
     */
    public String httpGet(String url) {
        Response response = null;
        String result="";
        Request request = new Request.Builder().url(url).get().build();
        try {
            response = client.newCall(request).execute();
            result=response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
