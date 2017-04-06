import java.util.ArrayList;

public class Mapper {
	
	 public static  ArrayList <Pair> mapperOutput(String str)
	 {
		 
		 String [] stringOfWords = str.split("[- ,.\"']+");
		  ArrayList <Pair>collectionOfPair=new ArrayList ();
		
		
			  for (String s : stringOfWords) 
			  {
				  //make all string to lower case
				  s=s.toLowerCase();
				  
				  // if the string is an english word
					if (!s.equals("txt")&&!s.equals("abc")&&!s.equals("mum")&&!s.equals("edu")&&s.matches("^[A-Za-z][A-Za-z]*$")) {
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
