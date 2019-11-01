package searchResult;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.io.IOUtils;

import searchProduct.SearchMapper.Map;
import searchProduct.SearchReducer.Reduce;

import java.net.URI;
import java.io.ByteArrayOutputStream;

public class SearchDriver {
	public static void main(String keyword) throws Exception {
		Search(keyword);
	}

	public static String Search(String keyword) throws Exception {
		// TODO Auto-generated method stub
		
		long startTime = System.currentTimeMillis();
		
		Configuration conf = new Configuration();
		//set the keyword
		conf.set("keyword", keyword);
		// conf.set("mapred.job.tracker", "192.168.1.2:9001");

		Job job = Job.getInstance(conf, "SearchDriver");
		// set jar to run
		job.setJarByClass(SearchDriver.class);

		// setMap、Combine or Reduce
		job.setMapperClass(Map.class);
		// job.setCombinerClass(Reduce.class);
		job.setReducerClass(Reduce.class);

		// output type
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		// set input and output
		Path input = new Path("hdfs://192.168.10.208:9000/input/a.txt");
		Path output = new Path("hdfs://192.168.10.208:9000/output");
		FileInputFormat.addInputPath(job, input);
		FileOutputFormat.setOutputPath(job, output);

		// if output directory exist , delete
		FileSystem fs = output.getFileSystem(conf);
		if (fs.exists(output)) {
			fs.delete(output, true);
		}
		job.waitForCompletion(true);
		//System.exit(job.waitForCompletion(true) ? 0 : 1);

		//read file 
		String outputFile = "hdfs://localhost:9000/output/part-r-00000";
		Configuration CfReadFile = new Configuration();
		FileSystem FSReadFile = FileSystem.get(URI.create(outputFile), CfReadFile);
		Path PReadFile = new Path(outputFile);
		FSDataInputStream in = FSReadFile.open(PReadFile);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtils.copyBytes(in, out, 4096, true);
		String result = out.toString();
		
		//show the running time
		long endTime = System.currentTimeMillis();
		long tiem =endTime-startTime;
		System.out.println("consuming time:"+tiem);
		
		return result;

	}

}
