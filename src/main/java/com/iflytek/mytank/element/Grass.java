package com.iflytek.mytank.element;

import com.iflytek.mytank.entity_old.Bullet;
import com.iflytek.mytank.loader.ImageCache;
import com.iflytek.mytank.loader.PropertiesLoader;

import java.awt.image.BufferedImage;


/**
 * 草地,坦克可从中通过,会被草地覆盖,草地半通明
 *
 * @author duhuang@iflytek.com
 * @version 2020/2/1 11:55
 */
public class Grass extends StaticElement {
    public Grass(int x, int y) {
        super(PropertiesLoader.Key.HOME_WIDTH, PropertiesLoader.Key.HOME_HEIGHT);
        this.x = x;
        this.y = y;
        noPassList.add(this);
    }

    public BufferedImage getImage() {
        return ImageCache.grass;
    }

    public void dealHit(Bullet b) {
    }
}
