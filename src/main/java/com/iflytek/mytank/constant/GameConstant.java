package com.iflytek.mytank.constant;

/**
 * 保存游戏定义的一些常数
 * 2019/9/21 9:49
 *
 * @author DuHuang
 */
public interface GameConstant {
    /**
     * 游戏状态
     */
    interface state {
        int START = 0;
        int RUNNING = 1;
        int PAUSE = 2;
        int GAMEOVER = 3;
        int SETMAP = 4;
    }
}
