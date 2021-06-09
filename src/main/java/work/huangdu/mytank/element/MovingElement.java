package work.huangdu.mytank.element;

import work.huangdu.mytank.constant.GameConstant;

/**
 * 移动元素类，所有移动元素的父类
 *
 * @author 445951954@qq.com
 */
public abstract class MovingElement extends Element {
    protected int step;

    /**
     * 有参构造设置元素的宽和高
     *
     * @param width  元素的宽属性名
     * @param height 元素的高属性名
     * @param step   步长
     */
    protected MovingElement(String width, String height, int step) {
        super(width, height);
        this.step = step;
    }

    protected int directionState = GameConstant.DirectionState.UP;

    //判断元素是否向上
    public boolean isUp() {
        return directionState == GameConstant.DirectionState.UP;
    }

    //判断元素是否向下
    public boolean isDown() {
        return directionState == GameConstant.DirectionState.DOWN;
    }

    //判断元素是否向左
    public boolean isLeft() {
        return directionState == GameConstant.DirectionState.LEFT;
    }

    //判断元素是否向右
    public boolean isRight() {
        return directionState == GameConstant.DirectionState.RIGHT;
    }

    //元素朝上
    public void turnUp() {
        directionState = GameConstant.DirectionState.UP;
    }

    //元素朝下
    public void turnDown() {
        directionState = GameConstant.DirectionState.DOWN;
    }

    //元素朝左
    public void turnLeft() {
        directionState = GameConstant.DirectionState.LEFT;
    }

    //元素朝右
    public void turnRight() {
        directionState = GameConstant.DirectionState.RIGHT;
    }

    /**
     * 元素移动
     */
    public abstract void step();
}
