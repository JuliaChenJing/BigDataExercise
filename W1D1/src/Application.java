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
					if (!s.equals("txt")&&!s.equals("abc")&&s.matches("^[A-Za-z][A-Za-z]*$")) {
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

/*
 * [
(a,1), 
(a,1), 
(a,1), 
(a,1), 
(and,1), 
(and,1), 
(and,1), 
(and,1), 
(are,1), 
(as,1), 
(as,1), 
(cat,1), 
(cat,1), 
(class,1), 
(collections,1), 
(comparator,1), 
(each,1), 
(each,1), 
(extract,1), 
(file,1), 
(for,1), 
(form,1), 
(given,1), 
(however,1), 
(inserted,1), 
(integer,1), 
(into,1), 
(involve,1), 
(is,1), 
(is,1), 
(is,1), 
(is,1), 
(key,1), 
(key,1), 
(key,1), 
(list,1), 
(list,1), 
(may,1), 
(not,1), 
(note,1), 
(note,1), 
(one,1), 
(output,1), 
(pair,1), 
(pair,1), 
(pair,1), 
(program,1), 
(program,1), 
(same,1), 
(sort,1), 
(such,1), 
(text,1), 
(that,1), 
(that,1), 
(the,1), 
(the,1), 
(the,1), 
(the,1), 
(the,1), 
(the,1), 
(the,1), 
(tokens,1), 
(treat,1), 
(two,1), 
(using,1), 
(value,1), 
(value,1), 
(value,1), 
(will,1), 
(word,1), 
(word,1), 
(word,1), 
(words,1), 
(words,1), 
(writing,1), 
(your,1)]
*/
