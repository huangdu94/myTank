package work.huangdu.mytank.element;

import work.huangdu.mytank.loader.ImageCache;
import work.huangdu.mytank.loader.PropertiesLoader;

import java.awt.image.BufferedImage;


/**
 * 草地,坦克可从中通过,会被草地覆盖,草地半通明
 *
 * @author duhuang@iflytek.com
 * @version 2020/2/1 11:55
 */
public class Grass extends StaticElement {
    public Grass(int x, int y) {
        super(PropertiesLoader.Key.GRASS_WIDTH, PropertiesLoader.Key.GRASS_HEIGHT);
        this.x = x;
        this.y = y;
    }

    public BufferedImage getImage() {
        return ImageCache.grass;
    }

    public void dealHit(Bullet b) {
    }
}
