package com.cloudwick.hadoop.mr.assinments.joinquery;

import java.io.IOException;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class NormalJoinMapper extends
		Mapper<LongWritable, Text, IntWritable, Text> {

	protected void map(LongWritable offset, Text line, Context context)
			throws IOException, InterruptedException {

		String words[] = line.toString().split(",");

		if (words.length == 4) {
			context.write(new IntWritable(Integer.parseInt(words[3])),
					new Text(words[0] + "," + words[1] + "," + words[2]));
		}
		
		System.err.println("Map got "+line);
		
	}
}
