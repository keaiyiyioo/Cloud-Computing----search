package wrodcountProduct;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class WrodcountReducer {
	public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {		
		
		// realize reduce
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		
			int count = 0;
			
			// count every word
			for (IntWritable val : values) {
				count += val.get();
			}
			
			context.write(key, new IntWritable(count));
        }   
	}
}
