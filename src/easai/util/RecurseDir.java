package easai.util;

import java.io.File;

/**
 * @author IB USER
 *
 */
public abstract class RecurseDir {
	/**
	 * Executed on each file.
	 * 
	 * @param fileName
	 */
	public abstract void executeOnFile(String fileName);

	/**
	 * Executed on each directory.
	 * 
	 * @param fileName
	 */
	public abstract void executeOnDirectory(String fileName);

	/**
	 * Main recursive loop that goes down each sub directory.
	 * 
	 * @param dirName
	 */
	public void applyCommand(String dirName) {
		try {
			File dir = new File(dirName);
			String updir = dir.getParent();
			String path = dir.getAbsolutePath();
			String list[] = dir.list();
			if (list != null) {
				for (String dirname : list) {
					String pathname = path + File.separator + dirname;
					File file = new File(pathname);
					if (file.isDirectory()) {
						executeOnDirectory(pathname);
						applyCommand(pathname);
					} else {
						executeOnFile(pathname);
					}
				}
			} else {
				executeOnFile(dirName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
