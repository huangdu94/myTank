package work.huangdu.mytank.loader;

import work.huangdu.mytank.action.ElementPool;
import work.huangdu.mytank.constant.GameConstant;
import work.huangdu.mytank.element.Grass;
import work.huangdu.mytank.element.Home;
import work.huangdu.mytank.element.Ice;
import work.huangdu.mytank.element.River;
import work.huangdu.mytank.element.Steel;
import work.huangdu.mytank.element.Wall;
import com.iflytek.mytank.element.*;
import work.huangdu.mytank.loader.entity.MapData;
import work.huangdu.mytank.loader.entity.ThingData;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.util.*;

/**
 * 地图加载类
 * 封装用于编辑地图的静态方法
 * 保存关卡地图
 *
 * @author 445951954@qq.com
 */
public class GameMapLoader {
    /**
     * 关卡地图数据列表
     */
    private static List<MapData> mapDataList = new ArrayList<>();

    static {
        loadXML();
    }

    /**
     * 加载xml文件
     */
    @SuppressWarnings("unchecked")
    private static void loadXML() {
        try {
            SAXReader reader = new SAXReader();
            String filepath = PropertiesLoader.getProperties(PropertiesLoader.Key.MAP_PATH);
            Document doc = reader.read(new FileInputStream(GameMapLoader.class.getResource(filepath).getPath()));
            System.out.println("读取XML文档完毕!");
            // 获取根元素
            Element root = doc.getRootElement();
            List<Element> mapElements = root.elements(GameConstant.XmlFileLabel.GAME_MAP);
            for (Element mapElement : mapElements) {
                MapData mapData = new MapData();
                List<ThingData> thingDataList = new LinkedList<>();
                //获取地图等级
                String level = mapElement.attributeValue(GameConstant.XmlFileLabel.LEVEL);
                mapData.setLevel(Integer.parseInt(level));
                //获取当前地图thingList
                List<Element> thingElements = mapElement.elements();
                for (Element thingElement : thingElements) {
                    ThingData thingData = new ThingData();
                    thingData.setType(thingElement.getName());
                    thingData.setStyle(thingElement.attributeValue(GameConstant.XmlFileLabel.STYLE));
                    thingData.setX(thingElement.elementText(GameConstant.XmlFileLabel.X));
                    thingData.setY(thingElement.elementText(GameConstant.XmlFileLabel.Y));
                    thingDataList.add(thingData);
                }
                mapData.setThingDataList(thingDataList);
                mapDataList.add(mapData);
            }
            System.out.println("解析完毕!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加wallList
     */
    private static void addWallList(int x, int y, String style) {
        int xEnd = x + 40;
        int yEnd = y + 40;
        if (style != null) {
            switch (style) {
                case GameConstant.XmlFileLabel.STYLE_UP:
                    yEnd -= 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_DOWN:
                    y += 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_LEFT:
                    xEnd -= 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_RIGHT:
                    x += 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_LEFT_UP:
                    xEnd -= 20;
                    yEnd -= 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_LEFT_DOWN:
                    xEnd -= 20;
                    y += 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_RIGHT_UP:
                    x += 20;
                    yEnd -= 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_RIGHT_DOWN:
                    x += 20;
                    y += 20;
                    break;
                default:
                    break;
            }
        }
        for (int ix = x; ix < xEnd; ix += 10) {
            for (int iy = y; iy < yEnd; iy += 10) {
                Wall wall = new Wall(ix, iy);
                ElementPool.getCurrentMap().getWallList().add(wall);
            }
        }
    }

    /**
     * 添加steelList
     */
    private static void addSteelList(int x, int y, String style) {
        int xEnd = x + 40;
        int yEnd = y + 40;
        if (style != null) {
            switch (style) {
                case GameConstant.XmlFileLabel.STYLE_UP:
                    yEnd -= 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_DOWN:
                    y += 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_LEFT:
                    xEnd -= 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_RIGHT:
                    x += 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_LEFT_UP:
                    xEnd -= 20;
                    yEnd -= 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_LEFT_DOWN:
                    xEnd -= 20;
                    y += 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_RIGHT_UP:
                    x += 20;
                    yEnd -= 20;
                    break;
                case GameConstant.XmlFileLabel.STYLE_RIGHT_DOWN:
                    x += 20;
                    y += 20;
                    break;
                default:
                    break;
            }
        }
        for (int ix = x; ix < xEnd; ix += 20) {
            for (int iy = y; iy < yEnd; iy += 20) {
                Steel steel = new Steel(ix, iy);
                ElementPool.getCurrentMap().getSteelList().add(steel);
            }
        }
    }

    /**
     * 根据关卡加载地图
     *
     * @param level 关卡
     * @return 静态物体List
     */
    public static ElementPool loadCurrentMap(int level) {
        if (mapDataList.size() == 0) {
            return null;
        }
        for (MapData mapData : mapDataList) {
            if (mapData.getLevel() == level) {
                for (ThingData thingData : mapData.getThingDataList()) {
                    //坐标转换
                    int x = Integer.parseInt(thingData.getX());
                    int y = Integer.parseInt(thingData.getY());
                    x = (x - 1) * 40;
                    y = (y - 1) * 40;
                    switch (thingData.getType()) {
                        case GameConstant.XmlFileLabel.HOME:
                            Home home = new Home(x, y);
                            ElementPool.getCurrentMap().setHome(home);
                            break;
                        case GameConstant.XmlFileLabel.WALL:
                            addWallList(x, y, thingData.getStyle());
                            break;
                        case GameConstant.XmlFileLabel.STEEL:
                            addSteelList(x, y, thingData.getStyle());
                            break;
                        case GameConstant.XmlFileLabel.RIVER:
                            River river = new River(x, y);
                            ElementPool.getCurrentMap().getRiverList().add(river);
                            break;
                        case GameConstant.XmlFileLabel.GRASS:
                            Grass grass = new Grass(x, y);
                            ElementPool.getCurrentMap().getGrassList().add(grass);
                            break;
                        case GameConstant.XmlFileLabel.ICE:
                            Ice ice = new Ice(x, y);
                            ElementPool.getCurrentMap().getIceList().add(ice);
                            break;
                    }
                }
                return ElementPool.getCurrentMap();
            }
        }
        return null;
    }
}
