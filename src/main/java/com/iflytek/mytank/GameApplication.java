package com.iflytek.mytank;

import com.iflytek.mytank.constant.GameConstant;
import com.iflytek.mytank.entity.World;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 游戏入口
 */
public class GameApplication {
    public static void run() {
        World world = new World();
        world.controlGame();
        Timer timer = new Timer();
        int interval = 10;
        timer.schedule(new TimerTask() {
            public void run() {
                if (world.getState() == GameConstant.state.RUNNING) {
                    world.EnterAction();
                    world.stepAction();
                    world.hitAction();
                    world.outOfBoundAction();
                    world.clearRemove();
                }
                world.repaint();
            }
        }, interval, interval);
    }

    public static void main(String[] args) {
        GameApplication.run();
    }
}