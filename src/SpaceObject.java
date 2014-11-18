/**
 * 
 */

/**
 * @author Austin
 *
 */
public abstract class SpaceObject {
	/**
	 * 
	 */
	
	
	
	//SpaceObject class attributes
	private String name;
	private State state;    //State stores instantaneous position, velocity, and acceleration of the object
	
	//Constructors
	public SpaceObject (String n, State s) {
		this.name = n;
		this.state = s;
	}
	
	public SpaceObject() {
		this.name = "Name not specified";
		this.state = new State();
	}

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
	
	//Update the acceleration, velocity, and position state attributes
	protected void nextState () {
		SpaceCalc.updateAcceleration(this.state);
		SpaceCalc.updateVelocity(this.state, Universe.TIME_STEP);
		SpaceCalc.updatePosition(this.state, Universe.TIME_STEP);
	}
	
	//Combine masses, conserve linear momentum, invalidate "s"
	private void mergeObjects(SpaceObject s) {
		//Combine the mass of the two objects
		double totalMass = this.state.getMass() + s.getState().getMass();
		this.state.setMass(totalMass);
		
		//Update the radius accounting for the newly added mass
		double newRadius = SpaceCalc.calculateRadius(totalMass, Universe.DEFAULT_DENSITY);
		this.state.setRadius(newRadius);
		
		//Conservation of linear momentum, two masses have same Vf = (m1v1+m2v2)/(m1+m2)
		double newVx = (this.state.getMass()*this.getState().getVx() + s.getState().getMass()*s.getState().getVx())/(totalMass);
		double newVy = (this.state.getMass()*this.getState().getVy() + s.getState().getMass()*s.getState().getVy())/(totalMass);
		
		//Update the velocity state attributes
		this.state.setVx(newVx);
		this.state.setVy(newVy);
		
		//Invalidate "s"
		s.setName(null);
		s.setState(null);
		s = null;
	} 
	
	//Check if the two SpaceObject radii overlap, if so merge the smaller mass into the larger mass
	protected void verifyOverlap (SpaceObject s) { 
		//Find difference between x, y coordinates of SpaceObjects
		double dx = this.getState().getX() - s.getState().getX();
		double dy = this.getState().getY() - s.getState().getY();
		
		//Calculate distance between two SpaceObjects
		double r = Math.sqrt(dx*dx + dy*dy);
		
		//If SpaceObjects are overlapping, merge them
		if (r < (this.state.getRadius() + s.getState().getRadius())) {
			if (this.state.getMass() >= s.getState().getMass()) {
				this.mergeObjects(s);
			}
			else {
				//TODO: Can I do this?
				s.mergeObjects(this);
			}
		}
	}
	
	public boolean equals (SpaceObject s) {
		return (this.name.equals(s.getName()) && this.state.equals(s.getState()));
	}
	
	
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: ");
		sb.append(this.name);
		sb.append("  State :");
		sb.append(this.state);

		
		return sb.toString();
	}
	
}
