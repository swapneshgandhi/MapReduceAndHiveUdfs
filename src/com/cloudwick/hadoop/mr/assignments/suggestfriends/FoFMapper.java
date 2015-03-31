package com.cloudwick.hadoop.mr.assignments.suggestfriends;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class FoFMapper extends Mapper<LongWritable, Text, Text, Text> {

	protected void map(LongWritable offset, Text line, Context context)
			throws IOException, InterruptedException {

		String Friends[] = line.toString().split(" ");

		for (String key : Friends) {
			LinkedList<String> pairs = getVals(key, Friends);
			for (String val : pairs)
				context.write(new Text(key), new Text(val));
		}
	}

	private LinkedList<String> getVals(String key, String[] value) {
		LinkedList<String> pairs = new LinkedList<String>();

		for (String val : value) {

			if (!key.equals(val)) {

				if (key.equals(value[0]) || val.equals(value[0])) {
					pairs.add(val + ",0");
				} else {
					pairs.add(val + ",1");
				}
			}
		}
		return pairs;
	}
}
