
public class Pair {
	
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
		return "(" + key + "," + value + ")";
	}

}
