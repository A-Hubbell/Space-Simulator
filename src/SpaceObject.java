/* Copyright (c) 2014 Sci-Tech Innovations. All Rights Reserved
 * 
 * This software is part of a larger space simulation project at Sci-Tech Innovations.
 * It can be used, modified, and distributed for educational purposes free of charge, 
 * as long as it is not used for, or in conjunction with, any monetary gain. 
 */


/**
 * This class is a superclass of all "space objects" that can exist in our "universe". This class provides
 * common attributes and methods for all "space objects", such as Planets, Moons, Spaceships, and Satellites.
 * 
 * @author Austin
 * @version 0.1
 */ 
public abstract class SpaceObject {

	/** Name of the SpaceObject */
	private String name;
	
	/** State of the SpaceObject, storing mass, radius, position, velocity, acceleration, and net force */
	private State state;
	

	/**
	 * Initialize a new SpaceObject with default name "Name not specified", 
	 * and state using default State() constructor.
	 */
	public SpaceObject() {
		this.name = "Name not specified";
		this.state = new State();
	}
	

	public SpaceObject (State s) {
		this.name = "Name not specified";
		this.state = s;
	}
	
	/**
	 * Initialize a new SpaceObject with name "n" and state "s"
	 * 
	 * @param n		name of new SpaceObject.
	 * @param s		state of new SpaceObject.
	 */
	public SpaceObject (String n, State s) {
		this.name = n;
		this.state = s;
	}
	
	
	

	/**
	 * Copy constructor: initialize a new SpaceObject that is a deep copy 
	 * of SpaceObject "s".
	 * 
	 * @param s		SpaceObject to be copied.
	 */
	public SpaceObject(SpaceObject s) {
		this.name = s.getName();
		
		State newState = new State(s.getState());
		this.state = newState;
	}
	
	
	//Accessor and modifier methods
	protected String getName() { return name; }
	protected void setName(String name) { this.name = name; }

	protected State getState() {return state;}
	protected void setState(State state) {this.state = state; }
	
	
	/** 
	 * Updates the State attribute of this SpaceObject, subsequently updating 
	 * the acceleration, velocity, and position attributes within the State.
	 * The "SpaceCalc" helper class defines exactly how each of these is 
	 * calculated.
	 * 
	 * @return   current state attribute of calling object after being updated
	 * @see    	 {@link SpaceCalc#updateAcceleration(State)}, {@link SpaceCalc#updateVelocity(State, double)}, {@link SpaceCalc#updatePosition(State, double)}
	 */
	protected State nextState () {
		this.state = SpaceCalc.updateAcceleration(this.state);
		this.state = SpaceCalc.updateVelocity(this.state, Universe.TIME_STEP);
		this.state = SpaceCalc.updatePosition(this.state, Universe.TIME_STEP);

		return this.getState();
	}
	
	/** 
	 * Combines two SpaceObjects by adding the mass of "s" to "this", 
	 * computing a new common velocity using conservation of linear
	 * momentum: Vf = (m1v1+m2v2)/(m1+m2), and calculates a new radius
	 * for the combined object. Invalidates object "s" by setting it 
	 * to null.
	 * 
	 * @param s    the SpaceObject to be merged with "this" (calling object)
	 * 			   and then invalidated.
	 * @return     current state attribute of calling object after merge
	 * @see        {@link SpaceCalc#calculateRadius(double, double)}
	 */
	private State mergeSpaceObjects(SpaceObject s) {
		//Combined mass of the two objects
		double totalMass = this.state.getMass() + s.getState().getMass();
		
		//Conservation of linear momentum, two masses have same Vf = (m1v1+m2v2)/(m1+m2)
		//TODO: Move to SpaceCalc, make general elastic collision calculator
		double newVx = (this.state.getMass()*this.getState().getVx() + s.getState().getMass()*s.getState().getVx())/(totalMass);
		double newVy = (this.state.getMass()*this.getState().getVy() + s.getState().getMass()*s.getState().getVy())/(totalMass);
		
		this.state.setVx(newVx);    //Update the velocity state attributes
		this.state.setVy(newVy);
		this.state.setMass(totalMass);    //Update the mass state attribute
		
		//Calculate radius accounting for the newly added mass
		double newRadius = SpaceCalc.calculateRadius(totalMass, Universe.DEFAULT_DENSITY);
		this.state.setRadius(newRadius);
		
		//Invalidate "s"
		s.setName(null);
		s.setState(null);
		s = null;
		
		return this.getState();
		
	} 
	
	/**
	 *  Checks if two SpaceObject radii are overlapping. If they are, merge 
	 *  the smaller mass into the larger mass using mergeSpaceObjects().
	 * 
	 * @param s    the SpaceObject, whose radius is to be compared with 
	 * 			   "this" (calling object) radius.
	 * @return	   true if objects overlapped and are now merged, false otherwise
	 * @see        {@link #mergeSpaceObjects(SpaceObject)}
	 */
	protected boolean verifyOverlap (SpaceObject s) throws IllegalArgumentException { 
		if (s == null)
			throw new IllegalArgumentException();
		//Find difference between x, y coordinates of SpaceObjects
		double dx = this.state.getX() - s.getState().getX();
		double dy = this.state.getY() - s.getState().getY();
		
		//Calculate distance between two SpaceObjects
		double r = Math.sqrt(dx*dx + dy*dy);
		
		//If SpaceObject radii are overlapping, merge them
		if (r < (this.state.getRadius() + s.getState().getRadius())) {
			if (this.state.getMass() >= s.getState().getMass()) {
				this.mergeSpaceObjects(s);
			}
			else {
				//TODO: Can I do this?
				s.mergeSpaceObjects(this);
			}
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if two SpaceObject objects are equal. Two SpaceObject objects are 
	 * considered equal if they both contain the same name and state objects.
	 * 
	 * @param s 	SpaceObject object to be compared to the calling SpaceObject object.
	 * @return		true if both SpaceObject objects are considered equal, otherwise false.
	 */
	public boolean equals (SpaceObject s) {
		return (this.name.equals(s.getName()) && this.state.equals(s.getState()));
	}
	
	/**
	 * Generate and return a string that is representative of the calling SpaceObject.
	 * This is done by creating a new string containing the name of the calling
	 * SpaceObject, as well as an invocation of the State class' toString() method on
	 * the state of the calling SpaceObject.
	 * 
	 * @return 		string representative of the calling SpaceObject object.
	 */
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append("name:" + this.name);
		sb.append(this.state);
		
		return sb.toString();
	}
	
}
