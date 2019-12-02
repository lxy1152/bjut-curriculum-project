package com.finalwork.android.e_commerce.controller;

import android.content.Context;

import com.finalwork.android.e_commerce.model.HomeCategory;
import com.finalwork.android.e_commerce.model.HomeCategoryList;
import com.finalwork.android.e_commerce.model.LocalUserInfo;
import com.finalwork.android.e_commerce.model.LocalUserManager;

import java.util.ArrayList;

//此类将用户输入的指令和数据传递给业务模型
public class UserController {
    private static UserController userController = new UserController();

    public static UserController getInstance() {
        return userController;
    }

    public boolean addHomeCategory(ArrayList<String> des) {
        HomeCategoryList mainCategories = HomeCategoryList.getInstance();
        HomeCategory category = HomeCategoryList.getNewCategory();
        boolean result = category.set(des);
        if (result) {
            mainCategories.add(category);
            return true;
        } else return false;
    }

    public HomeCategoryList getMainCategoryList() {
        return HomeCategoryList.getInstance();
    }

    public boolean hasInfo(Context context) {
        return LocalUserManager.getInstance().hasUserInfo(context);
    }

    public void saveInfo(Context context, String userName, String password, String type) {
        LocalUserManager.getInstance().saveUserInfo(context, userName, password, type);
    }

    public ArrayList<String> getInfo(Context context) {
        LocalUserInfo localUserInfo = LocalUserManager.getInstance().getUserInfo(context);
        ArrayList<String> info = new ArrayList<>();
        info.add(localUserInfo.getUserName());
        info.add(localUserInfo.getPassword());
        info.add(localUserInfo.getType());
        return info;
    }

    public void userQuit(Context context) {
        ArrayList<String> info = getInfo(context);
        saveInfo(context, info.get(0), info.get(1), "quit");
    }


}
