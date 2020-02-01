package com.iflytek.mytank.element;

import com.iflytek.mytank.entity_old.Bullet;
import com.iflytek.mytank.loader.ImageCache;
import com.iflytek.mytank.loader.PropertiesLoader;

import java.awt.image.BufferedImage;


/**
 * 河地形，不可被消灭
 *
 * @author 445951954@qq.com
 */
public class River extends StaticElement {
    public River(int x, int y) {
        super(PropertiesLoader.Key.RIVER_WIDTH, PropertiesLoader.Key.RIVER_HEIGHT);
        this.x = x;
        this.y = y;
        noPassList.add(this);
    }

    private int liveIndex = 0;

    public BufferedImage getImage() {
        return ImageCache.river[liveIndex++ / 10 % 2];// /10用于增加河流动画时间
    }

    public void dealHit(Bullet b) {
    }
}
