import junit.framework.*;
public class Tests {
	@Test
	Model model = new Model();
	View view = new View();
	Food food = new Food();;
	NestPiece nestpiece = new NestPiece();
	public static void testDetectCollisions() {
		assertEquals(0, model.detectCollisions());
		assertEquals(1, model.detectCollisions());
	}
	public static void testMove() {
		assertEquals(0, model.move());
		assertEquals(1, model.move());
	}
	public static void testCreatImage() {
		assertNull(view.createImage());
		assertNotNull(view.createImage());
	}
	public static void testRegen() {
		assertEquals(0, food.move());
		assertEquals(1, food.move());
	}
	public static void testMove() {
		assertEquals(0, nestpiece.move());
		assertEquals(1, nestpiece.move());
	}
}