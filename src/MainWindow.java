

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class MainWindow extends JFrame implements Runnable {
	private Field field = new Field();
	private Grid grid = new Grid(field);
	private Wall wall = new Wall(field, grid);
	private UnitController unitController = new UnitController(field, grid);
	
	public static void main(String[] args) {
		MainWindow window = new MainWindow();
		window.setBounds(0, 0, 800, 600);
		window.setDefaultCloseOperation(3);
		window.setVisible(true);
		
		Thread thread = new Thread(window);
		thread.run();
	}
	
	public MainWindow() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				unitController.mouseClicked(e);
				repaint();
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		this.createBufferStrategy(2);
		BufferStrategy bs = this.getBufferStrategy();
		g = bs.getDrawGraphics();
		
		super.paint(g);
		grid.render(g);
		wall.render(g);
		unitController.render(g);
		
		bs.show();
	}

	@Override
	public void run() {
		while(true){
			unitController.tick();
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}