package com.iflytek.mytank.entity_old;
/**
 * 移动元素类，所有移动元素的父类
 * @author 445951954@qq.com
 */
public abstract class MovingElement extends Element{
	protected int step;
	/**
	 * 无参构造
	 */
	public MovingElement(){}
	/**
	 * 有参构造
	 * @param width 宽
	 * @param height 高
	 * @param step 移动速度
	 */
	public MovingElement(int width,int height,int step) {
		super(width,height);
		this.step=step;
	}
	/**
	 * 描述方向状态的常量
	 */
	protected static final int UP=0;
	protected static final int DOWN=1;
	protected static final int LEFT=2;
	protected static final int RIGHT=3;
	protected int directionState=UP;
	//判断元素是否向上
	public boolean isUp() {
		return directionState==UP;
	}
	//判断元素是否向下
	public boolean isDown() {
		return directionState==DOWN;
	}
	//判断元素是否向左
	public boolean isLeft() {
		return directionState==LEFT;
	}
	//判断元素是否向右
	public boolean isRight() {
		return directionState==RIGHT;
	}
	//元素朝上
	public void turnUp() {
		directionState=UP;
	}
	//元素朝下
	public void turnDown() {
		directionState=DOWN;
	}
	//元素朝左
	public void turnLeft() {
		directionState=LEFT;
	}
	//元素朝右
	public void turnRight() {
		directionState=RIGHT;
	}
	/**
	 *元素移动
	 */
	public abstract void step();
}
