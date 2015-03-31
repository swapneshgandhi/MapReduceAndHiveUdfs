package com.cloudwick.hadoop.pig;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

public class Encrypt extends EvalFunc<String> {

	public String exec(Tuple arg0) throws IOException {

		String first = (String) arg0.get(0);
		if (first != null)
			return new StringBuilder(first).reverse().toString();
		return "";
	}

}
