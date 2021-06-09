package work.huangdu.mytank.action;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import lombok.Data;
import work.huangdu.mytank.constant.GameConstant;
import work.huangdu.mytank.element.AEnemy;
import work.huangdu.mytank.element.Bullet;
import work.huangdu.mytank.element.Element;
import work.huangdu.mytank.element.Grass;
import work.huangdu.mytank.element.Hero;
import work.huangdu.mytank.element.Home;
import work.huangdu.mytank.element.Ice;
import work.huangdu.mytank.element.River;
import work.huangdu.mytank.element.StaticElement;
import work.huangdu.mytank.element.Steel;
import work.huangdu.mytank.element.Tank;
import work.huangdu.mytank.element.Wall;
import work.huangdu.mytank.loader.ImageCache;

/**
 * 封装现有地图里的所有物体
 *
 * @author duhuang@iflytek.com
 * @version 2020/2/2 11:52
 */
@Data
public class ElementPool {
    private static ElementPool instance;

    private ElementPool() {
        hero = new Hero();
    }

    /**
     * 清空地图,重新加载地图时使用
     */
    public static void clearAll() {
        instance = null;
    }

    public static ElementPool getCurrentMap() {
        if (instance == null) {
            synchronized (ElementPool.class) {
                if (instance == null) {
                    instance = new ElementPool();
                }
            }
        }
        return instance;
    }

    /**
     * 描述游戏的状态
     */
    private int state = GameConstant.GameState.START;
    private int level;
    private Home home;
    private List<Wall> wallList = new LinkedList<>();
    private List<Steel> steelList = new LinkedList<>();
    private List<River> riverList = new LinkedList<>();
    private List<Grass> grassList = new LinkedList<>();
    private List<Ice> iceList = new LinkedList<>();

    private Hero hero;
    private List<Bullet> heroBullets = new LinkedList<>();

    private List<Tank> enemies = new LinkedList<>();
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
                state = GameConstant.GameState.GAMEOVER;
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
            for (Tank enemy : enemies) {
                if (enemy.hit(b) && enemy.isLive() && b.isLive()) {
                    b.turnDead();
                    enemy.turnDead();
                }
            }
            for (Bullet eb : enemyBullets) {
                if (eb.hit(b) && eb.isLive() && b.isLive()) {
                    b.turnDead();
                    eb.turnDead();
                }
            }
        }
        for (Bullet b : enemyBullets) {
            if (home.hit(b) && home.isLive() && b.isLive()) {
                state = GameConstant.GameState.GAMEOVER;
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
                System.out.println(hero.showLife());
                if (hero.showLife() <= 0) {
                    state = GameConstant.GameState.GAMEOVER;
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

        enemies.removeIf(Element::isRemove);
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
        for (Tank t : enemies) {
            t.step();
        }
    }

    /**
     * 敌人坦克以及子弹入场
     */
    private int shootF = 0; //设置敌人子弹射击频率

    public void EnterAction() {
        if (enemies.size() < 3) {//保持地图上有三辆敌人坦克
            enemies.add(new AEnemy());
        }
        if (shootF++ % 20 == 0) {
            int i = (int) (Math.random() * enemies.size());
            enemyBullets.add(enemies.get(i).shoot());
        }
    }

    public void paint(Graphics g) {
        if (state == GameConstant.GameState.RUNNING) {
            g.drawImage(ImageCache.background, 0, 0, null);
            for (StaticElement element : iceList) {
                element.paint(g);
            }
            hero.paint(g);
            for (Tank tank : enemies) {
                tank.paint(g);
            }
            home.paint(g);
            for (StaticElement element : wallList) {
                element.paint(g);
            }
            for (StaticElement element : steelList) {
                element.paint(g);
            }
            for (StaticElement element : riverList) {
                element.paint(g);
            }
            for (Bullet b : heroBullets) {
                b.paint(g);
            }
            for (Bullet b : enemyBullets) {
                b.paint(g);
            }
            for (StaticElement element : grassList) {
                element.paint(g);
            }
        }
        switch (state) {
            case GameConstant.GameState.START:
                g.drawImage(ImageCache.start, 0, 0, null);
                break;
            case GameConstant.GameState.PAUSE:
                g.drawImage(ImageCache.pause, 0, 0, null);
                break;
            case GameConstant.GameState.GAMEOVER:
                g.drawImage(ImageCache.gameover, 0, 0, null);
                break;
        }
    }
}
