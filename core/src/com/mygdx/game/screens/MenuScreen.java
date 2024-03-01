package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.FightGame;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.utils.Settings;

import java.util.Set;

public class MenuScreen implements Screen {

    public FightGame game;
    public Stage stage;
    public OrthographicCamera camera;
    public TextureRegion background;
    public MenuScreen(final FightGame game){
        this.game = game;
        background = new TextureRegion(AssetManager.backgroundRegion);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT,camera);
        stage = new Stage(viewport);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.batch.draw(background,0,0,Settings.GAME_WIDTH,Settings.GAME_HEIGHT);

        game.font.draw(game.batch, "Welcome!",100,70);
        stage.draw();
        stage.act();

        if(Gdx.input.isTouched()){
            game.setScreen(new GameScreen(game));
            dispose();
        }
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
