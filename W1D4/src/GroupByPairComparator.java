import java.util.Comparator;

public class GroupByPairComparator implements Comparator<GroupByPair>{

	@Override
	public int compare(GroupByPair p1, GroupByPair p2) {
		return p1.getKey().compareTo(p2.getKey());
	}
}