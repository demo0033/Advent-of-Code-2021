import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day14 {
	public static Map<String,Integer> charCount;
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day14.txt");
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
		
		//Part 1 (Using strings and brute force...it gets slow)
		Map<String,String> rules = new HashMap<>();
		charCount = new HashMap<>();
		String polymer = data.get(0);
		for(int i = 0; i < polymer.length(); i++) {
			count(polymer.substring(i,i+1));
		}
		
		// Parse ruleset
		for(int i = 2; i < data.size(); i++) {
			rules.put(data.get(i).substring(0, 2), data.get(i).substring(6));
		}
		
		// Apply ruleset 10 times
		for(int i = 0; i < 40; i++) {
			String newPolymer = "";
			for(int j = 0; j < polymer.length()-1; j++) {
				String one = polymer.substring(j, j+1);
				String two = polymer.substring(j+1, j+2);
				if(rules.containsKey(one + two)) {
					newPolymer+=one + rules.get(one + two); 
					count(rules.get(one + two));
				}
			}
			polymer = newPolymer+polymer.substring(polymer.length()-1);
		}
		String largest = "", smallest = "";
		for(String s : charCount.keySet()) {
			if(!charCount.containsKey(largest) || charCount.get(s) > charCount.get(largest)) {
				largest = s;
			}
			if(!charCount.containsKey(smallest) || charCount.get(s) < charCount.get(smallest)) {
				smallest = s;
			}
		}
		System.out.println("Most common minus least common: " + (charCount.get(largest)-charCount.get(smallest)));
	}
	public static void count(String s) {
		if(charCount.containsKey(s)) {
			charCount.put(s, charCount.get(s)+1);
		}
		else {
			charCount.put(s,1);
		}
	}
}