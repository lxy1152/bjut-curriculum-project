package com.finalwork.android.e_commerce.model.entity;

import java.util.Map;

public class SearchItem {
    private int row;
    private Map left;
    private Map right;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Map getLeft() {
        return left;
    }

    public void setLeft(Map left) {
        this.left = left;
    }

    public Map getRight() {
        return right;
    }

    public void setRight(Map right) {
        this.right = right;
    }

    public SearchItem(int row, Map left, Map right){
        setRow(row);
        setLeft(left);
        setRight(right);
    }
}
