package com.cloudwick.hadoop.mr.assignments.uniqueip;

import java.io.IOException;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class UIPMapper extends Mapper<LongWritable, Text, Text, Text> {

	protected void map(LongWritable offset, Text line, Context context)
			throws IOException, InterruptedException {

		String tokens[] = line.toString().split(":");

		context.write(new Text(tokens[0]), new Text(tokens[1]));

	}
}
