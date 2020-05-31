public class ShapeOptionFactory {
    public static Util.ShapeOption getShapeOption(String shapesName) {
        switch (shapesName) {
        
            case "line":
            	//for adding a line
                return Util.ShapeOption.LINE;
                
            case "circle":
            	//for adding a circle
                return Util.ShapeOption.CIRCLE;
                
            case "rectangle":
            	//for adding a rectangle
                return Util.ShapeOption.RECTANGLE;
                
            case "triangle":
            	//for adding a triangle
                return Util.ShapeOption.TRIANGLE;
                
            case "text":
            	//for adding a text
                return Util.ShapeOption.TEXT;
                
            case "oval":
            	//for adding an oval
                return Util.ShapeOption.OVAL;
            default:
                return null;
        }
    }
}
