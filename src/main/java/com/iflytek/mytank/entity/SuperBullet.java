package com.iflytek.mytank.entity;
import java.awt.image.BufferedImage;
/**
 * 超级子弹
 * @author Bean
 */
public class SuperBullet extends Bullet{
	public SuperBullet(int x,int y,int directionState) {
		super(x, y, directionState);
	}
	
	private int deadIndex=0;
	public BufferedImage getImage() {
		if(isLive()) {
			if(isUp()||isDown()) {
				return Image.rBulletVertical;
			}else {
				return Image.rBulletAcross;
			}
		}else if(isDead()) {
			BufferedImage img=Image.bulletBang[deadIndex++/5];// /5用于增加爆炸动画时间
			if(deadIndex/5>=Image.bulletBang.length) {
				existState=REMOVE;
			}
			return img;
		}
		return null;
	}
}
