import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
//import java.util.regex;
public class Application {
	
	public static void main(String [] args) throws IOException
	{
		
		Pair pair=new Pair("cat",1);	
		System.out.println(pair.toString());
		String str="";
		
		try {
			 str=readFile();
			//System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("---"+e.getMessage());
		}
		
		//  String str1 = "This is a string of words";
		  String [] stringOfWords = str.split(" ");
		  for(int i=0;i<stringOfWords.length;i++)
		  {
			  System.out.println(stringOfWords[i]);
		  }
		
		
		
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
