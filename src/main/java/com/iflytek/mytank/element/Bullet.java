package com.iflytek.mytank.element;


import com.iflytek.mytank.action.World;
import com.iflytek.mytank.loader.ImageCache;
import com.iflytek.mytank.loader.PropertiesLoader;

import java.awt.image.BufferedImage;

/**
 * 子弹类
 *
 * @author 445951954@qq.com
 */
public class Bullet extends MovingElement {
    /**
     * 0 普通子弹 1超级子弹
     */
    private int style = 0;

    /**
     * 有参构造
     *
     * @param directionState 方向
     */
    public Bullet(int x, int y, int directionState) {
        super(PropertiesLoader.Key.BULLET_WIDTH, PropertiesLoader.Key.BULLET_HEIGHT, Integer.parseInt(PropertiesLoader.getProperties(PropertiesLoader.Key.BULLET_STEP)));
        this.x = x;
        this.y = y;
        this.directionState = directionState;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    /**
     * 子弹移动
     */
    public void step() {
        if (isLive()) {
            if (isUp()) {
                y -= step;
            } else if (isDown()) {
                y += step;
            } else if (isLeft()) {
                x -= step;
            } else {
                x += step;
            }
        }
    }

    /**
     * 判断子弹是否越界
     *
     * @return true表示子弹越界
     */
    public boolean outOfBounds() {
        int x1 = 0;
        int x2 = World.GAME_WIDTH - this.height;
        int y1 = 0;
        int y2 = World.GAME_WIDTH - this.height;
        return x <= x1 || x >= x2 || y <= y1 || y >= y2;
    }

    private int deadIndex = 0;

    public BufferedImage getImage() {
        if (style == 0) {
            if (isLive()) {
                if (isUp() || isDown()) {
                    return ImageCache.wBulletVertical;
                } else {
                    return ImageCache.wBulletAcross;
                }
            } else if (isDead()) {
                BufferedImage img = ImageCache.bulletBang[deadIndex++ / 5];// /5用于增加爆炸动画时间
                if (deadIndex / 5 >= ImageCache.bulletBang.length) {
                    turnRemove();
                }
                return img;
            }
        } else {
            if (isLive()) {
                if (isUp() || isDown()) {
                    return ImageCache.rBulletVertical;
                } else {
                    return ImageCache.rBulletAcross;
                }
            } else if (isDead()) {
                BufferedImage img = ImageCache.bulletBang[deadIndex++ / 5];// /5用于增加爆炸动画时间
                if (deadIndex / 5 >= ImageCache.bulletBang.length) {
                    turnRemove();
                }
                return img;
            }
        }
        return null;
    }
}
