public class T7Tester {

    public static void main(String[] args) {
        Window window = new Window(30, 30, '*');
        Oval oval = new Oval(10, 10, 5, 5, '*');
        window.addTheShape(oval);
        window.showDisplay();
    }
}
