package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.utils.Settings;

public class Enemy extends Actor {
    public static final int ENEMY_RUN = 5;
    public static final int ENEMY_ATTACK = 7;
    private float stateTime;
    public float speed = 50f; // Ajusta la velocidad según tus necesidades

    //Parametros enemy
    private Vector2 position;
    private int width, height;
    private int direction;
    private float runTime;

    public Rectangle collisionCircle;

    private float attackCooldown = 1.0f;
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

    public Enemy(float x, float y, int width, int height, Animation animation) {

        // Inicialitzem els arguments segons la crida del constructor
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        // Inicialitzem cat hero a l'estat normal
        direction = ENEMY_RUN;
        this.animation = animation;
        stateTime = 0f;

        this.collisionCircle = new Rectangle(x, y, width, height);

    }

    public Rectangle getBounds(){
        return this.collisionCircle;
    }
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
        float newX = position.x - speed * delta; // Usa position.x en lugar de getX()
        if (newX + this.width > 0) {
            position.x= newX;
        }else{
            remove();
        }


        collisionCircle.setPosition(position.x, position.y);




        /*// Movem cat depenent de la direcció controlant que no surti de la pantalla
        switch (direction) {
            case ENEMY_ATTACK:
                if (this.position.x - Settings.ENEMY_VELOCITY * delta >= 0) {
                    this.position.x += Settings.ENEMY_VELOCITY * delta;
                }
                break;
            case ENEMY_RUN:
                break;
        }*/

        timeSinceLastAttack += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch,parentAlpha);
        // batch.draw(getSpacecraftTexture(), position.x, position.y, width, height);

        TextureRegion currentFrame = getEnemyTexture().getKeyFrame(stateTime, true);
        batch.draw(currentFrame, position.x, position.y, width, height);
    }

    public void goRun() {
        direction = ENEMY_RUN;
    }

    public void attack() {
        this.speed = 0;
        direction = ENEMY_ATTACK;
       /* if (timeSinceLastAttack >= attackCooldown) {
            timeSinceLastAttack = 0.0f;
            System.out.println("ENEMY ataca!");
        }*/
    }
    public Animation<TextureRegion> getEnemyTexture() {
        switch (direction) {
            case ENEMY_ATTACK:
                return AssetManager.enemyAttackAnim;
            default:
                return AssetManager.enemyRunAnim;
        }
    }

    public Rectangle getCollisionCircle() {
        return collisionCircle;
    }


}
