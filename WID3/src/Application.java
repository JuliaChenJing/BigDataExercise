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
		
		//----------------------------Map Input-----------------------
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
		
		for(int i=0;i<inputSplits.size();i++)
		{ 
			System.out.println("---------------------------Mapper"+i+" Input-------------");
			System.out.println(inputSplits.get(i));
		}
		
		
		
		
		//----------------------------Map Input-----------------------
		
		Mapper mapper=new Mapper();
		
		List 	<List <Pair> >  mapperOutputs=new ArrayList<List <Pair> >();
		mapperOutputs.add(mapper.mapperOutput(inputSplits.get(0)));
		mapperOutputs.add(mapper.mapperOutput(inputSplits.get(1)));
		mapperOutputs.add(mapper.mapperOutput(inputSplits.get(2)));
		
		for(int i=0;i<inputSplits.size();i++)
		{
			System.out.println("---------------------------Mapper"+i+" Output-------------");
			System.out.println(mapperOutputs.get(i).toString());
		}


		
		
		//----------------------------Reducer Input-----------------------
		
	
		List 	<List <Pair> >  reducerInputs=new ArrayList<List <Pair> >();
		for(int i=0;i<numOfReducers;i++)
		{
			List <Pair> listPairTemp=new ArrayList <Pair>();
			reducerInputs.add(listPairTemp);
			
		}
		Iterator <Pair> it =mapperOutputs.get(0).iterator();
		
	
		 while (  it.hasNext() )
		 {
			 Pair tempPair=it.next();
			 String key=tempPair.getKey();
			 List <Pair> listPairTemp= reducerInputs.get(WordCount.getPartition(key, numOfReducers));
			 listPairTemp.add(tempPair);
					 
		 }
		Reducer reducer=new Reducer();
		
		 List <GroupByPair> collectionOfGroupByPair=reducer.reducerOutput((ArrayList)mapperOutputs.get(0));
		
		       
		System.out.println("---------------------------Reducer Input------------");
		System.out.println(collectionOfGroupByPair.toString());
		
		
		//----------------------------Reducer Input-----------------------
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

