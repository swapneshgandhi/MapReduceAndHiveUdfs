package com.cloudwick.hadoop.mr.assignments.uniqueip;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UIPReducer extends Reducer<Text, Text, Text, IntWritable> {

	protected void reduce(Text url, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		HashSet<Text> ipHashSet = new HashSet<Text>();
		Iterator<Text> iter = values.iterator();
		while (iter.hasNext()) {
			ipHashSet.add(iter.next());
		}

		context.write(url, new IntWritable(ipHashSet.size()));

	}

}
