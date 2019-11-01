package task2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class wordcount {
	private static String input = "/Users/yiyi/Downloads/a.txt";
	private static String output = "/Users/yiyi/Downloads/d.txt";
	private static BufferedReader br = null;
	private static BufferedWriter bw = null;
	private static String currentLine = null;// current line
	private static String[] words = null;// store words
	private static List<String> wordList = new ArrayList<String>();

	public static void main(String[] args) {
	
		File file = new File(input);
		if (!file.exists()) {
			System.out.println("file " + file + " is not existed, exit");
			return;
		}

		try {
			// start time
			long starttime = System.currentTimeMillis();
			br = new BufferedReader(new FileReader(file.getPath()));	
			currentLine = br.readLine();
			while (currentLine != null) {
				//first try
				//words = currentLine.split(" |,|\\.");
				//second try
				//words = currentLine.split("[^a-zA-Z']+");
				//Third try
				currentLine=currentLine.replaceAll(",","");//remove comma
				currentLine=currentLine.replaceAll("'", "");//remove single quotes
				currentLine=currentLine.replaceAll("\"", "\\\"");//remove double quotation marks
				words = currentLine.split("[^a-zA-Z']+");//use letter	
				for (String s : words) {
					if (!s.equals(""))
						wordList.add(s);
				}

				currentLine = br.readLine();
			}

			for (String temp : wordList) {
				System.out.println(temp);
			}

			// HashSet
			Set<String> hashSet = new HashSet<String>(wordList);
			for (String str : hashSet) {
				System.out.println("word: " + str + ", occur times: " + Collections.frequency(wordList, str));
			}

			// HashMap
			Map<String, Integer> hashMap = new HashMap<String, Integer>();
			for (String temp : wordList) {
				Integer count = hashMap.get(temp);
				hashMap.put(temp, (count == null) ? 1 : count + 1);
			}

			// TreeMap
			TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(hashMap);

			// Record result to another file
			printMap(treeMap);
			
			long endtime = System.currentTimeMillis();// end time

			System.out.println("Time:" + (endtime-starttime));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeInputStream(br);//close read
			closeOutputStream(bw);//close write
		}
	}

	public static void printMap(Map<String, Integer> map) throws IOException {

		bw = new BufferedWriter(new FileWriter(output));

		Set<String> keys = map.keySet();
		for (String s : keys) {
			System.out.println("word: " + s + ", times: " + map.get(s));
			writeResult("word: " + s + ", times: " + map.get(s));
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("word: " + entry.getKey() + ", number : " + entry.getValue());
			writeResult("word: " + entry.getKey() + ", number : " + entry.getValue());
		}

	}

	public static void writeResult(String line) throws IOException {

		try {
			if (bw != null) {
				bw.write(line);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
			closeOutputStream(bw);
		}
	}

	public static void closeOutputStream(BufferedWriter writer) {
		try {
			if (writer != null) {
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void closeInputStream(BufferedReader reader) {
		try {
			if (reader != null) {
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
