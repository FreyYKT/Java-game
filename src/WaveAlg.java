import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WaveAlg {
	
	ArrayList<Point> wave = new ArrayList<Point>();
	List<Point> oldWave = new ArrayList<Point>();
	int[][] cloneMap;
	int stepNum;
	
	int[] dx = { 0, 1, 0,-1,-1, 1,-1, 1};
	int[] dy = {-1, 0, 1, 0,-1,-1, 1, 1};
	int neighbourNum = dx.length;
	
	int targetX, targetY, x, y;
	
	int _x, _y; //sub-vars
	
	public ArrayList<Point> findPath(int[][] map, int x, int y, int targetX, int targetY){
		this.targetX = targetX;
		this.targetY = targetY;
		this.x = x;
		this.y = y;
		
		System.out.println("======" + targetX + " " + targetY);
		
		cloneMap = clone(map);
		stepNum = 0;
		oldWave.clear();
		oldWave.add(new Point(x, y));
		cloneMap[y][x] = stepNum;
		
		makeWave();

		
		if(cloneMap[targetY][targetX] > 0) { //Way found
			waveRecovery();
		}else { //No way, but try get closer
			Point newTarget = getCloser();
			print(cloneMap);
			if (newTarget.x >= 0 && newTarget.y >= 0) {
				wave = findPath(map, x, y, newTarget.x, newTarget.y);
				waveOut(wave);
			}else {
				System.out.println("No way."); 
			}
			
		}
		
		return wave;
	}
	
	private void makeWave() {
		while(oldWave.size()>0) {
			stepNum++;
			wave.clear();
			for(Point p : oldWave) {
				for(int i = 0; i < neighbourNum; i++) {
					_x = p.x + dx[i];
					_y = p.y + dy[i];
					if ((_x >= 0 && _x < cloneMap[0].length) && (_y >= 0 && _y < cloneMap.length)) {
						if (cloneMap[_y][_x] == Field.FREE) {
							cloneMap[_y][_x] = stepNum;
							wave.add(new Point(_x, _y));
						}
					}
				}
			}
			oldWave = new ArrayList<Point>(wave);
		}
	}
	
	private void waveRecovery() {
		boolean fl = true;
		wave.clear();
		wave.add(new Point(targetX, targetY));
		
		while(cloneMap[targetY][targetX] != 0) {
			fl = true;
			for(int i = 0; i < neighbourNum; i++) {
				_x = targetX + dx[i];
				_y = targetY + dy[i];
				if ((_x >= 0 && _x < cloneMap[0].length) && (_y >= 0 && _y < cloneMap.length)) {
					if (cloneMap[targetY][targetX] - 1 == cloneMap[_y][_x]) {
						targetX = _x;
						targetY = _y;
						wave.add(new Point(targetX, targetY));
						fl = false;
						break;
					}
				}
				if(fl) {
					System.out.println("Something went wrong.");
				}
			}
		}
		
		for (Point p : wave) cloneMap[p.y][p.x] = 0;
		Collections.reverse(wave);
	}

	private Point getCloser() {
		int _min = 1000;
        
        Point newTarget = new Point(-1, -1);

        for (int i = 0; i < cloneMap.length; i++) {
            for (int j = 0; j < cloneMap[i].length; j++) {
                if (cloneMap[i][j] >= 0 && Math.max(Math.abs(targetY - i), Math.abs(targetX - j)) < _min) {
                    System.out.println("gc " + i + " " + j + "   " + cloneMap[i][j] + "   " + Math.max(Math.abs(targetY - i), Math.abs(targetX - j)));
                    _min = Math.max(Math.abs(targetY - i), Math.abs(targetX - j));
                    newTarget.x = j; newTarget.y = i;
                }
            }
        }
        
        if (_min == 0)
        	return new Point(-1, -1);
        else
        	return newTarget;
	}
	
	private int[][] clone(int[][] map) {
		int[][] cloneMap = new int[map.length][map[0].length];
		
		for (int i = 0; i < map.length; i++)
			for (int j=0; j < map[i].length; j++)
				cloneMap[i][j] = map[i][j];
		
		return cloneMap;
	}
	
	private int[][] print(int[][] map) {
		int[][] cloneMap = new int[map.length][map[0].length];
		
		for (int i = 0; i < map.length; i++) {
			for (int j=0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		
		return cloneMap;
	}
	
	public void waveOut(ArrayList<Point> wave) {
		for(Point p: wave) {
			System.out.println("x="+p.x+",y="+p.y);
		}
	}
}