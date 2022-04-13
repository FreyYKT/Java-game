import java.util.ArrayList;

public class Ability {
	public void attack(Unit unit, int damage) {
		unit.takeDamage(damage);
	}
	
	public ArrayList<Unit> lookAround(Unit player, ArrayList<Unit> units) {
		ArrayList<Unit> neighbours = new ArrayList<Unit>();
		int[] dx = { 0, 1, 0, -1,-1, 1,-1, 1};
		int[] dy = {-1, 0, 1,  0,-1,-1, 1, 1};
		for(Unit u: units) {
			int neighbourNum = dx.length;
			int x = 0;
			int y = 0;
			for(int _d = 0; _d < neighbourNum; _d++) {
				x = player.getX()+dx[_d];
				y = player.getY()+dy[_d];
				if(u.getX() == x && u.getY() == y) {
					neighbours.add(u);
				}
			}
		}
		
		return neighbours;
	}
	
	public void build(Field field, int x, int y) {
		field.set(x, y, Field.WALL);
	}
	
	public void demolish(Field field, int x, int y, int direction) {
		switch(direction) {
		case 0:
			field.set(x, y-1, Field.FREE);
			break;
		case 1:
			field.set(x+1, y-1, Field.FREE);
			break;
		case 2:
			field.set(x+1, y, Field.FREE);
			break;
		case 3:
			field.set(x+1, y+1, Field.FREE);
			break;
		case 4:
			field.set(x, y+1, Field.FREE);
			break;
		case 5:
			field.set(x-1, y+1, Field.FREE);
			break;
		case 6:
			field.set(x-1, y, Field.FREE);
			break;
		case 7:
			field.set(x-1, y-1, Field.FREE);
			break;
		}	
	}
}