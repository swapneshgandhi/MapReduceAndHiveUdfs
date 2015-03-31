package com.cloudwick.hadoop.mr.assinments.joinquery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class NormalJoinReducer extends
		Reducer<IntWritable, Text, IntWritable, Text> {

	protected void reduce(IntWritable Id, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		Iterator<Text> it = values.iterator();
		String deptName = null;
		ArrayList<String[]> tempList = new ArrayList<String[]>();

		while (it.hasNext()) {

			String words[] = it.next().toString().split(",");

			if (words.length == 1) {
				deptName = words[0];

				for (String[] columns : tempList) {
					context.write(
							new IntWritable(Integer.parseInt(columns[0])),
							new Text(columns[1] + "," + columns[2] + ","
									+ deptName));
				}

			} else if (deptName == null) {
				tempList.add(words);
			} else {
				context.write(new IntWritable(Integer.parseInt(words[0])),
						new Text(words[1] + "," + words[2] + "," + deptName));
			}
			//System.err.println("Red got " + it.next().toString());
		}

	}
}
