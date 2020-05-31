/*
This is just an initial skeleton of the class to help you get started.
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports.
*/

public class Rectangle extends Shape {
    public static final String TAG_CLASSNAME = "rectangle";
    private int height;
    private int width;

    //define the constructor following the signature in the specification
    public Rectangle(int rowBase, int colBase, int height, int width,
                     char drawingCharacter) {
        this.rb = rowBase;
        this.cb = colBase;
        this.height = height;
        this.width = width;
        this.character = drawingCharacter;
    }

    public void draw(Window window) throws Exception {
        //treat the rectangle as four lines

        //Line line1 = new Line(/*appropriate parameters goes here*/);
        Line line1 = new Line(rb, cb, width, 0, 1, character); //topleft  - topright
        Line line2 = new Line(rb, cb, height, 1, 0, character); //topleft  - bottomLeft
        Line line4 = new Line(rb, cb + width, height, 1, 0, character); //topright  - bottomright
        Line line3 = new Line(rb + height, cb, width, 0, 1, character); //bottomleft  - bottomright
        //similarly line2, line3, line4

        //now use the draw method in the Line class to draw the rectangle
        line1.draw(window);
        line2.draw(window);
        line3.draw(window);
        line4.draw(window);
    }

    @Override
    protected StringBuilder buildSpecsInFormat() {
        StringBuilder specs = new StringBuilder();
        specs.append("\n").append(TAG_CLASSNAME);
        specs.append("\n").append(this.rb);
        specs.append("\t").append(this.cb);
        specs.append("\t").append(this.height);
        specs.append("\t").append(this.width);
        specs.append("\n").append(character);
        specs.append("\n.");
        return specs;
    }

    //define other methods...


    @Override
    public String toString() {
        return TAG_CLASSNAME + " (" + rb + ',' + cb + ") " + '(' + height + ',' + width + ") " + '(' + character + ')';
    }
}
