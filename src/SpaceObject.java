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
	protected String name;
	protected State state;    //State stores instantaneous position, velocity, and acceleration of the object
	protected double mass;    //Mass of the space object
	protected double radius;
	protected double[] netForce = new double[2];    //Instantaneous net force acting on the object [x, y]
	
	
	//Constructors
	public SpaceObject (String n, State s, double m, double r, double[] f) {
		this.name = n;
		this.state = s;
		this.mass = m;
		this.radius = r;
		this.netForce = f;
	}
	
	public SpaceObject() {
		this.name = "Name not specified";
		this.state = new State();
		this.mass = Universe.DEFAULT_MASS;
		this.radius = SpaceObject.calculateRadius(this.mass, Universe.DEFAULT_DENSITY);
		this.netForce[0] = Math.random()*Universe.DEFAULT_FORCE_FACTOR;
		this.netForce[1] = Math.random()*Universe.DEFAULT_FORCE_FACTOR;
		
	}
	
	//Accessor and modifier methods
	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected State getState() {
		return state;
	}

	protected void setState(State state) {
		this.state = state;
	}

	protected double getMass() {
		return mass;
	}

	protected void setMass(double mass) {
		this.mass = mass;
	}

	protected double[] getNetForce() {
		return netForce;
	}

	protected void setNetForce(double[] netForce) {
		this.netForce = netForce;
	}
	
	protected double getRadius() {
		return radius;
	}

	protected void setRadius(double radius) {
		this.radius = radius;
	}

	
	protected double[] calcGravForce(SpaceObject s) {
		double dx = this.state.getX() - s.getState().getX();
		double dy = this.state.getY() - s.getState().getY();
		double r = Math.sqrt(dx*dx + dy*dy); //Calculate distance between objects
		
		//Unit vector from "s" to "this"
		double [] vector = {dx/r, dy/r};
		
		//Standard F = Gmm/r^2
		double gravForce = (Universe.GRAV_CONST*this.mass*s.getMass())/(r*r);
		
		//Multiply unit vector by magnitude of gravitational force
		vector[0] = vector[0]*gravForce;
		vector[1] = vector[1]*gravForce;
		
		return vector;	
	}
	
	//Update the instantaneous acceleration given a certain net force
	protected void updateAcceleration () throws IllegalStateException {
		if (netForce == null)
			throw new IllegalStateException("Net force not known, cannot calculate acceleration");
		this.state.setAx(this.netForce[0]/this.mass);
		this.state.setAy(this.netForce[1]/this.mass);
	}
	
	//Update the radius of the object given a certain mass and density, assuming spherical shape
	public static double calculateRadius (double mass, double density) {
		//  V=m/p  -->  (4/3)*PI*r^3 = m/p    where "p" is density of the object
		double temp = (3*mass)/(4*Math.PI*density);
		temp = Math.pow(temp, 1/3);
		return temp;
	}
	

	//Combine masses, conserve linear momentum, invalidate "s"
	private void mergeObjects(SpaceObject s) {
		//Combine the mass of the two objects
		this.mass += s.getMass();
		
		//Conservation of linear momentum, two masses have same Vf = (m1v1+m2v2)/(m1+m2)
		double newVx = (this.mass*this.getState().getVx() + s.getMass()*s.getState().getVx())/(this.mass + s.getMass());
		double newVy = (this.mass*this.getState().getVy() + s.getMass()*s.getState().getVy())/(this.mass + s.getMass());
		
		this.getState().setVx(newVx);
		this.getState().setVy(newVy);
		
		//Invalidate "s"
		s.setName(null);
		s.setMass(0);
		s.setState(null);
		s.setNetForce(null);
	} 
	
	//Check if the two SpaceObject radii overlap, if so merge the smaller mass into the larger mass
	protected void verifyOverlap (SpaceObject s) { 
		//Find difference between x, y coordinates of SpaceObjects
		double dx = this.getState().getX() - s.getState().getX();
		double dy = this.getState().getY() - s.getState().getY();
		
		//Calculate distance between two SpaceObjects
		double r = Math.sqrt(dx*dx + dy*dy);
		
		//If SpaceObjects are overlapping, merge them
		if (r < (this.getRadius() + s.getRadius())) {
			if (this.getMass() >= s.getMass()) {
				this.mergeObjects(s);
			}
			else {
				//TODO: Can I do this?
				s.mergeObjects(this);
			}
		}
	}
	
	//TODO: Implement equals()
	protected boolean equals () {
		return false;
	}
	
	
}
