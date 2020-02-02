package com.iflytek.mytank.element;

import com.iflytek.mytank.action.CurrentMap;
import com.iflytek.mytank.loader.ImageCache;
import com.iflytek.mytank.loader.PropertiesLoader;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 英雄tank
 */
public class Hero extends Tank {
    private int life;

    public Hero() {
        super(PropertiesLoader.Key.TANK_WIDTH, PropertiesLoader.Key.TANK_HEIGHT, Integer.parseInt(PropertiesLoader.getProperties(PropertiesLoader.Key.TANK_STEP)));
        resetLocation();
        life = 3;
    }

    /**
     * 用于英雄坦克复活时重置位置
     */
    public void resetLocation() {
        x = 160;
        y = 480;
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

    /**
     * 英雄坦克射击
     */
    public void heroShoot() {
        List<Bullet> bulletList = CurrentMap.getCurrentMap().getHeroBullets();
        // 正常火力只能射一颗子弹 中等和超级火力射两颗
        if (this.isNormalFire()) {
            if (bulletList.size() == 0) {
                bulletList.add(super.shoot());
            }
        } else {
            if (bulletList.size() < 2) {
                bulletList.add(super.shoot());
            }
        }
    }
}