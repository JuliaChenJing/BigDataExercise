package pairs;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.util.TreeSet;


public class Pairs {
    
    public static void main(String[] args) throws Exception {
        Job job = new Job(new Configuration());
        job.setJarByClass(Pairs.class);

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
        
        TreeSet<Pair> priorityQueue = new TreeSet<>();// could not store the same element twice
        double totalCount = 0;

        public void reduce(Text key, Iterable<Text> values, Context context)
                throws IOException, InterruptedException {
        	//   A10,*      {1,4} 
        	//   A10,A12    (1,2 }
        	//   A10,A14     {1,1}
            String keyStr = key.toString();
            int count = 0;

            for (Text value : values) {
                count += Integer.parseInt(value.toString());//1+4
            }

            if (keyStr.matches(".*\\*")) {//if the next one is a " A12,*" ,that means the one with A10 as a starter finishes
                totalCount = count;
            } 
            else {
                String[] pair = keyStr.split(",");
                priorityQueue.add(new Pair(count / totalCount, pair[0], pair[1]));

                if (priorityQueue.size() > 100) {
                    priorityQueue.pollFirst();
                }
            }
            while (!priorityQueue.isEmpty()) {
                Pair pair = priorityQueue.pollLast();
                context.write(new Text(pair.key+"--> "+pair.value), new Text(String.format("%.2f", pair.relativeFrequency)));
            }
        }

       

        // inner class Pair
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
