
public class Driver {

	public static void main(String[] args) {
		
		Planet p1 = new Planet();
		Planet p2 = new Planet();
		Planet p3 = new Planet();
		
		State s1 = new State(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		State s2 = new State(100.0,100.0, 0.0, 0.0, 0.0, 0.0);
		State s3 = new State(-100.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		
		p1.setState(s1);
		p2.setState(s2);
		p3.setState(s3);
		
		
		
	}

}
