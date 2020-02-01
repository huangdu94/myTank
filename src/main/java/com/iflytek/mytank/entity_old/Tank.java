package com.iflytek.mytank.entity_old;

public abstract class Tank extends MovingElement{
	/**
	 * 无参构造
	 */
	public Tank() {}
	/**
	 * 有参构造
	 * @param width 宽
	 * @param height 高
	 * @param step 移动速度
	 */
	public Tank(int width,int height,int step) {
		super(width,height,step);
	}
	/**
	 * 判断坦克是否可以向上走
	 */
	private boolean canGoUp() {
		int y1=0;
		boolean noOutOfBounds=y-step>=y1;
		boolean noBlock=true;
		if(y/60!=0) {
			if(x%60==0) {
				noBlock=!StaticElement.block[x/60][y/60-1];
			}else {
				noBlock=!StaticElement.block[x/60][y/60-1]&&!StaticElement.block[x/60+1][y/60-1];
			}
		}
		return noOutOfBounds&&noBlock;
	}
	/**
	 * 判断坦克是否可以向下走
	 */
	private boolean canGoDown() {
		int y2=World.HEIGHT-this.height;
		boolean noOutOfBounds=y+step<=y2;
		boolean noBlock=true;
		if(y/60!=12) {
			if(x%60==0) {
				noBlock=!StaticElement.block[x/60][y/60+1];
			}else {
				noBlock=!StaticElement.block[x/60][y/60+1]&&!StaticElement.block[x/60+1][y/60+1];
			}
		}
		return noOutOfBounds&&noBlock;
	}
	/**
	 * 判断坦克是否可以向左走
	 */
	private boolean canGoLeft() {
		int x1=0;
		boolean noOutOfBounds=x-step>=x1;
		boolean noBlock=true;
		if(x/60!=0) {
			if(y%60==0) {
				noBlock=!StaticElement.block[x/60-1][y/60];
			}else {
				noBlock=!StaticElement.block[x/60-1][y/60]&&!StaticElement.block[x/60-1][y/60+1];
			}
		}
		return noOutOfBounds&&noBlock;
	}
	/**
	 * 判断坦克是否可以右走
	 */
	private boolean canGoRight() {
		int x2=World.WIDTH-this.width;
		boolean noOutOfBounds=x+step<=x2;
		boolean noBlock=true;
		if(x/60!=12) {
			if(y%60==0) {
				noBlock=!StaticElement.block[x/60+1][y/60];
			}else {
				noBlock=!StaticElement.block[x/60+1][y/60]&&!StaticElement.block[x/60+1][y/60+1];
			}
		}
		return noOutOfBounds&&noBlock;
	}
	/**
	 * 向上走
	 */
	public void goUp() {
		turnUp();
		if(canGoUp()) { 
			y-=step;
		}
	}
	/**
	 * 向下走
	 */
	public void goDown() {
		turnDown();
		if(canGoDown()) { 
			y+=step;
		}
	}
	/**
	 * 向左走
	 */
	public void goLeft() {
		turnLeft();
		if(canGoLeft()) { 
			x-=step;
		}
	}
	/**
	 * 向右走
	 */
	public void goRight() {
		turnRight();
		if(canGoRight()) { 
			x+=step;
		}
	}
	/**
	 * 火力模式常量
	 */
	private static final int NORMALFIRE=0;
	private static final int SUPERFIRE=1;
	private int fireState=NORMALFIRE;
	//判断坦克是否为正常火力
	public boolean isNormalFire() {
		return fireState==NORMALFIRE;
	}
	//判断坦克是否为超级火力
	public boolean isSuperFire() {
		return fireState==SUPERFIRE;
	}
	//设置坦克为正常火力
	public void turnNormalFire() {
		fireState=NORMALFIRE;
	}
	//设置坦克为超级火力
	public void turnSuperFire() {
		fireState=SUPERFIRE;
	}
	/**
	 * 坦克射击
	 * @return 子弹
	 */
	public Bullet shoot() {
		Bullet bs;
		int xStep=this.width/2;
		int yStep=this.height/2;
		int length=10;
		if(isNormalFire()) {
			if(isUp()) {
				bs=new NormalBullet(this.x+xStep,this.y-length,UP);
				return bs;
			}else if(isDown()) {
				bs=new NormalBullet(this.x+xStep,this.y+this.height+length,DOWN);
				return bs;
			}else if(isLeft()) {
				bs=new NormalBullet(this.x-length,this.y+yStep,LEFT);
				return bs;
			}else {
				bs=new NormalBullet(this.x+this.width+length,this.y+yStep,RIGHT);
				return bs;
			}
		}else {
			if(isUp()) {
				bs=new SuperBullet(this.x+xStep,this.y-length,UP);
				return bs;
			}else if(isDown()) {
				bs=new SuperBullet(this.x+xStep,this.y+this.height+length,DOWN);
				return bs;
			}else if(isLeft()) {
				bs=new SuperBullet(this.x-length,this.y+yStep,LEFT);
				return bs;
			}else {
				bs=new SuperBullet(this.x+this.width+length,this.y+yStep,RIGHT);
				return bs;
			}
		}
	}
}
