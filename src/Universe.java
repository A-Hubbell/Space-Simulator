/* Copyright (c) 2014 Sci-Tech Innovations. All Rights Reserved
 * 
 * This software is part of a larger space simulation project at Sci-Tech Innovations.
 * It can be used, modified, and distributed for educational purposes free of charge, 
 * as long as it is not used for, or in conjunction with, any monetary gain. 
 */

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class contains all entities (SpaceObject, etc.) that exist in the universe.
 * It is the main class for handling interactions between entities, such as
 * gravitational forces between entities. This class also handles the main logic and
 * order of operations for updating the states of different entities given a certain 
 * time step.
 * 
 * This class also contains all of the characteristic constants of the universe.
 * These constants can be tweaked to change the behaviour of the simulation, but 
 * are currently unchecked, so problems may arise from inappropriate choice of 
 * values for the constants.
 * 
 * @author Austin
 * @version 0.1
 */ 
public class Universe {

	//Universe characteristics
	public static final double X_DIM = 100;
	public static final double Y_DIM = 100;
	public static final double DEFAULT_VELOCITY_FACTOR = 20;
	public static final double DEFAULT_ACCELERATION_FACTOR = 5;
	public static final double DEFAULT_MASS = 10;     //Mass of the Earth = 5.972e24 kg
	public static final double DEFAULT_DENSITY = 5520;    //Average density of the Earth = 5520 kg/m^3
	public static final double DEFAULT_RADIUS = Math.pow((3*DEFAULT_MASS)/(4*Math.PI*DEFAULT_DENSITY), 1.0/3);
	public static final double DEFAULT_FORCE_FACTOR = 1e5;
	public static final double GRAV_CONST = 2e3;
	public static final double TIME_STEP = 0.1;
	
	/** Store all Planet objects existing in the universe */
	private ArrayList<Planet> planets;

	
	/**
	 * Initialize a new Universe with an ArrayList of planets "a".
	 * 
	 * @param a		ArrayList of planets for new Universe object.
	 */
	public Universe (ArrayList<Planet> a) {
		this.planets = a;
	}
	
	/**
	 * Initialize a new Universe with an empty ArrayList "planets".
	 */
	public Universe () {
		this.planets = new ArrayList<>();
	}
	
	/**
	 * Copy constructor: initialize a new Universe that is a deep copy of 
	 * Universe "u".
	 *  
	 * @param u		Universe object to be copied.
	 */
	public Universe (Universe u) {
		ArrayList<Planet> newList = new ArrayList<>();
		Iterator<Planet> it = u.getPlanets().iterator();
		
		while(it.hasNext())
			newList.add(it.next());
		
		this.planets = newList;
	}
	
	
	/** 
	 * Updates the universe by first updating all of the net forces acting on all
	 * entities using updateNetForces(), then moving the simulation one time step
	 * forward using updateStates(). 
	 * 
	 * The updateNetForces() and updateSates() methods provide more details.
	 * 
	 * @see {@link #updateNetForces()} {@link #updateStates()}
	 * @return      current state attribute of calling object after being updated
	 */
	public void update () {
		this.updateNetForces();
		this.updateStates();
	}
	
	
	/** 
	 * Adds a new Planet object to the ArrayList "planets", effectively introducing
	 * a new planet into the universe. This method uses the "add()" method that is
	 * built in to the ArrayList to add the new Planet.
	 * 
	 * @param p     Planet object to be added to the universe.
	 */
	public void addPlanet (Planet p) {
		this.planets.add(p);
	}
	
	
	//Accessor and modifier methods
	public ArrayList<Planet> getPlanets () { return planets; }
	public void setPlanets (ArrayList<Planet> p) { this.planets = p; }
	
	
	/** 
	 * Update the net force state attribute of every entity in the universe by
	 * calculating the gravitational force acting on each entity due to every
	 * other entity in the universe.
	 */
	private void updateNetForces () {
		//TODO: Logic testing
		for (Planet p1 : planets) {
			double[] vector = {p1.getState().getFx(), p1.getState().getFy()};
			for (Planet p2 : planets) {
				if (p1.equals(p2)) 
					continue;
				//Calculate the gravitational force acting on "p1" due to "p2"
				//Add the force vector to the current net force vector of "p1"
				vector[0] += SpaceCalc.calcGravForce(p1.getState(), p2.getState())[0];
				vector[1] += SpaceCalc.calcGravForce(p1.getState(), p2.getState())[1];

			}
			//Create a new State object that is a copy of p1's current state
			State newState = new State(p1.getState());
			
			//Set the net force attributes of newState to the new net force for p1
			newState.setFx(vector[0]);
			newState.setFy(vector[1]);
			
			//Set p1's "state" to newState
			p1.setState(newState);
		}
	}
	
	
	/** 
	 * Update the acceleration, velocity, and position state attributes of every
	 * entity in the universe by calling the nextState() method on every entity.
	 * Afterwards, every entity's radius is checked against every other radius
	 * for merging using verifyOverlap().
	 * 
	 * The nextState() method provides more details on how this is done.
	 * 
	 * @see {@link Planet#nextState()}, {@link Planet#verifyOverlap()}
	 */
	private void updateStates () {
		//Update acceleration, velocity, and position vectors
		for (Planet p1 : this.planets) {
			p1.nextState(); 
		}
		
		//Check if any planets need to be merged, if so merge them
		for (int i=0; i<this.planets.size()-2; i++) {
			for (int j=i+1; j<this.planets.size()-1; j++) {
				//TODO: Logic testing
				this.planets.get(i).verifyOverlap(this.planets.get(j));
			}
		}
	}

	/**
	 * Check if two Universe objects are equal. Two Universe objects are considered
	 * equal if they both contain the same Planet objects in the same order in their
	 * "planets" ArrayList.
	 * 
	 * @param u 	Universe object to be compared to the calling object.
	 * @return		true if both Universe objects are considered equal, otherwise false.
	 */
	public boolean equals (Universe u) {
		boolean flag = true;
		ArrayList<Planet> thisPlanets = this.getPlanets();
		ArrayList<Planet> otherPlanets = u.getPlanets();
		
		for (int i=0; i<this.planets.size(); i++) {
			if (!(thisPlanets.get(i).equals(otherPlanets.get(i))))
				flag = false;
		}
		
		return flag;
	}
	
	/**
	 * Generate and return a string that is representative of the calling Universe
	 * object. This is done by iterating through "planets" and invoking the toString()
	 * method of the Planet class on each Planet.
	 * 
	 * @return 		string representative of the calling Universe object.
	 */
	public String toString () {
		StringBuilder sb = new StringBuilder();
		Iterator<Planet> it = planets.iterator();
		while (it.hasNext())
			sb.append(it.next());
		
		return it.toString();
	}
	

}
