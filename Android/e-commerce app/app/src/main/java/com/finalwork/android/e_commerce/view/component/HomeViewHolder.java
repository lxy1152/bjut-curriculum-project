package com.finalwork.android.e_commerce.view.component;

import android.widget.ImageView;
import android.widget.TextView;

public class HomeViewHolder {
    private ImageView firstLine;
    private ImageView slf, sls, slt;
    private TextView title;

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    private TextView slfTv, slsTv, sltTv;

    public ImageView getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(ImageView firstLine) {
        this.firstLine = firstLine;
    }

    public ImageView getSlf() {
        return slf;
    }

    public void setSlf(ImageView slf) {
        this.slf = slf;
    }

    public ImageView getSls() {
        return sls;
    }

    public void setSls(ImageView sls) {
        this.sls = sls;
    }

    public ImageView getSlt() {
        return slt;
    }

    public void setSlt(ImageView slt) {
        this.slt = slt;
    }

    public TextView getSlfTv() {
        return slfTv;
    }

    public void setSlfTv(TextView slfTv) {
        this.slfTv = slfTv;
    }

    public TextView getSlsTv() {
        return slsTv;
    }

    public void setSlsTv(TextView slsTv) {
        this.slsTv = slsTv;
    }

    public TextView getSltTv() {
        return sltTv;
    }

    public void setSltTv(TextView sltTv) {
        this.sltTv = sltTv;
    }


}
