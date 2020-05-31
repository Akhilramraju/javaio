public class Logger {

    private static final char EMPTY_CHAR = ' '; // empty char is separator between subsequent characters
    private static final char NEW_LINE = '\n';

    public static void printChar(char value) {
        System.out.print(value);
    }

    public static void addSeparator() {
        System.out.print(EMPTY_CHAR);
    }

    public static void addNewLine() {
        System.out.print(NEW_LINE);
    }

    public static void println(String value) {
        System.out.println(value);
    }

    public static void print(String value) {
        System.out.print(value);
    }
}
