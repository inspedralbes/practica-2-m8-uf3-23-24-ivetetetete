package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.FightGame;
import com.mygdx.game.utils.Settings;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Practica2_videojoc");
		config.setWindowedMode(Settings.GAME_WIDTH*2, Settings.GAME_HEIGHT * 2);
		new Lwjgl3Application(new FightGame(), config);
	}
}
