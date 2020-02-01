package com.iflytek.mytank.element;

import com.iflytek.mytank.entity_old.Bullet;
import com.iflytek.mytank.loader.ImageCache;
import com.iflytek.mytank.loader.PropertiesLoader;

import java.awt.image.BufferedImage;


/**
 * 雪地,坦克从上通过会加速
 *
 * @author duhuang@iflytek.com
 * @version 2020/2/1 11:55
 */
public class Ice extends StaticElement {
    public Ice(int x, int y) {
        super(PropertiesLoader.Key.HOME_WIDTH, PropertiesLoader.Key.HOME_HEIGHT);
        this.x = x;
        this.y = y;
        noPassList.add(this);
    }

    public BufferedImage getImage() {
        return ImageCache.ice;
    }

    public void dealHit(Bullet b) {
    }
}
