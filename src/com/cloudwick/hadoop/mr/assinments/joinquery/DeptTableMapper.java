package com.cloudwick.hadoop.mr.assinments.joinquery;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class DeptTableMapper extends
		Mapper<LongWritable, Text, IntWritable, Text> {

	static final HashMap<Integer, String> deptTable = new HashMap<>();

	protected void setup(Context context) {
		Configuration conf = context.getConfiguration();
		String name = conf.get("deptTable");
		String data = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			Path[] cacheFiles = DistributedCache.getLocalCacheFiles(conf);	
			fr = new FileReader(cacheFiles[0].toString());
			br = new BufferedReader(fr);

			while ((data = br.readLine()) != null) {
				String[] line = data.split(",");
				deptTable.put(Integer.parseInt(line[0]), line[1]);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}

	protected void map(LongWritable offset, Text line, Context context)
			throws IOException, InterruptedException {

		String words[] = line.toString().split(",");
		
		if (words.length == 2) {
			context.write(new IntWritable(Integer.parseInt(words[0])),
					new Text(deptTable.get(Integer.parseInt(words[0]))));
		}
		System.err.println("DeptMap got "+line);
	}
}
