package com.cloudwick.test.hadoop.junit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;
import com.cloudwick.hadoop.mr.assignments.wcount.WCMapper;
import com.cloudwick.hadoop.mr.assignments.wcount.WCReducer;

public class MRUnitTest {
	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
	MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

	@Before
	public void setUp() {
		WCMapper mapper = new WCMapper();
		WCReducer reducer = new WCReducer();
		mapDriver = MapDriver.newMapDriver(mapper);
		reduceDriver = ReduceDriver.newReduceDriver(reducer);
		mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
	}

	@Test
	public void testMapper() {
		mapDriver.withInput(new LongWritable(), new Text("swap swap gandhi"));
		mapDriver.withOutput(new Text("swap"), new IntWritable(1));
		mapDriver.withOutput(new Text("swap"), new IntWritable(1));
		mapDriver.withOutput(new Text("gandhi"), new IntWritable(1));
		try {
			mapDriver.runTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testReducer() {
		List<IntWritable> values = new ArrayList<IntWritable>();
		values.add(new IntWritable(1));
		values.add(new IntWritable(1));
		reduceDriver.withInput(new Text("swap"), values);
		values.clear();
		values.add(new IntWritable(1));
		reduceDriver.withInput(new Text("gandhi"), values);
		reduceDriver.withOutput(new Text("swap"), new IntWritable(2));
		reduceDriver.withOutput(new Text("gandhi"), new IntWritable(1));
		try {
			reduceDriver.runTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testMapReduce() {
		mapReduceDriver.withInput(new LongWritable(), new Text("swap swap gandhi"));
		mapReduceDriver.withOutput(new Text("gandhi"), new IntWritable(1));
		mapReduceDriver.withOutput(new Text("swap"), new IntWritable(2));
		try {
			mapReduceDriver.runTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}