package work.huangdu.mytank.element;

import work.huangdu.mytank.loader.ImageCache;
import work.huangdu.mytank.loader.PropertiesLoader;

import java.awt.image.BufferedImage;


/**
 * 家，与子弹发生碰撞则游戏结束
 *
 * @author 445951954@qq.com
 */
public class Home extends StaticElement {
    public Home(int x, int y) {
        super(PropertiesLoader.Key.HOME_WIDTH, PropertiesLoader.Key.HOME_HEIGHT);
        this.x = x;
        this.y = y;
        noPassList.add(this);
    }

    private int deadIndex = 0;

    public BufferedImage getImage() {
        if (isLive()) {
            return ImageCache.home;
        } else if (isDead()) {
            BufferedImage img = ImageCache.dead[deadIndex++ / 5];// /5用于增加爆炸动画时间
            if (deadIndex / 5 >= ImageCache.dead.length) {
                turnRemove();
            }
            return img;
        }
        return null;
    }

    public void dealHit(Bullet b) {
        turnDead();
        noPassList.remove(this);
    }
}
