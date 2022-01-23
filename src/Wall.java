import java.awt.Color;
import java.awt.Graphics;

public class Wall {
	private Field field;
	private Grid grid;
	
	public Wall(Field field, Grid grid) {
		this.field = field;
		this.grid = grid;
	}

	public void render(Graphics g) {
		for (int i = 0; i < field.getRow(); i++) {
			for (int k = 0; k < field.getCol(); k++) {
				if (field.getMap()[i][k] == -2) {
					g.setColor(Color.GRAY);
					g.fillRect( grid.offsetX + k * grid.width,
								grid.offsetY + i * grid.height,
								grid.width, grid.height);
				}
			}
		}
	}
}