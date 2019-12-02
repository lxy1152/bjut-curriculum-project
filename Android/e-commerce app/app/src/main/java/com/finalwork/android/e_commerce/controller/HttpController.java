package com.finalwork.android.e_commerce.controller;

import com.finalwork.android.e_commerce.model.ThreadHttpGet;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpController {
    private static ExecutorService pool = Executors.newCachedThreadPool();
    private static HttpController httpController = new HttpController();

    public static HttpController getInstance() {
        return httpController;
    }

    public void requestThread(String url, ArrayList<NameValuePair> pairs){
        pool.execute(new ThreadHttpGet(url, pairs));
    }

    public void requestThread(String url, String json){
        pool.execute(new ThreadHttpGet(url, json));
    }
}
