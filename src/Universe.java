import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 */

/**
 * @author Austin
 *
 */
public class Universe {

	/**
	 * 
	 */
	
	//Universe characteristics
	public static final double X_DIM = 100;
	public static final double Y_DIM = 100;
	public static final double DEFAULT_VELOCITY_FACTOR = 20;
	public static final double DEFAULT_ACCELERATION_FACTOR = 5;
	//public static final double DEFAULT_MASS = 5.972e24;    //Mass of the Earth = 5.972e24 kg
	public static final double DEFAULT_MASS = 10;
	public static final double DEFAULT_DENSITY = 5520;    //Average density of the Earth = 5520 kg/m^3
	public static final double DEFAULT_RADIUS = Math.pow((3*DEFAULT_MASS)/(4*Math.PI*DEFAULT_DENSITY), 1.0/3);
	public static final double DEFAULT_FORCE_FACTOR = 1e5;
	public static final double GRAV_CONST = 2e3;
	public static final double TIME_STEP = 0.1;
	
	//Class attributes
	private ArrayList<Planet> planets;
	//private ArrayList <Moon> moons;
	//private ArrayList <SpaceShip> spaceships;
	
	
	//Constructors
	public Universe (ArrayList<Planet> a) {
		this.planets = a;
	}
	
	public Universe () {
		this.planets = new ArrayList<>();
	}
	
	public Universe (Universe u) {
		ArrayList<Planet> newList = new ArrayList<>();
		Iterator<Planet> it = u.getPlanets().iterator();
		
		while(it.hasNext())
			newList.add(it.next());
		
		this.planets = newList;
	}
	
	public void update () {
		this.updateNetForces();
		this.updateStates();
	}
	
	public void addPlanet (Planet p) {
		this.planets.add(p);
	}
	
	//Accessor and modifier methods
	public ArrayList<Planet> getPlanets () { return planets; }
	public void setPlanets (ArrayList<Planet> p) { this.planets = p; }
	
	
	//Update the netForce attribute of every planet by calculating the gravitational force due to every other planet
	//TODO: Logic testing
	private void updateNetForces () {
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
	
	//Update the states of all planets in the universe
	private void updateStates () {
		//Update acceleration, velocity, and position vectors
		for (Planet p1 : this.planets) {
			p1.nextState(); 
		}
		
		//Check if any planets need to be merged, if so merge them
		for (int i=0; i<this.planets.size()-2; i++) {
			for (int j=i+1; j<this.planets.size()-1; j++) {
				this.planets.get(i).verifyOverlap(this.planets.get(j));
			}
		}
	}

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
	
	public String toString () {
		StringBuilder sb = new StringBuilder();
		Iterator<Planet> it = planets.iterator();
		while (it.hasNext())
			sb.append(it.next());
		
		return it.toString();
	}
	

}
