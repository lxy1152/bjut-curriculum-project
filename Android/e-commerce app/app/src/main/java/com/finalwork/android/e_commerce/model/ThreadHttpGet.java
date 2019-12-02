package com.finalwork.android.e_commerce.model;


import com.finalwork.android.e_commerce.controller.ResultController;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

public class ThreadHttpGet extends Thread {
    //    private Controller controller = Controller.getInstance();
    private String url;
    private ArrayList<NameValuePair> pairs;
    private String result = null;
    private String json = null;
    private boolean exit = false;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public ThreadHttpGet(String url, ArrayList<NameValuePair> pairs) {
        this.url = url;
        this.pairs = pairs;
    }

    public ThreadHttpGet(String url, String json) {
        this.url = url;
        this.json = json;
    }

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

    public String getJSON() {
        return json;
    }

    public void setJSON(String json) {
        this.json = json;
    }

    @Override
    public void run() {
        super.run();
        HttpRequestToSever httpRequestToSever = new HttpRequestToSever();
        System.out.println("sendPairs!!!!");
        ResultController.getInstance().setResult(httpRequestToSever.Get(getUrl(), getPairs()));
    }
}
