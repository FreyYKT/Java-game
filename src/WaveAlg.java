import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WaveAlg {
	public ArrayList<Point> findPath(int[][] map, int x, int y, int targetX, int targetY){
		ArrayList<Point> wave = new ArrayList<Point>();
		List<Point> oldWave = new ArrayList<Point>();
		int[][] cloneMap = clone(map);
		int stepNum = 0;
		
		int[] dx = { 0, 1, 0,-1,-1, 1,-1, 1};
		int[] dy = {-1, 0, 1, 0,-1,-1, 1, 1};
		int neighbourNum = dx.length;
		
		oldWave.add(new Point(x, y));
		cloneMap[y][x] = stepNum;
		
		while(oldWave.size()>0) {
			stepNum++;
			wave.clear();
			for(Point p : oldWave) {
				for(int i = 0; i < neighbourNum; i++) {
					x = p.x + dx[i];
					y = p.y + dy[i];
					if ((x >= 0 && x < cloneMap[0].length) && (y >= 0 && y < cloneMap.length)) {
						if (cloneMap[y][x] == Field.FREE) {
							cloneMap[y][x] = stepNum;
							wave.add(new Point(x, y));
						}
					}
				}
			}
			oldWave = new ArrayList<Point>(wave);
		}
		
		boolean fl = true;
		wave.clear();
		
		if(cloneMap[targetY][targetX] > 0) {
			wave.add(new Point(targetX, targetY));
			
			while(cloneMap[targetY][targetX] != 0) {
				fl = true;
				for(int i = 0; i < neighbourNum; i++) {
					x = targetX + dx[i];
					y = targetY + dy[i];
					if ((x >= 0 && x < cloneMap[0].length) && (y >= 0 && y < cloneMap.length)) {
						if (cloneMap[targetY][targetX] - 1 == cloneMap[y][x]) {
							targetX = x;
							targetY = y;
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
		}else {
			System.out.println("No way.");
		}
		
		return wave;
	}
	
	private int[][] clone(int[][] map) {
		int[][] cloneMap = new int[map.length][map[0].length];
		
		for (int i = 0; i < map.length; i++)
			for (int j=0; j < map[i].length; j++)
				cloneMap[i][j] = map[i][j];
		
		return cloneMap;
	}
}