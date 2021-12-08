import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day2 {
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day2.txt");
			Scanner scanner = new Scanner(file);	  
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				data.add(line);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		int x = 0, y = 0, aim = 0;
		for(int i = 0; i<data.size();i++) {
			String direction = data.get(i);
			int value = Integer.parseInt(direction.substring(1+direction.lastIndexOf(" ")));
			
			if(direction.startsWith("down")) {
				aim+=value;
			}
			else if(direction.startsWith("up")) {
				aim-=value;
			}
			else {
				y+=value;
				x+=aim*value;
			}
		}
		System.out.println("Depth: " + x);
		System.out.println("Position: " + y);
		System.out.println("x*y="+x*y);
	}
}