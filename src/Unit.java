

public class Unit {
	private Field field;
	private int x, y;
	private boolean selected = false;
	private int targetX;
	private int targetY;
	
	public Unit(Field field, int x, int y) {
		this.x = x;
		this.y = y;
		targetX = x;
		targetY = y;
		this.field = field;
		field.set(x, y, field.UNIT);
	}
	
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	
	public int getY() {return y;}
	public void setY(int y) {this.y = y;}

	public boolean isSelected() {return selected;}
	public void setSelected(boolean selected) {this.selected = selected;}
	
	public int getTx() {return targetX;}
	public void setTx(int targetX) { if (targetX>=0 && targetX <= field.getCol()) this.targetX = targetX;}

	public int getTy() {return targetY;}
	public void setTy(int targetY) { if (targetY>=0 && targetY <= field.getRow()) this.targetY = targetY; }

	public void setTarget(int targetX, int targetY) {
		if (targetX >= 0 && targetX < field.getCol() && targetY >= 0 && targetY < field.getRow()) {
			this.targetX = targetX;
			this.targetY = targetY;
		}
	}
	
	public void tick() {
		int nextX = x;
		int nextY = y;
		if (x > targetX) {
			nextX--;
		}else if(x < targetX) {
			nextX++;
		}
		
		if (y > targetY) {
			nextY--;
		}else if(y < targetY) {
			nextY++;
		}
		if (field.get(nextX, nextY) == 0) {
			field.set(x, y, field.FREE);
			x = nextX;
			y = nextY;
			field.set(x, y, field.UNIT);
		}
	}
}