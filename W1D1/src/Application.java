import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class Application {
	
	public static void main(String [] args) throws IOException
	{
		
		String str="";
		
		// read the txt file to string
		try {
			 str=readFile();
			//System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("---"+e.getMessage());
		}
		
		//str="  5. Note that tokens such as input123, abc.txt,  a23bc and abc_ptr  are not words. However, key-value is two words.";
		
		//split the long string to array of strings
		  String [] stringOfWords = str.split("[- ,.\"]+");
		  Collection <Pair>collectionOfPair=new ArrayList ();
		  Collection <GroupByPair >collectionOfGroupByPair=new ArrayList ();
		
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
		System.out.println("---------------------------Mapper Output-------------");
		System.out.println(collectionOfPair.toString());
		
		 String strtemp="";
		 String strNext="";
		 int n=1;
		 for (Iterator <Pair> it =collectionOfPair.iterator();  it.hasNext(); )
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
				
				// System.out.println(strtemp);
			 } 
			
			 
		 }
		       
		System.out.println("---------------------------Reducer Input------------");
		System.out.println(collectionOfGroupByPair.toString());
		
		System.out.println("---------------------------Reducer Output------------");
		 for (Iterator <GroupByPair> it =collectionOfGroupByPair.iterator(); it.hasNext(); )
		 {
			GroupByPair temp=it.next();
	    	System.out.println(temp.toString_reducerOutput());
		 }
		
	}
	
	// read the txt file and store it to a string
   static String readFile() 
	{
		String targetFileString="";
		FileInputStream targetFile;
		try {
			targetFile = new FileInputStream(new File("testDataForW1D1.txt"));
			targetFileString =IOUtils.toString(targetFile, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("---"+e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("---"+e.getMessage());
		}
		
		return targetFileString;
	}

}

