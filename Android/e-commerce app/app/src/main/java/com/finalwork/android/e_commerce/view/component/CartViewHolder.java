package com.finalwork.android.e_commerce.view.component;

import android.widget.ImageView;
import android.widget.TextView;

public class CartViewHolder {
    private ImageView img;
    private TextView name;
    private TextView price;
    private TextView des;

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }

    public TextView getDes() {
        return des;
    }

    public void setDes(TextView des) {
        this.des = des;
    }
}
