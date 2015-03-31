package com.cloudwick.hadoop.mr.assignments.wcount;

import java.io.IOException;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class WCMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	protected void map(LongWritable offset, Text line, Context context)
			throws IOException, InterruptedException {

		String words [] = line.toString().split(" ");
		
		for (String word : words){
		
			context.write(new Text(word), new IntWritable(1));
			
		}
	}
}
