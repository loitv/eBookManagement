package reference;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

// Search all book file
public class FilterPDF {

	private ArrayList<String> pdfList = new ArrayList<String>();

	public FilterPDF(String dir) {
		File file = new File(dir);
//		System.out.println(file.isDirectory());
		if (file.isDirectory()) {
			pdfFilter(file);
//			System.out.println(pdfList.size());
			for (int i = 0; i < pdfList.size(); i++) {
				System.out.println(pdfList.get(i));
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid Directory!");
		}
	}

	public ArrayList<String> getPdfList() {
		return pdfList;
	}

	///////Chu y: Thuat toan nay con thieu sot
	public void pdfFilter(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			try {
				for (File f : files) {
					if (f.isDirectory()) {
						String[] books = f.list(new FilenameFilter() {
							@Override
							public boolean accept(File f, String name) {
								return name.endsWith(".pdf");
							}
						});
						for (String pdfFile : books) {
							pdfList.add(pdfFile);
						}
						pdfFilter(f);
					}
				}
			} catch (Exception ex) {
				// System.out.println("error");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Invalid Input Directory");
		}
	}

	public static void main(String[] args) {
		new FilterPDF("F:\\Books\\Computers & Technology\\Web Development & Design\\Web Design");
	}
}
