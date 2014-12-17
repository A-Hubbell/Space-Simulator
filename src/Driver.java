import java.io.IOException;


public class Driver {

	public static void main(String[] args) {

		Universe u2 = new Universe();
		
		
		State s1 = new State(Universe.DEFAULT_MASS, Universe.DEFAULT_RADIUS, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		State s2 = new State(Universe.DEFAULT_MASS, 9, 10.0,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		State s3 = new State(Universe.DEFAULT_MASS, 9, -10.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		
		Planet p1 = new Planet(s1);
		Planet p2 = new Planet("P1", s2);
		Planet p3 = new Planet("P2", s3);
		Planet p4 = new Planet();
		
		//System.out.println(p1);
		//System.out.println(p2);
		//System.out.println(p3);
	
		u2.addPlanet(p2);
		u2.addPlanet(p3);
		//u2.addPlanet(p3);
		//u2.addPlanet(p4);
		//System.out.println(u2);
		
		//TODO: Check if file already exists --> check in WriteFile class?
		WriteFile dataWriter = new WriteFile("testData.txt", true);
	
		for(int i=0; i<2; i++) {
			u2.update();
			
			try {
				dataWriter.writeLineToFile(u2.getPlanets().get(0).toString());
				dataWriter.writeLineToFile(u2.getPlanets().get(1).toString());
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println(u2.getPlanets().get(0));
		}
		
		/*
		Iterator<Planet> planets = u2.planetsIterator();
		
		while (planets.hasNext()) {
			System.out.println(planets.next());
		}
		
		*/
		
		
		
		
		
		/*
		Visualizer v = new Visualizer();
		v.frame.setResizable(false);
		v.frame.setTitle("Space-Simulator");
		v.frame.add(v);
		v.frame.pack();
		v.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.frame.setLocationRelativeTo(null);
		v.frame.setVisible(true);

		v.start();
		*/
		
		//u2.visualize();
		
		System.out.println("Program Terminated Successfully!");
		
	}

}
