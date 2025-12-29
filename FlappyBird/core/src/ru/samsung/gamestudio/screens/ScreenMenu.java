package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.samsung.gamestudio.components.MovingBackground;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.components.TextButton;

public class ScreenMenu implements Screen {
    MyGdxGame myGdxGame;
    MovingBackground background;
    TextButton textButtonStart;
    TextButton textButtonExit;

    public ScreenMenu(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        textButtonStart = new TextButton(100, 400, "Start");
        textButtonExit = new TextButton(700, 400, "Exit");
        background = new MovingBackground("background/play1.png");
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(touch);
            
            if (textButtonStart.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenGame);
                return;
            }
            if (textButtonExit.isHit((int) touch.x, (int) touch.y)) {
                Gdx.app.exit();
            }
        }

        background.move();
        ScreenUtils.clear(0, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        textButtonStart.draw(myGdxGame.batch);
        textButtonExit.draw(myGdxGame.batch);
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
        textButtonExit.dispose();
        textButtonStart.dispose();
    }
}
