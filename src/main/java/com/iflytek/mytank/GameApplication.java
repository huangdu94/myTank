package com.iflytek.mytank;

import com.iflytek.mytank.action.GameFrame;
import com.iflytek.mytank.constant.GameConstant;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 游戏入口
 */
public class GameApplication {
    public static void run() {
        GameFrame gameFrame = new GameFrame();
        gameFrame.controlGame();
        Timer timer = new Timer();
        int interval = 10;
        timer.schedule(new TimerTask() {
            public void run() {
                if (gameFrame.getState() == GameConstant.GameState.RUNNING) {
                    GameFrame.elementPool.EnterAction();
                    GameFrame.elementPool.stepAction();
                    GameFrame.elementPool.hitAction();
                    GameFrame.elementPool.outOfBoundAction();
                    GameFrame.elementPool.clearRemove();
                }
                gameFrame.repaint();
            }
        }, interval, interval);
    }

    public static void main(String[] args) {
        GameApplication.run();
    }
}