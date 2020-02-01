package com.iflytek.mytank.constant;

/**
 * 保存游戏定义的一些常数
 * 2019/9/21 9:49
 *
 * @author 445951954@qq.com
 */
public interface GameConstant {
    /**
     * 游戏属性文件路径
     */
    String PROPERTIES_PATH = "/mytank.properties";

    /**
     * 游戏状态
     */
    interface GameState {
        int START = 0;
        int RUNNING = 1;
        int PAUSE = 2;
        int GAMEOVER = 3;
        int SETMAP = 4;
    }

    /**
     * 描述生存状态的常量,活，死，可以移出
     */
    interface ElementState {
        int LIVE = 0;
        int DEAD = 1;
        int REMOVE = 2;
    }
}
