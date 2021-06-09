package work.huangdu.mytank.element;

import work.huangdu.mytank.constant.GameConstant;

public abstract class Tank extends MovingElement {
    /**
     * 有参构造设置元素的宽和高
     *
     * @param width  元素的宽属性名
     * @param height 元素的高属性名
     * @param step   步长
     */
    protected Tank(String width, String height, int step) {
        super(width, height, step);
        noPassList.add(this);
    }

    /**
     * 向上走
     */
    public void goUp() {
        turnUp();
        y -= step;
        if (!canPass(this)) {
            y += step;
        }
    }

    /**
     * 向下走
     */
    public void goDown() {
        turnDown();
        y += step;
        if (!canPass(this)) {
            y -= step;
        }
    }

    /**
     * 向左走
     */
    public void goLeft() {
        turnLeft();
        x -= step;
        if (!canPass(this)) {
            x += step;
        }
    }

    /**
     * 向右走
     */
    public void goRight() {
        turnRight();
        x += step;
        if (!canPass(this)) {
            x -= step;
        }
    }

    /**
     * 火力模式常量
     */
    private int fireState = GameConstant.FireState.NORMAL_FIRE;

    //判断坦克是否为正常火力
    public boolean isNormalFire() {
        return fireState == GameConstant.FireState.NORMAL_FIRE;
    }

    //判断坦克是否为中等火力
    public boolean isMediumFire() {
        return fireState == GameConstant.FireState.MEDIUM_FIRE;
    }

    //判断坦克是否为超级火力
    public boolean isSuperFire() {
        return fireState == GameConstant.FireState.SUPER_FIRE;
    }

    //设置坦克为正常火力
    public void turnNormalFire() {
        fireState = GameConstant.FireState.NORMAL_FIRE;
    }

    //设置坦克为中等火力
    public void turnMediumFire() {
        fireState = GameConstant.FireState.MEDIUM_FIRE;
    }

    //设置坦克为超级火力
    public void turnSuperFire() {
        fireState = GameConstant.FireState.SUPER_FIRE;
    }

    /**
     * 坦克射击
     *
     * @return 子弹
     */
    public Bullet shoot() {
        Bullet bs;
        int xStep = this.width / 2;
        int yStep = this.height / 2;
        int length = -1;
        if (isUp()) {
            bs = new Bullet(this.x + xStep, this.y - length, GameConstant.DirectionState.UP);
        } else if (isDown()) {
            bs = new Bullet(this.x + xStep, this.y + this.height + length, GameConstant.DirectionState.DOWN);
        } else if (isLeft()) {
            bs = new Bullet(this.x - length, this.y + yStep, GameConstant.DirectionState.LEFT);
        } else {
            bs = new Bullet(this.x + this.width + length, this.y + yStep, GameConstant.DirectionState.RIGHT);
        }
        if (isSuperFire()) {
            bs.setStyle(1);
        }
        return bs;
    }

    //让元素状态变成死
    public void turnDead() {
        existState = GameConstant.ElementState.DEAD;
        noPassList.remove(this);
    }
}
