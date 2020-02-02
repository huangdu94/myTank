package com.iflytek.mytank.element;

import com.iflytek.mytank.loader.ImageCache;
import com.iflytek.mytank.loader.PropertiesLoader;

import java.awt.image.BufferedImage;


/**
 * 钢板地形，只可被超级子弹消灭
 *
 * @author 445951954@qq.com
 */
public class Steel extends StaticElement {
    public Steel(int x, int y) {
        super(PropertiesLoader.Key.STEEL_WIDTH, PropertiesLoader.Key.STEEL_HEIGHT);
        this.x = x;
        this.y = y;
        noPassList.add(this);
    }

    public BufferedImage getImage() {
        if (isLive()) {
            return ImageCache.steel;
        } else if (isDead()) {
            return null;
        }
        return null;
    }

    public void dealHit(Bullet b) {
        if (b.getStyle() == 1) {
            turnDead();
            noPassList.remove(this);
        }
    }
}
