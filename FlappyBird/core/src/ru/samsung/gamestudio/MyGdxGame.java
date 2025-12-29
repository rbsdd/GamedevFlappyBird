package ru.samsung.gamestudio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.samsung.gamestudio.screens.ScreenGame;
import ru.samsung.gamestudio.screens.ScreenMenu;
import ru.samsung.gamestudio.screens.ScreenRestart;

public class MyGdxGame extends Game {

    public SpriteBatch batch;
    public OrthographicCamera camera;

    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;

    public ScreenGame screenGame;
    public ScreenRestart screenRestart;
    public ScreenMenu screenMenu;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        screenMenu = new ScreenMenu(this);

        screenGame = new ScreenGame(this);
        screenRestart = new ScreenRestart(this);

        setScreen(screenMenu);
    }


    @Override
    public void dispose() {

        batch.dispose();
    }
}
