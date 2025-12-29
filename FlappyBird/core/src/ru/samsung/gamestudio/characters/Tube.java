package ru.samsung.gamestudio.characters;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import java.util.Random;

public class Tube {
    Texture textureUpperTube;
    Texture textureDownTube;
    int x, gapY;
    int distanceBetweenTubes;
    int speed = 10;
    final int width = 200;
    final int height = 700;
    int gapHeight = 400;
    int padding = 100;
    Random random;
    boolean isPointReceived;

    public Tube(int tubeCount, int tubeIdx) {
        random = new Random();
        gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        distanceBetweenTubes = (SCR_WIDTH + width) / (tubeCount - 1);
        x = distanceBetweenTubes * tubeIdx + SCR_WIDTH;

        textureUpperTube = new Texture("tube/tube_flipped.png");
        textureDownTube = new Texture("tube/tube.png");
        isPointReceived = false;
    }

    public void draw(Batch batch) {
        batch.draw(textureUpperTube, x, gapY + gapHeight / 2, width, height);
        batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, width, height);
    }
    
    public void move() {
        x -= speed;
        if (x < -width) {
            isPointReceived = false;
            x = SCR_WIDTH + distanceBetweenTubes;
            gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        }
    }

    public boolean isHit(Bird bird){
        if (bird.getY() <= gapY - gapHeight / 2 && bird.getX() + bird.getWidth() >= x && bird.getX() <= x + width)
            return true;
        if (bird.getY() + bird.getHeight() >= gapY + gapHeight / 2 && bird.getX() + bird.getWidth() >= x && bird.getX() <= x + width)
            return true;
        return false;
    }
    
    public boolean needAddPoint(Bird bird) {
        return !isPointReceived && bird.getX() > x + width;
    }
    
    public void setPointReceived() {
        isPointReceived = true;
    }
    
    public void dispose(){
        textureUpperTube.dispose();
        textureDownTube.dispose();
    }
}
