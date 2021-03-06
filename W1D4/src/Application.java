import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application {

	public static void main(String[] args) throws IOException {

		// ----------------------------Map Input-----------------------
		// read the txt file to string
		List<String> inputSplits = new ArrayList<String>();
		int numOfReducers = 4;

		

		try {
			inputSplits.add(readFile("0.txt"));
			inputSplits.add(readFile("1.txt"));
			inputSplits.add(readFile("2.txt"));
			// System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("---" + e.getMessage());
		}
		System.out.println("Number of Input-Splits:" + inputSplits.size());
		System.out.println("Number of Reducers:" + numOfReducers);
		 
		for (int i = 0; i < inputSplits.size(); i++) {
			System.out.println("---------------------------Mapper" + i + " Input-------------");
			System.out.println(inputSplits.get(i));
		}

		// ----------------------------Map Input-----------------------

		InMapperWordCount mapper = new InMapperWordCount();

		List<List<GroupByPair>> mapperOutputs = new ArrayList<List<GroupByPair>>();
		for(int i=0;i<inputSplits.size();i++)
		mapperOutputs.add(mapper.reducerOutput(mapper.mapperOutput(inputSplits.get(i))));
		
		
		for (int i = 0; i < inputSplits.size(); i++) {
			System.out.println("---------------------------Mapper" + i + " Output-------------");
			
			for(int j=0;j<mapperOutputs.get(i).size();j++)
			System.out.println(mapperOutputs.get(i).get(j).toString_showSize());
		}

		// ----------------------------Reducer Input-----------------------

		// create a certain number of reducerInputs, each reducerInput is a List
		// of Pair, in this case it is 4
		List<List<GroupByPair>> reducerInputs = new ArrayList<List<GroupByPair>>();
		
		for (int i = 0; i < numOfReducers; i++) {
			List<GroupByPair> listPairTemp = new ArrayList<GroupByPair>();
			reducerInputs.add(listPairTemp);

		}

		// for every mapperOutputs
		for (int i = 0; i < mapperOutputs.size(); i++) {

			// create an iterator for each of a mapperOutput
			//initiation 
			Iterator<GroupByPair> it = mapperOutputs.get(i).iterator();

			List<List<GroupByPair>> fromMapperToReduce = new ArrayList<List<GroupByPair>>();
			for (int j = 0; j < numOfReducers; j++) {
				List<GroupByPair> listPairTemp = new ArrayList<GroupByPair>();
				fromMapperToReduce.add(listPairTemp);

			}
			
			
			while (it.hasNext()) {
				GroupByPair tempPair = it.next();
				String key = tempPair.getKey();
				// decide which reducerInput should this Pair go based on key
				int reduceNo = WordCount.getPartition(key, numOfReducers);
				List<GroupByPair> listPairTemp = reducerInputs.get(reduceNo);
				listPairTemp.add(tempPair);
				fromMapperToReduce.get(reduceNo).add(tempPair);
			}
			for (int j = 0; j < numOfReducers; j++) {

				System.out.println(
						"-------------------------Pairs sent from Mapper" + i + " to  Reducer" + j + "------");
				for(int k=0;k<fromMapperToReduce.get(j).size();k++)
				System.out.println(fromMapperToReduce.get(j).get(k).toString_showSize());
			}
		}

		Reducer reducer = new Reducer();
		List<List<GroupByPair>> reducerInputsAfterCombining = new ArrayList<List<GroupByPair>>();
		for (int i = 0; i < numOfReducers; i++) {
			System.out.println("---------------------------Reducer" + i + " Input-------------");
			//System.out.println(reducerInputs.get(i));
			
			
			List<GroupByPair> tempList=reducer.combineReducerInput(reducerInputs.get(i));
			 reducerInputsAfterCombining.add(tempList);
			System.out.println(tempList);
		}

		// ----------------------------Reducer Output-----------------------

		List <List<GroupByPair>> reducerOutputs =new ArrayList<List<GroupByPair>>();
		
		for (int i = 0; i < numOfReducers; i++) {
			List<GroupByPair> listPairTemp = new ArrayList<GroupByPair>();
			reducerOutputs.add(listPairTemp);

		}
		for (int i = 0; i < numOfReducers; i++) {
			System.out.println("---------------------------Reducer" + i + " Output-------------");
			List<GroupByPair> tempList=new ArrayList<GroupByPair>();
			tempList=reducer.reducerOutput(reducerInputsAfterCombining.get(i));
			reducerOutputs.add(tempList);

			
			
			for (Iterator<GroupByPair> it_2 = tempList.iterator(); it_2.hasNext();) {
				GroupByPair temp = it_2.next();
				System.out.println(temp.toString());
			}
			
		}

	}

	// read the txt file and store it to a string
	static String readFile(String filename) {
		String targetFileString = "";
		FileInputStream targetFile;
		try {
			targetFile = new FileInputStream(new File(filename));
			targetFileString = IOUtils.toString(targetFile, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("---" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("---" + e.getMessage());
		}

		return targetFileString;
		
	}

}
