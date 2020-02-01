package com.iflytek.mytank.entity_old;

import com.iflytek.mytank.loader.ImageCache;

import java.awt.image.BufferedImage;


/**
 * 钢板地形，只可被超级子弹消灭
 *
 * @author 445951954@qq.com
 */
public class Steel extends StaticElement {
    public Steel(int index) {
        super(60, 60);
        this.x = (index % 13) * 60;
        this.y = (index / 13) * 60;
        block[index % 13][index / 13] = true;
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
            block[x / 60][y / 60] = false;
        }
    }
}
