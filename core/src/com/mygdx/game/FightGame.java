package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.objects.Background;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MenuScreen;

public class FightGame extends Game {
	public SpriteBatch batch;
	private Texture backgroundTexture;
	private Background background;

	public BitmapFont font;

	@Override
	public void create () {
		// A l'iniciar el joc carreguem els recursos
		AssetManager.load();
		batch = new SpriteBatch();
		font = new BitmapFont();

		// I definim la pantalla principal com a la pantalla
		setScreen(new MenuScreen(this));
	}

	@Override
	public void resize(int width, int height){
		super.resize(width,height);
		Gdx.app.log("LifeCycle","resize("+Integer.toString(width)+","+Integer.toString(height)+")");
	}
	@Override
	public void pause() {
		super.pause();
		Gdx.app.log("LifeCycle", "pause()");
	}

	@Override
	public void resume() {
		super.resume();
		Gdx.app.log("LifeCycle", "resume()");
	}

	@Override
	public void render() {

		super.render();
		batch.begin();
		batch.end();
//Gdx.app.log("LifeCycle", "render()");
	}


	// Cridem per descartar els recursos carregats.
	@Override
	public void dispose() {
		super.dispose();
		AssetManager.dispose();
	}
}
