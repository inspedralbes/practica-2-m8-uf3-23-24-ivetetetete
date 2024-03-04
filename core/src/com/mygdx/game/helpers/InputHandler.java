package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.objects.CatHero;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.utils.Settings;

public class InputHandler implements InputProcessor {
    // Objectes necessaris
    private CatHero cathero;
    private GameScreen screen;

    public InputHandler(CatHero cathero) {
        // Obtenim tots els elements necessaris
        this.cathero = cathero;

    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
                cathero.goRight();
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
                cathero.goStraight();
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float halfScreenWidth = Settings.GAME_WIDTH / 2;
        if (screenX < halfScreenWidth) {
            cathero.goLeft();
        } else {
            cathero.goRight();
        }

        cathero.attack();

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        cathero.goStraight();
        return true;
    }
    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
