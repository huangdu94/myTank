package work.huangdu.mytank.element;

import work.huangdu.mytank.loader.ImageCache;
import work.huangdu.mytank.loader.PropertiesLoader;

import java.awt.image.BufferedImage;


/**
 * 雪地,坦克从上通过会加速
 *
 * @author duhuang@iflytek.com
 * @version 2020/2/1 11:55
 */
public class Ice extends StaticElement {
    public Ice(int x, int y) {
        super(PropertiesLoader.Key.ICE_WIDTH, PropertiesLoader.Key.ICE_HEIGHT);
        this.x = x;
        this.y = y;
    }

    public BufferedImage getImage() {
        return ImageCache.ice;
    }

    public void dealHit(Bullet b) {
    }
}
