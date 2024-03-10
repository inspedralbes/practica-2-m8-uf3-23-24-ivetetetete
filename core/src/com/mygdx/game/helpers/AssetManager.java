package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {
    // Sprite Sheet
    public static Texture sheet, sheetEnemy;

    public static Texture background, backgroundMenu;

    public static TextureRegion backgroundRegion, backgroundMenuRegion;


    // Asteroide
    public static TextureRegion[] catheroRun, catheroAttack, enemyRun, enemyAttack;
    public static Animation catheroRunAnim,catheroAttackAnim, enemyRunAnim, enemyAttackAnim;

    public static void load() {
        background = new Texture("Background.png");
        background.setFilter(Texture.TextureFilter.Nearest,Texture.TextureFilter.Nearest);
        backgroundRegion = new TextureRegion(background,3,3,153,114);
        backgroundMenuRegion = new TextureRegion(background,316,122,151,113);


        // Carreguem les textures i li apliquem el mètode d'escalat 'nearest'
        sheet = new Texture("spritesheet.png");
        sheet.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);


        //Animacion corriendo
        catheroRun = new TextureRegion[6];
        for (int i = 0; i < catheroRun.length; i++) {
            int frameX = 103 + i * 28;
            int frameY = 16;
            int frameWidth = 28;
            int frameHeight = 29;
            catheroRun[i] = new TextureRegion(sheet, frameX, frameY, frameWidth, frameHeight);
            //catheroRight[i].flip(false, true);
        }
        //Que se ejecute la animacion
        catheroRunAnim = new Animation(0.05f, catheroRun);

        //Animacion del cathero ataque
        catheroAttack = new TextureRegion[8];
        for (int i = 0; i < catheroAttack.length; i++) {
            int frameX = 7 + i * 45;
            int frameY = 62;
            int frameWidth = 45;
            int frameHeight = 29;
            catheroAttack[i] = new TextureRegion(sheet, frameX, frameY, frameWidth, frameHeight);
            catheroAttack[i].flip(false, false);
        }
        //Que se ejecute la animacion
        catheroAttackAnim = new Animation(0.05f, catheroAttack);


        //DEL ENEMIGO
        sheetEnemy = new Texture("enemy-spritesheet.png");
        sheetEnemy.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        //Enemy run
        enemyRun = new TextureRegion[6];

        for (int i = 0; i < enemyRun.length; i++) {
            int frameX = 63 + i * 25;
            int frameY = 16;
            int frameWidth = 25;
            int frameHeight = 36;
            enemyRun[i] = new TextureRegion(sheetEnemy, frameX, frameY, frameWidth, frameHeight);
            enemyRun[i].flip(true, false);

        }
        //Que se ejecute la animacion
        enemyRunAnim = new Animation(0.05f, enemyRun);

        //Enemy attack
        enemyAttack = new TextureRegion[9];

        for (int i = 0; i < enemyAttack.length; i++) {
            int frameX = 7 + i * 44;
            int frameY = 70;
            int frameWidth = 44;
            int frameHeight = 36;
            enemyAttack[i] = new TextureRegion(sheetEnemy, frameX, frameY, frameWidth, frameHeight);
            enemyAttack[i].flip(true, false);

        }
        //Que se ejecute la animacion
        enemyAttackAnim = new Animation(0.05f, enemyAttack);

    }

    public static void dispose() {
        sheet.dispose();
        sheetEnemy.dispose();

        background.dispose();
    }
}
