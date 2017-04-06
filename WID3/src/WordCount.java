
import java.util.List;

public class WordCount {
	


	List <Pair>  mappers;
	List <GroupOfPair>  reducers;
	
	WordCount()
	{
		
	}
	
	public WordCount(List<Pair> mappers, List<GroupOfPair> reducers) {
		super();
		this.mappers = mappers;
		this.reducers = reducers;
	}

	

	   public int getPartition(String key){
           return (int) key.hashCode() % reducers.size();
    }


	public List<Pair> getMappers() {
		return mappers;
	}


	public void setMappers(List<Pair> mappers) {
		this.mappers = mappers;
	}


	public List<GroupOfPair> getReducers() {
		return reducers;
	}


	public void setReducers(List<GroupOfPair> reducers) {
		this.reducers = reducers;
	}
}
