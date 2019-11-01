package wrodcountProduct;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WrodcountMapper {

	public static class Map extends Mapper<Object, Text, Text, IntWritable> {

		// word show 1 time
		private final static IntWritable one = new IntWritable(1);

		// spilt word
		private Text word = new Text();

		// realize map
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		
			//convert text to String
			String valueString = value.toString();
			
			//spilt txt by "\n"(every line)
			StringTokenizer tokenizerArticle = new StringTokenizer(valueString, "\n");
			
			 //traverse every line
			while(tokenizerArticle.hasMoreTokens()){
				
				//spilt by one or more space
				StringTokenizer tokenizer = new StringTokenizer(tokenizerArticle.nextToken());

				//way 1 for my txt (using a.txt)
//				String[] items = valueString.split("\\s+");
				
				// way 2 for common txt (using aa.txt)
				valueString=valueString.replaceAll(",","");//remove comma
				valueString=valueString.replaceAll("'", "");//remove single quotes
				valueString=valueString.replaceAll("\"", "\\\"");//remove double quotation marks
				String[] items = valueString.split("[^a-zA-Z']+");//use letter	
				//traverse every word
				for (int i = 0; i < items.length; i++) {	
			        word.set(items[i]);
			        context.write(word, one);
				}		
			}
		}
	}
}
