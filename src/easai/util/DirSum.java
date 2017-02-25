package easai.util;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class DirSum extends RecurseDir {
	long sum=0;
	
	DirSum(String dir){
		applyCommand(dir);
	}
	
	@Override
	public void executeOnFile(String fileName) {
		File file=new File(fileName);
		sum+=file.length();
	}

	@Override
	public void executeOnDirectory(String fileName) {		
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
			opt.addOption("?", false, "Usage: java -jar DirSum.jar [directory]");
			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse(opt, args);
			if (cmd.hasOption("?") || args.length == 0) {
				System.out.println("Usage: java -jar DirSum.jar [directory]");
			} else {
				long sum=0;
				for (String dir : args) {
					DirSum dirSum=new DirSum(dir);
					sum+=dirSum.sum;
				}
				System.out.println(sum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
