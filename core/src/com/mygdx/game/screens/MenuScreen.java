package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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

    public  Button startButton;
    public Button settingsButton;

    public Music music;

    public Skin mySkin;
    public MenuScreen(final FightGame game){
        this.game = game;
        music = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));

        background = new TextureRegion(AssetManager.backgroundMenuRegion);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT,camera);
        stage = new Stage(viewport);


        mySkin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        startButton = new TextButton("Start", mySkin);
        startButton.setPosition(300, 100);
        startButton.setSize(200, 50);

        settingsButton = new TextButton("Setting", mySkin);
        settingsButton.setPosition(300,50);
        settingsButton.setSize(200, 50);

        startButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log("MenuScreen", "Start button clicked");
                music.stop();
                music.dispose();
                game.setScreen(new GameScreen(game));
                dispose();
                return true;
            }
        });

        settingsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                showMusicToggleModal();
                dispose();
                return true;
            }
        });

        music.play();
        music.setLooping(true);
    }

    private void showMusicToggleModal() {
        Table contentTable = new Table();
        TextButton musicOnButton = new TextButton("Toggle On", mySkin);
        TextButton musicOffButton = new TextButton("Toggle Off", mySkin);
        TextButton cancelButton = new TextButton("Cancel", mySkin);

        //Control de la musica
        final Slider volumeSlider = new Slider(0f, 1f, 0.1f, false, mySkin);

        contentTable.add(new Label("Music Settings", mySkin)).padBottom(20f).row();
        contentTable.add(musicOnButton).padBottom(10f).row();
        contentTable.add(musicOffButton).padBottom(10f).row();
        // Volume slider
        contentTable.add(new Label("Volume", mySkin)).padTop(20f).padBottom(5f).row();
        contentTable.add(volumeSlider).width(200f).padBottom(20f).row();
        contentTable.add(cancelButton);



        //ACCIONES DE LOS BOTONES
        musicOnButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //PONER MUSICA
                if(!music.isPlaying()){
                    music.play();
                }
                dispose();
                return true;
            }
        });

        musicOffButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //QUITAR MUSICA
                music.pause();
                dispose();
                return true;
            }
        });

        cancelButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuScreen(game));
                dispose();
                return true;
            }
        });

        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float volume = volumeSlider.getValue();
                music.setVolume(volume);
            }
        });


        Dialog musicDialog = new Dialog("Ajustes", mySkin);
        musicDialog.getContentTable().add(contentTable).pad(10f);
        musicDialog.setSize(200, 100);

        musicDialog.show(stage);
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
        stage.addActor(startButton);
        stage.addActor(settingsButton);


        game.font.draw(game.batch, "Welcome!",400,200);
        stage.draw();
        stage.act();

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
