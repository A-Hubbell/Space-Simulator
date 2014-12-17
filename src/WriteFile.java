import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


//TODO: Check if filePath already exists

public class WriteFile {

	private String path;
	private boolean append = false;
	
	
	public WriteFile (String filePath) {
		this.path = filePath;
	}
	
	public WriteFile (String filePath, boolean append) {
		this.path = filePath;
		this.append = append;
	}
	
	public void writeLineToFile (String text) throws IOException {
		FileWriter write = new FileWriter(this.path, this.append);
		PrintWriter printLine = new PrintWriter(write);
		printLine.println(text);
		printLine.close();
	}
	
	
}
