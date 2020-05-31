public class ShapeOptionFactory {
    public static Utils.ShapeOption getShapeOption(String shapeName) {
        switch (shapeName) {
            case "line":
                return Utils.ShapeOption.LINE;
            case "circle":
                return Utils.ShapeOption.CIRCLE;
            case "rectangle":
                return Utils.ShapeOption.RECTANGLE;
            case "triangle":
                return Utils.ShapeOption.TRIANGLE;
            case "text":
                return Utils.ShapeOption.TEXT;
            case "oval":
                return Utils.ShapeOption.OVAL;
            default:
                return null;
        }
    }
}
