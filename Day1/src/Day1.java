import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day1 {
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day1.txt");
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
		
		int w1 = Integer.parseInt(data.get(0)),
			w2 = Integer.parseInt(data.get(1)),
			w3 = Integer.parseInt(data.get(2)),
			count = 0;
		for(int i = 3; i<data.size();i++) {
			int current = Integer.parseInt(data.get(i));
			
			if((w2+w3+current)>(w1+w2+w3)) {
				count++;
			}
			w1=w2;
			w2=w3;
			w3=current;
		}
		System.out.println(count);
	}
}