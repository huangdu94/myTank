package com.iflytek.mytank.loader;

import com.iflytek.mytank.entity.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片加载类
 * 加载了游戏里所有的图片
 *
 * @author DuHuang 2019/9/21 0:20
 */
public class ImageLoader {
    private static ImageLoader imageLoader = new ImageLoader();
    private Map<String, BufferedImage> pictureMap = new HashMap<>();

    private ImageLoader() {
        loadAll();
    }

    /**
     * 加载所有图片
     */
    private void loadAll() {
        String path = PropertiesLoader.getProperties(PropertiesLoader.Key.IMAGE_PATH);
        URL url = ImageLoader.class.getResource(path);
        File file = new File(url.getPath());
        File[] images = file.listFiles();
        try {
            for (File f : images) {
                if (f.getName().endsWith(PropertiesLoader.getProperties(PropertiesLoader.Key.IMAGE_FORMAT))) {
                    BufferedImage img = ImageIO.read(Image.class.getResource(path + File.separator + f.getName()));
                    pictureMap.put(f.getName(), img);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得图片
     */
    public static BufferedImage getImageByName(String key) {
        return imageLoader.pictureMap.get(key);
    }
}
