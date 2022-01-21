

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class UnitController {
	private Unit[] units = new Unit[3];
	private Grid grid;
	
	public UnitController(Field field, Grid grid) {
		units[0]= new Unit(field, 3, 1);
		units[1]= new Unit(field, 2, 3);
		units[2]= new Unit(field, 7, 5);
		this.grid = grid;
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getX() < grid.offsetX || e.getY() < grid.offsetY) {
			return;
		}
		int sx = (e.getX()-grid.offsetX)/grid.width;
		int sy = (e.getY()-grid.offsetY)/grid.height;
		if(e.getButton()==1) {
			for(Unit u: units) {
				if(u.getX()==sx && u.getY()==sy) {
					u.setSelected(!u.isSelected());
				}
			}
		}else if (e.getButton()==3) {
			for(Unit u: units) {
				if (u.isSelected()) {
					u.setTarget(sx, sy);
				}
			}
		}
	}

	public void render(Graphics g) {
		for(Unit u: units) {
			g.setColor(Color.BLUE);
			g.fillOval(u.getX()*grid.width+grid.offsetX, u.getY()*grid.height
					+grid.offsetY, grid.width, grid.height);
			if(u.isSelected()) {
				g.setColor(Color.RED);
				g.drawOval(u.getX()*grid.width+grid.offsetX, u.getY()*grid.height
					+grid.offsetY, grid.width, grid.height);
			}
		}
	}

	public void tick() {
		for (Unit u: units) {
			if (u.isSelected()) {
				u.tick();
			}
		}
	}
	
}
