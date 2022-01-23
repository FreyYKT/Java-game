public class Builder extends Unit{
	public Builder(Field field, int x, int y) {
		super(field, x, y);
	}
	
	@Override
	public void tick() {
		super.tick();
		ability.build(field, previousX, previousY);
	}
}