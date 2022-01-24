public class Demolisher extends Unit {
	public Demolisher(Field field, int x, int y) {
		super(field, x, y);
	}

	@Override
	public void tick() {
		super.tick();
		ability.demolish(this.field, this.x, this.y, this.getDirection());
	}
}