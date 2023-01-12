package com.untgame.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.untgame.game.screens.MainScreen;

public class UntitledGame extends Game {

	public SpriteBatch batch;
	public BitmapFont blackFont, whiteFont;
	public Pixmap pixmap;
	public Cursor cursor;

	public BitmapFont getBlackFont() {
		return blackFont;
	}

	public BitmapFont getWhiteFont() {
		return whiteFont;
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		// Fonts
		blackFont = new BitmapFont(Gdx.files.internal("blackFont.fnt"), false);
		whiteFont = new BitmapFont(Gdx.files.internal("whiteFont.fnt"), false);
		// Cursor
		pixmap = new Pixmap(Gdx.files.internal("cursor.png"));
		int centerX = 15;
		int centerY = 15;
		cursor = Gdx.graphics.newCursor(pixmap, centerX, centerY);
		Gdx.graphics.setCursor(cursor);

		// Screen (start main menu)
		this.setScreen(new MainScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		whiteFont.dispose();
		blackFont.dispose();
		pixmap.dispose();
	}


}


