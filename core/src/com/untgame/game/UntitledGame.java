package com.untgame.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.untgame.game.screens.MainScreen;

public class UntitledGame extends Game {

	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		Pixmap pixmap = new Pixmap(Gdx.files.internal("cursor.png"));
		int centerX = 15;
		int centerY = 15;
		Cursor cursor = Gdx.graphics.newCursor(pixmap, centerX, centerY);
		pixmap.dispose();
		Gdx.graphics.setCursor(cursor);
		this.setScreen(new MainScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}


