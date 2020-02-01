package com.iflytek.mytank.element;

import com.iflytek.mytank.constant.GameConstant;
import com.iflytek.mytank.entity_old.Bullet;
import com.iflytek.mytank.entity_old.NormalBullet;
import com.iflytek.mytank.entity_old.SuperBullet;

public abstract class Tank extends MovingElement {
    /**
     * 有参构造设置元素的宽和高
     *
     * @param width  元素的宽属性名
     * @param height 元素的高属性名
     * @param step   步长
     */
    protected Tank(String width, String height, int step) {
        super(width, height, step);
    }

    /**
     * 向上走
     */
    public void goUp() {
        turnUp();
        y -= step;
        if (!canPass(this)) {
            y += step;
        }
    }

    /**
     * 向下走
     */
    public void goDown() {
        turnDown();
        y += step;
        if (!canPass(this)) {
            y -= step;
        }
    }

    /**
     * 向左走
     */
    public void goLeft() {
        turnLeft();
        x -= step;
        if (!canPass(this)) {
            x += step;
        }
    }

    /**
     * 向右走
     */
    public void goRight() {
        turnRight();
        x += step;
        if (!canPass(this)) {
            x -= step;
        }
    }

    /**
     * 火力模式常量
     */
    private static final int NORMALFIRE = 0;
    private static final int SUPERFIRE = 1;
    private int fireState = NORMALFIRE;

    //判断坦克是否为正常火力
    public boolean isNormalFire() {
        return fireState == NORMALFIRE;
    }

    //判断坦克是否为超级火力
    public boolean isSuperFire() {
        return fireState == SUPERFIRE;
    }

    //设置坦克为正常火力
    public void turnNormalFire() {
        fireState = NORMALFIRE;
    }

    //设置坦克为超级火力
    public void turnSuperFire() {
        fireState = SUPERFIRE;
    }

    /**
     * 坦克射击
     *
     * @return 子弹
     */
    public Bullet shoot() {
        Bullet bs;
        int xStep = this.width / 2;
        int yStep = this.height / 2;
        int length = 10;
        if (isNormalFire()) {
            if (isUp()) {
                bs = new NormalBullet(this.x + xStep, this.y - length, GameConstant.DirectionState.UP);
                return bs;
            } else if (isDown()) {
                bs = new NormalBullet(this.x + xStep, this.y + this.height + length, GameConstant.DirectionState.DOWN);
                return bs;
            } else if (isLeft()) {
                bs = new NormalBullet(this.x - length, this.y + yStep, GameConstant.DirectionState.LEFT);
                return bs;
            } else {
                bs = new NormalBullet(this.x + this.width + length, this.y + yStep, GameConstant.DirectionState.RIGHT);
                return bs;
            }
        } else {
            if (isUp()) {
                bs = new SuperBullet(this.x + xStep, this.y - length, GameConstant.DirectionState.UP);
                return bs;
            } else if (isDown()) {
                bs = new SuperBullet(this.x + xStep, this.y + this.height + length, GameConstant.DirectionState.DOWN);
                return bs;
            } else if (isLeft()) {
                bs = new SuperBullet(this.x - length, this.y + yStep, GameConstant.DirectionState.LEFT);
                return bs;
            } else {
                bs = new SuperBullet(this.x + this.width + length, this.y + yStep, GameConstant.DirectionState.RIGHT);
                return bs;
            }
        }
    }
}
