package com.finalwork.android.e_commerce.model;

import java.util.ArrayList;

public class HomeCategoryList extends ArrayList<HomeCategory> {
    private static HomeCategoryList mainCategories = new HomeCategoryList();

    public static HomeCategoryList getInstance() {
        return mainCategories;
    }

    public static HomeCategory getNewCategory() {
        return new HomeCategory();
    }
}
