package com.finalwork.android.e_commerce.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class LocalUserManager {
    private static LocalUserManager instance;

    public static LocalUserManager getInstance() {
        if (instance == null) {
            instance = new LocalUserManager();
        }
        return instance;
    }

    public void saveUserInfo(Context context, String username, String password, String type) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("USER_NAME", username);
        editor.putString("PASSWORD", password);
        editor.putString("TYPE", type);
        editor.apply();
    }

    public LocalUserInfo getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        LocalUserInfo userInfo = new LocalUserInfo();
        userInfo.setUserName(sp.getString("USER_NAME", ""));
        userInfo.setPassword(sp.getString("PASSWORD", ""));
        userInfo.setType(sp.getString("TYPE",""));
        return userInfo;
    }

    /**
     * userInfo中是否有数据
     */
    public boolean hasUserInfo(Context context) {
        LocalUserInfo userInfo = getUserInfo(context);
        if (userInfo != null) {
            if ((!TextUtils.isEmpty(userInfo.getUserName())) && (!TextUtils.isEmpty(userInfo.getPassword()))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
