import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

public class UnitController {
	private Unit[] units = new Unit[3];
	private Grid grid;
	
	public UnitController(Field field, Grid grid) {
		units[0]= new Unit(field, 3, 1);
		units[1]= new Builder(field, 2, 3);
		units[2]= new Dummy(field, 7, 5);
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
			int _x = u.getX()*grid.width + grid.offsetX;
			int _y = u.getY()*grid.height + grid.offsetY;
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(
			    RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setStroke(new BasicStroke(1f));
			 
			g2d.setColor(Color.BLUE);
			g2d.fillOval(_x, _y, grid.width, grid.height);
			
			g2d.setColor(Color.BLACK);
			g2d.fillArc(_x, _y, grid.width, grid.height, 68 - 45*u.getDirection(), 44);
			
			g2d.setColor(Color.YELLOW);
			g2d.drawString(u.getClass().getSimpleName(), _x + 5, _y + 40);
			
			if(u.isSelected()) {
				g2d.setStroke(new BasicStroke(2f));
				g2d.setColor(Color.RED);
				g2d.drawOval(_x, _y, grid.width, grid.height);
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
