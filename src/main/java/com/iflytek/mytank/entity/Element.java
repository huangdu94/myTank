package com.iflytek.mytank.entity;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 元素类，游戏中所有元素的父类
 * @author Bean
 */
public abstract class Element {
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	/**
	 * 无参构造
	 */
	public Element() {}
	/**
	 * 有参构造
	 * @param width 元素的宽
	 * @param height 元素的高
	 */
	public Element(int width,int height) {
		this.width=width;
		this.height=height;
	}
	/**
	 * 描述生存状态的常量,活，死，可以移出，默认为活
	 */
	protected static final int LIVE=0;
	protected static final int DEAD=1;
	protected static final int REMOVE=2;
	protected int existState=LIVE;
	//判断元素是否活着
	public boolean isLive() {
		return existState==LIVE;
	}
	//判断元素是否死了
	public boolean isDead() {
		return existState==DEAD;
	}
	//判断元素是否可以移除
	public boolean isRemove() {
		return existState==REMOVE;
	}
	//让元素状态变成死
	public void turnDead() {
		existState=DEAD;
	}
	//让元素状态变成可以移除
	public void turnRemove() {
		existState=REMOVE;
	}
	/**
	 * 将getImage()方法返回的图片画出来
	 * @param g 画笔
	 */
	public void paint(Graphics g) {
		g.drawImage(getImage(),x,y,null);
	}
	/**
	 * 得到元素当前状态的图片
	 * @return 元素当前状态的图片
	 */
	public abstract BufferedImage getImage();
	/**
	 * 判断是否碰撞的方法
	 * @param other 其它元素
	 * @return true表示碰撞发生
	 */
	public boolean hit(Element other) {
		int x1=this.x-other.width;
		int x2=this.x+this.width;
		int y1=this.y-other.height;
		int y2=this.y+this.height;
		int x=other.x;
		int y=other.y;
		return x>=x1&&x<=x2&&y>=y1&&y<=y2;
	}
}
