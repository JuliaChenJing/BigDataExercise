package pairmapperstripereducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class PairmapperStripereducer {
	
	 public static void main(String[] args) throws Exception {
	        Job job = new Job(new Configuration());
	        job.setJarByClass(PairmapperStripereducer.class);

	        job.setNumReduceTasks(1);

	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(Text.class);

	        job.setMapperClass(Map.class);
	        job.setCombinerClass(Combine.class);
	        job.setReducerClass(Reduce.class);

	        job.setInputFormatClass(TextInputFormat.class);
	        job.setOutputFormatClass(TextOutputFormat.class);

	        FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));

	        job.waitForCompletion(true);
	    }

	    //mapper
	    public static class Map extends Mapper<LongWritable, Text, Text, Text> {
	        
	        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	            String[] words = value.toString().split(" ");

	            for (String word : words) {
	                if (word.matches("^\\w+$")) {
	                    int count = 0;
	                    for (String term : words) {
	                        if (term.matches("^\\w+$") && !term.equals(word)) {
	                            context.write(new Text(word + "," + term), new Text("1"));
	                            count++;
	                        }
	                    }
	                    context.write(new Text(word + ",*"), new Text(String.valueOf(count)));
	                }
	            }
	        }
	    }
	    

	    //combiner
	    private static class Combine extends Reducer<Text, Text, Text, Text> {
	        
	        public void reduce(Text key, Iterable<Text> values, Context context)
	                throws IOException, InterruptedException {
	            int count = 0;
	            for (Text value : values) {
	                count += Integer.parseInt(value.toString());
	            }
	            context.write(key, new Text(String.valueOf(count)));
	        }
	    }
	    

	
	       
	    //reducer
	    public static class Reduce extends Reducer<Text, Text, Text, Text> {
	    	
	    

	        public void reduce(Text key, Iterable<Text> values, Context context)
	                throws IOException, InterruptedException {
	        	//initiate the HashMap stripe
	            java.util.Map<String, Integer> stripe = new HashMap<>();
	            
	            double totalCount = 0;

	            for (Text value : values) {
	                String[] stripes = value.toString().split(",");

	                for (String termCountStr : stripes) {
	                    String[] termCount = termCountStr.split(":");//stripeStr.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
	                    String term = termCount[0];
	                    int count = Integer.parseInt(termCount[1]);

	                    Integer countSum = stripe.get(term);
	                    stripe.put(term, (countSum == null ? 0 : countSum) + count);

	                    totalCount += count;
	                }
	            }

	            
	            StringBuilder stripeStr = new StringBuilder();
	            for (java.util.Map.Entry entry : stripe.entrySet()) {
	            	
	            	double d=new Double(entry.getValue().toString())/totalCount;
	            	
	                stripeStr.append(entry.getKey()).append(":").append(String.format("%.2f", d)).append("   ");
	            }
	            
	            context.write(key, new Text(stripeStr.toString()));

	        }

	       

	        class Pair implements Comparable<Pair> {
	            double relativeFrequency;
	            String key;
	            String value;

	            Pair(double relativeFrequency, String key, String value) {
	                this.relativeFrequency = relativeFrequency;
	                this.key = key;
	                this.value = value;
	            }

	            @Override
	            public int compareTo(Pair pair) {
	                if (this.relativeFrequency >= pair.relativeFrequency) {
	                    return 1;
	                } else {
	                    return -1;
	                }
	            }
	        }
	    }

}
