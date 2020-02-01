package com.iflytek.mytank.element;

import com.iflytek.mytank.action.World;
import com.iflytek.mytank.constant.GameConstant;
import com.iflytek.mytank.loader.PropertiesLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

/**
 * 元素类，游戏中所有元素的父类
 *
 * @author 445951954@qq.com
 */
public abstract class Element {
    // 元素宽和高
    protected int width;
    protected int height;
    // 元素位置
    protected int x;
    protected int y;
    // 元素状态，默认为活
    protected int existState = GameConstant.ElementState.LIVE;
    // 不可通过Element列表(允许重叠的物体例如Grass,Ice不用标记)
    protected static List<Element> noPassList = new LinkedList<>();

    /**
     * 是否可通过
     *
     * @param element 待检测物体
     * @return true可通过
     */
    public boolean canPass(Element element) {
        int x = element.x;
        int y = element.y;
        if (x < 0 || x > World.gameWidth - element.width || y < 0 || y > World.gameHeight - element.height) {
            return false;
        }
        for (Element other : noPassList) {
            if (other.hit(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 有参构造
     *
     * @param width  元素的宽属性名
     * @param height 元素的高属性名
     */
    protected Element(String width, String height) {
        this.width = Integer.parseInt(PropertiesLoader.getProperties(width));
        this.height = Integer.parseInt(PropertiesLoader.getProperties(height));
    }

    //判断元素是否活着
    public boolean isLive() {
        return existState == GameConstant.ElementState.LIVE;
    }

    //判断元素是否死了
    public boolean isDead() {
        return existState == GameConstant.ElementState.DEAD;
    }

    //判断元素是否可以移除
    public boolean isRemove() {
        return existState == GameConstant.ElementState.REMOVE;
    }

    //让元素状态变成死
    public void turnDead() {
        existState = GameConstant.ElementState.DEAD;
    }

    //让元素状态变成可以移除
    public void turnRemove() {
        existState = GameConstant.ElementState.REMOVE;
    }

    /**
     * 将getImage()方法返回的图片画出来
     *
     * @param g 画笔
     */
    public void paint(Graphics g) {
        g.drawImage(getImage(), x, y, null);
    }

    /**
     * 得到元素当前状态的图片
     *
     * @return 元素当前状态的图片
     */
    public abstract BufferedImage getImage();

    /**
     * 判断是否碰撞的方法
     *
     * @param other 其它元素
     * @return true表示碰撞发生
     */
    public boolean hit(Element other) {
        int x1 = this.x - other.width;
        int x2 = this.x + this.width;
        int y1 = this.y - other.height;
        int y2 = this.y + this.height;
        int x = other.x;
        int y = other.y;
        return x > x1 && x < x2 && y > y1 && y < y2;
    }
}
