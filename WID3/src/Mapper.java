import java.util.ArrayList;
import java.util.Collection;

public class Mapper {
	
	 public static  Collection <Pair> mapperOutput(String str)
	 {
		 
		 String [] stringOfWords = str.split("[- ,.\"]+");
		  Collection <Pair>collectionOfPair=new ArrayList ();
		
		
			  for (String s : stringOfWords) 
			  {
				  //make all string to lower case
				  s=s.toLowerCase();
				  
				  // if the string is an english word
					if (!s.equals("txt")&&!s.equals("abc")&&s.matches("^[A-Za-z][A-Za-z]*$")) {
						// put it in collections
					    
						collectionOfPair.add(new Pair(s,1));
					}
			  }
			  
		//sort the collection from a to z
		PairComparator comparatorOfPair=new PairComparator();
		Pair.sort(collectionOfPair, comparatorOfPair);
		
		return  collectionOfPair;
	 }
	
	
}
