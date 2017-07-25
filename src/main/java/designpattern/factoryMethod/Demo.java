package designpattern.factoryMethod;

/**
 * Created by chandlerzhao on 2017/7/20.
 */
public class Demo {

    public static void main(String[] args) {

        ShapeFactoryInterface shapeFactoryInterface1 = new CircleShape();
        Shape circle = shapeFactoryInterface1.getShape();

        circle.draw();

        ShapeFactoryInterface shapeFactoryInterface2 = new RectangleShape();
        Shape rectangle = shapeFactoryInterface2.getShape();

        rectangle.draw();

        ShapeFactoryInterface shapeFactoryInterface3 = new SquareShape();
        Shape square = shapeFactoryInterface3.getShape();

        square.draw();

    }
}
