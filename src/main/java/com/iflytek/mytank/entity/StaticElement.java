package com.iflytek.mytank.entity;
/**
 * 静止元素类，所有静止元素的父类
 * @author Bean
 */
public abstract class StaticElement extends Element{
	/**
	 * 无参构造
	 */
	public StaticElement() {}
	/**
	 * 有参构造设置元素的宽和高
	 * @param width 元素的宽
	 * @param height 元素的高
	 */
	public StaticElement(int width,int height) {
		super(width,height);
	}
	/**
	 * 当碰撞发生时，根据子弹类型处理碰撞
	 */
	public abstract void dealHit(Bullet b);
	//用于设置不可通过
	public static boolean[][] block=new boolean[13][13];
}
