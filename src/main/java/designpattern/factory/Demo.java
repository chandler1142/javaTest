package designpattern.factory;

/**
 * Created by chandlerzhao on 2017/7/20.
 */
public class Demo {

    public static void main(String[] args) {

        Shape shape1 = ShapeFactory.getShape("Circle");
        shape1.draw();

        Shape shape2 = ShapeFactory.getShape("Rectangle");
        shape2.draw();

        Shape shape3 = ShapeFactory.getShape("Square");
        shape3.draw();
    }
}
