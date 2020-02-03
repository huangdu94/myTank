package com.iflytek.mytank.action;

import com.iflytek.mytank.constant.GameConstant;
import com.iflytek.mytank.element.Hero;
import com.iflytek.mytank.loader.GameMapLoader;
import com.iflytek.mytank.loader.PropertiesLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 用于加载世界窗口
 *
 * @author 445951954@qq.com
 */
public class GameFrame extends JPanel {
    public static ElementPool elementPool = ElementPool.getCurrentMap();

    /**
     * 加载窗口模块
     */
    private static JFrame frame = new JFrame(PropertiesLoader.getProperties(PropertiesLoader.Key.GAME_NAME));

    /**
     * 无参构造用于加载图片
     */
    public GameFrame() {
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
        Hero hero = elementPool.getHero();
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (elementPool.getState() == GameConstant.GameState.RUNNING) {
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
                    if (elementPool.getState() == GameConstant.GameState.START) {
                        elementPool.setState(GameConstant.GameState.RUNNING);
                    }
                    if (elementPool.getState() == GameConstant.GameState.PAUSE) {
                        elementPool.setState(GameConstant.GameState.RUNNING);
                    }
                    if (elementPool.getState() == GameConstant.GameState.GAMEOVER) {
                        //CurrentMap.clearAll();
                        elementPool.setState(GameConstant.GameState.RUNNING);
                    }
                    if (elementPool.getState() == GameConstant.GameState.SETMAP) {
                        elementPool.setState(GameConstant.GameState.RUNNING);
                    }
                }
                if (keyCode == KeyEvent.VK_2) {
                    if (elementPool.getState() == GameConstant.GameState.RUNNING) {
                        elementPool.setState(GameConstant.GameState.PAUSE);
                    }
                }
            }
        });
    }

    public void paint(Graphics g) {
        elementPool.paint(g);
    }
}