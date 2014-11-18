
public class Driver {

	public static void main(String[] args) {
		
		Universe universe = new Universe();
		
		Planet p1 = new Planet();
		Planet p2 = new Planet();
		Planet p3 = new Planet();
		
		State s1 = new State(Universe.DEFAULT_MASS, Universe.DEFAULT_RADIUS, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		State s2 = new State(Universe.DEFAULT_MASS, Universe.DEFAULT_RADIUS, 100.0,100.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		State s3 = new State(Universe.DEFAULT_MASS, Universe.DEFAULT_RADIUS, -100.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		
		p1.setState(s1);
		p2.setState(s2);
		p3.setState(s3);
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		
		System.out.println(universe);
		
		universe.addPlanet(p1);
		universe.addPlanet(p2);
		universe.addPlanet(p3);
		
		System.out.println(universe);
		
		universe.update();
		
		System.out.println(universe);
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		
		System.out.println("Program Terminated Successfully!");
		
	}

}
