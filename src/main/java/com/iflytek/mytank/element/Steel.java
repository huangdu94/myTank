package com.iflytek.mytank.element;

import com.iflytek.mytank.entity_old.Bullet;
import com.iflytek.mytank.entity_old.SuperBullet;
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

    private int deadIndex = 0;

    public BufferedImage getImage() {
        if (isLive()) {
            return ImageCache.steel;
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
        if (b instanceof SuperBullet) {
            turnDead();
            noPassList.remove(this);
        }
    }
}
