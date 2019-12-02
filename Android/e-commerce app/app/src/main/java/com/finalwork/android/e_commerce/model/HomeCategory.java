package com.finalwork.android.e_commerce.model;

import java.util.ArrayList;

public class HomeCategory {
    private String title;
    private String firstLine, slf, sls, slt;
    private String tvf, tvs, tvt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getSlf() {
        return slf;
    }

    public void setSlf(String slf) {
        this.slf = slf;
    }

    public String getSls() {
        return sls;
    }

    public void setSls(String sls) {
        this.sls = sls;
    }

    public String getSlt() {
        return slt;
    }

    public void setSlt(String slt) {
        this.slt = slt;
    }

    public String getTvf() {
        return tvf;
    }

    public void setTvf(String tvf) {
        this.tvf = tvf;
    }

    public String getTvs() {
        return tvs;
    }

    public void setTvs(String tvs) {
        this.tvs = tvs;
    }

    public String getTvt() {
        return tvt;
    }

    public void setTvt(String tvt) {
        this.tvt = tvt;
    }

    public boolean set(ArrayList<String> des) {
        if(des.size() != 8){
            return false;
        }else{
            this.setTitle(des.get(0));
            this.setFirstLine(des.get(1));
            this.setSlf(des.get(2));
            this.setSls(des.get(3));
            this.setSlt(des.get(4));
            this.setTvf(des.get(5));
            this.setTvs(des.get(6));
            this.setTvt(des.get(7));
            return true;
        }
    }
}
