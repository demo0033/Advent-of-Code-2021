import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day7 {
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day7.txt");
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
		String[] splitData = data.get(0).split(",");
		List<Integer> positions = new ArrayList<>();
		
		int total = 0;
		for(int i = 0; i < splitData.length; i++) {
			positions.add(i, Integer.parseInt(splitData[i]));
			total+=positions.get(i);
		}
		Collections.sort(positions);
		
		int lowestCost = Integer.MAX_VALUE, lowestPosition = 0;
		for(int i = positions.get(0); i < positions.get(positions.size()-1); i++) {
			int cost = calcScore(positions, i);
			if(cost < lowestCost) {
				lowestCost = cost;
				lowestPosition = i;
			}
		}
		System.out.println("Best position: " + lowestPosition);
		System.out.println("Cost: " + lowestCost);
		
//		Average??
//		int average = (int)Math.round((double)total/positions.size());
//		
//		int totalCost = 0;
//		for(int i = 0; i < positions.size(); i++) {
//			int distance = Math.abs(average-positions.get(i));
//			totalCost+=(distance+1) * (distance/2);
//			if(distance%2==1) {
//				totalCost+=distance/2+1;
//			}
//		}
//		System.out.println("Total Cost: " + totalCost);
		
		
		
//		~~~~P1~~~~
//		Median??
//		int middle = positions.size()/2;
//		int median = positions.get(middle+1);
//		
//		if(positions.size()%2 == 0) {
//			median = (positions.get(middle) + positions.get(middle-1)) / 2; 
//		}
//		
//		int totalCost = 0;
//		for (int i = 0; i < positions.size(); i++) {
//			totalCost+=Math.abs(positions.get(i)-median);
//		}
//		System.out.println("Total Cost: " + totalCost);
	}
	public static int calcScore(List<Integer> positions, int v) {
		int totalCost = 0;
		for(int i = 0; i < positions.size(); i++) {
			int distance = Math.abs(v-positions.get(i));
			totalCost+=(distance+1) * (distance/2);
			if(distance%2==1) {
				totalCost+=distance/2+1;
			}
		}
		return totalCost;
	}
}