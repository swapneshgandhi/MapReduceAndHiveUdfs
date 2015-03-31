package com.cloudwick.hadoop.mr.assignments.filter;

import java.io.IOException;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class FilterMapper extends Mapper<LongWritable, Text, Text, Text> {

	protected void map(LongWritable offset, Text line, Context context)
			throws IOException, InterruptedException {

		String tokens[] = line.toString().split(":");
		
		if (tokens[1].equals("21.122.12.1"))
		context.write(new Text(tokens[0]), new Text(tokens[1]));

	}
}
