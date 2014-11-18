/* Copyright (c) 2014 Sci-Tech Innovations. All Rights Reserved.
 * 
 * This software is part of a larger space simulation project at Sci-Tech Innovations.
 * It can be used, modified, and distributed for educational purposes free of charge, 
 * as long as it is not used for, or in conjunction with, any monetary gain. 
 */


/**
 * This class is an extension of the SpaceObject class, intended to represent physical 
 * spaceships as objects of the SpaceShip class. This class contains attributes and methods 
 * pertaining specifically to physical spaceships. The main difference between spaceships
 * and other entities in the universe is that a spaceship can apply a thrust in a given
 * direction, it is not just affected by the force of gravity.
 * 
 * @author Austin
 * @version 0.1
 */ 



public class SpaceShip extends SpaceObject {

	public double[] thrust; //Instantaneous thrust
	
	public SpaceShip() {
		
	}
	
	//TODO: Deep copy constructor
	public SpaceShip (SpaceShip s) {
		
	}

}
