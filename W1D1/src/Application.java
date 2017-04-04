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
		  Collection <Pair >collection=new ArrayList ();
		
			  for (String s : stringOfWords) 
			  {
				  //make all string to lower case
				  s=s.toLowerCase();
				  // if the string is an english word
					if (s.matches("^[A-Za-z][A-Za-z]*$")) {
						// put it in collection
						collection.add(new Pair(s,1));
						//System.out.println(s);
						
					}
			  }
			  
		//sort the collection from a to z
		PairComparator comparator=new PairComparator();
		Pair.sort(collection, comparator);
		// print out the collection
		System.out.println(collection.toString());
		
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
