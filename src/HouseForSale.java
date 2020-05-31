public class HouseForSale {

	public static void main(String[] args) throws Exception {

		Window w = new Window(20, 30, '*');

		//To draw the following shapes
		Line ground = new Line(19, 1, 29, 0, 1, '#');
		w.addTheShape(ground);
		Line post = new Line(12, 5, 6, 1, 0, '#');
		w.addTheShape(post);
		Circle light = new Circle(10, 5, 2, '+');
		w.addTheShape(light);
		Rectangle house = new Rectangle(8, 16, 11, 10, '=');
		w.addTheShape(house);
		Rectangle door = new Rectangle(11, 19, 8, 4, '=');
		w.addTheShape(door);
		Triangle roof = new Triangle(2, 21, 6, 1, 0, '*');
		w.addTheShape(roof);
		Text msg = new Text(2, 10, "FOR SALE", 1, 0);
		w.addTheShape(msg);
		w.showDisplay();
	}
}