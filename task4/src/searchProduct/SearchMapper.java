package searchProduct;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;

public class SearchMapper {

	public static class Map extends Mapper<Object, Text, Text, Text> {

		// spilt word
		private Text word = new Text();
		
		//initialize line
		private int line = 0;
		 
		// realize map
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		
			Configuration conf = context.getConfiguration();
			
			String keyword = conf.get("keyword");
			
			//convert text to String
			String valueString = value.toString();
			
			//spilt txt by "\n"(every line)
			StringTokenizer tokenizerArticle = new StringTokenizer(valueString, "\n");
			
			//traverse every line
			while(tokenizerArticle.hasMoreTokens()){
				
				//updata every line
				line++;
				
				//spilt by one or more space
				StringTokenizer tokenizer = new StringTokenizer(tokenizerArticle.nextToken());
	
				//way 1 for my txt (using aa.txt)
//				String[] items = valueString.split("\\s+");
				
				// way 2 for common txt (using a.txt)
				valueString=valueString.replaceAll(",","");//remove comma
				valueString=valueString.replaceAll("'", "");//remove single quotes
				valueString=valueString.replaceAll("\"", "\\\"");//remove double quotation marks
				String[] items = valueString.split("[^a-zA-Z']+");//use letter	
					
				//initialize each line word
				int eachline = 0;
				
				//traverse every word
				for (String item:items) {
					//updata every word in current line
					 eachline++;
					 //find the keyword
					 if (item.equals(keyword)) {
						 //set each to value
						 word.set(" count: "+ eachline+"<br/>"); 
						 //set line to key
		                 context.write(new Text("Line: "+ line), word);
					 }
				}
			}
		}
	}
}
