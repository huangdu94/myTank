package com.iflytek.mytank.entity;
import java.awt.image.BufferedImage;
/**
 * 无地形
 * @author Bean
 */
public class NullThing extends StaticElement{
	public BufferedImage getImage() {
		return null;
	}
	public void dealHit(Bullet b) {}
}
