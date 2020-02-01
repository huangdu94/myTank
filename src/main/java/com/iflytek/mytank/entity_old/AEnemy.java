package com.iflytek.mytank.entity_old;

import com.iflytek.mytank.loader.ImageCache;

import java.awt.image.BufferedImage;


public class AEnemy extends Tank {
    private static int location = 0;//控制出生地点

    public AEnemy() {
        super(60, 60, 15);
        switch (location++ % 3) {
            case 0:
                x = 0;
                y = 0;
                break;
            case 1:
                x = 360;
                y = 0;
                break;
            case 2:
                x = 720;
                y = 0;
        }
    }

    /**
     * 敌人坦克AI模块
     */
    private int v = 0;//控制敌人坦克移动速度

    public void step() {
        if (v++ % 10 == 0) {
            int move = (int) (Math.random() * 4);
            switch (move) {
                case 0:
                    goUp();
                    break;
                case 1:
                    goDown();
                    break;
                case 2:
                    goLeft();
                    break;
                case 3:
                    goRight();
                    break;
            }
        }
    }

    private int deadIndex = 0;

    public BufferedImage getImage() {
        if (isLive()) {
            switch (directionState) {
                case UP:
                    return ImageCache.aEnemyUp;
                case DOWN:
                    return ImageCache.aEnemyDown;
                case LEFT:
                    return ImageCache.aEnemyLeft;
                case RIGHT:
                    return ImageCache.aEnemyRight;
                default:
                    return null;
            }
        } else if (isDead()) {
            BufferedImage img = ImageCache.dead[deadIndex++ / 5];// /5用于增加爆炸动画时间
            if (deadIndex / 5 >= ImageCache.dead.length) {
                existState = REMOVE;
            }
            return img;
        }
        return null;
    }
}
