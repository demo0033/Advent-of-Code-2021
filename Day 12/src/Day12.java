import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day12 {
	public static Map<String, Cave> caves;
	public static Set<String> paths;
	public static void main(String[] args) {
		List<String[]> data = new ArrayList<>();
		try {
			File file = new File("day12.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				data.add(line.split("-"));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		caves = new HashMap<>();
		paths = new HashSet<>();
		for(int i = 0; i < data.size(); i++) {
			boolean size;
			
			// Create first cave of connection
			String id1 = data.get(i)[0];
			if(!caves.containsKey(id1)) {
				size = Cave.BIG;
				if(id1.equals(id1.toLowerCase())) {
					size = Cave.SMALL;
				}
				Cave c1 = new Cave(id1,size);
				caves.put(id1, c1);
			}
			// Create second cave of connection
			String id2 = data.get(i)[1];
			if(!caves.containsKey(id2)) {
				size = Cave.BIG;
				if(id2.equals(id2.toLowerCase())) {
					size = Cave.SMALL;
				}
				Cave c2 = new Cave(id2,size);
				caves.put(id2, c2);
			}
			
			// Add connections
			caves.get(id1).openConnections.add(id2);
			caves.get(id2).openConnections.add(id1);
		}
		
		findEnd("start", true, "");
		System.out.println("Paths: " + paths.size());
	}
	public static int findEnd(String start, boolean hasTime, String path) {
		if(!caves.get(start).isOpen) {
			return 0;
		}
		path+=caves.get(start).id;
		if(start.equals("end")) {
			paths.add(path);
			return 1;
		}
		int paths = 0;
		if(!caves.get(start).isBig) {
			caves.get(start).isOpen = false;
			for(String s : caves.get(start).openConnections) {
				paths+=findEnd(s, hasTime,path);
			}
			caves.get(start).isOpen = true;
			
			if(hasTime && !start.equals("start")) {
				for(String s : caves.get(start).openConnections) {
					paths+=findEnd(s, false,path);
				}
			}
		}
		else {
			for(String s : caves.get(start).openConnections) {
				paths+=findEnd(s, hasTime,path);
			}
		}
		return paths;
	}
}