import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day8 {
	public static String a, b, c, d, e, f, g;
	public static void main(String[] args) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File("day8.txt");
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
		
		int sum = 0;
		a = "";
		e = "";
		g = "";
		b = "";
		c = "";
		d = "";
		f = "";
		
		for(int i = 0; i < data.size(); i++) {
			String[] output = data.get(i).split(" ");
			
//			Find 1 to label c/f
			for(int j = 0; j < 10; j++) {
				if(output[j].length()==2) {
					c=output[j];
					f=c;
					break;
				}
			}

//			Find 4 to label b/d
			for(int j = 0; j < 10; j++) {
				if(output[j].length()==4) {
					b = output[j].replace(c.substring(1), "")
							.replace(c.substring(0,1),"");
					d = b;
					break;
				}
			}

//			Find 7 to label a
			for(int j = 0; j < 10; j++) {
				if(output[j].length()==3) {
					a = output[j].replace(c.substring(1), "")
							.replace(c.substring(0,1),"");
					break;
				}
			}
			
//			Find 9 to label g
			for(int j = 0; j < 10; j++) {
				if(output[j].length()==6 &&
						output[j].contains(b.substring(0, 1)) &&
						output[j].contains(b.substring(1)) &&
						output[j].contains(c.substring(0, 1)) &&
						output[j].contains(c.substring(1))) {
					g = output[j].replace(b.substring(0, 1), "")
							.replace(b.substring(1), "")
							.replace(c.substring(0, 1), "")
							.replace(c.substring(1), "")
							.replace(a, "");
					break;
				}
			}
			
//			Find 8 to label e
			for(int j = 0; j < 10; j++) {
				if(output[j].length()==7) {
					e = output[j].replace(b.substring(0, 1), "")
							.replace(b.substring(1), "")
							.replace(c.substring(0, 1), "")
							.replace(c.substring(1), "")
							.replace(g,"")
							.replace(a,"");
					break;
				}
			}
			
//			Find 2 to label rest
			for(int j = 0; j < 10; j++) {
				if(output[j].length()==5 &&
						output[j].contains(e)) {
					if(output[j].contains(c.substring(1))) {
						c = c.substring(1);
						f = f.substring(0,1);
					}
					else {
						c = c.substring(0,1);
						f = f.substring(1);
					}
					if(output[j].contains(d.substring(1))) {
						d = d.substring(1);
						b = b.substring(0,1);
					}
					else {
						d = d.substring(0,1);
						b = b.substring(1);
					}
					break;
				}
			}
			
			sum+= Integer.parseInt(getNumber(output[11]) + getNumber(output[12])
				+ getNumber(output[13]) + getNumber(output[14]));
		}
		System.out.println("Sum of values: " + sum);
	}
	public static String getNumber(String c) {
		if(c.length() == 2) {
			return "1";
		}
		else if(c.length() == 3) {
			return "7";
		}
		else if(c.length() == 4) {
			return "4";
		}
		else if(c.length() == 7) {
			return "8";
		}
		else if(c.length() == 5) {
			if(c.contains(b)) {
				return "5";
			}
			else if(c.contains(e)) {
				return "2";
			}
			else {
				return "3";
			}
		}
		else {
			if(!c.contains(e)) {
				return "9";
			}
			else if(c.contains(d)) {
				return "6";
			}
			else {
				return "0";
			}
		}
	}
}