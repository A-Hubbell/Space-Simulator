import java.util.Iterator;


public class Screen {

	private int width;
	private int height;
	private int[] pixels;
	
	private Universe u;
	
	public Screen (Universe u) {
		this.width = 300;
		this.height = 175;
		this.pixels = new int[this.width * this.height];
		this.u = u;
	}
	
	public Screen(int width, int height, Universe u) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		this.u = u;
	}
	
	public void render() {
		//TODO: call u.update() and modifiy the pixels array using the positions of SpaceObjects
		
		//Change "pixels" here
		
		Iterator<Planet> it = u.planetsIterator();
		
		while(it.hasNext()) {
			Planet p = it.next();
			pixels[(int) (p.getState().getX() + this.width*p.getState().getY())] = 0xffffff;
		}
	}
	
	public void clear() {
		for (int i=0; i<pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public int[] getPixels() { return pixels; }
	public void setPixels(int[] pixels) { this.pixels = pixels; }

	public int getWidth() { return width; }

	public int getHeight() { return height; }
	
	
	
}
