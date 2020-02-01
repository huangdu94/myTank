package com.iflytek.mytank.element;

import com.iflytek.mytank.entity_old.Bullet;
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

    private int deadIndex = 0;

    public BufferedImage getImage() {
        if (isLive()) {
            return ImageCache.wall;
        } else if (isDead()) {
            BufferedImage img = ImageCache.dead[deadIndex++ / 5];// /5用于增加爆炸动画时间
            if (deadIndex / 5 >= ImageCache.dead.length) {
                turnRemove();
            }
            return img;
        }
        return null;
    }

    public void dealHit(Bullet b) {
        turnDead();
        noPassList.remove(this);
    }
}
