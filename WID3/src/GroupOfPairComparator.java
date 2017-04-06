import java.util.Comparator;

public class GroupOfPairComparator implements Comparator<GroupOfPair>{

	@Override
	public int compare(GroupOfPair p1, GroupOfPair p2) {
		return p1.getKey().compareTo(p2.getKey());
	}
}