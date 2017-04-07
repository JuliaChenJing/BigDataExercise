import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Reducer {
	
	 public static List <GroupByPair> reducerOutput(List <GroupByPair>collectionOfPair)
	 {
		 String strtemp="";
		 String strNext="";
		 GroupByPairComparator comparatorOfGroupByPair=new GroupByPairComparator();
		 GroupByPair.sort(collectionOfPair, comparatorOfGroupByPair);
		 List <GroupByPair> collectionOfGroupByPair=new ArrayList <GroupByPair> ();
		 int n=1;
		 Iterator <GroupByPair> it =collectionOfPair.iterator();
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
