package com.finalwork.android.e_commerce.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

public class HttpRequestToSever {
    private String url = null;
    private String toPost = null;
    private ArrayList<NameValuePair> pairs;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<NameValuePair> getPairs() {
        return pairs;
    }

    public void setPairs(ArrayList<NameValuePair> pairs) {
        this.pairs = pairs;
    }

    public String getToPost() {
        return toPost;
    }

    public void setToPost(String toPost) {
        this.toPost = toPost;
    }

    public String Get(String url, ArrayList<NameValuePair> pairs) {
        String res = "";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        try {
            HttpEntity request = new UrlEncodedFormEntity(pairs, "utf-8");
            httpPost.setEntity(request);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
//            if (response.getStatusLine().getStatusCode() == 200) {
            res = EntityUtils.toString(entity, "utf-8");
            Log.w("requestServer:", res);
//            } else if (response.getStatusLine().getStatusCode() == 500) {
//                System.out.println("error!!!!");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

//    public String Get(String url, String json) {
//        String res = "";
//        HttpClient httpClient = new DefaultHttpClient();
//        try {
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.addHeader("content-type", "application/json;charset=utf-8");
//
//            HttpEntity request = new StringEntity(json, "utf-8");
//            httpPost.setEntity(request);
//            HttpResponse response = httpClient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            if (response.getStatusLine().getStatusCode() == 200) {
//                res = EntityUtils.toString(entity, "utf-8");
//                Log.w("requestServer:", res);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return res;
//    }
}
