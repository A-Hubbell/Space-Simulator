import java.util.ArrayList;

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
	public static final double DEFAULT_MASS = 5.972e24;    //Mass of the Earth = 5.972e24 kg
	public static final double DEFAULT_DENSITY = 5520;    //Average density of the Earth = 5520 kg/m^3
	public static final double DEFAULT_FORCE_FACTOR = 1e5;
	public static final double GRAV_CONST = 2e3;
	
	//Class attributes
	private ArrayList <Planet> planets;
	private ArrayList <Moon> moons;
	private ArrayList <SpaceShip> spaceships;
	
	
	
	public Universe() {
		// TODO Auto-generated constructor stub
	}
	
	//Update the netForce attribute of every planet by calculating the gravitational force due to every other planet
	public void updateNetForce () {
		for (Planet p1 : planets) {
			double[] vector = p1.getNetForce();
			for (Planet p2 : planets) {
				if (p1.equals(p2)) 
					continue;
				//Calculate the gravitational force acting on "p1" due to "p2"
				//Add the force vector to the current net force vector of "p1"
				vector[0] += p1.calcGravForce(p2)[0];
				vector[1] += p1.calcGravForce(p2)[1];
			}
			//Update the netForce attribute of "p1" to be the sum of all the forces from all other planets
			p1.setNetForce(vector);
		}
	}

}
