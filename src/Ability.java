public class Ability {
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