package reference;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class FileSystemViewDemo {

	public static void main(String[] args) {
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		File[] roots = fileSystemView.getRoots();
		for (File fileSystemRoot : roots) {
			File[] files = fileSystemView.getFiles(fileSystemRoot, true);
			for (File file : files) {
				if (file.isDirectory()) {
//					node.add(new DefaultMutableTreeNode(file));
					System.out.println(file);
					File[] filePCs = file.listFiles();
					for (File filePC : filePCs) {
						System.out.println(filePC);
					}
					break;
					
				}
			}
			//
//			System.out.println(fileSystemRoot);
		}
	}
}
