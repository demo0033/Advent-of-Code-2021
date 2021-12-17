import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day14P2 {
	public static Map<String, String> rules;
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
		rules = new HashMap<>();
		List<PairCount> polymerCount = new ArrayList<>();
		for(int i = 2; i < data.size(); i++) {
			rules.put(data.get(i).substring(0, 2), data.get(i).substring(6));
		}
		String polymer = data.get(0);
		for(int i = 0; i < polymer.length()-1; i++) {
			String name = polymer.substring(i, i+2);
			boolean addNewValue = true;
			for(int j = 0; j < polymerCount.size(); j++) {
				if(name.equals(polymerCount.get(j).name)) {
					polymerCount.get(j).count = polymerCount.get(j).count.add(BigInteger.ONE);
					addNewValue = false;
					break;
				}
			}
			if(addNewValue) {
				polymerCount.add(new PairCount(name,BigInteger.ONE));
			}
		}
		
		polymerCount = count(polymerCount, 0);
		Map<String,BigInteger> letterCount = new HashMap<>();
		
		// Count number of each character that appears
		// Count all left-side letters
		String key;
		for(PairCount p : polymerCount) {
			key = p.name.substring(0, 1);
			if(!letterCount.containsKey(key)) {
				letterCount.put(key, p.count);
			}
			else {
				letterCount.put(key, letterCount.get(key).add(p.count));
			}
		}
		// Count final right-side letter (hasn't changed from the start!)
		key = polymer.substring(polymer.length()-1);
		if(!letterCount.containsKey(key)) {
			letterCount.put(key, BigInteger.ONE);
		}
		else {
			letterCount.put(key, letterCount.get(key).add(BigInteger.ONE));
		}
		
		String smallest = "", largest = "";
		for(String s : letterCount.keySet()) {
			if(largest.equals("") || letterCount.get(s).compareTo(letterCount.get(largest)) > 0) {
				largest = s;
			}
			if(smallest.equals("") || letterCount.get(s).compareTo(letterCount.get(smallest)) < 0) {
				smallest = s;
			}
		}
		System.out.println("Most common minus least common: " + (letterCount.get(largest).subtract(letterCount.get(smallest))));
	}
	public static List<PairCount> count(List<PairCount> count, int depth) {
		if(depth==40) {
			return count;
		}
		List<PairCount> newCount = new ArrayList<>();
		for(int i = 0; i < count.size(); i++) {
			String left = count.get(i).left;
			String right = count.get(i).right;
			boolean addNewLeftValue = true, addNewRightValue = true;
			for(int j = 0; j < newCount.size(); j++) {
				if(newCount.get(j).name.equals(left)) {
					newCount.get(j).count = newCount.get(j).count.add(count.get(i).count);
					addNewLeftValue = false;
					break;
				}
			}
			for(int j = 0; j < newCount.size(); j++) {
				if(newCount.get(j).name.equals(right)) {
					newCount.get(j).count = newCount.get(j).count.add(count.get(i).count);
					addNewRightValue = false;
					break;
				}
			}
			if(addNewLeftValue) {
				newCount.add(new PairCount(left,count.get(i).count));
			}
			if(addNewRightValue) {
				newCount.add(new PairCount(right,count.get(i).count));
			}
		}
		return count(newCount, depth+1);
	}
	public static void print(List<PairCount> p) {
		System.out.println("Polymers:");
		for(PairCount c : p) {
			System.out.print(c.name + ": " + c.count + ",  ");
		}
		System.out.println();
	}
}