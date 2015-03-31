package com.cloudwick.hadoop.mr.assignments.allkindsofsorts;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FoFReducer extends Reducer<Text, Text, Text, Text> {

	protected void reduce(Text personName, Iterable<Text> values,
			Context context) throws IOException, InterruptedException {

		HashSet<String> friendHashSet = new HashSet<String>();
		HashSet<String> suggestionHashSet = new HashSet<String>();
		Iterator<Text> iter = values.iterator();

		while (iter.hasNext()) {
			String[] tokens = iter.next().toString().split(",");
			if (tokens[1].equals("1")) {
				suggestionHashSet.add(tokens[0]);
			} else if (tokens[1].equals("0")) {
				friendHashSet.add(tokens[0]);
			}
		}
		suggestionHashSet.removeAll(friendHashSet);

		for (String suggestion : suggestionHashSet) {
			context.write(personName, new Text(suggestion));
		}

	}
}
