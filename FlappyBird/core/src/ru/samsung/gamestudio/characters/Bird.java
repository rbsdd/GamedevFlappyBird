package ru.samsung.gamestudio.characters;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    private int x;
    private int y;
    int width, height;
    int speed;
    int jumpHeight;
    final int maxHeightOfJump = 100;
    boolean jump = true;
    int frameCounter;
    Texture[] framesArray;

    public Bird(int x, int y, int speed, int birdWidth, int birdHeight) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = birdWidth;
        this.height = birdHeight;
        frameCounter = 0;
        framesArray = new Texture[]{
                new Texture("bird/bird0.png"),
                new Texture("bird/bird1.png"),
                new Texture("bird/bird2.png"),
                new Texture("bird/bird1.png"),
        };
    }
    
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void onClick() {
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }
    
    public void fly() {
        if (y >= jumpHeight) {
            jump = false;
        }
        if (jump) {
            y += speed;
        } else {
            y -= speed;
        }
    }
    
    public boolean isInField() {
        return y + height > 0 && y < SCR_HEIGHT;
    }

    public void draw(Batch batch) {
        int frameMultiplier = 10;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }
    
    public void dispose() {
        for (Texture texture : framesArray) {
            texture.dispose();
        }
    }
}
