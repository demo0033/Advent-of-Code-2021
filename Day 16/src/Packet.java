import java.util.ArrayList;
import java.util.List;

public class Packet {
	int versionNum, typeID, size;
	long literalValue;
	List<Packet> packetList;
	public Packet(int v, int id) {
		versionNum = v;
		typeID = id;
		packetList = new ArrayList<>();
	}
	public Packet(int v, int id, Packet p) {
		this(v, id);
		packetList.add(p);
	}
	public Packet(int v, int id, long lv) {
		this(v, id);
		literalValue = lv;
	}
}
