public class Logger {

    private static final char EMPTY_CHAR = ' '; // empty char is separator between subsequent characters
    private static final char NEW_LINE = '\n';

    public static void printChar(char valueInput) {
        System.out.print(valueInput);
    }

    public static void addsSeparator() {
        System.out.print(EMPTY_CHAR);
    }

    public static void addsNewLine() {
        System.out.print(NEW_LINE);
    }

    public static void println(String valueInput) {
        System.out.println(valueInput);
    }

    public static void print(String valueInput) {
        System.out.print(valueInput);
    }
}
