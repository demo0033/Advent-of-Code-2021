import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day11 {
	static int width;
	static int height;
	static int[][] octoMap;
	static boolean[][] flashed;
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day11.txt");
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
		
		// Initialize...
		width = data.get(0).length();
		height = data.size();
		octoMap = new int[width][height];
		flashed = new boolean[width][height];
		int syncCount = 0;
		int stepCount = 0;
		
		// Populate map
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				octoMap[x][y] = Integer.parseInt(data.get(y).substring(x, x+1));
				flashed[x][y] = false;
			}
		}
		
		// Iterate 100 steps
		while(syncCount<100) {
			syncCount = 0;
			for(int y = 0; y < 10; y++) {
				for(int x = 0; x < 10; x++) {
					if(!flashed[x][y]) {
						octoMap[x][y]++;
						octoMap[x][y]%=10;
						if(octoMap[x][y]==0) {
							flashed[x][y]= true;
							syncCount+=flash(x,y);
						}
					}
				}
			}
			for(int y = 0; y < 10; y++) {
				for(int x = 0; x < 10; x++) {
					flashed[x][y]= false;
				}
			}
			stepCount++;
		}
		System.out.println("Step count: " + stepCount);
	}
	public static int flash(int x, int y) {
		int count = 1;
		for(int i = 0; i < 9; i++) {
			try {
				int newX = x - 1 + i%3;
				int newY = y - 1 + i/3;
				
				if(!flashed[newX][newY]) {
					octoMap[newX][newY]++;
					octoMap[newX][newY] %=10;
					if(octoMap[newX][newY]==0) {
						flashed[newX][newY]= true;
						count+=flash(newX,newY);
					}
				}
			} catch(IndexOutOfBoundsException e) {}
		}
		
		return count;
	}
}