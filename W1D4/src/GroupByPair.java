import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GroupByPair {
	
	String key;
	List <Integer> values;
	
	public GroupByPair(String key,List <Integer> values) {

		this.key = key;
		this.values=values;
		
	}
	
	public GroupByPair()
	{
		
	}
	
	@Override
	public String toString() {
		return "\n<"+ key + ", " + values + ">";
	}

	

	public String toString_showSize() {
		return "\n<" + key + "," + values.size()+ ">";
	}
	

	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<Integer> getValues() {
		return values;
	}
	public void setValues(List<Integer> values) {
		this.values = values;
	}

	public static <T> List<T> sort(Collection<T> self, Comparator<T> comparator) {
		  List<T> list = (List<T>) self;
		  Collections.sort(list, comparator);
		  return list;
		}




}
