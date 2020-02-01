package com.iflytek.mytank.action;

import com.iflytek.mytank.constant.GameConstant;
import com.iflytek.mytank.element.StaticElement;
import com.iflytek.mytank.loader.GameMapLoader;
import com.iflytek.mytank.loader.ImageCache;
import com.iflytek.mytank.loader.PropertiesLoader;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
    private int state = GameConstant.GameState.START;
    /**
     * 加载窗口模块
     */
    private static JFrame frame = new JFrame(PropertiesLoader.getProperties(PropertiesLoader.Key.GAME_NAME));

    /**
     * 无参构造用于加载图片
     */
    public World() {
        //new GameMap(3);//测试代码调用第一关地图
        loadFrame(this);
    }

    public static void loadFrame(Component c) {
        int wigth = Integer.parseInt(PropertiesLoader.getProperties(PropertiesLoader.Key.FRAME_WIGHT));
        int height = Integer.parseInt(PropertiesLoader.getProperties(PropertiesLoader.Key.FRAME_HEIGHT));
        frame.add(c);
        frame.setSize(wigth, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public int getState() {
        return state;
    }

    public void paint(Graphics g) {
        List<StaticElement> staticElements = GameMapLoader.getStaticElements("1");
        if (state == GameConstant.GameState.RUNNING) {
            g.drawImage(ImageCache.background, 0, 0, null);
//            hero.paint(g);
//            for (int i = 0; i < heroBullets.length; i++) {
//                heroBullets[i].paint(g);
//            }
//            for (int i = 0; i < enemyBullets.length; i++) {
//                enemyBullets[i].paint(g);
//            }
//            for (int i = 0; i < enemies.length; i++) {
//                enemies[i].paint(g);
//            }
            for (StaticElement element : staticElements) {
                element.paint(g);
            }
//            g.drawString("分数：" + score, 360, 735);
//            g.drawString("生命：" + hero.showLife(), 360, 755);
        }
//        if (state == GameConstant.GameState.SETMAP) {
//            g.drawImage(com.iflytek.mytank.element.ImageCache.background, 0, 0, null);
//            hero.paint(g);
//            for (int i = 0; i < staticElements.length; i++) {
//                staticElements[i].paint(g);
//            }
//        }
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