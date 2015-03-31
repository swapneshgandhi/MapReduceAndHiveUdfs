package com.clouldwick.hadoop.commons;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;


public class CommandParser {
	final static Logger logger = Logger.getLogger(CommandParser.class);

	public static void main (String args[]){

		Options optionObj=new Options();

		optionObj.addOption("f", "firstname", true, "enter first name");
		optionObj.addOption("l", "lastname", true, "enter last name");

		CommandLineParser parser = new BasicParser();

		CommandLine cmd = null;
		try {
			cmd = parser.parse(optionObj, args);

			if (cmd.hasOption("f"))
				System.out.println(cmd.getOptionValue("f"));

			if (cmd.hasOption("l"))
				System.out.println(cmd.getOptionValue("l"));

		}
		catch (ParseException e) {
			logger.error("Parsing Exception");
		}
	}
}
