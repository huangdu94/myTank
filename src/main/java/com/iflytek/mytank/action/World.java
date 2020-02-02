package com.iflytek.mytank.action;

import com.iflytek.mytank.constant.GameConstant;
import com.iflytek.mytank.element.Bullet;
import com.iflytek.mytank.element.Hero;
import com.iflytek.mytank.element.StaticElement;
import com.iflytek.mytank.element.Tank;
import com.iflytek.mytank.loader.GameMapLoader;
import com.iflytek.mytank.loader.ImageCache;
import com.iflytek.mytank.loader.PropertiesLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 用于加载世界窗口
 * 封装了世界需要处理的一些过程，以及世界里的所有对象
 *
 * @author 445951954@qq.com
 */
public class World extends JPanel {
    /**
     * 描述游戏的状态
     */
    private static int state = GameConstant.GameState.START;
    /**
     * 加载窗口模块
     */
    private static JFrame frame = new JFrame(PropertiesLoader.getProperties(PropertiesLoader.Key.GAME_NAME));

    /**
     * 无参构造用于加载图片
     */
    public World() {
        GameMapLoader.loadCurrentMap(1);
        loadFrame(this);
    }

    public static final int GAME_WIDTH = Integer.parseInt(PropertiesLoader.getProperties(PropertiesLoader.Key.FRAME_WIGHT));
    public static final int GAME_HEIGHT = Integer.parseInt(PropertiesLoader.getProperties(PropertiesLoader.Key.FRAME_HEIGHT));

    public static void loadFrame(Component c) {
        frame.add(c);
        frame.setSize(GAME_WIDTH + 17, GAME_HEIGHT + 40);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * 游戏控制模块
     */
    public void controlGame() {
        Hero hero = CurrentMap.getCurrentMap().getHero();
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (state == GameConstant.GameState.RUNNING) {
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
                            hero.heroShoot();
                            break;
                    }
                }
                if (keyCode == KeyEvent.VK_1) {
                    if (state == GameConstant.GameState.START) {
                        state = GameConstant.GameState.RUNNING;
                    }
                    if (state == GameConstant.GameState.PAUSE) {
                        state = GameConstant.GameState.RUNNING;
                    }
                    if (state == GameConstant.GameState.GAMEOVER) {
                        //CurrentMap.clearAll();
                        state = GameConstant.GameState.RUNNING;
                    }
                    if (state == GameConstant.GameState.SETMAP) {
                        state = GameConstant.GameState.RUNNING;
                    }
                }
                if (keyCode == KeyEvent.VK_2) {
                    if (state == GameConstant.GameState.RUNNING) {
                        state = GameConstant.GameState.PAUSE;
                    }
                }
            }
        });
    }


    public static int getState() {
        return state;
    }

    public static void setState(int state) {
        World.state = state;
    }

    public void paint(Graphics g) {
        if (state == GameConstant.GameState.RUNNING) {
            g.drawImage(ImageCache.background, 0, 0, null);
            for (StaticElement element : CurrentMap.getCurrentMap().getIceList()) {
                element.paint(g);
            }
            CurrentMap.getCurrentMap().getHero().paint(g);
            for (Tank tank : CurrentMap.getCurrentMap().getEnemies()) {
                tank.paint(g);
            }
            CurrentMap.getCurrentMap().getHome().paint(g);
            for (StaticElement element : CurrentMap.getCurrentMap().getWallList()) {
                element.paint(g);
            }
            for (StaticElement element : CurrentMap.getCurrentMap().getSteelList()) {
                element.paint(g);
            }
            for (StaticElement element : CurrentMap.getCurrentMap().getRiverList()) {
                element.paint(g);
            }
            for (Bullet b : CurrentMap.getCurrentMap().getHeroBullets()) {
                b.paint(g);
            }
            for (Bullet b : CurrentMap.getCurrentMap().getEnemyBullets()) {
                b.paint(g);
            }
            for (StaticElement element : CurrentMap.getCurrentMap().getGrassList()) {
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