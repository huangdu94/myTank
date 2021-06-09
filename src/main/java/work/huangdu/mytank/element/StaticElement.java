package work.huangdu.mytank.element;

/**
 * 静止元素类，所有静止元素的父类
 *
 * @author 445951954@qq.com
 */
public abstract class StaticElement extends Element {
    /**
     * 有参构造设置元素的宽和高
     *
     * @param width  元素的宽属性名
     * @param height 元素的高属性名
     */
    protected StaticElement(String width, String height) {
        super(width, height);
    }

    /**
     * 当碰撞发生时，根据子弹类型处理碰撞
     */
    public abstract void dealHit(Bullet b);
}
