package work.huangdu.mytank.loader.entity;

import lombok.Data;

import java.util.List;

/**
 * GameMap实体类
 *
 * @author duhuang@iflytek.com
 * @version 2020/2/1 14:05
 */
@Data
public class MapData {
    /**
     * 地图关卡
     */
    private int level;
    /**
     * XML文件中数据
     */
    private List<ThingData> thingDataList;
}
