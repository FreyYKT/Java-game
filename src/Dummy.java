public class Dummy extends Unit{
	public Dummy(Field field, int x, int y) {
		super(field, x, y);
	}
	
	@Override
	public void tick() {
		super.tick2();
	}
}