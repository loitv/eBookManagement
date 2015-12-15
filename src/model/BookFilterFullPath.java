package model;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BookFilterFullPath {

	private ArrayList<String> bookList = new ArrayList<String>();
	private ArrayList<String> pdfList = new ArrayList<String>();
	FilenameFilter boookFilter = new FilenameFilter() {
		public boolean accept(File dir, String name) {
			String lowercaseName = name.toLowerCase();
			if (lowercaseName.endsWith(".pdf")) {
				return true;
			} else {
				return false;
			}
		}
	};

	public BookFilterFullPath(String dir) {
		File file = new File(dir);
		if (file.isDirectory()) {
			bookFilter(file);
			for (int i = 0; i < bookList.size(); i++) {
				System.out.println(bookList.get(i));
				System.out.println(pdfList.get(i));
				System.out.println();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid Directory!");
		}
	}

	public void bookFilter(File file) {
		if (file.isDirectory()) {
			try {
				File[] files = file.listFiles(boookFilter);
				for (File f : files) {
					bookList.add(f.getCanonicalPath());
				}
				String[] pdfFiles = file.list(boookFilter);
				for (String pdf : pdfFiles) {
					pdfList.add(pdf);
				}
				
				File[] files1 = file.listFiles();
				for (File f : files1) {
					if (f.isDirectory()) {
						bookFilter(f);
					}
				}
			} catch (Exception ex) {
//				System.out.println("Error");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid Directory!");
		}
	}
	
	public ArrayList<String> getPdfList() {
		return pdfList;
	}

	public ArrayList<String> getBookList() {
		return bookList;
	}

	public static void main(String[] args) {
		new BookFilterFullPath("F:\\Books\\Computers & Technology\\Web Development & Design\\Web Design");
	}
}
