public class Builder extends Unit{
	public Builder(Field field, int x, int y, int speed, int health, int damage) {
		super(field, x, y, speed, health, damage);
	}
	
	@Override
	public void tick() {
		super.tick();
		ability.build(field, previousX, previousY);
	}
}