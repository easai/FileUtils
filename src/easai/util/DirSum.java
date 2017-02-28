package easai.util;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class DirSum extends RecurseDir {

	final static String OPTION_HELP = "help";
	final static String OPTION_DIR = "dir";

	long sum = 0;

	DirSum(String dir) {
		applyCommand(dir);
	}

	@Override
	public void executeOnFile(String fileName) {
		File file = new File(fileName);
		sum += file.length();
	}

	@Override
	public void executeOnDirectory(String fileName) {
	}

	/**
	 * DirSum calculates the sum of file sizes of the files in the specified
	 * directory, including all the sub-directories.
	 * 
	 * Usage: java -jar DirList.jar [directory]
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Options opt = new Options();
		try {
			opt.addOption("?", OPTION_HELP, false, "print this message");
			Option option = Option.builder("d").required(true).longOpt(OPTION_DIR).hasArgs()
					.desc("calculate recursively the sum of file sizes of files in the specified directory").build();
			opt.addOption(option);

			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse(opt, args);
			if (cmd.hasOption(OPTION_HELP)) {
				throw new Exception();
			} else {
				long sum = 0;
				String dir[] = cmd.getOptionValues(OPTION_DIR);
				for (String d : dir) {
					DirSum dirSum = new DirSum(d);
					sum += dirSum.sum;
				}
				System.out.println(sum);
			}
		} catch (Exception e) {
			HelpFormatter help = new HelpFormatter();
			help.printHelp("UpdateKeyword", opt, true);
		}
	}
}
