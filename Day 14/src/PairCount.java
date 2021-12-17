import java.math.BigInteger;

public class PairCount {
	String name, left, right;
	BigInteger count;
	public PairCount(String n, BigInteger c) {
		name = n;
		count = c;
		left = n.substring(0, 1) + Day14P2.rules.get(n);
		right = Day14P2.rules.get(n) + n.substring(1);
	}
}
