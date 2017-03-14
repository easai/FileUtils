package easai.util;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class FindFile extends RecurseDir {

	final static String OPTION_HELP = "help";
	final static String OPTION_DIR = "dir";
	final static String OPTION_FILE = "file";

	String pattern = "";

	public FindFile(String pattern, String dir) {
		this.pattern = pattern;
		applyCommand(dir);
	}

	@Override
	public void executeOnFile(String fileName) {
		try {
			if (fileName.endsWith(File.separator + pattern)) {
				System.out.println(fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void executeOnDirectory(String fileName) {
		try {
			if (fileName.endsWith(File.separator + pattern)) {
				System.out.println(fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * FindFile calculates the sum of file sizes of the specified directory,
	 * including all the sub-directories.
	 * 
	 * Usage: java -jar FindFile.jar -d <directory> -f <filename>
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Options opt = new Options();
		try {		
			opt.addOption("?", OPTION_HELP, false, "print this message");

			Option option = Option.builder("f").required(true).longOpt(OPTION_FILE).hasArgs().desc("file name").build();
			opt.addOption(option);

			Option dirOption = Option.builder("d").required(true).longOpt(OPTION_DIR).hasArgs().desc("target directory")
					.build();
			opt.addOption(dirOption);

			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse(opt, args);
			if (cmd.hasOption(OPTION_HELP)) {
				throw new Exception();
			} else if (cmd.hasOption(OPTION_DIR) && cmd.hasOption(OPTION_FILE)) {
				String dir = cmd.getOptionValue(OPTION_DIR);
				String fileName = cmd.getOptionValue(OPTION_FILE);
				new FindFile(fileName, dir);
			}else{
				throw new Exception();
			}
		} catch (Exception e) {
			HelpFormatter help = new HelpFormatter();
			help.printHelp("FindFile", opt, true);
		}
	}
}
