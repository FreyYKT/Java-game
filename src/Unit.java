
import java.util.ArrayList;

public class Unit {
	private Field field;
	private int x, y;
	private boolean selected = false;
	private int targetX;
	private int targetY;
	private ArrayList<Point> path = new ArrayList<Point>();
	
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
			if (field.getMap()[targetY][targetX] != Field.FREE)
				return;
			
			this.targetX = targetX;
			this.targetY = targetY;
			path = new WaveAlg().findPath(field.getMap(), this.x, this.y, targetX, targetY);
		}
	}
	
	public int getDir(){
		return 0;
	}
	
	public void tick() {
		if(path!=null && path.size()>1) {
			Point p = path.get(1);
			if (field.get(p.x, p.y) == Field.FREE) {
				field.set(x, y, Field.FREE);
				x = p.x;
				y = p.y;
				field.set(x, y, field.UNIT);
				path.remove(1);
			}
		}
	}
}