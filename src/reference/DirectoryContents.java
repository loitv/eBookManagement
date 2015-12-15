package reference;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class DirectoryContents {

	public static void main(String[] args) throws IOException {

		File f = new File("D:\\"); // current directory

		FilenameFilter textFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".pdf")) {
					return true;
				} else {
					return false;
				}
			}
		};

		File[] files = f.listFiles(textFilter);
		for (File file : files) {
//			if (file.isDirectory()) {
//				System.out.print("directory:");
//			} else {
//				System.out.print("     file:");
//			}
			System.out.println(file.getCanonicalPath());
		}

	}

}