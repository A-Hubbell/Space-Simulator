public class State {
		
	//Class attributes: x/y position, x/y velocity, x/y acceleration
	private double x, y, vx, vy, ax, ay;
		
	//Constructors
	public State() {
		this.x = Math.random()*Universe.X_DIM;
		this.y = Math.random()*Universe.Y_DIM;
		this.vx = Math.random()*Universe.DEFAULT_VELOCITY_FACTOR;
		this.vy = Math.random()*Universe.DEFAULT_VELOCITY_FACTOR;
		this.ax = Math.random()*Universe.DEFAULT_ACCELERATION_FACTOR;
		this.ay = Math.random()*Universe.DEFAULT_ACCELERATION_FACTOR;
	}
	
	public State(double x, double y, double vx, double vy, double ax, double ay) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.ax = ax;
		this.ay = ay;
	}

	//Accessor and modifier methods
	public double getX() { return x; }
	public void setX(double x) { this.x = x; }

	public double getY() { return y; }
	public void setY(double y) { this.y = y; }

	public double getVx() { return vx; }
	public void setVx(double vx) { this.vx = vx; }

	public double getVy() { return vy; }
	public void setVy(double vy) { this.vy = vy; }

	public double getAx() { return ax; }
	public void setAx(double ax) { this.ax = ax; }

	public double getAy() { return ay; }
	public void setAy(double ay) { this.ay = ay; }
	
	
	public boolean equals (State s) {
		if (this.x == s.getX() && this.y == s.getY() && this.vx == s.getVx() && this.vy == s.getVy() && this.ax == s.getAx() && this.ay == s.getAy())
			return true;
		return false;
	}
		
	//TODO: Implement toString()
	public String toString () {
		return null;
	}
	
}