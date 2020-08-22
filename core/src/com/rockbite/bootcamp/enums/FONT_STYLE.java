package com.rockbite.bootcamp.enums;

public enum FONT_STYLE {
    SELWAIK_BOLD_34("selawkb.ttf", 34),
    SELWAIK_BOLD_36("selawkb.ttf", 36),
    SELWAIK_BOLD_44("selawkb.ttf", 44),
    SELWAIK_BOLD_80("selawkb.ttf", 80);

    private String path;
    private int size;

    FONT_STYLE (String path, int size) {
        this.path = path;
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public int getSize() {
        return size;
    }
}
