import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day15 {
	public static int [][] map;
	public static int[][] path;
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day15.txt");
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
		int width = data.get(0).length()*5, height = data.size()*5; 
		map = new int[width][height];
		path = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int xMod = x % 100;
				int yMod = y % 100;
				int xFactor = x / 100;
				int yFactor = y / 100;
				int n = Integer.parseInt(data.get(yMod).substring(xMod,xMod+1));
				for(int i = 0; i < xFactor + yFactor; i++) {
					n++;
					if(n== 10) {n=1;}
				}
				map[x][y] = n;
				path[x][y] = 1000000;
			}
		}
		System.out.println("Map loaded");
		path[0][0] = 0;
		for(int diagRow = 1; diagRow < height; diagRow++) {
			for(int diagCol = 0; diagCol <= diagRow; diagCol++) {
				int x = diagCol, y = diagRow-diagCol;
				check(x,y);
			}
		}
		for(int diagRow = 1; diagRow < height; diagRow++) {
			for(int diagCol = height-1; diagCol >= diagRow; diagCol--) {
				int x = diagCol, y = (height-1)-(diagCol-diagRow);
				check(x,y);
			}
		}
		System.out.println("Shortest Path: " + path[width-1][height-1]);
	}
	public static void check(int x, int y) {
		int smallX = 1000000, smallY = 1000000, smallValue = 1000000;
		if(x != 0) { // check left
			smallX = x-1;
			smallY = y;
			smallValue = path[smallX][smallY];
		}
		if(x != 499) { // check right
			if(path[x+1][y] < smallValue) {
				smallX = x+1;
				smallY = y;
				smallValue = path[smallX][smallY];
			}
		}
		if(y != 0) { // check check above
			if(path[x][y-1] < smallValue) {
				smallX = x;
				smallY = y-1;
				smallValue = path[smallX][smallY];
			}
		}
		if(y != 499) { // check below
			if(path[x][y+1] < smallValue) {
				smallX = x;
				smallY = y+1;
				smallValue = path[smallX][smallY];
			}
		}
		if(path[x][y] > smallValue) {
			path[x][y] = smallValue + map[x][y];
			check(smallX,smallY);
		}
	}
}