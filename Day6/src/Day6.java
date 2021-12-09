import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day6 {
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day6.txt");
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
		char[] charData = data.get(0).replaceAll(",", "").toCharArray();
		long[] fishAtAge = new long[9];
		for(char c : charData) {
			fishAtAge[(c-48)]++;
		}
		for(int i = 0; i < 256; i++) {
			long births = fishAtAge[0];
			fishAtAge[0] = fishAtAge[1];
			fishAtAge[1] = fishAtAge[2];
			fishAtAge[2] = fishAtAge[3];
			fishAtAge[3] = fishAtAge[4];
			fishAtAge[4] = fishAtAge[5];
			fishAtAge[5] = fishAtAge[6];
			fishAtAge[6] = fishAtAge[7] + births;
			fishAtAge[7] = fishAtAge[8];
			fishAtAge[8] = births;
		}
		
		long totalFish = 0;
		for(int i = 0; i < 9; i++) {
			totalFish+=fishAtAge[i];
		}
		System.out.println("Total Fish: " + totalFish);
	}
}