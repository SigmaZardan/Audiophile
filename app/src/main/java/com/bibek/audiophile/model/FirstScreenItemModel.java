package com.bibek.audiophile.model;

import androidx.annotation.IdRes;

public class FirstScreenItemModel {
    @IdRes
    private int firstScreenItemIconId;
    private String firstScreenItemTitle;
    @IdRes
    private int firstScreenArrowIcon;


    public int getFirstScreenItemIconId() {
        return firstScreenItemIconId;
    }

    public void setFirstScreenItemIconId(int firstScreenItemIconId) {
        this.firstScreenItemIconId = firstScreenItemIconId;
    }

    public String getFirstScreenItemTitle() {
        return firstScreenItemTitle;
    }

    public void setFirstScreenItemTitle(String firstScreenItemTitle) {
        this.firstScreenItemTitle = firstScreenItemTitle;
    }

    public int getFirstScreenArrowIcon() {
        return firstScreenArrowIcon;
    }

    public void setFirstScreenArrowIcon(int firstScreenArrowIcon) {
        this.firstScreenArrowIcon = firstScreenArrowIcon;
    }
}
