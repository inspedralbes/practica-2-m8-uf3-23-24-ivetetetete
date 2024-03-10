package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.utils.Settings;

public class CatHero extends Actor {
    //Posiciones
    public static final int CATHERO_RUN = 0;

    public static final int CATHERO_ATTACK = 7;
    private float stateTime;


    //Parametros cathero
    private Vector2 position;
    private int width, height;
    private int direction;
    private float runTime;

    private float attackCooldown = 1.0f; // Set the cooldown time for attacks (in seconds)
    private float timeSinceLastAttack = 0.0f;

    public Rectangle collisionRect;

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

        //Cathero corriendo
        direction = CATHERO_RUN;
        this.animation = animation;
        stateTime = 0f;

        collisionRect = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds(){
        return this.collisionRect;
    }

    public void act(float delta) {
        super.act(delta);

        // Movem cat depenent de la direcció controlant que no surti de la pantalla
        switch (direction) {
            case CATHERO_RUN:
                if (this.position.x - Settings.CATHERO_VELOCITY * delta >= 0) {
                    this.position.x += Settings.CATHERO_VELOCITY * delta;
                }
                break;
            case CATHERO_ATTACK:
                break;
        }

        stateTime += delta;
        timeSinceLastAttack += delta;

        collisionRect.set(position.x, position.y + 3, width, 10);


    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch,parentAlpha);
       // batch.draw(getSpacecraftTexture(), position.x, position.y, width, height);

        TextureRegion currentFrame = getCatheroTexture().getKeyFrame(stateTime, true);
        batch.draw(currentFrame, position.x, position.y, width, height);
    }


    public void goLeft() {}
//Cathero corre
    public void goRight() {
        direction =CATHERO_RUN;
    }

    //Ataca
    public void goAttack() {
        direction = CATHERO_ATTACK;

        if (timeSinceLastAttack >= attackCooldown) {
            timeSinceLastAttack = 0.0f;
            System.out.println("Cathero ataca!");
        }
    }


    public float getRunTime() {
        return runTime;
    }

    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    public Animation<TextureRegion> getCatheroTexture() {
        switch (direction) {
            case CATHERO_ATTACK:
                return AssetManager.catheroAttackAnim;
            default:
                return AssetManager.catheroRunAnim;
        }
    }



}
