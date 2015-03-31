package com.cloudwick.hadoop.pig;

import java.io.IOException;

import org.apache.pig.FilterFunc;
import org.apache.pig.data.Tuple;

public class Filter extends FilterFunc {

	@Override
	public Boolean exec(Tuple arg0) throws IOException {

		Object a0 = arg0.get(0);

		if (a0 == (Integer) 1) {
		return true;	
		}
		return false;
	}

}
