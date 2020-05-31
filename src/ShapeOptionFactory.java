public class ShapeOptionFactory {
    public static Util.ShapeOption getShapeOption(String shapesName) {
        switch (shapesName) {
            case "line":
                return Util.ShapeOption.LINE;
            case "circle":
                return Util.ShapeOption.CIRCLE;
            case "rectangle":
                return Util.ShapeOption.RECTANGLE;
            case "triangle":
                return Util.ShapeOption.TRIANGLE;
            case "text":
                return Util.ShapeOption.TEXT;
            case "oval":
                return Util.ShapeOption.OVAL;
            default:
                return null;
        }
    }
}
