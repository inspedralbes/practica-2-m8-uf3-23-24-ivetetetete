package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.FightGame;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.objects.CatHero;
import com.mygdx.game.utils.Settings;

public class GameScreen implements Screen {
    private Stage stage;
    private CatHero cathero;
    // Representació de figures geomètriques
    private ShapeRenderer shapeRenderer;
    // Per obtenir el batch de l'stage
    private Batch batch;

    private OrthographicCamera camera;

    private FightGame game;

    public TextureRegion background;
public float backgroundX = 0;
public float backgroundSpeed = 10;

    public GameScreen(final FightGame game) {
        this.game = game;

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
        cathero = new CatHero(Settings.CATHERO_STARTX,Settings.CATHERO_STARTY,Settings.CATHERO_WIDTH, Settings.CATHERO_HEIGHT, AssetManager.catheroRightAnim);

        // Afegim els actors a l'stage
        stage.addActor(cathero);

    }

    private void drawElements(){

        /* 1 */
// Pintem el fons de negre per evitar el "flickering"
        Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /* 2 */
// Recollim les propietats del Batch de l'Stage
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
// Inicialitzem el shaperenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        /* 3 */
// Definim el color (verd)
        shapeRenderer.setColor(new Color(0, 1, 0, 1));
// Pintem la nau
        shapeRenderer.rect(cathero.getX(), cathero.getY(), cathero.getWidth(), cathero.getHeight());

        /* 4 */
        /*
        // Recollim tots els Asteroid / enemics
        ArrayList<Asteroid> asteroids = scrollHandler.getAsteroids();
        Asteroid asteroid;

        for (int i = 0; i < asteroids.size(); i++) {

            asteroid = asteroids.get(i);
            switch (i) {
                case 0:
                    shapeRenderer.setColor(1,0,0,1);
                    break;
                case 1:
                    shapeRenderer.setColor(0,0,1,1);
                    break;
                case 2:
                    shapeRenderer.setColor(1,1,0,1);
                    break;
                default:
                    shapeRenderer.setColor(1,1,1,1);
                    break;
            }
            shapeRenderer.circle(asteroid.getX() + asteroid.getWidth()/2, asteroid.getY() + asteroid.getWidth()/2, asteroid.getWidth()/2);
        }*/
        /* 5 */
        shapeRenderer.end();
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


        // Dibuixem i actualitzem tots els actors de l'stage
        stage.draw();
        stage.act(delta);

        game.batch.end();
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
