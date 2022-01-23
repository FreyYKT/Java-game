public class Field {
	private final int col = 8;
	private final int row = 8;
	int[][] map = new int[row][col];
	
	public static final int FREE = -1;
	public static final int WALL = -2;
	public static final int UNIT = -5;
	
	public Field() {
		super();
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j] = FREE;
			}
		} 
		
		map[0][0] = this.WALL;
		map[0][1] = this.WALL;
		map[1][0] = this.WALL;
		map[0][2] = this.WALL;
		map[1][2] = this.WALL;
		map[2][2] = this.WALL;
		map[2][3] = this.WALL;
		map[2][4] = this.WALL;
		map[3][3] = this.WALL;
		map[4][3] = this.WALL;
		
		map[6][7] = this.WALL;
		map[7][7] = this.WALL;
		map[7][6] = this.WALL;
		map[7][5] = this.WALL;
	}
	
	public int[][] getMap() { return map; }

	public int getCol() { return map[0].length; }
	public int getRow() { return map.length; }

	public void set(int x, int y, int status) { map[y][x] = status; }
	public int get(int x, int y) { return map[y][x]; }
}