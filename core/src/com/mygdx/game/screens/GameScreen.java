package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.FightGame;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.helpers.InputHandler;
import com.mygdx.game.objects.CatHero;
import com.mygdx.game.objects.Enemy;
import com.mygdx.game.utils.Settings;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private Stage stage;
    private CatHero cathero;
    private Enemy enemy;
    //Para spawnear enemigos
    private float timeSinceLastEnemy = 0f;
    private float enemySpawnInterval = 10f;
    public ArrayList<Enemy> enemies;

    // Representació de figures geomètriques
    private ShapeRenderer shapeRenderer;
    // Per obtenir el batch de l'stage
    private Batch batch;

    private OrthographicCamera camera;

    private FightGame game;

    public TextureRegion background;
    public float backgroundX = 0;
    public float backgroundSpeed = 10;
    private InputHandler inputHandler;

    private Music musicBattle;


    public GameScreen(final FightGame game) {
        this.game = game;

        //Musica
        musicBattle = Gdx.audio.newMusic(Gdx.files.internal("Battle.mp3"));

        // Creem el ShapeRenderer
        shapeRenderer = new ShapeRenderer();

        // Creem la càmera de les dimensions del joc
        camera = new OrthographicCamera();
        // Posant el paràmetre a true configurem la càmera perquè
        // faci servir el sistema de coordenades Y-Down
        camera.setToOrtho(false,Settings.GAME_WIDTH,Settings.GAME_HEIGHT);

        // Creem el viewport amb les mateixes dimensions que la càmera
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT , camera);

        // Creem l'stage
        stage = new Stage(viewport);
        batch = stage.getBatch();

        background = new TextureRegion(AssetManager.backgroundRegion);
        // Creem la nau i la resta d'objectes
        cathero = new CatHero(Settings.CATHERO_STARTX, Settings.CATHERO_STARTY, Settings.CATHERO_WIDTH, Settings.CATHERO_HEIGHT, AssetManager.catheroRunAnim);
        enemy = new Enemy(Settings.ENEMY_STARTX, Settings.ENEMY_STARTY, Settings.ENEMY_WIDTH, Settings.ENEMY_HEIGHT, AssetManager.enemyRunAnim);

        enemies = new ArrayList<Enemy>();

        stage.addActor(cathero);
        stage.addActor(enemy);
        enemies.add(enemy);


        cathero.goRight();
        enemy.goRun();

        inputHandler = new InputHandler(cathero);
        Gdx.input.setInputProcessor(inputHandler);

    }

    //COLISIONES
    public boolean checkCollision() {
        for (Actor actor : stage.getRoot().getChildren()) {
            if (actor instanceof Enemy) {
                final Enemy enemy = (Enemy) actor;
                float distance = cathero.getX() - enemy.getX();
                if (Math.abs(distance) <= 100) {
                    System.out.println("CERCA");
                   // enemy.setEn(AssetsManager.barrelBreakAnim);
                    enemy.attack();

                    if (enemy.getBounds().overlaps(cathero.getBounds())) {
                        Gdx.app.log("MainScreen", "Collision detected!");
                        //barrel.playAnimationBreak();
                        //enemy.setBarrelAnim(AssetsManager.barrelBreakAnim);
                        //enemy.breaks();
                        //Gdx.app.log("MainScreen", "vidas: " + cathero.restarVida());

                        return true;
                    }
                }


            }
        }
        return false;
    }


    private void drawElements(){

        /* 2
// Recollim les propietats del Batch de l'Stage
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
// Inicialitzem el shaperenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

// Definim el color (verd)
        shapeRenderer.setColor(new Color(0, 1, 0, 1));
// Pintem la nau
        shapeRenderer.rect(cathero.getX(), cathero.getY(), cathero.getWidth(), cathero.getHeight());

        shapeRenderer.end();*/
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();


        backgroundX-=backgroundSpeed*delta;

        if(backgroundX<(Settings.GAME_WIDTH - (Settings.GAME_WIDTH*2))){
            backgroundX=0;
        }
       game.batch.draw(background,backgroundX,0,Settings.GAME_WIDTH,Settings.GAME_HEIGHT);
       game.batch.draw(background,backgroundX+Settings.GAME_WIDTH,0,Settings.GAME_WIDTH,Settings.GAME_HEIGHT);

        timeSinceLastEnemy += delta;
        if (timeSinceLastEnemy >= enemySpawnInterval) {
            spawnEnemey();
            timeSinceLastEnemy = 0f; // Reinicia el temporizador
        }

        stage.act(delta);
        checkCollision();
        game.batch.end();

       // music.play();
        musicBattle.play();

        // Dibuixem i actualitzem tots els actors de l'stage
        stage.draw();


    }
    private void spawnEnemey() {
        enemy = new Enemy(Settings.ENEMY_STARTX, Settings.ENEMY_STARTY, Settings.ENEMY_WIDTH, Settings.ENEMY_HEIGHT, AssetManager.enemyRunAnim);
        stage.addActor(enemy);
    }


    public CatHero getCatHero() {
        return cathero;
    }

    public Stage getStage() {
        return stage;
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
