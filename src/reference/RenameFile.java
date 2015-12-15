package reference;

import java.io.File;

public class RenameFile {
	public static void main(String[] args) {
		File oldName = new File("D:\\test.txt");
		File newName = new File("D:\\test1.txt");
		oldName.renameTo(newName);
	}
}
