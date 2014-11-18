/* Copyright (c) 2014 Sci-Tech Innovations. All Rights Reserved.
 * 
 * This software is part of a larger space simulation project at Sci-Tech Innovations.
 * It can be used, modified, and distributed for educational purposes free of charge, 
 * as long as it is not used for, or in conjunction with, any monetary gain. 
 */


/**
 * This class is an extension of the SpaceObject class, intended to represent physical 
 * planets as objects of the Planet class. This class contains attributes and methods 
 * pertaining specifically to physical planets.
 * 
 * @author Austin
 * @version 0.1
 */ 
public class Planet extends SpaceObject {

	/** Moons that are orbiting this planet */
	private Moon[] moons;
	
	/**
	 * 
	 */
	public Planet() {
		super();
	}
	
	public Planet(String n, State s, double m, double r, double[] f) {
		super(n, s);
	}
	
	//TODO: Deep copy constructor
	public Planet (Planet p) {
		
	}
	
	//Accessor and modifier methods
	public Moon[] getMoons() { return moons; }
	public void setMoons(Moon[] moons) { this.moons = moons; }

}
