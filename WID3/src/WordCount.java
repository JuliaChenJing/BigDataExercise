
import java.util.List;

public class WordCount {

	

	   public static int getPartition(String key, int numOfReducers){
           return (int) key.hashCode() % numOfReducers;
    }

}
