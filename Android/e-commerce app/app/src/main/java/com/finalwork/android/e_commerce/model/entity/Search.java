package com.finalwork.android.e_commerce.model.entity;

public class Search {
    private String clip;
    private String order;
    private String sort;
    private String page;
    private String rows;

    public String getClip() {
        return clip;
    }

    public void setClip(String clip) {
        this.clip = clip;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String toString() {
        return "[ clip=" + clip
                + ", order=" + order
                + ", sort=" + sort
                + ", page=" + page
                + ", rows=" + rows
                + "]";
    }
}
