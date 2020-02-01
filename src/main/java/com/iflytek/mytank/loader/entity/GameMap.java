package com.iflytek.mytank.loader.entity;

import lombok.Data;

import java.util.List;

/**
 * GameMap实体类
 *
 * @author duhuang@iflytek.com
 * @version 2020/2/1 14:05
 */
@Data
public class GameMap {
    private String level;
    private List<Thing> thingList;
}
