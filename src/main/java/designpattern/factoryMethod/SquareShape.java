package designpattern.factoryMethod;

/**
 * Created by chandlerzhao on 2017/7/20.
 */
public class SquareShape implements ShapeFactoryInterface {
    @Override
    public Shape getShape() {
        return new Square();
    }
}
