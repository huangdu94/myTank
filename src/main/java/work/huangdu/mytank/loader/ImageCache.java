package work.huangdu.mytank.loader;

import java.awt.image.BufferedImage;

/**
 * 图片分配类
 *
 * @author 445951954@qq.com
 */
public class ImageCache {
    //背景图片
    public static BufferedImage background;
    //游戏的三种状态图片
    public static BufferedImage start;
    public static BufferedImage pause;
    public static BufferedImage gameover;
    //英雄坦克图片
    public static BufferedImage heroSUp;
    public static BufferedImage heroSDown;
    public static BufferedImage heroSLeft;
    public static BufferedImage heroSRight;
    //敌人坦克图片
    public static BufferedImage aEnemyUp;
    public static BufferedImage aEnemyDown;
    public static BufferedImage aEnemyLeft;
    public static BufferedImage aEnemyRight;
    //子弹图片
    public static BufferedImage wBulletAcross;
    public static BufferedImage wBulletVertical;
    public static BufferedImage rBulletAcross;
    public static BufferedImage rBulletVertical;
    public static BufferedImage[] bulletBang;
    //家图片
    public static BufferedImage home;
    //地形图片
    public static BufferedImage[] river;
    public static BufferedImage steel;
    public static BufferedImage wall;
    public static BufferedImage ice;
    public static BufferedImage grass;
    //元素死亡图片
    public static BufferedImage[] dead;

    static {
        //背景
        background = ImageLoader.getImageByName("background.png");
        //游戏三种状态
        start = ImageLoader.getImageByName("start.png");
        pause = ImageLoader.getImageByName("pause.png");
        gameover = ImageLoader.getImageByName("gameover.png");
        //英雄坦克
        heroSUp = ImageLoader.getImageByName("heroSUp.png");
        heroSDown = ImageLoader.getImageByName("heroSDown.png");
        heroSLeft = ImageLoader.getImageByName("heroSLeft.png");
        heroSRight = ImageLoader.getImageByName("heroSRight.png");
        //敌人坦克
        aEnemyUp = ImageLoader.getImageByName("AEnemyDown.png");
        aEnemyDown = ImageLoader.getImageByName("AEnemyDown.png");
        aEnemyLeft = ImageLoader.getImageByName("AEnemyLeft.png");
        aEnemyRight = ImageLoader.getImageByName("AEnemyRight.png");
        //子弹
        wBulletAcross = ImageLoader.getImageByName("wBulletAcross.png");
        wBulletVertical = ImageLoader.getImageByName("wBulletVertical.png");
        rBulletAcross = ImageLoader.getImageByName("rBulletAcross.png");
        rBulletVertical = ImageLoader.getImageByName("rBulletVertical.png");
        bulletBang = new BufferedImage[5];
        for (int i = 0; i < bulletBang.length; i++) {
            bulletBang[i] = ImageLoader.getImageByName("bulletBang" + i + ".png");
        }
        //家
        home = ImageLoader.getImageByName("home.png");
        //地形
        river = new BufferedImage[2];
        for (int i = 0; i < river.length; i++) {
            river[i] = ImageLoader.getImageByName("river" + i + ".png");
        }
        steel = ImageLoader.getImageByName("steel.png");
        wall = ImageLoader.getImageByName("wall.png");
        ice = ImageLoader.getImageByName("ice.png");
        grass = ImageLoader.getImageByName("grass.png");
        //元素死亡图片
        dead = new BufferedImage[5];
        for (int i = 0; i < dead.length; i++) {
            dead[i] = ImageLoader.getImageByName("dead" + i + ".png");
        }
    }
}