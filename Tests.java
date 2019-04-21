import junit.framework.*;
public class Tests {
	@Before
	public void init() {
		Model model = new Model();
		View view = new View();
	}
	
	@Test
	public static void testDetectCollisions() {
		assertEquals(0, model.detectCollisions());
		assertEquals(1, model.detectCollisions());
	}
	public static void testMove() {
		assertEquals(0, model.move());
		assertEquals(1, model.move());
	}

	public static void testRegen() {
		model.energyLevel = 50;
		model.regen();
		assertEquals(52, model.energyLevel);
	}
	public static void testFatigue() {
		model.energyLevel = 50;
		model.fatigue();
		assertEquals(49, model.energyLevel);
	}
	public static void testBuildNest() {
		model.nestProgress = 0;
		model.buildNest();
		assertEquals(10, model.nestProgress);
	public static void testMove() {
		assertEquals(0, nestpiece.move());
		assertEquals(1, nestpiece.move());
	}
}