package com.iflytek.mytank.entity;

import com.iflytek.mytank.constant.GameConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

/**
 * 用于加载世界窗口
 * 封装了世界需要处理的一些过程，以及世界里的所有对象
 *
 * @author Bean
 */
public class World extends JPanel {
    private static final long serialVersionUID = 1L;

    public static StaticElement[] staticElements = new StaticElement[13 * 13];
    private Hero hero;
    private Tank[] enemies = {};
    private Bullet[] enemyBullets = {};
    private Bullet[] heroBullets = {};

    /**
     * 无参构造用于加载图片
     */
    public World() {
        new GameMap(1);//测试代码调用第一关地图
        hero = new Hero();
        loadFrame(this);
    }

    /**
     * 世界的宽和高
     */
    public static final int WIDTH = 780;
    public static final int HEIGHT = 780;
    /**
     * 加载窗口模块
     */
    private static JFrame frame = new JFrame("Battle City");

    public static void loadFrame(Component c) {
        frame.add(c);
        frame.setSize(WIDTH + 17, HEIGHT + 40);//窗口大小修正，解决地图不完全显示问题
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * 描述游戏的状态
     */
    private int state = GameConstant.state.START;

    public int getState() {
        return state;
    }

    /**
     * 游戏控制模块
     */
    private int setMapIndex = 0;

    public void controlGame() {
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (state == GameConstant.state.RUNNING) {
                    switch (keyCode) {
                        case KeyEvent.VK_UP:
                            hero.goUp();
                            break;
                        case KeyEvent.VK_DOWN:
                            hero.goDown();
                            break;
                        case KeyEvent.VK_LEFT:
                            hero.goLeft();
                            break;
                        case KeyEvent.VK_RIGHT:
                            hero.goRight();
                            break;
                        case KeyEvent.VK_SPACE:
                            isShoot = true;
                            break;
                    }
                }
                if (state == GameConstant.state.SETMAP) {
                    switch (keyCode) {
                        case KeyEvent.VK_UP:
                            if (hero.y >= 60) {
                                hero.y -= 60;
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (hero.y <= 660) {
                                hero.y += 60;
                            }
                            break;
                        case KeyEvent.VK_LEFT:
                            if (hero.x >= 60) {
                                hero.x -= 60;
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (hero.x <= 660) {
                                hero.x += 60;
                            }
                            break;
                        case KeyEvent.VK_SPACE:
                            if (setMapIndex % 4 == 0) {
                                GameMap.setWall(hero.x, hero.y);
                                setMapIndex++;
                            } else if (setMapIndex % 4 == 1) {
                                GameMap.setSteel(hero.x, hero.y);
                                setMapIndex++;
                            } else if (setMapIndex % 4 == 2) {
                                GameMap.setRiver(hero.x, hero.y);
                                setMapIndex++;
                            } else {
                                GameMap.setNullThing(hero.x, hero.y);
                                setMapIndex++;
                            }
                            break;
                    }
                }
                if (keyCode == KeyEvent.VK_1) {
                    if (state == GameConstant.state.START) {
                        state = GameConstant.state.RUNNING;
                    }
                    if (state == GameConstant.state.PAUSE) {
                        state = GameConstant.state.RUNNING;
                    }
                    if (state == GameConstant.state.GAMEOVER) {
                        new GameMap(1);
                        hero = new Hero();
                        enemies = new Tank[0];
                        heroBullets = new Bullet[0];
                        enemyBullets = new Bullet[0];
                        score = 0;
                        state = GameConstant.state.RUNNING;
                    }
                    if (state == GameConstant.state.SETMAP) {
                        state = GameConstant.state.RUNNING;
                    }
                }
                if (keyCode == KeyEvent.VK_2) {
                    if (state == GameConstant.state.RUNNING) {
                        state = GameConstant.state.PAUSE;
                    }
                }
                if (keyCode == KeyEvent.VK_3) {
                    if (state == GameConstant.state.START) {
                        new GameMap(0);
                        state = GameConstant.state.SETMAP;
                    }
                }
            }
        });
    }

    /**
     * 生成下一辆敌人坦克
     *
     * @return 一辆坦克
     */
    public Tank nextOne() {
        return new AEnemy();
    }

    /**
     * 敌人坦克以及子弹入场
     */
    private int shootF = 0; //设置敌人子弹射击频率
    private boolean isShoot = false;

    public void EnterAction() {
        if (enemies.length < 3) {//保持地图上有三辆敌人坦克
            enemies = Arrays.copyOf(enemies, enemies.length + 1);
            enemies[enemies.length - 1] = nextOne();
        }
        if (isShoot) {
            heroBullets = Arrays.copyOf(heroBullets, heroBullets.length + 1);
            heroBullets[heroBullets.length - 1] = hero.shoot();
            isShoot = false;
        }
        if (shootF++ % 20 == 0) {
            enemyBullets = Arrays.copyOf(enemyBullets, enemyBullets.length + 1);
            int i = (int) (Math.random() * enemies.length);
            enemyBullets[enemyBullets.length - 1] = enemies[i].shoot();
        }
    }

    /**
     * 子弹越界处理
     */
    public void outOfBoundAction() {
        for (int i = 0; i < heroBullets.length; i++) {
            Bullet b = heroBullets[i];
            if (b.outOfBounds() & !b.isRemove()) {
                b.turnDead();
            }
        }
        for (int i = 0; i < enemyBullets.length; i++) {
            Bullet b = enemyBullets[i];
            if (b.outOfBounds() & !b.isRemove()) {
                b.turnDead();
            }
        }
    }

    /**
     * 子弹射到东西处理
     */
    public void hitAction() {
        for (int i = 0; i < heroBullets.length; i++) {
            Bullet b = heroBullets[i];
            for (int j = 0; j < staticElements.length; j++) {
                StaticElement s = staticElements[j];
                if (s.hit(b) && s.isLive() && b.isLive()) {
                    if (s instanceof Home) {
                        state = GameConstant.state.GAMEOVER;
                    }
                    b.turnDead();
                    s.dealHit(b);
                }
            }
            for (int j = 0; j < enemies.length; j++) {
                Tank t = enemies[j];
                if (t.hit(b) && t.isLive() && b.isLive()) {
                    b.turnDead();
                    t.turnDead();
                }
            }
        }
        for (int i = 0; i < enemyBullets.length; i++) {
            Bullet b = enemyBullets[i];
            for (int j = 0; j < staticElements.length; j++) {
                StaticElement s = staticElements[j];
                if (s.hit(b) && s.isLive() && b.isLive()) {
                    if (s instanceof Home) {
                        state = GameConstant.state.GAMEOVER;
                    }
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
                    hero.x = 240;
                    hero.y = 720;
                }
                if (hero.showLife() <= 0) {
                    state = GameConstant.state.GAMEOVER;
                }
            }
        }
    }

    /**
     * 清除REMOVE状态的对象
     */
    public void clearRemove() {
        int index = 0;
        Bullet[] heroBulletLives = new Bullet[heroBullets.length];
        for (int i = 0; i < heroBullets.length; i++) {
            Bullet b = heroBullets[i];
            if (!b.isRemove()) {
                heroBulletLives[index++] = b;
            }
        }
        heroBullets = Arrays.copyOf(heroBulletLives, index);

        index = 0;
        Bullet[] enemyBulletLives = new Bullet[enemyBullets.length];
        for (int i = 0; i < enemyBullets.length; i++) {
            Bullet b = enemyBullets[i];
            if (!b.isRemove()) {
                enemyBulletLives[index++] = b;
            }
        }
        enemyBullets = Arrays.copyOf(enemyBulletLives, index);

        index = 0;
        Tank[] enemyLives = new Tank[enemies.length];
        for (int i = 0; i < enemies.length; i++) {
            Tank m = enemies[i];
            if (!m.isRemove()) {
                enemyLives[index++] = m;
            }
        }
        enemies = Arrays.copyOf(enemyLives, index);

        for (int i = 0; i < staticElements.length; i++) {
            StaticElement s = staticElements[i];
            if (s.isRemove()) {
                s = new NullThing();
            }
        }
    }

    /**
     * 子弹和敌人坦克的移动
     */
    public void stepAction() {
        for (int i = 0; i < heroBullets.length; i++) {
            heroBullets[i].step();
        }
        for (int i = 0; i < enemyBullets.length; i++) {
            enemyBullets[i].step();
        }
        for (int i = 0; i < enemies.length; i++) {
            enemies[i].step();
        }
    }

    /**
     * 画世界上的对象
     */
    private int score = 0;

    public void paint(Graphics g) {
        if (state == GameConstant.state.RUNNING) {
            g.drawImage(Image.background, 0, 0, null);
            hero.paint(g);
            for (int i = 0; i < heroBullets.length; i++) {
                heroBullets[i].paint(g);
            }
            for (int i = 0; i < enemyBullets.length; i++) {
                enemyBullets[i].paint(g);
            }
            for (int i = 0; i < enemies.length; i++) {
                enemies[i].paint(g);
            }
            for (int i = 0; i < staticElements.length; i++) {
                staticElements[i].paint(g);
            }
            g.drawString("分数：" + score, 360, 735);
            g.drawString("生命：" + hero.showLife(), 360, 755);
        }
        if (state == GameConstant.state.SETMAP) {
            g.drawImage(Image.background, 0, 0, null);
            hero.paint(g);
            for (int i = 0; i < staticElements.length; i++) {
                staticElements[i].paint(g);
            }
        }
        switch (state) {
            case GameConstant.state.START:
                g.drawImage(Image.start, 0, 0, null);
                break;
            case GameConstant.state.PAUSE:
                g.drawImage(Image.pause, 0, 0, null);
                break;
            case GameConstant.state.GAMEOVER:
                g.drawImage(Image.gameover, 0, 0, null);
                break;
        }
    }
}