package com.rockbite.bootcamp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.rockbite.bootcamp.enums.FONT_STYLE;
import com.rockbite.bootcamp.enums.SHAPE;
import com.rockbite.bootcamp.enums.UI_COLOR;
import sun.security.provider.SHA;

public class Numpad extends ApplicationAdapter {
	private Stage stage;

	private ComponentProvider componentProvider;

	private Table container;
	private Table header;
	private Table body;

	@Override
	public void create () {
		super.create();
		stage = new Stage(new FitViewport(1000, 1000));
		componentProvider = new ComponentProvider();
		Gdx.input.setInputProcessor(stage);

		container = new Table();
		header = new Table();
		body = new Table();

		header.setWidth(623);
		header.setHeight(81);
		body.setWidth(623);
		body.setHeight(721);

		container.setFillParent(true);

		container.addActor(header);
		container.addActor(body);

//		Image headerImage = new Image(componentProvider.retrieveDrawable(SHAPE.SQUIRQLE_DIALOG_HEADER, UI_COLOR.WHITE));

//		header.add(headerImage);
//		headerImage.setSize(632, 81);

//		Image bodyImage = new Image(componentProvider.retrieveDrawable(SHAPE.SQUIRQLE_R_20, UI_COLOR.WHITE));
//		bodyImage.setSize(632, 721);
//		body.add(bodyImage);

		body.defaults().padLeft(160).padBottom(120);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				addButtonToBody(Integer.toString(i * 3 + j),
						SHAPE.SQUIRQLE_R_20,
						SHAPE.SQUIRQLE_R_20_FRAME,
						FONT_STYLE.SELWAIK_BOLD_80,
						UI_COLOR.BUTTON_BACKGROUND,
						UI_COLOR.BUTTON_BORDER,
						UI_COLOR.WHITE,
						120, 100);
			}
			body.row();
		}
		addButtonToBody(Integer.toString(0),
				SHAPE.SQUIRQLE_R_20,
				SHAPE.SQUIRQLE_R_20_FRAME,
				FONT_STYLE.SELWAIK_BOLD_80,
				UI_COLOR.BUTTON_BACKGROUND,
				UI_COLOR.BUTTON_BORDER,
				UI_COLOR.WHITE,
				120, 100);

		addButtonToBody("OK",
				SHAPE.SQUIRQLE_R_20,
				SHAPE.SQUIRQLE_R_20_FRAME,
				FONT_STYLE.SELWAIK_BOLD_36,
				UI_COLOR.RACKLEY,
				UI_COLOR.MOONSTONE_BLUE,
				UI_COLOR.WHITE,
				280, 100);

//		stage.setDebugAll(true);
		stage.addActor(container);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render () {
		super.render();
		float delta = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(87/255f, 82/255f, 80/255f,1);
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}

	private void addButtonToBody (String text, SHAPE shape, SHAPE borderShape, FONT_STYLE fontStyle, UI_COLOR buttonColor, UI_COLOR buttonBorderColor, UI_COLOR textColor, int width, int height) {
		Group group = new Group();

		Image button = new Image(componentProvider.retrieveDrawable(shape, buttonColor));
		Image buttonBorder = new Image(componentProvider.retrieveDrawable(borderShape, buttonBorderColor));
		Label label = componentProvider.retrieveLabel(text, fontStyle, textColor);

		button.setSize(width, height);
		buttonBorder.setSize(width, height);
		label.setPosition(label.getX() + (button.getWidth() - label.getWidth()) / 2, label.getY() + (button.getHeight() - label.getHeight()) / 2);

		group.addActor(button);
		group.addActor(buttonBorder);
		group.addActor(label);

		body.add(group);
	}
}
