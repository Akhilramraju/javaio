public class HouseForSale {

    public static void main(String[] args) {
        // Create a window
        Window w = new Window(20, 30, '*');
// Draw the ground
        Line ground = new Line(19, 1, 29, 0, 1, '#');
        w.addTheShape(ground);
// Draw the post
        Line post = new Line(12, 5, 6, 1, 0, '#');
        w.addTheShape(post);
// Draw the light
        Circle light = new Circle(10, 5, 2, '+');
        w.addTheShape(light);
// Draw the house
        Rectangle house = new Rectangle(8, 16, 11, 10, '=');
        w.addTheShape(house);
// Draw the door
        Rectangle door = new Rectangle(11, 19, 8, 4, '=');
        w.addTheShape(door);
// Draw the roof
        Triangle roof = new Triangle(2, 21, 6, 1, 0, '*');
        w.addTheShape(roof);
// Display text message
        Text msg = new Text(2, 10, "FOR SALE", 1, 0);
        w.addTheShape(msg);
        w.showDisplay();
    }
}