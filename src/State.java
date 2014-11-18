/* Copyright (c) 2014 Sci-Tech Innovations. All Rights Reserved.
 * 
 * This software is part of a larger space simulation project at Sci-Tech Innovations.
 * It can be used, modified, and distributed for educational purposes free of charge, 
 * as long as it is not used for, or in conjunction with, any monetary gain. 
 */


/**
 * This class is designed to store all relevant mechanics information of a rigid body, 
 * allowing a single object to represent the entire instantaneous state of a physical
 * object. This means storing mass, radius, x & y positions, x&y velocities, 
 * x&y accelerations, and x&y net forces acting on the physical object.
 * 
 * @author Austin
 * @version 0.1
 */ 
public class State {
		
	/** Mass of the physical object */
	private double mass;
	/** Radius of the physical object */
	private double radius;
	/** x-position of the physical object */
	private double x;
	/** y-position of the physical object */
	private double y;
	/** x-velocity of the physical object */
	private double vx;
	/** y-velocity of the physical object */
	private double vy;
	/** x-acceleration of the physical object */
	private double ax;
	/** y-acceleration of the physical object */
	private double ay;
	/** x-net force acting on the physical object */
	private double fx;
	/** y-net force acting on the physical object */
	private double fy;
	
	
	/**
	 * Initialize a new State object with randomized attributes (weighted by
	 * the static constants specified in "Universe").
	 */
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
	
	
	/**
	 * Initialize a new State object with mass "m", radius "r", x-position "x",
	 * y-position "y", x-velocity "vx", y-velocity "vy", x-acceleration "ax", 
	 * y-acceleration "ay", x-net force "fx", y-net force "fy".
	 * 
	 * @param m		mass of new State object.
	 * @param r		radius of new State object.
	 * @param x		x-position of new State object.
	 * @param y		y-position of new State object.
	 * @param vx	x-velocity of new State object.
	 * @param vy	y-velocity of new State object.
	 * @param ax	x-acceleration of new State object.
	 * @param ay	y-acceleration of new State object.
	 * @param fx	x-net force of new State object.
	 * @param fy	y-net force of new State object
	 */
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
	
	/**
	 * Copy constructor: initialize a new State object that is a deep copy 
	 * of State object "s".
	 * 
	 * @param s		State object to be copied.
	 */
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
	
	/**
	 * Check if two State objects are equal. Two State objects are considered
	 * equal if they both contain the same mass, radius, x&y position, x&y 
	 * velocity, x&y acceleration, and x&y net force.
	 * 
	 * @param s 	State object to be compared to the calling State object.
	 * @return		true if both State objects are considered equal, otherwise false.
	 */
	public boolean equals (State s) {
		if (this.mass == s.getMass() && this.radius == s.getRadius() && this.x == s.getX() && this.y == s.getY() && this.vx == s.getVx() && this.vy == s.getVy() && this.ax == s.getAx() && this.ay == s.getAy() && this.fx == s.getFx() && this.fy == s.getFy())
			return true;
		return false;
	}
	
	/**
	 * Generate and return a string that is representative of the calling State object.
	 * This is done by creating a new string containing all of the attributes of the 
	 * calling object and their values.
	 * 
	 * @return 		string representative of the calling State object.
	 */	
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