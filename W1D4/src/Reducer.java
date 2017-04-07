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
				// System.out.println("equals   "+strNext);
				// System.out.println(list);
			 }
			 //if this key does not equal,store it to the collectionOfGroupByPair
			 else
			 {
				
				 if(!strtemp.equals(""))
				 {
					
					 list.add(pairInProcessing.getValues().size());
					
					 collectionOfGroupByPair.add(new GroupByPair(strtemp,list));
	
					 list=new ArrayList <Integer> ();
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
	 
	 
	 public static List <GroupByPair> reducerOutput(List <GroupByPair>collectionOfPair)
	 {
	
		 String strtemp="";
		 GroupByPair rePair=new GroupByPair();
		 GroupByPair pair=new GroupByPair();
		 List <Integer> relist=new ArrayList <Integer> ();
		 List <Integer> templist=new ArrayList <Integer> ();
		 
		 List <GroupByPair> reGroupByPair=new ArrayList <GroupByPair> ();
		 
		 Iterator <GroupByPair> it =collectionOfPair.iterator();
		 
		 while (  it.hasNext() )
		 {
			 relist=new ArrayList <Integer> ();
			 
			 pair=it.next();
			 strtemp=pair.getKey();
			 templist=pair.getValues();
			 int n=0;
			 for(int i=0;i<templist.size();i++)
			 {
				 n+=templist.get(i);
				 
			 }
			 relist.add(n);
		
			 reGroupByPair.add(new GroupByPair(strtemp,relist));
			 
		 }
		
		 return reGroupByPair;
	 }
	 
	 public static void main(String [] args)
	 {
		 List <GroupByPair> listPair=new ArrayList <GroupByPair>();
		 List <Integer> listInt=new ArrayList<Integer>();
		 listInt.add(1);
		 listInt.add(1);
		 List <Integer> listInt2=new ArrayList<Integer>();
		 listInt2.add(1);
		 GroupByPair g1=new GroupByPair("apple", listInt);
		 GroupByPair g2=new GroupByPair("banana", listInt2);
		 GroupByPair g3=new GroupByPair("orange", listInt2);
		 listPair.add(g1);
		 listPair.add(g1);
		 listPair.add(g2);
		 listPair.add(g3);
		 System.out.println("\nprevious list:"+ listPair);
		 List <GroupByPair> reList= combineReducerInput(listPair);
		 System.out.println("\nresult list:"+reList);
		 
		 
		 System.out.println("\noutput result list:"+reducerOutput(reList));
		
		 
	 }
	 
	 

}
