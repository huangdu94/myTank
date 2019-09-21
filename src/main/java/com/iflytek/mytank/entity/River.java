package com.iflytek.mytank.entity;
import java.awt.image.BufferedImage;
/**
 * 河地形，不可被消灭
 * @author Bean
 */
public class River extends StaticElement{
	public River(int index) {
		super(60,60);
		this.x=(index%13)*60;
		this.y=(index/13)*60;
		block[x/60][y/60]=true;
	}

	private int liveIndex=0;
	public BufferedImage getImage() {
		return Image.river[liveIndex++/10%2];// /10用于增加河流动画时间
	}
	
	public void dealHit(Bullet b) {}
}
