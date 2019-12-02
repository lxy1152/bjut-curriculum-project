package com.finalwork.android.e_commerce.controller;

import com.alibaba.fastjson.JSON;
import com.finalwork.android.e_commerce.model.entity.User;

import java.util.Map;

public class ResultController {
    public static String DEFAULT = "nothing";
    private static ResultController resultController = new ResultController();
    private String result = DEFAULT;

    public static ResultController getInstance() {
        return resultController;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String res) {
        result = res;
    }

    public void setUser(String json) {
        System.out.println(json);
        Map map = JSON.parseObject(json);
        Map userMap = (Map) map.get("user");
        User.getInstance().setUserPhotoLocation(userMap.get("userPhotoLocation").toString());
        User.getInstance().setLoginName(userMap.get("loginName").toString());
        User.getInstance().setUserName(userMap.get("userName").toString());
        User.getInstance().setUserPhone(userMap.get("userPhone").toString());
        User.getInstance().setUserEmail(userMap.get("userEmail").toString());

//        String score = (String) userMap.get("userTotalScore");
//        long totalScore = Long.parseLong(score.substring(0, score.length() - 1));
//        User.getInstance().setUserTotalScore(totalScore);
        User.getInstance().setUserTotalScore((long) 100);
    }
}
