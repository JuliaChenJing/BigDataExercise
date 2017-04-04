
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Pair implements Comparable<Pair> {
	
	private String key;
	private int value;
	Pair(String k, int v)
	{
		key=k;
		value=v;
		
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "\n(" + key + "," + value + ")";
	}
	
	@Override
	public int compareTo(Pair p) {
		
		return this.key.compareTo(p.key);
	}
	
	  
	public static <T> List<T> sort(Collection<T> self, Comparator<T> comparator) {
		  List<T> list = (List<T>) self;
		  Collections.sort(list, comparator);
		  return list;
		}

}
