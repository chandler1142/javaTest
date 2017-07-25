package designpattern.factoryMethod;

/**
 * Created by chandlerzhao on 2017/7/20.
 */
public class RectangleShape implements ShapeFactoryInterface {
    @Override
    public Shape getShape() {
        return new Rectangle();
    }
}
