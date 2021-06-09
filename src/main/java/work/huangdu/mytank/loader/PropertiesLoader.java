package work.huangdu.mytank.loader;

import work.huangdu.mytank.constant.GameConstant;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 属性加载类
 *
 * @author 445951954@qq.com 2019/9/21 0:28
 */
public class PropertiesLoader {
    //单例模式 实例
    private static PropertiesLoader propertiesLoader = new PropertiesLoader();

    private PropertiesLoader() {
        loadAll();
    }

    /**
     * 属性文件的key
     */
    public interface Key {
        String GAME_NAME = "game.name";
        String MAP_PATH = "map.path";
        String IMAGE_PATH = "image.path";
        String IMAGE_FORMAT = "image.format";
        String FRAME_WIGHT = "frame.width";
        String FRAME_HEIGHT = "frame.height";
        String RIVER_WIDTH = "river.width";
        String RIVER_HEIGHT = "river.height";
        String STEEL_WIDTH = "steel.width";
        String STEEL_HEIGHT = "steel.height";
        String GRASS_WIDTH = "grass.width";
        String GRASS_HEIGHT = "grass.height";
        String ICE_WIDTH = "ice.width";
        String ICE_HEIGHT = "ice.height";
        String WALL_WIDTH = "wall.width";
        String WALL_HEIGHT = "wall.height";
        String HOME_WIDTH = "home.width";
        String HOME_HEIGHT = "home.height";
        String TANK_WIDTH = "tank.width";
        String TANK_HEIGHT = "tank.height";
        String TANK_STEP = "tank.step";
        String BULLET_WIDTH = "bullet.width";
        String BULLET_HEIGHT = "bullet.height";
        String BULLET_STEP = "bullet.step";
    }

    /**
     * 存储所有属性的Map
     */
    private Properties props;

    /**
     * 加载全部属性文件
     */
    private void loadAll() {
        props = new Properties();
        try {
            props.load(getClass().getResourceAsStream(GameConstant.PROPERTIES_PATH));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("找不到配置mytank.properties文件", e);
        } catch (IOException e) {
            throw new RuntimeException("读取配置mytank.properties文件出错", e);
        }
    }

    /**
     * 根据key获得属性值
     */
    public static String getProperties(String key) {
        return propertiesLoader.props.getProperty(key);
    }
}
