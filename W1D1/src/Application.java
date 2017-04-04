import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
public class Application {
	
	public static void main(String [] args) throws IOException
	{
		
		//Pair pair=new Pair("cat",1);	
		//System.out.println(pair.toString());
		String str="";
		
		try {
			 str=readFile();
			//System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("---"+e.getMessage());
		}
		
		  String [] stringOfWords = str.split(" ");
		  Collection <Pair >collection=new ArrayList ();
		
			  for (String s : stringOfWords) 
			  {
				  s=s.toLowerCase();
					if (s.matches("^[A-Za-z][A-Za-z0-9]*$")) {
						collection.add(new Pair(s,1));
						//System.out.println(s);
						
					}
			  }
			  
		PairComparator comparator=new PairComparator();
		Pair.sort(collection, comparator);
		System.out.println(collection.toString());
		
	}
	
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