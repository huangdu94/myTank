package com.iflytek.mytank.entity_old;

import com.iflytek.mytank.loader.ImageCache;

import java.awt.image.BufferedImage;


/**
 * 河地形，不可被消灭
 * @author 445951954@qq.com
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
		return ImageCache.river[liveIndex++/10%2];// /10用于增加河流动画时间
	}
	
	public void dealHit(Bullet b) {}
}