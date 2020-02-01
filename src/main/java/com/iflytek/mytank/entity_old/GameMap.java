package com.iflytek.mytank.entity_old;
/**
 * 地图类
 * 封装用于编辑地图的静态方法
 * 保存关卡地图
 * @author 445951954@qq.com
 */
public class GameMap {
	/**
	 * 有参构造，用于调用关卡地图
	 * @param level
	 */
	public GameMap(int level) {
		switch(level) {
		case 0:
			setMap();
			break;
		case 1:
			loadMap1();
			break;
		case 2:
			loadMap2();
			break;
		case 3:
			loadMap3();
			break;
		}
		World.staticElements[0*13+0]=new NullThing();//敌人坦克出生位置
		World.staticElements[0*13+6]=new NullThing();
		World.staticElements[0*13+12]=new NullThing();
		World.staticElements[12*13+4]=new NullThing();//英雄坦克出生位置
		World.staticElements[12*13+5]=new Home(12*13+5);
		World.staticElements[12*13+6]=new Home(12*13+6);
		World.staticElements[12*13+7]=new Home(12*13+7);
	}
	//
	public void setMap() {
		for(int i=0;i<World.staticElements.length;i++) {
			World.staticElements[i]=new NullThing();
		}
	}
	//第一关地图
	public void loadMap1() {
		for(int i=0;i<World.staticElements.length;i++) {
			World.staticElements[i]=new Wall(i);
		}
		World.staticElements[3*13+3]=new Steel(3*13+3);
		World.staticElements[3*13+9]=new Steel(3*13+9);
		World.staticElements[6*13+0]=new Steel(6*13+0);
		World.staticElements[6*13+6]=new Steel(6*13+6);
		World.staticElements[6*13+12]=new Steel(6*13+12);
		World.staticElements[9*13+3]=new Steel(9*13+3);
		World.staticElements[9*13+9]=new Steel(9*13+9);
	}
	//第二关地图
	public void loadMap2() {
		for(int i=0;i<World.staticElements.length;i++) {
			World.staticElements[i]=new Wall(i);
		}
	}
	//第三关地图
	public void loadMap3() {
		for(int i=0;i<World.staticElements.length;i++) {
			World.staticElements[i]=new Wall(i);
		}
		World.staticElements[3*13+3]=new River(3*13+3);
		World.staticElements[3*13+9]=new River(3*13+9);
		World.staticElements[6*13+0]=new River(6*13+0);
		World.staticElements[6*13+6]=new River(6*13+6);
		World.staticElements[6*13+12]=new River(6*13+12);
		World.staticElements[9*13+3]=new River(9*13+3);
		World.staticElements[9*13+9]=new River(9*13+9);
	}
	/**
	 * 设置地图
	 * @param x x坐标
	 * @param y y坐标
	 */
	public static void setWall(int x,int y) {
		World.staticElements[y/60*13+x/60]=new Wall(y/60*13+x/60);
	}
	public static void setSteel(int x,int y) {
		World.staticElements[y/60*13+x/60]=new Steel(y/60*13+x/60);
	}
	public static void setRiver(int x,int y) {
		World.staticElements[y/60*13+x/60]=new River(y/60*13+x/60);
	}
	public static void setNullThing(int x,int y) {
		World.staticElements[y/60*13+x/60]=new NullThing();
	}
}
