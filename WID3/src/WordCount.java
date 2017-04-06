
import java.util.List;

public class WordCount {
	


	List <Pair>  mappers;
	List <GroupByPair>  reducers;
	
	WordCount()
	{
		
	}
	
	public WordCount(List<Pair> mappers, List<GroupByPair> reducers) {
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


	public List<GroupByPair> getReducers() {
		return reducers;
	}


	public void setReducers(List<GroupByPair> reducers) {
		this.reducers = reducers;
	}
}
