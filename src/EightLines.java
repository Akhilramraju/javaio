public class EightLines {
    public static void main(String[] args) throws Exception {
        Window window = new Window(20, 20, '*');
        int row = 10, column = 10, length = 5;
        Line line = new Line(row, column, length, 0, 1, '1');
        window.addTheShape(line);
        line = new Line(row, column, length, 1, 1, '2');
        window.addTheShape(line);
        line = new Line(row, column, length, 1, 0, '3');
        window.addTheShape(line);
        line = new Line(row, column, length, 1, -1, '4');
        window.addTheShape(line);
        line = new Line(row, column, length, 0, -1, '5');
        window.addTheShape(line);
        line = new Line(row, column, length, -1, -1, '6');
        window.addTheShape(line);
        line = new Line(row, column, length, -1, 0, '7');
        window.addTheShape(line);
        line = new Line(row, column, length, -1, 1, '8');
        window.addTheShape(line);
        window.showDisplay();
    }
}