/*This is just an initial skeleton of the class to help you get started.
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports.*/


public class Triangle extends Shape {
    private static final String TAG_CLASSNAME = "triangle";
    private int height;    // height of isosceles triangle
    private int rInc;   // only (1, 0), (-1,0), (0,1) and (0,-1)
    private int cInc;   // are allowed

    //define the constructor following the signature in the specification
    public Triangle(int rowBase, int colBase, int height, int rowIncrement,
                    int colIncrement, char drawingCharacter) {
        this.height = height;
        this.rInc = rowIncrement;
        this.cInc = colIncrement;
        this.character = drawingCharacter;
        this.rb = rowBase;
        this.cb = colBase;
    }

    public void draws(Window window) throws Exception {
        //assuming row position of the base point of this triangle is 'rb'
        //assuming column position of the base point of this triangle is 'cb'
        //assuming the drawing character is 'character'
        //assuming the constructor in the Line class has been defined according to the specification

        if (rInc == 0)//when the height vector goes right or left from the base point
        {
            Line line1 = new Line(rb, cb, height, -1, cInc, character);
            Line line2 = new Line(rb, cb, height, 1, cInc, character);
            Line line3 = new Line(rb - height, cb + cInc * height, 2 * height,
                    1, 0, character);
            line1.draws(window);
            line2.draws(window);
            line3.draws(window);
            //now use the draw method in the Line class to draw the triangle
        } else if (cInc == 0)//when the height vector goes up or down from the base point
        {
            Line line1 = new Line(rb, cb, height, rInc, -1, character);
            Line line2 = new Line(rb, cb, height, rInc, 1, character);
            Line line3 = new Line(rb + rInc * height, cb - height,
                    2 * height, 0, 1, character);
            //now use the draw method in the Line class to draw the triangle
            line1.draws(window);
            line2.draws(window);
            line3.draws(window);
        }
    }

    @Override
    public String toString() {
        return TAG_CLASSNAME + " (" + rb  + ',' + cb + ") " + '(' +  rInc + ','  + cInc + ") " + '(' +  character + ')';
    }

    @Override
    protected StringBuilder buildSpecsInFormat() {
        StringBuilder specs = new StringBuilder();
        specs.append("\n").append(TAG_CLASSNAME);
        specs.append("\n").append(this.rb);
        specs.append("\t").append(this.cb);
        specs.append("\t").append(this.height);
        specs.append("\t").append(this.rInc);
        specs.append("\t").append(this.cInc);
        specs.append("\n").append(character);
        specs.append("\n.");
        return specs;
    }

}
