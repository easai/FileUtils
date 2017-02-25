package easai.util;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class DirList extends RecurseDir {
	DirList(String dir) {
		applyCommand(dir);
	}

	/*
	 * Each directory name will be printed followed by a colon.
	 * 
	 * @see easai.util.RecurseDir#executeOnDirectory(java.lang.String)
	 */
	public void executeOnDirectory(String fileName) {
		System.out.println();
		System.out.println(fileName + ":");
	}

	/*
	 * Each file name will be printed (one per line).
	 * 
	 * @see easai.util.RecurseDir#executeOnFile(java.lang.String)
	 */
	public void executeOnFile(String fileName) {
		try {
			File file = new File(fileName);
			System.out.println(file.getPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * DirList is a utility that prints out all files under the specified
	 * directory, including all the sub-directories.
	 * 
	 * Usage: java -jar DirList.jar [directory]
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Options opt = new Options();
			opt.addOption("?", false, "Usage: java -jar DirList.jar [directory]");
			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse(opt, args);
			if (cmd.hasOption("?") || args.length == 0) {
				System.out.println("Usage: java -jar DirList.jar [directory]");
			} else {
				for (String dir : args) {
					new DirList(dir);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
