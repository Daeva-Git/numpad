package com.rockbite.bootcamp.enums;

import com.badlogic.gdx.graphics.Color;

public enum UI_COLOR {
    WHITE("#FFFFFF"),
    BACKGROUND("#575250"),
    BUTTON_BACKGROUND("#7e7772"),
    BUTTON_BORDER("#cfc3ba"),
    RACKLEY("#5b92a8"),
    MOONSTONE_BLUE("#6ab0cb"),
    DARK_BLUE("#406575");

    private String color;
    private Color colorValue;

    UI_COLOR (String color) {
        this.color = color;
        this.colorValue = Color.valueOf(this.color);
    }

    public String getColor() {
        return color;
    }

    public Color getColorValue() {
        return colorValue;
    }
}
