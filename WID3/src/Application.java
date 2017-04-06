import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application {
	
	public static void main(String [] args) throws IOException
	{
		
		
		// read the txt file to string
		List <String> inputSplits=new ArrayList<String>();
		int numOfReducers=4;
		
		System.out.println("Number of Input-Splits:"+inputSplits.size() );
		System.out.println("Number of Reducers:"+numOfReducers );
		
		try {
			 inputSplits.add(readFile("0.txt"));
			 inputSplits.add(readFile("1.txt"));
			 inputSplits.add(readFile("2.txt"));
			//System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("---"+e.getMessage());
		}
		
		
		System.out.println("---------------------------Mapper 0 Input-------------");
		System.out.println(inputSplits.get(0));
		
		System.out.println("---------------------------Mapper 1 Input-------------");
		System.out.println(inputSplits.get(1));
		
		System.out.println("---------------------------Mapper 2 Input-------------");
		System.out.println(inputSplits.get(2));
		
		Mapper mapper=new Mapper();
		
		List <Pair> mapperOutput_0=mapper.mapperOutput(inputSplits.get(0));
		List <Pair> mapperOutput_1=mapper.mapperOutput(inputSplits.get(1));
		List <Pair> mapperOutput_2=mapper.mapperOutput(inputSplits.get(2));

		System.out.println("---------------------------Mapper 0 Output-------------");
		System.out.println(mapperOutput_0.toString());
		
		System.out.println("---------------------------Mapper 1 Output-------------");
		System.out.println(mapperOutput_1.toString());
		
		System.out.println("---------------------------Mapper 2 Output-------------");
		System.out.println(mapperOutput_2.toString());

		
		
		// -Reducer Input--
		Reducer reducer=new Reducer();
		
		 List <GroupByPair> collectionOfGroupByPair=reducer.reducerOutput((ArrayList)mapperOutput_0);
		
		       
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
   static String readFile(String filename) 
	{
		String targetFileString="";
		FileInputStream targetFile;
		try {
			targetFile = new FileInputStream(new File( filename));
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

