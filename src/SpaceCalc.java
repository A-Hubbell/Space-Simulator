
public class SpaceCalc {
	
	public static State updatePosition (State s, double dt) {
		// x = v*t + (1/2)*a*t^2
		double newX = s.getVx()*dt + 0.5*s.getAx()*(dt*dt);
		double newY = s.getVy()*dt + 0.5*s.getAy()*(dt*dt);
		
		//Set the new position values of state "s"
		s.setX(newX);
		s.setY(newY);
		
		State newState = new State(s);
		
		return newState;
	}
	
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
	
	public static State updateAcceleration (State s) {
		s.setAx(s.getFx()/s.getMass());
		s.setAy(s.getAy()/s.getMass());
		
		State newState = new State(s);
		
		return newState;
	}
	
	//Calculates the gravitational force experienced by "s1" as a result of "s2"
	//Returns a vector pointing from "s1" to "s2" 
	public static double[] calcGravForce (State s1, State s2) {
		double dx = s2.getX() - s1.getX();
		double dy = s2.getY() - s1.getY();
		double r = Math.sqrt(dx*dx + dy*dy); //Calculate distance between objects
		
		//Unit vector from "s1" to "s2"
		double [] vector = {dx/r, dy/r};
		
		//Standard F = Gmm/r^2
		double gravForce = (Universe.GRAV_CONST*s1.getMass()*s2.getMass())/(r*r);
		
		//Multiply unit vector by magnitude of gravitational force
		vector[0] = vector[0]*gravForce;
		vector[1] = vector[1]*gravForce;
		
		return vector;
	}
	
	//Update the radius of the object given a certain mass and density, assuming spherical shape
		public static double calculateRadius (double mass, double density) {
			//  V=m/p  -->  (4/3)*PI*r^3 = m/p    where "p" is density of the object
			double temp = (3*mass)/(4*Math.PI*density);
			temp = Math.pow(temp, 1/3);
			return temp;
		}
}
