package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {
    // Sprite Sheet
    public static Texture sheet;

    // Nau i fons
    //public static TextureRegion catheroStand, catheroRight, catheroLeft, background;

    public static Texture background, backgroundMenu;

    public static TextureRegion backgroundRegion, backgroundMenuRegion;


    // Asteroide
    public static TextureRegion[] catheroRight,catheroStand;
    public static Animation catheroRightAnim,catheroStandAnim;

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

        //Animacion del cathero caminando
        catheroRight = new TextureRegion[6];
        for (int i = 0; i < catheroRight.length; i++) {

            catheroRight[i] = new TextureRegion(sheet, i * 104, 16, 26, 28);
            //catheroRight[i].flip(false, true);
        }
        //Que se ejecute la animacion
        catheroRightAnim = new Animation(0.05f, catheroRight);

    }

    public static void dispose() {
        sheet.dispose();
        background.dispose();
    }
}
