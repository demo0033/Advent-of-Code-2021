public class Board {
	int[][] values;
	boolean[][] isGuessed;
	public Board(String[] rows) {
		values = new int[5][5];
		isGuessed = new boolean[5][5];
		for(int i = 0; i < 5; i++) {
			
			String[] row = rows[i].trim().replaceAll("  ", " ").split(" ");
			for(int j = 0; j < 5; j++) {
				values[j][i] = Integer.parseInt(row[j].trim());
				isGuessed[j][i] = false;
			}
		}
	}
	
	public boolean checkNumber(int num) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(values[j][i] == num) {
					isGuessed[j][i] = true;
					if(isGuessed[j][(i+1)%5] == true &&
							isGuessed[j][(i+2)%5] == true &&
							isGuessed[j][(i+3)%5] == true &&
							isGuessed[j][(i+4)%5] == true) {
//						System.out.println("BINGO!");
//						print();
						return true;
					}
					if(isGuessed[(j+1)%5][i] == true &&
							isGuessed[(j+2)%5][i] == true &&
							isGuessed[(j+3)%5][i] == true &&
							isGuessed[(j+4)%5][i] == true) {
//						System.out.println("BINGO!");
//						print();
						return true;
					}
				}
			}
		}
		return false;
	}
	public int getScore() {
		int sum = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(isGuessed[j][i] == false)
					sum+=values[j][i];
			}
		}
		return sum;
	}
	public void print() {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(values[j][i] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(isGuessed[j][i] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}