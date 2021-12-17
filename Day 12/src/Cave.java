import java.util.ArrayList;
import java.util.List;

public class Cave {
	public static final boolean BIG = true, SMALL = false;
	public  List<String> openConnections;
	public String id;
	public boolean isBig;
	public boolean isOpen;
	public Cave(String caveID, boolean cSize) {
		id = caveID;
		isBig = cSize;
		openConnections = new ArrayList<>();
		isOpen = true;
	}
	//Required for Map functionality
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		if(!(o instanceof Cave)) {
			return false;
		}
		Cave c = (Cave)o;
		return c.id.equals(this.id);
	}
}
