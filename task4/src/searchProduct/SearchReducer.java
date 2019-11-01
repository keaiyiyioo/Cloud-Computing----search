package searchProduct;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class SearchReducer {
	public static class Reduce extends Reducer<Text, Text, Text, Text> {		
		
		// realize reduce
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
	    
		for (Text val : values) {
			context.write(key, val);
			}
         }   
	}
}
