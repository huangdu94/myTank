package work.huangdu.mytank.element;

import work.huangdu.mytank.loader.ImageCache;
import work.huangdu.mytank.loader.PropertiesLoader;
import work.huangdu.mytank.constant.GameConstant;

import java.awt.image.BufferedImage;

/**
 * 敌方tank实验版
 */
public class AEnemy extends Tank {
    private static int location = 0;//控制出生地点

    public AEnemy() {
        super(PropertiesLoader.Key.TANK_WIDTH, PropertiesLoader.Key.TANK_HEIGHT, 10);
        switch (location++ % 3) {
            case 0:
                x = 0;
                y = 0;
                break;
            case 1:
                x = 240;
                y = 0;
                break;
            case 2:
                x = 480;
                y = 0;
        }
    }

    /**
     * 敌人坦克AI模块
     */
    private int v = 0;//控制敌人坦克移动速度

    public void step() {
        if (v++ % 10 == 0) {
            int move = (int) (Math.random() * 4);
            switch (move) {
                case 0:
                    goUp();
                    break;
                case 1:
                    goDown();
                    break;
                case 2:
                    goLeft();
                    break;
                case 3:
                    goRight();
                    break;
            }
        }
    }

    private int deadIndex = 0;

    public BufferedImage getImage() {
        if (isLive()) {
            switch (directionState) {
                case GameConstant.DirectionState.UP:
                    return ImageCache.aEnemyUp;
                case GameConstant.DirectionState.DOWN:
                    return ImageCache.aEnemyDown;
                case GameConstant.DirectionState.LEFT:
                    return ImageCache.aEnemyLeft;
                case GameConstant.DirectionState.RIGHT:
                    return ImageCache.aEnemyRight;
                default:
                    return null;
            }
        } else if (isDead()) {
            BufferedImage img = ImageCache.dead[deadIndex++ / 5];// /5用于增加爆炸动画时间
            if (deadIndex / 5 >= ImageCache.dead.length) {
                turnRemove();
            }
            return img;
        }
        return null;
    }
}
