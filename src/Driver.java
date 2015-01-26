import java.io.IOException;


public class Driver {

	public static void main(String[] args) {

		final int NUM_STEPS = 500;
		
		Universe u2 = new Universe();
		
		
		//State s1 = new State(Universe.DEFAULT_MASS, Universe.DEFAULT_RADIUS, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		State s2 = new State(Universe.DEFAULT_MASS, 1, 10.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		State s3 = new State(Universe.DEFAULT_MASS, 1, -10.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		
		Planet p2 = new Planet("P1", s2);
		Planet p3 = new Planet("P2", s3);
	
		u2.addPlanet(p2);
		u2.addPlanet(p3);
		
		//TODO: Check if file already exists --> check in WriteFile class?
		WriteFile dataWriter = new WriteFile("testData.txt", true);
	
		u2.visualize();
		
		for(int i=0; i<NUM_STEPS; i++) {
			u2.update();
			
			try {
				dataWriter.writeLineToFile(u2.getPlanets().get(0).toString());
				dataWriter.writeLineToFile(u2.getPlanets().get(1).toString());
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		
		//u2.visualize();
		
		System.out.println("Program Terminated Successfully!");
		
	}

}
