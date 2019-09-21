package com.iflytek.mytank.entity;
import java.awt.image.BufferedImage;

public class Hero extends Tank{
	private int life;
	public Hero() {
		super(60,60,15);
		x=240;
		y=720;
		life=3;
	}
	/**
	 * 减命
	 */
	public void subtractLife() {
		life--;
	}
	/**
	 * 加命
	 */
	public void addLife() {
		life++;
	}
	/**
	 * 显示命
	 * @return 命
	 */
	public int showLife() {
		return life;
	}
	
	public void step() {}
	
	public BufferedImage getImage() {
		if(isUp()) {
			return Image.heroSUp;
		}else if(isDown()){
			return Image.heroSDown;
		}else if(isLeft()) {
			return Image.heroSLeft;
		}else {
			return Image.heroSRight;
		}
	}
}
