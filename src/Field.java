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
		
		map[0][0] = Field.WALL;
		map[0][1] = Field.WALL;
		map[1][0] = Field.WALL;
		map[0][2] = Field.WALL;
		map[1][2] = Field.WALL;
		map[2][2] = Field.WALL;
		map[2][3] = Field.WALL;
		map[2][4] = Field.WALL;
		map[3][3] = Field.WALL;
		map[4][3] = Field.WALL;
		
		map[6][7] = Field.WALL;
		map[7][7] = Field.WALL;
		map[7][6] = Field.WALL;
		map[7][5] = Field.WALL;
	}
	
	public int[][] getMap() { return map; }

	public int getCol() { return map[0].length; }
	public int getRow() { return map.length; }

	public void set(int x, int y, int status) { if (x >= 0 && x <= map[0].length && y >= 0 && y <= map.length) map[y][x] = status; }
	public int get(int x, int y) { return map[y][x]; }
}