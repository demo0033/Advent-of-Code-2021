import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Stack;

public class Day10 {
	public static Stack<Character> stack;
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day10.txt");
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
		
		stack = new Stack<Character>();
		int corrScore = 0;
		List<Long> autoScores = new ArrayList<Long>();
		
		outerLoop:
		for(int i = 0; i < data.size(); i++) {
			for(int j = 0; j < data.get(i).length(); j++) {
				char c = data.get(i).charAt(j);
				if(!nextChar(c)) {
					if(c=='>') {
						corrScore+=25137;
					}
					else if(c==')') {
						corrScore+=3;
					}
					else if(c==']') {
						corrScore+=57;
					}
					else {
						corrScore+= 1197;
					}
					stack.clear();
					continue outerLoop;
				}
			}
			long autoScore = 0;
			while(!stack.isEmpty()) {
				char c = stack.pop();
				autoScore*=5;
				if(c=='>') {
					autoScore+=4;
					continue;
				}
				else if(c==')') {
					autoScore+=1;
				}
				else if(c==']') {
					autoScore+=2;
				}
				else { // c = '}'
					autoScore+= 3;
				}
			}
			autoScores.add(autoScore);
		}
		Collections.sort(autoScores);
		System.out.println("Corrupted Score: " + corrScore);
		System.out.println("Autocomplete Score: " + autoScores.get(autoScores.size()/2));
	}
	public static boolean nextChar(char c) {
		if(c=='<') {
			stack.add('>');
		}
		else if(c=='(') {
			stack.add(')');
		}
		else if(c=='[') {
			stack.add(']');
		}
		else if(c=='{') {
			stack.add('}');
		}
		else if(c!=stack.pop()) {
			return false;
		}
		return true;
	}
}