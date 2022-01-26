public class Demolisher extends Unit {
	public Demolisher(Field field, int x, int y, int speed, int health, int damage) {
		super(field, x, y, speed, health, damage);
	}

	@Override
	public void tick() {
		super.tick();
		ability.demolish(this.field, this.x, this.y, this.getDirection());
	}
}