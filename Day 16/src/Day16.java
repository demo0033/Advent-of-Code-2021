import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day16 {
	public static void main(String[] args) {
		String data = "";
		try {
			File file = new File("day16.txt");
			Scanner scanner = new Scanner(file);	  
			while (scanner.hasNextLine()) {
				data = scanner.nextLine();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		String bData = "";
		for(int i = 0; i < data.length(); i++) {
			bData += hexToBin(data.substring(i, i+1));
		}
		
		List<Packet> decodedPackets = new ArrayList<>();
		int decodedPacketSize = 0;
		while (decodedPacketSize < bData.length()) {
			Packet p = decode(bData.substring(decodedPacketSize));
			decodedPackets.add(p);
			decodedPacketSize += p.size;
			decodedPacketSize += (4 - (decodedPacketSize % 4));
		}
		int sum = 0;
		for(Packet p : decodedPackets) {
			sum += getVersionNumSum(p);
		}
		System.out.println("Version number sum: " + sum);
		System.out.println("BITS evaluation: " + evaluatePacket(decodedPackets.get(0)));
	}
	public static long evaluatePacket(Packet p) {
		if(p.typeID == 0) { //sum
			long sum = 0L;
			for(int i = 0; i < p.packetList.size(); i++) {
				sum += evaluatePacket(p.packetList.get(i));
			}
			return sum;
		}
		else if(p.typeID == 1) { //product
			long product = 1L;
			for(int i = 0; i < p.packetList.size(); i++) {
				product *= evaluatePacket(p.packetList.get(i));
			}
			return product;
		}
		else if(p.typeID == 2) { // minimum
			long minimum = evaluatePacket(p.packetList.get(0));
			for(int i = 1; i < p.packetList.size(); i++) {
				if(minimum > evaluatePacket(p.packetList.get(i))) {
					minimum = evaluatePacket(p.packetList.get(i));
				}
			}
			return minimum;
		}
		else if(p.typeID == 3) { // maximum
			long maxmimum = evaluatePacket(p.packetList.get(0));
			for(int i = 1; i < p.packetList.size(); i++) {
				if(maxmimum < evaluatePacket(p.packetList.get(i))) {
					maxmimum = evaluatePacket(p.packetList.get(i));
				}
			}
			return maxmimum;
		}
		else if(p.typeID == 4) { // literal value
			return p.literalValue;
		}
		else if(p.typeID == 5) { // greater than
			if(evaluatePacket(p.packetList.get(0))>evaluatePacket(p.packetList.get(1))) {
				return 1;
			}
			return 0;
		}
		else if(p.typeID == 6) { // less than
			if(evaluatePacket(p.packetList.get(0))<evaluatePacket(p.packetList.get(1))) {
				return 1;
			}
			return 0;
		}
		else { // equality
			if(evaluatePacket(p.packetList.get(0))==evaluatePacket(p.packetList.get(1))) {
				return 1;
			}
			return 0;
		}
	}
	public static int getVersionNumSum(Packet p) {
		int sum = p.versionNum;
		for(Packet subp : p.packetList) {
			sum += getVersionNumSum(subp);
		}
		return sum;
	}
	public static Packet decode(String d) {
		int packetSize = 0;
		int v = (int) binToDec(d, 3);
		packetSize += 3;
		int id = (int) binToDec(d.substring(packetSize), 3);
		packetSize += 3;
		
		Packet packet;
		if(id == 4) {
			boolean moreValues = true;
			String literalValue = "";
			while(moreValues) {
				if(d.substring(packetSize, packetSize + 1).equals("0")) {
					moreValues = false;
				}
				packetSize++;
				literalValue += d.substring(packetSize, packetSize + 4);
				packetSize+=4;
			}
			long lv = binToDec(literalValue, literalValue.length());
			packet = new Packet(v, id, lv);
		}
		else {
			int lengthTypeID = Integer.parseInt(d.substring(packetSize, packetSize + 1));
			packetSize++;
			packet = new Packet(v, id);
			if(lengthTypeID == 0) {
				int subpacketLength = (int) binToDec(d.substring(packetSize), 15);
				packetSize+=15;
				int subpacketSize = 0;
				while(subpacketSize < subpacketLength) {
					Packet subpacket = decode(d.substring(packetSize));
					packetSize += subpacket.size;
					subpacketSize += subpacket.size;
					packet.packetList.add(subpacket);
				}
			}
			else {
				int subpacketCount = (int) binToDec(d.substring(packetSize), 11);
				packetSize+=11;
				for(int i = 0; i < subpacketCount; i++) {
					Packet subpacket = decode(d.substring(packetSize));
					packetSize += subpacket.size;
					packet.packetList.add(subpacket);
				}
			}
		}
		packet.size = packetSize;
		return packet;
	}
	public static long binToDec(String value, int length) {
		long binSum = 0L;
		for(int i = 0; i < length; i++) {
			binSum+= Long.parseLong(value.substring(i, i+1)) * (long) Math.pow(2, length - (i + 1));
		}
		return binSum;
	}
	public static String hexToBin(String hex) {
		String bin = "";
		
		if(hex.equals("0")) {
			bin = "0000";
		}
		else if(hex.equals("1")) {
			bin = "0001";
		}
		else if(hex.equals("2")) {
			bin = "0010";
		}
		else if(hex.equals("3")) {
			bin = "0011";
		}
		else if(hex.equals("4")) {
			bin = "0100";
		}
		else if(hex.equals("5")) {
			bin = "0101";
		}
		else if(hex.equals("6")) {
			bin = "0110";
		}
		else if(hex.equals("7")) {
			bin = "0111";
		}
		else if(hex.equals("8")) {
			bin = "1000";
		}
		else if(hex.equals("9")) {
			bin = "1001";
		}
		else if(hex.equals("A")) {
			bin = "1010";
		}
		else if(hex.equals("B")) {
			bin = "1011";
		}
		else if(hex.equals("C")) {
			bin = "1100";
		}
		else if(hex.equals("D")) {
			bin = "1101";
		}
		else if(hex.equals("E")) {
			bin = "1110";
		}
		else if(hex.equals("F")) {
			bin = "1111";
		}
		return bin;
	}
}