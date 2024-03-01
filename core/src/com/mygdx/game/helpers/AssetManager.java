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

    public static TextureRegion catheroStand;
    public static Texture background;

    public static TextureRegion backgroundRegion;


    // Asteroide
    public static TextureRegion[] catheroRight;
    public static Animation catheroRightAnim;

    // Explosió
    public static TextureRegion[] explosion;
    public static Animation explosionAnim;
    public static void load() {
        // Carreguem les textures i li apliquem el mètode d'escalat 'nearest'
        sheet = new Texture(Gdx.files.internal("spritesheet.png"));
        sheet.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        /*
        // Sprites de la nau
        catheroStand = new TextureRegion(sheet, 0, 0, 36, 15);
        catheroStand.flip(false, true);

        catheroRight = new TextureRegion(sheet, 36, 0, 36, 15);
        catheroRight.flip(false, true);

        catheroLeft = new TextureRegion(sheet, 72, 0, 36, 15);
        catheroLeft .flip(false, true);*/

        //Animacion del cathero caminando
        catheroRight = new TextureRegion[6];
        for (int i = 0; i < catheroRight.length; i++) {

            catheroRight[i] = new TextureRegion(sheet, i * 104, 16, 26, 28);
            //catheroRight[i].flip(false, true);
        }
        //Que se ejecute la animacion
        catheroRightAnim = new Animation(0.05f, catheroRight);


        background = new Texture("Background.png");
        background.setFilter(Texture.TextureFilter.Nearest,Texture.TextureFilter.Nearest);
        backgroundRegion = new TextureRegion(background,3,3,153,114);

    }

    public static void dispose() {
        sheet.dispose();
        background.dispose();
    }
}
