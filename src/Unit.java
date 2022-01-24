import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Unit {
	protected Field field;
	protected Ability ability = new Ability();
	
	private ArrayList<Point> path = new ArrayList<Point>();
	private ArrayList<Integer> directions = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
	
	private HashMap<Integer, Integer> directionMap = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> turnMap = new HashMap<Integer, Integer>();
	
	protected int previousX, previousY;
	protected int x, y;
	private int targetX, targetY;
	private boolean selected = false;
	
	public Unit(Field field, int x, int y) {
		this.x = x;
		this.y = y;
		targetX = x;
		targetY = y;
		this.field = field;
		field.set(x, y, Field.UNIT);
		
		directionMap.put(-3, 0);
		directionMap.put(-2, 1);
		directionMap.put( 1, 2);
		directionMap.put( 4, 3);
		directionMap.put( 3, 4);
		directionMap.put( 2, 5);
		directionMap.put(-1, 6);
		directionMap.put(-4, 7);
		turnMap.put(1, -1);
		turnMap.put(2, -1);
		turnMap.put(3, -1);
		turnMap.put(4,  1);
		turnMap.put(5,  1);
		turnMap.put(6,  1);
		turnMap.put(7,  1);
	}
	
	public int getY() {return y;}
	public int getX() {return x;}
	public void setY(int y) {this.y = y;}
	public void setX(int x) {this.x = x;}

	public boolean isSelected() {return selected;}
	public void setSelected(boolean selected) {this.selected = selected;}
	
	public int getDirection(){ return directions.get(0); }
	
	public void setTarget(int targetX, int targetY) {
		if (targetX >= 0 && targetX < field.getCol() && targetY >= 0 && targetY < field.getRow()) {
			if (field.getMap()[targetY][targetX] != Field.FREE)
				return;
			
			this.targetX = targetX;
			this.targetY = targetY;
			path = new WaveAlg().findPath(field.getMap(), this.x, this.y, targetX, targetY);
		}
	}
	
	public void tick() {
		if(path!=null && path.size()>1) {
			Point p = path.get(1);
			int newTrend = directionMap.get((p.x - x) + 3*(p.y - y));
//			p.x-x:		 p.y-y:			sum:			directionMap:
//			-1 0 1		 1  1  1		 2  3  4		-4 -3 -2
//			-1 ^ 1		 0  ^  0		-1  ^  1		-1  ^  1
//			-1 0 1		-1 -1 -1		-4 -3 -2		 2  3  4
				
			if(directions.get(0) == newTrend) {
				if (field.get(p.x, p.y) == Field.FREE) {
					field.set(x, y, Field.FREE);
					previousX = x;
					previousY = y;
					x = p.x;
					y = p.y;
					field.set(x, y, Field.UNIT);
					path.remove(1);
				}
			}else {
				int additiveTurn = 0;
				for(int i = 0; i < directions.size(); i++) {
					if(directions.get(i) == newTrend) {
						additiveTurn = i; break;
					}
				}
				Collections.rotate(directions, turnMap.get(additiveTurn));
			}
		}
	}
	
	public void tick2() { //no wave algorithm
		int _x = x;
		int _y = y;
		if (x > targetX) {
			_x--;
		}else if(x < targetX) {
			_x++;
		}
		
		if (y > targetY) {
			_y--;
		}else if(y < targetY) {
			_y++;
		}
		if (field.get(_x, _y) == Field.FREE) {
			field.set(x, y, Field.FREE);
			x = _x;
			y = _y;
			field.set(x, y, Field.UNIT);
		}
	}
}