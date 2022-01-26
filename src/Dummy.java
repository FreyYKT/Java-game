public class Dummy extends Unit{
	public Dummy(Field field, int x, int y, int speed, int health, int damage) {
		super(field, x, y, speed, health, damage);
	}
	
	@Override
	public void tick() {
		super.tick2();
	}
}