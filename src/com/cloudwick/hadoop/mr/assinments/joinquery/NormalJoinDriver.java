package com.cloudwick.hadoop.mr.assinments.joinquery;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class NormalJoinDriver extends Configured implements Tool {

	public int run(String[] args) throws Exception {

		if (args.length != 3) {
			System.out.printf(
					"Usage: %s [generic options] <input dir> <output dir>\n",
					getClass().getSimpleName());
			ToolRunner.printGenericCommandUsage(System.out);
			return -1;
		}

		Configuration conf = new Configuration();
		conf.set("deptTable", args[1]);
		DistributedCache.addCacheFile(new URI("/user/hadoop/dept"),conf);
		
		Job job = new Job(conf);
		job.setJarByClass(NormalJoinDriver.class);
		job.setJobName(this.getClass().getName());

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileInputFormat.setInputPaths(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		
		MultipleInputs.addInputPath(job, new Path(args[0]),
				TextInputFormat.class, NormalJoinMapper.class);
		MultipleInputs.addInputPath(job, new Path(args[1]),
				TextInputFormat.class, DeptTableMapper.class);

		job.setReducerClass(NormalJoinReducer.class);

		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);

		if (job.waitForCompletion(true)) {
			return 0;
		}
		return 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new NormalJoinDriver(), args);
		System.exit(exitCode);
	}

}
