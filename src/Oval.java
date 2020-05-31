public class Oval extends Shape {

    public static final String TAG_CLASSNAME = "oval";

    //Incremental counters
    
    int rowLength; 
    int colWidth; 


    public Oval(int rowBase, int colBase, int rowLength, int colLength, char displayChar) {
        this.rb = rowBase; 
        this.cb = colBase; 
        this.rowLength = rowLength;
        this.colWidth = colLength;
        this.character = displayChar;
    }

    //Angle is changed from 0 - 360
    @Override
    public void draws(Window window) throws Exception {
        for (int theta = 0; theta < 360; theta += 12) {
            
        	//Math.PI function is used for the conversion of radians
        	//Math.round is used for getting the value as a whole number and not a decimal
        	
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
