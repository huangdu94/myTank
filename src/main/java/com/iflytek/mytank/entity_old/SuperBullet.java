package com.iflytek.mytank.entity_old;

import com.iflytek.mytank.loader.ImageCache;

import java.awt.image.BufferedImage;


/**
 * 超级子弹
 *
 * @author 445951954@qq.com
 */
public class SuperBullet extends Bullet {
    public SuperBullet(int x, int y, int directionState) {
        super(x, y, directionState);
    }

    private int deadIndex = 0;

    public BufferedImage getImage() {
        if (isLive()) {
            if (isUp() || isDown()) {
                return ImageCache.rBulletVertical;
            } else {
                return ImageCache.rBulletAcross;
            }
        } else if (isDead()) {
            BufferedImage img = ImageCache.bulletBang[deadIndex++ / 5];// /5用于增加爆炸动画时间
            if (deadIndex / 5 >= ImageCache.bulletBang.length) {
                existState = REMOVE;
            }
            return img;
        }
        return null;
    }
}
