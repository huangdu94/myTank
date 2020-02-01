package com.iflytek.mytank.loader;

import com.iflytek.mytank.element.*;
import com.iflytek.mytank.loader.entity.GameMap;
import com.iflytek.mytank.loader.entity.Thing;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 地图加载类
 * 封装用于编辑地图的静态方法
 * 保存关卡地图
 *
 * @author 445951954@qq.com
 */
public class GameMapLoader {
    private static Map<String, List<StaticElement>> staticElementMap = new HashMap<>();
    private static List<GameMap> gameMapList = new LinkedList<>();

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
            List<Element> mapElements = root.elements("gamemap");
            for (Element mapElement : mapElements) {
                GameMap gameMap = new GameMap();
                List<Thing> thingList = new LinkedList<>();
                //获取地图等级
                String level = mapElement.elementText("level");
                gameMap.setLevel(level);
                //获取当前地图thingList
                List<Element> thingElements = mapElement.elements("thing");
                for (Element thingElement : thingElements) {
                    Thing thing = new Thing();
                    thing.setType(thingElement.elementText("type"));
                    thing.setStyle(thingElement.elementText("style"));
                    thing.setX(thingElement.elementText("x"));
                    thing.setY(thingElement.elementText("y"));
                    thingList.add(thing);
                }
                gameMap.setThingList(thingList);
                gameMapList.add(gameMap);
            }
            System.out.println("解析完毕!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将实体类转换为最终要用的Map
     */
    private static void transfer(String level) {
        if (staticElementMap.get(level) != null) {
            return;
        }
        for (GameMap gameMap : gameMapList) {
            if (gameMap.getLevel().equals(level)) {
                List<StaticElement> staticElements = new LinkedList<>();
                for (Thing thing : gameMap.getThingList()) {
                    //坐标转换
                    int x = Integer.parseInt(thing.getX());
                    int y = Integer.parseInt(thing.getY());
                    x = (x - 1) * 40;
                    y = (y - 1) * 40;
                    switch (thing.getType()) {
                        case "home":
                            Home home = new Home(x, y);
                            staticElements.add(home);
                            break;
                        case "wall":
                            addWallList(x, y, thing.getStyle(), staticElements);
                            break;
                        case "steel":
                            addSteelList(x, y, thing.getStyle(), staticElements);
                            break;
                        case "river":
                            River river = new River(x, y);
                            staticElements.add(river);
                            break;
                        case "grass":
                            Grass grass = new Grass(x, y);
                            staticElements.add(grass);
                            break;
                        case "ice":
                            Ice ice = new Ice(x, y);
                            staticElements.add(ice);
                            break;
                    }
                }
                staticElementMap.put(gameMap.getLevel(), staticElements);
                return;
            }
        }
    }

    /**
     * 添加wallList
     */
    private static void addWallList(int x, int y, String style, List<StaticElement> staticElements) {
        int xEnd = x + 40;
        int yEnd = y + 40;
        switch (style) {
            case "全部":
                break;
            case "上":
                yEnd -= 20;
                break;
            case "下":
                y += 20;
                break;
            case "左":
                xEnd -= 20;
                break;
            case "右":
                x += 20;
                break;
            case "左上":
                xEnd -= 20;
                yEnd -= 20;
                break;
            case "左下":
                xEnd -= 20;
                y += 20;
                break;
            case "右上":
                x += 20;
                yEnd -= 20;
                break;
            case "右下":
                x += 20;
                y += 20;
                break;
        }
        for (int ix = x; ix < xEnd; ix += 10) {
            for (int iy = y; iy < yEnd; iy += 10) {
                Wall wall = new Wall(ix, iy);
                staticElements.add(wall);
            }
        }
    }

    /**
     * 添加steelList
     */
    private static void addSteelList(int x, int y, String style, List<StaticElement> staticElements) {
        int xEnd = x + 40;
        int yEnd = y + 40;
        switch (style) {
            case "全部":
                break;
            case "上":
                yEnd -= 20;
                break;
            case "下":
                y += 20;
                break;
            case "左":
                xEnd -= 20;
                break;
            case "右":
                x += 20;
                break;
            case "左上":
                xEnd -= 20;
                yEnd -= 20;
                break;
            case "左下":
                xEnd -= 20;
                y += 20;
                break;
            case "右上":
                x += 20;
                yEnd -= 20;
                break;
            case "右下":
                x += 20;
                y += 20;
                break;
        }
        for (int ix = x; ix < xEnd; ix += 20) {
            for (int iy = y; iy < yEnd; iy += 20) {
                Steel steel = new Steel(ix, iy);
                staticElements.add(steel);
            }
        }
    }

    /**
     * 根据关卡获得静态物体List
     *
     * @param level 关卡
     * @return 静态物体List
     */
    public static List<StaticElement> getStaticElements(String level) {
        transfer(level);
        return staticElementMap.get(level);
    }
}
