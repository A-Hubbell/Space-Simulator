import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Iterator;

import javax.swing.JFrame;


public class Visualizer extends Canvas implements Runnable {

	/**
	 * 
	 */
	
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	
	private static final long serialVersionUID = 1L;

	public JFrame frame;
	private boolean running = false;
	private Thread thread;
	private Screen screen;
	//Create image
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Visualizer (Universe u) {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		this.screen = new Screen(width, height, u);
		
		this.frame = new JFrame();
	}

	
	//Accessor and modifier methods
	public JFrame getFrame () { return frame; }
	public void setFrame(JFrame frame) { this.frame = frame; }

	public boolean isRunning () { return running; }
	public void setRunning(boolean running) { this.running = running; }

	public Thread getThread () { return thread; }
	public void setThread (Thread thread) { this.thread = thread; }
	
	
	//Starts the game running
	public synchronized void start () {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	//Stops the game running
	public synchronized void stop () {
		running = false;
		try {
			thread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	//Runs the game by rendering and updating
	public void run () {
		while (running) {
			this.update();    //60 FPS
			this.render();    //Speed not limited
		}
	}
	
	//This will be restricted to run at 60 FPS
	public void update () {
		 
	}
	
	//This will run as fast as the host computer will allow
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);    //triple buffering
			return;
		}
		
		//Clear the screen (all black), then render the new image on the clear screen
		screen.clear();    
		screen.render();
		
		for (int i=0; i<pixels.length; i++) {
			pixels[i] = screen.getPixels()[i];
		}
		
		Graphics g = bs.getDrawGraphics();    //all graphics must happen after this call and before "g.dispose()"
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());    //(0,0) is the top left corner
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();    //release all resources back to system
		bs.show();    //display the back buffer that has been calculated
	}

}
