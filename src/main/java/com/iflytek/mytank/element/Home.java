package com.iflytek.mytank.element;

import com.iflytek.mytank.entity_old.Bullet;
import com.iflytek.mytank.loader.ImageCache;
import com.iflytek.mytank.loader.PropertiesLoader;

import java.awt.image.BufferedImage;


/**
 * 家，与子弹发生碰撞则游戏结束
 *
 * @author 445951954@qq.com
 */
public class Home extends StaticElement {
    public Home(int x, int y) {
        super(PropertiesLoader.Key.HOME_WIDTH, PropertiesLoader.Key.HOME_HEIGHT);
        this.x = x;
        this.y = y;
        noPassList.add(this);
    }

    public BufferedImage getImage() {
        return ImageCache.home;
    }

    public void dealHit(Bullet b) {
    }
}
