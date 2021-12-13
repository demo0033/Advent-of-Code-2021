import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day9 {
	static int[][] intMap;
	static boolean[][] searchedMap;
	static int width, height;
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day9.txt");
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
		width = data.get(0).length();
		height = data.size();
		
		intMap = new int[width][height];
		searchedMap = new boolean[width][height];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				intMap[x][y] = Integer.parseInt(data.get(y).substring(x, x+1));
				searchedMap[x][y] = false;
				if(intMap[x][y] == 9) {
					searchedMap[x][y]=true;
				}
			}
		}
		
		p1();
		p2();
		
	}
	
	public static void p2() {
		int basin1 = 0, basin2 = 0, basin3=0, current = 0;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				current = findBasin(x,y);
				if(current>basin1) {
					basin3=basin2;
					basin2=basin1;
					basin1=current;
				}
				else if(current>basin2) {
					basin3=basin2;
					basin2=current;
				}
				else if(current>basin3) {
					basin3 = current;
				}
			}
		}
		System.out.println(basin1*basin2*basin3);
	}
	
	public static int findBasin(int x, int y) {
		if(searchedMap[x][y]) {
			return 0;
		}
		int sum = 1;
		searchedMap[x][y] = true;
		if(x+1<width) {
			sum+=findBasin(x+1,y);
		}
		if(y+1<height) {
			sum+=findBasin(x,y+1);
		}
		if(x-1>=0) {
			sum+=findBasin(x-1,y);
		}
		if(y-1>=0) {
			sum+=findBasin(x,y-1);
		}
		return sum;
	}
	public static void p1() {
		int risk = 0;
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				boolean isLow = false;
				if(intMap[x][y] == 0) {
					isLow=true;
				}
				else if(intMap[x][y] < 9) {
					if(x!=0 && intMap[x-1][y] < intMap[x][y]) {
						continue;
					}
					if(x+1 != width && intMap[x+1][y] < intMap[x][y]) {
						continue;
					}
					if(y!=0 && intMap[x][y-1] < intMap[x][y]) {
						continue;
					}
					if(y+1 != height && intMap[x][y+1] < intMap[x][y]) {
						continue;
					}
					isLow=true;
					
				}
				if(isLow) {
					risk+=intMap[x][y]+1;
				}
			}
		}
		
		System.out.println("Risk: " + risk);
	}
}