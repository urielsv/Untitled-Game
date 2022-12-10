package com.untgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static com.untgame.game.helper.Constants.*;
// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument

public class DesktopLauncher {

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(REFRESH_RATE);
		config.useVsync(true);
		config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
		config.setResizable(false);
		config.setTitle("untitled-game");
		new Lwjgl3Application(new UntitledGame(), config);
	}
}
