public class Logger {

    private static final char EMPTY_CHAR = ' '; 
    private static final char NEW_LINE = '\n';

    //Parameterized constructor has been made by inputing the character value
    
    public static void printChar(char valueInput) {
        System.out.print(valueInput);
    }

    //addsSeperator is the constructor
    
    public static void addsSeparator() {
        System.out.print(EMPTY_CHAR);
    }

    //addsNewLine is used for adding a new line
    public static void addsNewLine() {
        System.out.print(NEW_LINE);
    }

   
    //valueInput is the constructor
    public static void println(String valueInput) {
        System.out.println(valueInput);
    }

    
    public static void print(String valueInput) {
        System.out.print(valueInput);
    }
}
