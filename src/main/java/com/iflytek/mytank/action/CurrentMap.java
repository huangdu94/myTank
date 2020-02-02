package com.iflytek.mytank.action;

import com.iflytek.mytank.constant.GameConstant;
import com.iflytek.mytank.element.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 封装现有地图里的所有物体
 *
 * @author duhuang@iflytek.com
 * @version 2020/2/2 11:52
 */
@Data
public class CurrentMap {
    private static CurrentMap instance;

    private CurrentMap() {
        hero = new Hero();
    }

    /**
     * 清空地图,重新加载地图时使用
     */
    public static void clearAll() {
        instance = null;
    }

    public static CurrentMap getCurrentMap() {
        if (instance == null) {
            synchronized (CurrentMap.class) {
                if (instance == null) {
                    instance = new CurrentMap();
                }
            }
        }
        return instance;
    }

    private int level;
    private Home home;
    private List<Wall> wallList = new LinkedList<>();
    private List<Steel> steelList = new LinkedList<>();
    private List<River> riverList = new LinkedList<>();
    private List<Grass> grassList = new LinkedList<>();
    private List<Ice> iceList = new LinkedList<>();

    private Hero hero;
    private List<Bullet> heroBullets = new LinkedList<>();

    private List<Bullet> enemyBullets = new LinkedList<>();

    /**
     * 子弹越界处理
     */
    public void outOfBoundAction() {
        for (Bullet b : heroBullets) {
            if (b.outOfBounds() & !b.isRemove()) {
                b.turnDead();
            }
        }
        for (Bullet b : enemyBullets) {
            if (b.outOfBounds() & !b.isRemove()) {
                b.turnDead();
            }
        }
    }

    /**
     * 子弹射到东西处理
     */
    public void hitAction() {
        for (Bullet b : heroBullets) {
            if (home.hit(b) && home.isLive() && b.isLive()) {
                World.setState(GameConstant.GameState.GAMEOVER);
            }
            for (StaticElement s : wallList) {
                if (s.hit(b) && s.isLive() && b.isLive()) {
                    b.turnDead();
                    s.dealHit(b);
                }
            }
            for (StaticElement s : steelList) {
                if (s.hit(b) && s.isLive() && b.isLive()) {
                    b.turnDead();
                    s.dealHit(b);
                }
            }
        }
        for (Bullet b : enemyBullets) {
            if (home.hit(b) && home.isLive() && b.isLive()) {
                World.setState(GameConstant.GameState.GAMEOVER);
            }
            for (StaticElement s : wallList) {
                if (s.hit(b) && s.isLive() && b.isLive()) {
                    b.turnDead();
                    s.dealHit(b);
                }
            }
            for (StaticElement s : steelList) {
                if (s.hit(b) && s.isLive() && b.isLive()) {
                    b.turnDead();
                    s.dealHit(b);
                }
            }
            if (hero.hit(b) && b.isLive()) {
                b.turnDead();
                if (hero.isSuperFire()) {
                    hero.turnNormalFire();
                } else {
                    hero.subtractLife();
                    hero.resetLocation();
                }
                if (hero.showLife() <= 0) {
                    World.setState(GameConstant.GameState.GAMEOVER);
                }
            }
        }
    }

    /**
     * 清除REMOVE状态的对象
     */
    public void clearRemove() {
        heroBullets.removeIf(Element::isRemove);
        enemyBullets.removeIf(Element::isRemove);

        wallList.removeIf(Element::isRemove);
        steelList.removeIf(Element::isRemove);
    }

    /**
     * 子弹和敌人坦克的移动
     */
    public void stepAction() {
        for (Bullet b : heroBullets) {
            b.step();
        }
        for (Bullet b : enemyBullets) {
            b.step();
        }
    }
}
