package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {
    // Sprite Sheet
    public static Texture sheet;

    public static Texture background, backgroundMenu;

    public static TextureRegion backgroundRegion, backgroundMenuRegion;


    // Asteroide
    public static TextureRegion[] catheroRight,catheroStand, catheroLeft, catheroAttack;
    public static Animation catheroRightAnim,catheroStandAnim,catheroLeftAnim,catheroAttackAnim;

    public static void load() {
        background = new Texture("Background.png");
        background.setFilter(Texture.TextureFilter.Nearest,Texture.TextureFilter.Nearest);
        backgroundRegion = new TextureRegion(background,3,3,153,114);
        backgroundMenuRegion = new TextureRegion(background,316,122,151,113);


        // Carreguem les textures i li apliquem el mètode d'escalat 'nearest'
        sheet = new Texture("spritesheet.png");
        sheet.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);


        //Cathero normal
        catheroStand = new TextureRegion[3];

        for (int i = 0; i < catheroStand.length; i++) {
            int frameX = 7 + i * 29;
            int frameY = 16;
            int frameWidth = 29;
            int frameHeight = 29;
            catheroStand[i] = new TextureRegion(sheet, frameX, frameY, frameWidth, frameHeight);
        }
        //Que se ejecute la animacion
        catheroStandAnim = new Animation(0.5f, catheroStand);

        //Animacion del cathero caminando a la derecha
        catheroRight = new TextureRegion[6];
        for (int i = 0; i < catheroRight.length; i++) {
            int frameX = 103 + i * 28;
            int frameY = 16;
            int frameWidth = 28;
            int frameHeight = 29;
            catheroRight[i] = new TextureRegion(sheet, frameX, frameY, frameWidth, frameHeight);
            //catheroRight[i].flip(false, true);
        }
        //Que se ejecute la animacion
        catheroRightAnim = new Animation(0.05f, catheroRight);

        //Animacion del cathero caminando a la izquierda
        catheroLeft = new TextureRegion[6];
        for (int i = 0; i < catheroLeft.length; i++) {
            int frameX = 103 + i * 28;
            int frameY = 16;
            int frameWidth = 28;
            int frameHeight = 29;
            catheroLeft[i] = new TextureRegion(sheet, frameX, frameY, frameWidth, frameHeight);
            catheroLeft[i].flip(true, false);
        }
        //Que se ejecute la animacion
        catheroLeftAnim = new Animation(0.05f, catheroLeft);

        //Animacion del cathero ataque
        catheroAttack = new TextureRegion[8];
        for (int i = 0; i < catheroAttack.length; i++) {
            int frameX = 7 + i * 45;
            int frameY = 62;
            int frameWidth = 45;
            int frameHeight = 29;
            catheroAttack[i] = new TextureRegion(sheet, frameX, frameY, frameWidth, frameHeight);
            catheroAttack[i].flip(true, false);
        }
        //Que se ejecute la animacion
        catheroAttackAnim = new Animation(0.05f, catheroAttack);



    }

    public static void dispose() {
        sheet.dispose();
        background.dispose();
    }
}
