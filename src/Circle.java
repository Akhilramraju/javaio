/*This is just an initial skeleton of the class to help you get started.
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports.*/


public class Circle extends Shape {
    public static final String TAG_CLASSNAME = "circle";
    private int rad;    // radius

    //define the constructor following the signature in the specification
    public Circle(int rowBase, int colBase, int radius, char drawingCharacter) {
        this.rad = radius;
        this.rb = rowBase;
        this.cb = colBase;
        this.character = drawingCharacter;
    }

    public void draw(Window window) {
        //assuming row position of the center of this circle is 'rb'
        //assuming column position of the center of this circle is 'cb'

        //Attempt to draw 20 points and a possible way is shown below
        //you can come up with other possible logic for drawing a circle as well

        for (int i = 0; i < 20; i++) {
            double angle = i * Math.PI / 10; //radian angle
            int rdif = (int) Math.round(rad * Math.cos(angle));
            int row = rb + 1 + rdif;
            int cdif = (int) Math.round(rad * Math.sin(angle));
            int col = cb + 1 + cdif;
            //now row and col forms a point on the perimeter of the circle
            //appropriate call to setCell() method of the Window class...
            window.setCell(row, col, character);
        }
    }

    @Override
    public String toString() {
        //circle(baseRow,baseCol)(radius)(displayChar)
        return TAG_CLASSNAME + " (" + rb + ',' + cb + ") " + '(' + rad + ") " + '(' + character + ')';
    }

    //define other methods...
    @Override
    protected StringBuilder buildSpecsInFormat() {
        StringBuilder specs = new StringBuilder();
        specs.append("\n").append(TAG_CLASSNAME);
        specs.append("\n").append(this.rb);
        specs.append("\t").append(this.cb);
        specs.append("\t").append(this.rad);
        specs.append("\n").append(character);
        specs.append("\n.");
        return specs;
    }
}
