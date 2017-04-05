import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class GroupByPair {
	
	public GroupByPair(String key,List <Integer> values) {

		this.key = key;
		this.values=values;
		numOfValues=0;
	}
	String key;
	List <Integer> values;
	private int numOfValues;
	@Override
	public String toString() {
		return "\n(" + key + "," + values + ")";
	}
	
	public String toString_reducerOutput() {
		
		return "(" + key + "," + values.size()+ ")";
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

	public static void main(String  [] args){
		List <Integer>array=new ArrayList<Integer> ();
		array.add(1);
		array.add(2);
		
		GroupByPair pair=new GroupByPair("a",array);
		System.out.println("1"+pair);
		System.out.println("2"+pair.toString_reducerOutput());
	}
}
