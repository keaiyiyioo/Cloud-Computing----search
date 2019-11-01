package wrodcountResult;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import wrodcountProduct.WrodcountMapper.Map;
import wrodcountProduct.WrodcountReducer.Reduce;

public class WrodcountDriver {
	public static void main(String[] args) throws Exception {

		long startTime = System.currentTimeMillis();

		Configuration conf = new Configuration();

		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		// confirm the path of input address and output address

		if (otherArgs.length != 2) {
			System.err.println("Usage: wordcount <in> <out>"); 
			// if it has wrong with arguments system will exit
			System.exit(2);
		}

		// conf.set("mapred.job.tracker", "192.168.1.2:9001");
		Job job = Job.getInstance(conf, "WrodcountDriver");
		// set jar to run
		job.setJarByClass(WrodcountDriver.class);

		// setMap、Combine or Reduce
		job.setMapperClass(Map.class);
		// job.setCombinerClass(Reduce.class);
		job.setReducerClass(Reduce.class);

		// output type
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// set input and output
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		long endTime = System.currentTimeMillis();
		
		long tiem =endTime-startTime;

		System.out.println("consuming time:"+tiem);
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
