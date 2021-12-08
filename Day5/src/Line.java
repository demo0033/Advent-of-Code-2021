
public class Line {
	boolean isVertical, isHorizontal, isPos, isNeg;
	Point p1, p2;
	int length = 0;
	public Line(String point1, String point2) {
		String[] p1values = point1.split(",");
		String[] p2values = point2.split(",");
		
		p1 = new Point(Integer.parseInt(p1values[0]),Integer.parseInt(p1values[1]));
		p2 = new Point(Integer.parseInt(p2values[0]),Integer.parseInt(p2values[1]));
		
		isVertical = false;
		isHorizontal = false;
		isPos = false;
		isNeg = false;
		
		if(p1.x == p2.x) {
			isVertical = true;
			length = Math.abs(p1.y-p2.y);
		}
		else if(p1.y == p2.y) {
			isHorizontal = true;
			length = Math.abs(p1.x-p2.x);
		}
		else if((p1.x < p2.x && p1.y < p2.y) || (p2.x < p1.x && p2.y < p1.y)) {
			isPos = true;
			length = Math.abs(p2.x-p1.x);
		}
		else {
			isNeg = true;
			length = Math.abs(p1.x-p2.x);
		}
	}
}
