package ru.samsung.gamestudio.screens;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.samsung.gamestudio.components.MovingBackground;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.components.PointCounter;
import ru.samsung.gamestudio.components.TextButton;

public class ScreenRestart implements Screen {
    TextButton textButton;
    TextButton textButtonMenu;
    MovingBackground background;
    MyGdxGame myGdxGame;
    PointCounter pointCounter;
    final int pointCounterMarginTop = 130;
    final int pointCounterMarginRight = 790;
    private int gamePoints;
    
    public int getGamePoints() { return gamePoints; }
    public void setGamePoints(int gamePoints) { this.gamePoints = gamePoints; }

    public ScreenRestart(MyGdxGame myGdxGame_new) {
        this.myGdxGame = myGdxGame_new;
        background = new MovingBackground("background/play1.png");
        textButton = new TextButton(150, 200, "Restart");
        textButtonMenu = new TextButton(650, 200, "Menu");
        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(touch);
            
            if (textButton.isHit((int)touch.x, (int)touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenGame);
                return;
            }
            if (textButtonMenu.isHit((int)touch.x, (int)touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenMenu);
                return;
            }
        }
        
        background.move();
        ScreenUtils.clear(0, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        textButton.draw(myGdxGame.batch);
        textButtonMenu.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch, gamePoints);
        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        background.dispose();
        textButton.dispose();
        textButtonMenu.dispose();
        pointCounter.dispose();
    }
}
