package com.iflytek.mytank.entity_old;

import java.awt.image.BufferedImage;

/**
 * 无地形
 *
 * @author 445951954@qq.com
 */
public class NullThing extends StaticElement {
    public BufferedImage getImage() {
        return null;
    }

    public void dealHit(Bullet b) {
    }
}
