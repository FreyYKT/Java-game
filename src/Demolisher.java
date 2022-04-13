import java.util.ArrayList;

public class Demolisher extends Unit {
	public Demolisher(Field field, int x, int y, int speed, int health, int damage) {
		super(field, x, y, speed, health, damage);
	}

	@Override
	public void tick(ArrayList<Unit> units) {
		super.tick(units);
		ability.demolish(this.field, this.x, this.y, this.getDirection());
		
		ArrayList<Unit> neighbours = ability.lookAround(this, units);
		for (Unit u: neighbours) {
			
			if (!u.isDead()) {
				this.clearPath();
				if (ability.isFaceToFace(this, u)) {
					ability.attack(u, this.getDamage());
				}
			}
		} 
	}
}