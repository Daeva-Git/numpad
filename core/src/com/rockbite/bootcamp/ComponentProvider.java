package com.rockbite.bootcamp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.rockbite.bootcamp.enums.FONT_STYLE;
import com.rockbite.bootcamp.enums.SHAPE;
import com.rockbite.bootcamp.enums.UI_COLOR;

public class ComponentProvider {
    private ObjectMap<SHAPE, Drawable> drawables = new ObjectMap<>();
    private ObjectMap<FONT_STYLE, BitmapFont> bitmapFonts = new ObjectMap<>();
    private IntMap<Drawable> drawableCache = new IntMap<>();
    private IntMap<Label.LabelStyle> labelStyleCache = new IntMap<>();

    public  ComponentProvider () {
        initNinePatches();
        initFontPatches();
    }

    private void initNinePatches () {
        for (int i = 0; i < SHAPE.values().length; i++) {
            SHAPE shape = SHAPE.values()[i];
            NinePatch ninePatch = new NinePatch(new Texture(shape.getPath()), shape.getLeft(), shape.getRight(), shape.getTop(), shape.getBottom());
            NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);
            drawables.put(shape, ninePatchDrawable);
        }
    }

    private void initFontPatches () {


        for (int i = 0; i < FONT_STYLE.values().length; i++) {
            FONT_STYLE fontStyle = FONT_STYLE.values()[i];
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/" + fontStyle.getPath()));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = FONT_STYLE.values()[i].getSize();
            BitmapFont bitmapFont = generator.generateFont(parameter);
            bitmapFonts.put(fontStyle, bitmapFont);
            generator.dispose();
        }
    }

    public Drawable retrieveDrawable(SHAPE shape, UI_COLOR color) {
        int key = shape.hashCode() * 31 + color.hashCode();

        if (drawableCache.containsKey(key)) return drawableCache.get(key);

        Drawable drawable = drawables.get(shape);
        Drawable tintedDrawable = newDrawable(drawable, color.getColorValue());
        drawableCache.put(key, tintedDrawable);
        return tintedDrawable;
    }

    public Label retrieveLabel (String text, FONT_STYLE font_style, UI_COLOR color) {
        BitmapFont bitmapFont = bitmapFonts.get(font_style);
        Label.LabelStyle labelStyle;
        int key = font_style.hashCode() * 31 + color.hashCode();

        if (labelStyleCache.containsKey(key)) {
            labelStyle = labelStyleCache.get(key);
        } else {
            labelStyle = new Label.LabelStyle(bitmapFont, color.getColorValue());
            labelStyleCache.put(key, labelStyle);
        }
        Label label = new Label(text, labelStyle);
        return label;
    }

    private Drawable newDrawable (Drawable drawable, Color tint) {
        Drawable newDrawable;
        if (drawable instanceof TextureRegionDrawable)
            newDrawable = ((TextureRegionDrawable)drawable).tint(tint);
        else if (drawable instanceof NinePatchDrawable)
            newDrawable = ((NinePatchDrawable)drawable).tint(tint);
        else if (drawable instanceof SpriteDrawable)
            newDrawable = ((SpriteDrawable)drawable).tint(tint);
        else
            throw new GdxRuntimeException("Unable to copy,unkown drawable type: " + drawable.getClass());

        if (newDrawable instanceof BaseDrawable) {
            BaseDrawable named = (BaseDrawable)newDrawable;
            if (newDrawable instanceof BaseDrawable)
                named.setName(((BaseDrawable) drawable).getName() + " (" + tint + ")");
            else
                named.setName(" (" + tint + ")");
        }

        return newDrawable;
    }
}
