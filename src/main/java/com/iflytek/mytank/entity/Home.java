package com.iflytek.mytank.entity;
import java.awt.image.BufferedImage;
/**
 * 家，与子弹发生碰撞则游戏结束
 * @author Bean
 */
public class Home extends StaticElement{
	public Home(int index) {
		super(60,60);
		this.x=(index%13)*60;
		this.y=(index/13)*60;
		block[x/60][y/60]=true;
	}
	
	public BufferedImage getImage() {
		return Image.home;
	}
	
	public void dealHit(Bullet b) {}
}
