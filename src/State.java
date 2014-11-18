public class State {
		
	//Class attributes: mass, radius, x/y position, x/y velocity, x/y acceleration, x/y net force
	private double mass, radius, x, y, vx, vy, ax, ay, fx, fy;
		
	//Constructors
	public State() {
		this.mass = Math.random()*Universe.DEFAULT_MASS;
		this.radius = Math.random()*Universe.DEFAULT_RADIUS;
		this.x = Math.random()*Universe.X_DIM;
		this.y = Math.random()*Universe.Y_DIM;
		this.vx = Math.random()*Universe.DEFAULT_VELOCITY_FACTOR;
		this.vy = Math.random()*Universe.DEFAULT_VELOCITY_FACTOR;
		this.ax = Math.random()*Universe.DEFAULT_ACCELERATION_FACTOR;
		this.ay = Math.random()*Universe.DEFAULT_ACCELERATION_FACTOR;
		this.fx = Math.random();
		this.fy = Math.random();
	}
	
	public State(double m, double r, double x, double y, double vx, double vy, double ax, double ay, double fx, double fy) {
		this.mass = m;
		this.radius = r;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.ax = ax;
		this.ay = ay;
		this.fx = fx;
		this.fy = fy;
	}
	
	public State (State s) {
		this.mass = s.getMass();
		this.radius = s.getRadius();
		this.x = s.getX();
		this.y = s.getY();
		this.vx = s.getVx();
		this.vy = s.getVy();
		this.ax = s.getAx();
		this.ay = s.getAy();
	}

	//Accessor and modifier methods
	public double getMass () { return this.mass; }
	public void setMass (double m) { this.mass = m; }
	
	public double getRadius () { return this.radius; }
	public void setRadius (double r) { this.radius = r; }
	
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
	
	public double getFx () { return this.fx; }
	public void setFx (double fx) { this.fx = fx; }
	
	public double getFy () { return this.fy; }
	public void setFy (double fy) { this.fy = fy; }
	

	public boolean equals (State s) {
		if (this.mass == s.getMass() && this.radius == s.getRadius() && this.x == s.getX() && this.y == s.getY() && this.vx == s.getVx() && this.vy == s.getVy() && this.ax == s.getAx() && this.ay == s.getAy() && this.fx == s.getFx() && this.fy == s.getFy())
			return true;
		return false;
	}
		
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append("Mass: " + this.mass + "  ");
		sb.append("Radius: " + this.radius + "  ");
		sb.append("Position: [");
		sb.append(this.x + ", " + this.y + "]  ");
		sb.append("Velocity: [");
		sb.append(this.vx + ", " + this.vy + "]  ");
		sb.append("Acceleration: [");
		sb.append(this.ax + ", " + this.ay + "]  ");
		sb.append("Net Force: [");
		sb.append(this.fx + ", " + this.fy + "]  ");
		
		return sb.toString();
	}
	
}