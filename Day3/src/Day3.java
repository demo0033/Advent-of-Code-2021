import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day3 {
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day3.txt");
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
		p1(data);
		p2(data);
	}
	public static void p2(List<String> data) {
		List<String> oData = new ArrayList<>();
		for(int i = 0; i < data.size(); i++) {
			oData.add(data.get(i));
		}
		int oxygen = getOxygen(oData);
		int co2 = getCo2(data);
		System.out.println("Part 2:");
		System.out.println("Oxygen: " + oxygen);
		System.out.println("Co2: " + co2);
		System.out.println("Life support: " + co2*oxygen);
		System.out.println();
	}
	
	public static int getCo2(List<String> data) {
		int bit = 0;
		while(data.size()>1) {
			if(bit == 12) {
				break;
			}
			
			int count = 0;
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).charAt(bit)=='0') {
					count++;
				}
			}
			char matchMe = '0';
			if(count>data.size()-count) {
				matchMe = '1';
			}
			if(count==data.size()) {
				matchMe= data.get(0).charAt(bit);
			}
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).charAt(bit)!=matchMe) {
					data.remove(i);
					i--;
				}
			}
			bit++;
		}
		
		int co2 = 0;
		for(int j = 0; j < 12; j++) {
			if(data.get(0).charAt(j)=='1') {
				co2 += 1 << (11-j);
			}
		}
		return co2;
	}
	
	public static int getOxygen(List<String> data) {
		int bit = 0;
		while(data.size()>1) {
			if(bit == 12) {
				break;
			}
			
			int count = 0;
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).charAt(bit)=='1') {
					count++;
				}
			}
			char matchMe = '1';
			if(count<data.size()-count) {
				matchMe = '0';
			}
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).charAt(bit)!=matchMe) {
					data.remove(i);
					i--;
				}
			}
			bit++;
		}
		
		int oxygen = 0;
		for(int j = 0; j < 12; j++) {
			if(data.get(0).charAt(j)=='1') {
				oxygen += 1 << (11-j);
			}
		}
		return oxygen;
	}
	public static void p1(List<String> data) {
		int dataSize=12;
		int[] ones = new int[dataSize];
		for(int j = 0; j < dataSize; j++) {
			ones[j] = 0;
		}
		
		for(int i = 0; i < data.size(); i++) {
			for(int j = 0; j < dataSize; j++) {
				if(data.get(i).charAt(j)=='1') {
					ones[j]++;
				}
			}
		}
		
		int gamma = 0;
		for(int j = 0; j < dataSize; j++) {
			if(ones[dataSize-(j+1)]>(data.size()/2)) {
				gamma += 1 << j;
			}
		}
		System.out.println();
		int epsilon = ((1 << dataSize) - 1) - gamma;
		System.out.println("Part 1:");
		System.out.println("Gamma: " + gamma);
		System.out.println("Epsilon: " + epsilon);
		System.out.println("Power consumption: " + gamma*epsilon);
		System.out.println();
	}
}