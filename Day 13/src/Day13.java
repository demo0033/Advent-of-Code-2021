import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day13 {
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day13.txt");
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
		Set<Point> sheet = new HashSet<>();
		while(true) {
			if(data.get(0).equals("")) {
				data.remove(0);
				break;
			}
			String[] point = data.get(0).split(",");
			data.remove(0);
			int x = Integer.parseInt(point[0]);
			int y = Integer.parseInt(point[1]);
			sheet.add(new Point(x,y));
		}
		for(int i = 0; i < data.size(); i++) {
			String[] fold = data.get(i).split("=");
			int index = Integer.parseInt(fold[1]);
			List<Point> newPoints = new LinkedList<>();
			for(Iterator<Point> j = sheet.iterator(); j.hasNext();) {
			    Point p = j.next();
			    if(p.x > index && fold[0].substring(11).equals("x")) {
			    	newPoints.add(new Point(index-(p.x-index),p.y));
			    	j.remove();
			    }
			    else if(p.y > index && fold[0].substring(11).equals("y")) {
			    	newPoints.add(new Point(p.x,index-(p.y-index)));
					j.remove();
			    }
			}
			for(Point p : newPoints) {
				sheet.add(p);
			}
		}
		boolean[][] answer = new boolean[40][6];
		for(Point p : sheet) {
			answer[p.x][p.y] = true;
		}
		for(int i = 0; i < answer[0].length;i++) {
			for(int j = 0; j < answer.length;j++) {
				if(answer[j][i]) {
					System.out.print("x ");
				}
				else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}
}