package wordCount;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by hongleyou on 2017/4/13.
 */
public class WordCount {
    //mapper class
    
     public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);//emit
            }
        }
    }
    
     //reducer and combiner class
    public static class IntSumReducer  extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);//emit
        }
    }

   
   

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        
        job.setMapperClass(TokenizerMapper.class);//set mapper class
        job.setCombinerClass(IntSumReducer.class);//set combiner class
        job.setReducerClass(IntSumReducer.class);//set reducer class
        
        job.setOutputKeyClass(Text.class);//set output key class
        job.setOutputValueClass(IntWritable.class);//set output value class
        
        FileInputFormat.addInputPath(job, new Path(args[0]));//add  input path
        FileOutputFormat.setOutputPath(job, new Path(args[1]));// set output path
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);//if completed, exit
    }
}
