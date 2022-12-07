package com.untgame.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.untgame.game.UntitledGame;
import com.untgame.game.Defs;
// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(Defs.REFRESH_RATE.getValue());
		config.useVsync(true);
		config.setWindowedMode(Defs.SCREEN_WIDTH.getValue(), Defs.SCREEN_HEIGHT.getValue());
		config.setTitle("untitled-game");
		new Lwjgl3Application(new UntitledGame(), config);
	}
}
