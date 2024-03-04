package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.utils.Settings;

public class CatHero extends Actor {
    //Posiciones
    public static final int CATHERO_STRAIGHT = 0;
    public static final int CATHERO_RIGHT = 5;
    public static final int CATHERO_LEFT= -5;
    private float stateTime;


    //Parametros cathero
    private Vector2 position;
    private int width, height;
    private int direction;
    private float runTime;

    private float attackCooldown = 1.0f; // Set the cooldown time for attacks (in seconds)
    private float timeSinceLastAttack = 0.0f;


    private com.badlogic.gdx.graphics.g2d.Animation animation;

    public Vector2 getPosition() {
        return position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public CatHero(float x, float y, int width, int height, Animation animation) {

        // Inicialitzem els arguments segons la crida del constructor
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        // Inicialitzem cat hero a l'estat normal
        direction = CATHERO_STRAIGHT;
        this.animation = animation;
        stateTime = 0f;

    }

    public void act(float delta) {
        super.act(delta);

        // Movem cat depenent de la direcció controlant que no surti de la pantalla
        switch (direction) {
            case CATHERO_RIGHT:
                if (this.position.x - Settings.CATHERO_VELOCITY * delta >= 0) {
                    this.position.x += Settings.CATHERO_VELOCITY * delta;
                }
                break;
            case CATHERO_LEFT:
                if (this.position.x + width + Settings.CATHERO_VELOCITY * delta <= Settings.GAME_WIDTH) {
                    this.position.x -= Settings.CATHERO_VELOCITY * delta;
                }
                break;
            case CATHERO_STRAIGHT:
                break;
        }

        stateTime += delta;
        timeSinceLastAttack += delta;



    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch,parentAlpha);
       // batch.draw(getSpacecraftTexture(), position.x, position.y, width, height);

        TextureRegion currentFrame = getCatheroTexture().getKeyFrame(stateTime, true);
        batch.draw(currentFrame, position.x, position.y, width, height);
    }


    // Canviem la direcció de l'Spacecraft: Puja
    public void goLeft() {
        direction = CATHERO_LEFT;
    }

    // Canviem la direcció de l'Spacecraft: Baixa
    public void goRight() {
        direction =CATHERO_RIGHT;
    }

    // Posem l'Spacecraft al seu estat original
    public void goStraight() {
        direction = CATHERO_STRAIGHT;
    }

    public void attack() {
        // Check if enough time has passed since the last attack
        if (timeSinceLastAttack >= attackCooldown) {
            // Implement the attack logic here
            // For example, change animations, deal damage, etc.

            // Reset the timeSinceLastAttack
            timeSinceLastAttack = 0.0f;
        }
    }


    public float getRunTime() {
        return runTime;
    }

    public Animation<TextureRegion> getCatheroTexture() {
        switch (direction) {
            case CATHERO_LEFT:
                return AssetManager.catheroLeftAnim;
            case CATHERO_RIGHT:
                return AssetManager.catheroRightAnim;
            default:
                return AssetManager.catheroStandAnim;
        }
    }



}
