package com.rockbite.bootcamp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Numpad extends ApplicationAdapter {
	private Stage stage;

	private SpriteBatch batch;
	private Image img;
	
	@Override
	public void create () {
		super.create();
		stage = new Stage(new FitViewport(500,1000));
		Gdx.input.setInputProcessor(stage);

		batch = new SpriteBatch();
		Texture texture = new Texture("Preview.png");
		img = new Image(texture);

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("selawkb.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 8;
		BitmapFont font = generator.generateFont(parameter);
		stage.addActor(img);
	}

	@Override
	public void render () {
		super.render();
		float delta = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0,0,0,1);
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
