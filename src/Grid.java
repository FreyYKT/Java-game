

import java.awt.Color;
import java.awt.Graphics;

public class Grid {
	//grid offsets
	public int offsetX = 191;
	public int offsetY = 100;
	//cell sizes
	public int width = 50;
	public int height = 50;
	//cells amount
	public int col;
	public int row;
	
	public Grid(Field field) {
		this.col = field.getCol();
		this.row = field.getRow();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		for (int i = 0; i < row + 1; i++) //Вертикальные
			g.drawLine(offsetX + i*width, offsetY, offsetX + i*height, offsetY + col*height);
		for (int i = 0; i < col + 1; i++) //Горизонтальные
			g.drawLine(offsetX, offsetY + i*height, offsetX + row*height, offsetY + i*height);
	}
}