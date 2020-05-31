public class Oval extends Shape {

    public static final String TAG_CLASSNAME = "oval";

    int rowLength; //  the length of oval row wise
    int colWidth; //  the width of oval col wise


    public Oval(int rowBase, int colBase, int rowLength, int colLength, char displayChar) {
        this.rb = rowBase; //center base row point
        this.cb = colBase; //center base col point
        this.rowLength = rowLength;
        this.colWidth = colLength;
        this.character = displayChar;
    }

    //Using  parametric equation of ellipse to draw oval
    //angle(theta) is increased from 0 to 360 with step of 12
    @Override
    public void draw(Window window) {
        for (int theta = 0; theta < 360; theta += 12) {
            //convert theta to radians
            double thetaInRadians = (theta * (Math.PI / 180));
            int row = (int) Math.round(rowLength * Math.cos(thetaInRadians)) + rb;
            int col = (int) Math.round(colWidth * Math.sin(thetaInRadians)) + cb;
            window.setTheCell(row, col, character);
        }
    }

    @Override
    public String toString() {
        return TAG_CLASSNAME + " (" + rb + ',' + cb + ") " + '(' + rowLength + ',' + colWidth + ") " + '('
                + character + ')';
    }

    @Override
    protected StringBuilder buildSpecsInFormat() {
        StringBuilder specs = new StringBuilder();
        specs.append("\n").append(TAG_CLASSNAME);
        specs.append("\n").append(this.rb);
        specs.append("\t").append(this.cb);
        specs.append("\t").append(this.rowLength);
        specs.append("\t").append(this.colWidth);
        specs.append("\n").append(character);
        specs.append("\n.");
        return specs;
    }
}
