package com.iflytek.mytank.element;

import com.iflytek.mytank.loader.ImageCache;
import com.iflytek.mytank.loader.PropertiesLoader;

import java.awt.image.BufferedImage;

/**
 * 英雄tank
 */
public class Hero extends Tank {
    private int life;

    public Hero() {
        super(PropertiesLoader.Key.TANK_WIDTH, PropertiesLoader.Key.TANK_HEIGHT, Integer.parseInt(PropertiesLoader.getProperties(PropertiesLoader.Key.TANK_STEP)));
        x = 160;
        y = 480;
        life = 3;
    }

    /**
     * 减命
     */
    public void subtractLife() {
        life--;
    }

    /**
     * 加命
     */
    public void addLife() {
        life++;
    }

    /**
     * 显示命
     *
     * @return 命
     */
    public int showLife() {
        return life;
    }

    public void step() {
    }

    public BufferedImage getImage() {
        if (isUp()) {
            return ImageCache.heroSUp;
        } else if (isDown()) {
            return ImageCache.heroSDown;
        } else if (isLeft()) {
            return ImageCache.heroSLeft;
        } else {
            return ImageCache.heroSRight;
        }
    }
}
