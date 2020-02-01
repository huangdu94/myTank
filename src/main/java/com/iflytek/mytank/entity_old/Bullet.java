package com.iflytek.mytank.entity_old;
/**
 * 子弹类，所有子弹的父类
 * @author 445951954@qq.com
 */
public abstract class Bullet extends MovingElement{
	/**
	 * 有参构造
	 * @param x x坐标
	 * @param y y坐标
	 * @param directionState 方向
	 */
	public Bullet(int x,int y,int directionState) {
		super(4,10,10);
		this.x=x;
		this.y=y;
		this.directionState=directionState;
	}
	/**
	 * 子弹移动
	 */
	public void step() {
		if(isLive()) {
			if(isUp()) {
				y-=step;
			}else if(isDown()){
				y+=step;
			}else if(isLeft()) {
				x-=step;
			}else {
				x+=step;
			}
		}
	}
	/**
	 * 判断子弹是否越界
	 * @return true表示子弹越界
	 */
	public boolean outOfBounds() {
		int x1=0;
		int x2=World.WIDTH-this.height;
		int y1=0;
		int y2=World.HEIGHT-this.height;
		return x<=x1||x>=x2||y<=y1||y>=y2;
	}
}
