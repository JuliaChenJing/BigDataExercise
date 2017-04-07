import java.util.Comparator;

public class PairComparator implements Comparator<Pair>{

	@Override
	public int compare(Pair p1, Pair p2) {
		return p1.getKey().compareTo(p2.getKey());
	}
}