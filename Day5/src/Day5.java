import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day5 {
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day5.txt");
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
		
		Line[] lines = new Line[data.size()];
		int[][] board = new int[1000][1000];
		int count = 0;
		
		for(int i = 0; i < data.size(); i++) {
			String[] tokens = data.get(i).split(" ");
			lines[i] = new Line(tokens[0],tokens[2]);
			
			if(lines[i].isVertical) {
				for(int j = 0; j <= lines[i].length; j++) {
					int tempY = Math.min(lines[i].p1.y, lines[i].p2.y)+j;
					board[lines[i].p1.x][tempY]++;
					if(board[lines[i].p1.x][tempY]==2) {
						count++;
					}
				}
			}
			else if(lines[i].isHorizontal) {
				for(int j = 0; j <= lines[i].length; j++) {
					int tempX = Math.min(lines[i].p1.x, lines[i].p2.x)+j;
					board[tempX][lines[i].p1.y]++;
					if(board[tempX][lines[i].p1.y]==2) {
						count++;
					}
				}
			}
			else if(lines[i].isPos) {
				for(int j = 0; j <= lines[i].length; j++) {
					int tempX = Math.min(lines[i].p1.x, lines[i].p2.x)+j;
					int tempY = Math.min(lines[i].p1.y, lines[i].p2.y)+j;
					board[tempX][tempY]++;
					if(board[tempX][tempY]==2) {
						count++;
					}
				}
			}
			else {
				for(int j = 0; j <= lines[i].length; j++) {
					int tempX = Math.min(lines[i].p1.x, lines[i].p2.x)+j;
					int tempY = Math.max(lines[i].p1.y, lines[i].p2.y)-j;
					board[tempX][tempY]++;
					if(board[tempX][tempY]==2) {
						count++;
					}
				}
			}
		}
		
		for(int i = 0; i < 300; i++) {
			for(int j = 0; j < 300; j++) {
				System.out.print(board[j][i]);
			}
			System.out.println();
		}
		
		System.out.println("Points at +2: " + count);
	}
}