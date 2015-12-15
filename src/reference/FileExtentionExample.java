package reference;

import java.io.File;

public class FileExtentionExample {
	 
    public static void main(String[] args) {
        File file = new File("D:/test.xml");
        System.out.println(getExtension(file.getAbsolutePath()));
        System.out.println(getExtension(file.getName()));
    }
 
    public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int extensionPos = filename.lastIndexOf('.');
        int lastUnixPos = filename.lastIndexOf('/');
        int lastWindowsPos = filename.lastIndexOf('\\');
        int lastSeparator = Math.max(lastUnixPos, lastWindowsPos);
 
        int index = lastSeparator > extensionPos ? -1 : extensionPos;
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }
}