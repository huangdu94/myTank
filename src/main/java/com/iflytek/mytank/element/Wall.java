package com.iflytek.mytank.element;


import com.iflytek.mytank.loader.ImageCache;
import com.iflytek.mytank.loader.PropertiesLoader;

import java.awt.image.BufferedImage;


/**
 * 墙地形，可被普通子弹和超级子弹消灭
 *
 * @author 445951954@qq.com
 */
public class Wall extends StaticElement {
    public Wall(int x, int y) {
        super(PropertiesLoader.Key.WALL_WIDTH, PropertiesLoader.Key.WALL_HEIGHT);
        this.x = x;
        this.y = y;
        noPassList.add(this);
    }

    public BufferedImage getImage() {
        if (isLive()) {
            return ImageCache.wall;
        } else if (isDead()) {
            return null;
        }
        return null;
    }

    public void dealHit(Bullet b) {
        turnDead();
        noPassList.remove(this);
    }
}
