package designpattern.factory;

/**
 * Created by chandlerzhao on 2017/7/20.
 */
public class ShapeFactory {

    public static Shape getShape(String shapeType) {
        Shape shape = null;

        if (shapeType == null) {
            return shape;
        }

        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        }

        return shape;
    }
}
