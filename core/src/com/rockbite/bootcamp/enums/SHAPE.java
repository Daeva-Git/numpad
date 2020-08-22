package com.rockbite.bootcamp.enums;

public enum SHAPE {
    SQUIRQLE_R_10("shapes/white-squircle-r-10.9.png", 13, 19, 13, 20),
    SQUIRQLE_R_20("shapes/white-squircle-r-20.9.png", 23, 34, 23, 34),
    SQUIRQLE_R_10_FRAME("shapes/white-frame-squircle-r-10.9.png", 13, 20,13, 20),
    SQUIRQLE_R_20_FRAME("shapes/white-frame-squircle-r-20.9.png", 25, 32, 25, 32),
    SQUIRQLE_DIALOG_HEADER("shapes/dialog-header.9.png", 27, 38,  21, 52);

    private String path;
    private int left;
    private int right;
    private int top;
    private int bottom;

    SHAPE(String path, int left, int right, int top, int bottom) {
        this.path = path;
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public String getPath() {
        return path;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }
}
