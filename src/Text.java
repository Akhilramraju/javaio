/*This is just an initial skeleton of the class to help you get started.
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports.*/


public class Text extends Shape {
    public static final String TAG_CLASSNAME = "text";
    private String text;
    private int rInc;
    private int cInc;
    private int rb;
    private int cb;

    //define the constructor following the signature in the specification
    public Text(int rowBase, int colBase, String text, int rowIncrement, int colIncrement) {
        this.rb = rowBase;
        this.cb = colBase;
        this.text = text;
        this.rInc = rowIncrement;
        this.cInc = colIncrement;
    }

    public void draws(Window window) throws Exception {
        //assuming row position of the base point of this text is 'rb'
        //assuming column position of the base point of this text is 'cb'

        for (int i = 0; i < text.length(); i++) {
            character = text.charAt(i);
            int row = rb + 1 + i * rInc;
            int col = cb + 1 + i * cInc;

            //appropriate call to setCell() method of the Window class...
            window.setTheCell(row, col, character);
        }
    }

    @Override
    public String toString() {
        return TAG_CLASSNAME + " (" + rb + ',' + cb + ") " + '(' + rInc + ',' + cInc + ") " + '(' + text + ')';
    }

    //define other methods...
    @Override
    protected StringBuilder buildSpecsInFormat() {
        StringBuilder specs = new StringBuilder();
        specs.append("\n").append(TAG_CLASSNAME);
        specs.append("\n").append(this.rb);
        specs.append("\t").append(this.cb);
        specs.append("\n").append(this.text);
        specs.append("\n").append(this.rInc);
        specs.append("\t").append(this.cInc);
        specs.append("\n.");
        return specs;
    }

}
