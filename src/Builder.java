import java.util.ArrayList;

public class Builder extends Unit{
	public Builder(Field field, int x, int y, int speed, int health, int damage) {
		super(field, x, y, speed, health, damage);
	}
	
	@Override
	public void tick(ArrayList<Unit> units) {
		super.tick(units);
		ability.build(field, previousX, previousY);
		
		ArrayList<Unit> neighbours = ability.lookAround(this, units);
		for (Unit u: neighbours) {
			
			if (!u.isDead()) {
				this.clearPath();
				ability.attack(u, this.getDamage());
			}
		} 
	}
}