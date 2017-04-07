import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMapperWordCount {
	
	public static  ArrayList <Pair> mapperOutput(String str)
	 {
		 
		 String [] stringOfWords = str.split("[- ,.\"'\r\n|\r|\n]+");
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

	
	 public static List <GroupByPair> reducerOutput(List <Pair>collectionOfPair)
	 {
		 String strtemp="";
		 String strNext="";
		 PairComparator comparatorOfGroupByPair=new PairComparator();
		 Pair.sort(collectionOfPair, comparatorOfGroupByPair);
		 List <GroupByPair> collectionOfGroupByPair=new ArrayList <GroupByPair> ();
		 int n=1;
		 Iterator <Pair> it =collectionOfPair.iterator();
		 while (  it.hasNext() )
		 {
			 strNext=it.next().getKey();
			 
			 if(strtemp.equals(strNext))
			 {
				 n++;
			 }
			 else
			 {
				
				 if(!strtemp.equals(""))
				 {
					 
					 List <Integer> list=new ArrayList <Integer> ();
					 for(int i=0;i<n;i++)
						 list.add(1);
					 collectionOfGroupByPair.add(new GroupByPair(strtemp,list)); 
					 n=1;
				 }
				 
				 strtemp=strNext;
				
			 } 
			 
		 }
		 
		 List <Integer> list=new ArrayList <Integer> ();
		 for(int i=0;i<n;i++)
			 list.add(1);
		 collectionOfGroupByPair.add(new GroupByPair(strtemp,list)); 
		 
		 return collectionOfGroupByPair;
	 }

}
