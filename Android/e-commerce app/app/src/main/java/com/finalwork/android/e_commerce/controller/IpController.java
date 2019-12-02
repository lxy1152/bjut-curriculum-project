package com.finalwork.android.e_commerce.controller;

public class IpController {
    private final String ip = "http://154.8.216.102/";
    private final String where = "webmall/";
    private final String detailLogin = "user/user_login";
    private final String detailRegister = "user/user_regist";
    private final String detailProduct = "product/product_clips";
    private final String detailCart = "trolley/trolley_purchase";

    private String getAddress() {
        return ip + where;
    }

    public String getProfileAddress() {
        return getAddress() + detailLogin;
    }

    public String getRegisterAddress() {
        return getAddress() + detailRegister;
    }

    public String getProductAddress() {
        return getAddress() + detailProduct;
    }

    public String getCartAddress() {
        return getAddress() + detailCart;
    }
}
