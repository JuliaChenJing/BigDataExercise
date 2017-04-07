import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Reducer {
	
	 public static List <GroupByPair> combineReducerInput(List <GroupByPair>collectionOfPair)
	 {
		 String strtemp="";
		 String strNext="";
		 GroupByPair pairNext;
		 GroupByPair pairInProcessing=new GroupByPair();
		 List <Integer> list=new ArrayList <Integer> ();
		
		 //sort the list of GroupByPair
		 GroupByPairComparator comparatorOfGroupByPair=new GroupByPairComparator();
		 GroupByPair.sort(collectionOfPair, comparatorOfGroupByPair);
		 
		 
		 List <GroupByPair> collectionOfGroupByPair=new ArrayList <GroupByPair> ();
		 
		 Iterator <GroupByPair> it =collectionOfPair.iterator();
		 
		 while (  it.hasNext() )
		 {
			 pairNext=it.next();
			 strNext=pairNext.getKey();
			
				
			 //if this key equals next key
			 
			 if(strtemp.equals(strNext))
			 {
				 list.add(pairInProcessing.getValues().size());
			 }
			 //if this key does not equal 
			 else
			 {
				
				 if(!strtemp.equals(""))
				 {
					
					 list.add(pairInProcessing.getValues().size());
					 collectionOfGroupByPair.add(new GroupByPair(strtemp,list)); 
					 list.clear();
				 }
				 pairInProcessing=pairNext;
				 strtemp=strNext;
			 } 
			 
		 }
		// List <Integer> list=new ArrayList <Integer> ();
		 list.add(pairInProcessing.getValues().size());
		 collectionOfGroupByPair.add(new GroupByPair(strtemp,list)); 
		 
		 
		 return collectionOfGroupByPair;
	 }
	 
	 public static void main(String [] args)
	 {
		 List <GroupByPair> listPair=new ArrayList <GroupByPair>();
		 List <Integer> listInt=new ArrayList<Integer>();
		 listInt.add(1);
		 listInt.add(2);
		 GroupByPair g1=new GroupByPair("apple", listInt);
		 GroupByPair g2=new GroupByPair("banana", listInt);
		 listPair.add(g1);
		 listPair.add(g1);
		 List <GroupByPair> reList= combineReducerInput(listPair);
		 System.out.println(reList);
		 
	 }
	 
	 

}
