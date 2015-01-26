/* Copyright (c) 2014 Sci-Tech Innovations. All Rights Reserved
 * 
 * This software is part of a larger space simulation project at Sci-Tech Innovations.
 * It can be used, modified, and distributed for educational purposes free of charge, 
 * as long as it is not used for, or in conjunction with, any monetary gain. 
 */


/**
 * This class is a "helper" class containing some useful methods for mechanics 
 * calculations, such as for calculating the gravitational force between two 
 * State objects, or the radius of a sphere given a certain mass and density.
 * 
 * The methods in this class are used throughout the Space-Simulator project, 
 * and therefore require extensive unit testing before they can be trusted.
 * 
 * @author Austin
 * @version 0.1
 */ 
public class SpaceCalc {
	
	//TODO: Change lower bound in terms of resulting acceleration, so the simulation 
	//      is still valid for very small masses.
	public static final double FORCE_LOWER_BOUND = 1.0e-12;
	
	//SpaceCalc is not designed to be instantiated, so constructor is private
	private SpaceCalc () {}
	
	/**
	 *  Updates the acceleration of SpaceObject "s", using Newton's Second Law, 
	 *  F = m*a, to calculate the new acceleration of "s" given a certain mass
	 *  and net force. The equation uses the mass and net force attributes 
	 *  currently stored in "s".
	 *  
	 *  IMPORTANT: This method relies on mass and net force for 
	 *  calculating position. The updateNetForce() methods must be run first
	 *  for any given time step in order for this function to calculate the
	 *  correct new acceleration.
	 * 
	 * @param s    the SpaceObject, whose acceleration is to be updated
	 * @return	   state object corresponding to "s" with updated 
	 * 			   position attributes
	 * @see        {@link Universe#updateNetForces()}
	 */
	public static State updateAcceleration (State s) {

		s.setAx(s.getFx()/s.getMass());
		s.setAy(s.getAy()/s.getMass());
		
		State newState = new State(s);

		return newState;
	}
	
	
	/**
	 *  Updates the velocity of SpaceObject "s", using a constant-acceleration
	 *  1-D kinematics equation, Vf = Vi + a*t, to calculate the new velocity 
	 *  given a certain initial velocity, acceleration, and time step. The 
	 *  equation uses the velocity and acceleration attributes currently stored
	 *  in "s".
	 *  
	 *  IMPORTANT: This method relies on velocity and acceleration for 
	 *  calculating position. The updateAcceleration() methods must be run first
	 *  for any given time step in order for this function to calculate the
	 *  correct new velocity.
	 * 
	 * @param s    the SpaceObject, whose velocity is to be updated.
	 * @param dt   double representing the time step to be used in the 
	 * 			   kinematics equation for velocity.
	 * @return	   state object corresponding to "s" with updated 
	 * 			   velocity attributes.
	 * @see    	   #updateAcceleration(State)
	 */
	public static State updateVelocity (State s, double dt) {

		// Vf = Vi + a*t
		double newVx = s.getVx() + s.getAx()*dt;
		double newVy = s.getVy() + s.getAy()*dt;
		
		//Set the new velocity values of state "s"
		s.setVx(newVx);
		s.setVy(newVy);
		
		State newState = new State(s);

		return newState;
	}
	
	
	/**
	 *  Updates the position of SpaceObject "s", using a constant-acceleration
	 *  1-D kinematics equation, x = v*t + (1/2)*a*t^2, to calculate the new 
	 *  position given a certain velocity, acceleration, and time step. The 
	 *  equation uses the velocity and acceleration attributes currently stored 
	 *  in "s".
	 *  
	 *  IMPORTANT: This method relies on velocity and acceleration for 
	 *  calculating position. The updateAcceleration() and updateVelocity()
	 *  methods must be run first for any given time step in order for this
	 *  function to calculate the correct new position.
	 * 
	 * @param s    the SpaceObject, whose position is to be updated.
	 * @param dt   double representing the time step to be used in the 
	 * 			   kinematics equation for position.
	 * @return	   state object corresponding to "s" with updated 
	 * 			   position attributes.
	 * @see        SpaceCalc#updateVelocity(State, double)
	 */
	public static State updatePosition (State s, double dt) {
		// xf = xi + v*t + (1/2)*a*t^2
		double newX = s.getX() + s.getVx()*dt + 0.5*s.getAx()*(dt*dt);
		double newY = s.getY() + s.getVy()*dt + 0.5*s.getAy()*(dt*dt);
		
		//Set the new position values of state "s"
		s.setX(newX);
		s.setY(newY);
		
		State newState = new State(s);
		
		return newState;
	}

	
	/**
	 * Calculates the gravitational force experienced by SpaceObject "s1" as
	 * a result of the presence of SpaceObject "s2" using F = G*m1*m2/r^2.
	 * 
	 * @param s1    the SpaceObject, on which the gravitational force is acting
	 * @param s2	the SpaceObject causing the gravitational force
	 * @return	    vector in rectangular coordinates pointing from "s1" to "s2"
	 * 				with a magnitude equal to the force of gravity between them.
	 */
	public static double[] calcGravForce (State s1, State s2) {
		double dx = s2.getX() - s1.getX();
		double dy = s2.getY() - s1.getY();
		double r = Math.sqrt(dx*dx + dy*dy); //Calculate distance between objects
		
		//Unit vector from "s1" to "s2"
		double [] vector = {dx/r, dy/r};
		
		//Standard F = Gmm/r^2
		double gravForce = (Universe.GRAV_CONST*s1.getMass()*s2.getMass())/(r*r);
		if (gravForce < FORCE_LOWER_BOUND) {
			double[] zeros = {0.0, 0.0};
			return zeros;
		}
		//Multiply unit vector by magnitude of gravitational force
		vector[0] = vector[0]*gravForce;
		vector[1] = vector[1]*gravForce;
		
		return vector;
	}
	
	
	/**
	 * Calculates the radius of a spherical object of uniform density, given a
	 * certain mass and density of the object. (4/3)*PI*r^3 = m/p
	 * 
	 * @param m      the mass of the spherical object
	 * @param p	     the density (uniform) of the spherical object
	 * @return	     radius of a sphere with mass "m" and uniform density "p"
	 */
	public static double calculateRadius (double m, double p) {
		//  V=m/p  -->  (4/3)*PI*r^3 = m/p 
		double radius = (3*m)/(4*Math.PI*p);
		radius = Math.pow(radius, 1/3);
		return radius;
	}
}
