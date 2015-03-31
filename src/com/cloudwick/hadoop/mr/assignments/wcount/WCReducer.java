package com.cloudwick.hadoop.mr.assignments.wcount;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class WCReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

	protected void reduce(Text word, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		int sum = 0;
		Iterator<IntWritable> iter = values.iterator();
        while (iter.hasNext()) {
            sum += iter.next().get();
        }
		
		context.write(word, new IntWritable(sum));

	}

}
