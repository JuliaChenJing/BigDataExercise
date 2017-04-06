import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Application {
	
	public static void main(String [] args) throws IOException
	{
		
		
		// read the txt file to string
		String str="";
		try {
			 str=readFile();
			//System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("---"+e.getMessage());
		}
		
		Mapper mapper=new Mapper();
		 Collection <Pair>collectionOfPair=mapper.mapperOutput(str);

		System.out.println("---------------------------Mapper Output-------------");
		System.out.println(collectionOfPair.toString());
		
		
		// -Reducer Input--
		 String strtemp="";
		 String strNext="";
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
		       
		System.out.println("---------------------------Reducer Input------------");
		System.out.println(collectionOfGroupByPair.toString());
		
		System.out.println("---------------------------Reducer Output------------");
		
		for (Iterator <GroupByPair> it_2 =collectionOfGroupByPair.iterator(); it_2.hasNext(); )
		 {
			GroupByPair temp=it_2.next();
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

