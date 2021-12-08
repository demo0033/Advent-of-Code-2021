import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day4 {
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day4.txt");
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
		String[] tempNums = data.get(0).split(",");
		int[] nums = new int[tempNums.length];
		
		for(int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(tempNums[i]);
		}
		int boardCount = data.size()/6;
		Board[] boards = new Board[boardCount];
		
		for(int i = 0; i <boardCount; i++) {
			String[] boardInfo = new String[5];
			for(int j = 0; j < 5; j++) {
				boardInfo[j] = data.get(j + 2 + ( i * 6 ));
			}
			boards[i] = new Board(boardInfo);
		}
		int winningScore = 0;
		int completeBoards = 0;
		outerloop:
		for(int i = 0; i < nums.length; i++) {
			System.out.println("Guessing "+ nums[i]);
			for(int j =0; j<boards.length; j++) {
				if(boards[j] != null && boards[j].checkNumber(nums[i])) {
					completeBoards++;
					System.out.println(completeBoards + "/" + nums.length);
					if(completeBoards==nums.length) {
						boards[j].print();
						winningScore = boards[j].getScore()*nums[i];
						break outerloop;
					}
					boards[j] = null;
				}
			}
		}
		System.out.println("Winning Boardscore: " + winningScore);
	}
}