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
	
	public boolean isFaceToFace(Unit unit, Unit enemy) {
		int enemyDir = getDirection(unit, enemy);
		if (unit.getDirection() == enemyDir) {
			return true;
		}else {
			unit.changeDirection(enemyDir);
			return false;
		}
	}
	
	public int getDirection(Unit player, Unit enemy) {
		double angle = Math.atan2(enemy.y - player.y, enemy.x - player.x);
		int dir = 0;
		
			 if(angle>-0.39269908169872414 && angle<=0.39269908169872414) //0
			dir = 2;
		else if(angle>0.39269908169872414 && angle<=1.1780972450961724) //45
			dir = 3;
		else if(angle>1.1780972450961724 && angle<=1.9634954084936207) //90
			dir = 4;
		else if(angle>1.9634954084936207 && angle<=2.748893571891069) //135
			dir = 5;
		else if(angle>2.748893571891069 || angle<=-2.748893571891069) //180
			dir = 6;
		else if(angle>-2.748893571891069 && angle<=-1.9634954084936207) //225
			dir = 7;
		else if(angle>-1.9634954084936207 && angle<=-1.1780972450961724) //270
			dir = 0;
		else if(angle>-1.1780972450961724 && angle<=-0.39269908169872414) //315
			dir = 1;
			
		return dir;
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