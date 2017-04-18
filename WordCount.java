package wordCount;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WordCount {

	/*
	 * A non-static nested class has full access to the members of the class
	 * within which it is nested. A static nested class does not have a
	 * reference to a nesting instance, so a static nested class cannot invoke
	 * non-static methods or access non-static fields of an instance of the
	 * class within which it is nested.
	 */

	// class Map is a static nested class
	public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
		
		//set one as 1 as the value of the emit key-value pair
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		// method map  key is not used in this method
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();
			StringTokenizer tokenizer = new StringTokenizer(line); //put the line into a tokenizer
			while (tokenizer.hasMoreTokens()) {
				word.set(tokenizer.nextToken());//assign next token to word
				context.write(word, one);//emit (word, 1)
			}
		}
	}

	// class Reduce is a static nested class
	public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {
		// method reduce
		//for loop of key is automatically controlled by the framework
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException 
		{
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			context.write(key, new IntWritable(sum));
		}
	}

	// main method
	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		Job job = new Job(conf, "wordcount");
		job.setJarByClass(WordCount.class);//added by Julia 20170418

		job.setOutputKeyClass(Text.class);// set output key class
		job.setOutputValueClass(IntWritable.class);// set output value class

		job.setMapperClass(Map.class);// set mapper class
		job.setReducerClass(Reduce.class);// set reducer class

		job.setInputFormatClass(TextInputFormat.class); // st input format
		job.setOutputFormatClass(TextOutputFormat.class);// set output format

		FileInputFormat.addInputPath(job, new Path(args[0]));// add input path
		FileOutputFormat.setOutputPath(job, new Path(args[1]));// add output
																// path

		job.waitForCompletion(true);
	}

}
