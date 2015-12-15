package controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.xml.xmp.XmpWriter;

import model.BookFilterFullPath;
import view.EditInfoView;
import view.MainView;

public class MainViewController {

	private MainView mainView;
	private EditInfoView editInfo;
	private BookFilterFullPath bookFilter;
	private ArrayList<String> bookList = new ArrayList<String>();
	private ArrayList<String> pdfList = new ArrayList<String>();
	@SuppressWarnings("rawtypes")
	private Vector data;
	JFileChooser chooser;
	String choosertitle;
	private int row;
	private String selectPath;
	private String selectPDF;
	private String selectIsbn;
	private String selectTitle;
	private String selectAuthor;
	private String selectPublisher;
	private String selectCategory;
	private String selectKeywords;
	// private Desktop desktop;

	public MainViewController() {
		mainView = new MainView();

		// handle event for button fileBorrow
		mainView.setButtonBorrowActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("D:\\Downloads\\Documents"));
				chooser.setDialogTitle(choosertitle);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				//
				// disable the "All files" option.
				//
				chooser.setAcceptAllFileFilterUsed(false);
				//
				if (chooser.showOpenDialog(mainView) == JFileChooser.APPROVE_OPTION) {
					mainView.getPathName().setText(chooser.getSelectedFile().toString());
				} else {
					System.out.println("No Selection ");
				}

			}
		});

		// handle events for button Scan
		mainView.setButtonScanActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dir = mainView.getPathName().getText();
				bookFilter = new BookFilterFullPath(dir);
				bookList = bookFilter.getBookList();
				pdfList = bookFilter.getPdfList();
				updateBook();
			}
		});

		// handle events for button Open
		mainView.setButtonOpenActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().open(new File(selectPath));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "NO FILE IS SELECTED!");
				}
				// catch (IOException IOEx) {
				// IOEx.printStackTrace();
				// }
			}
		});

		// handle events for button OpenLocation
		mainView.setButtonOpenLocActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File(selectPath.replace(selectPDF, "")));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "NO FILE IS SELECTED!");
				}
				// catch (IllegalArgumentException e1) {
				// JOptionPane.showMessageDialog(null, "File Not Found!");
				// } catch (IOException IOEx) {
				// IOEx.printStackTrace();
				// }
			}
		});

		// handle events for button Delete
		mainView.setButtonDeleteActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectPath == null) {
					JOptionPane.showMessageDialog(null, "NO FILE IS SELECTED!");
				} else {
					int k = JOptionPane.showConfirmDialog(null, "Are you sure to delete this file?", "Warning",
							JOptionPane.YES_NO_OPTION);
					if (k == 0) {
						try {
							File selectedFile = new File(selectPath);
							selectedFile.delete();
							// delete selected row
							DefaultTableModel model = (DefaultTableModel) mainView.getBookInfo().getModel();
							model.removeRow(row);
						} catch (IllegalArgumentException e1) {
							JOptionPane.showMessageDialog(null, "File Not Found!");
						}
					}
				}
			}
		});

		// handle event for button Edit
		mainView.setButtonEditActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectPath == null) {
					JOptionPane.showMessageDialog(null, "NO FILE IS SELECTED!");
				} else {
					editInfo = new EditInfoView();
					editInfo.getTfISBN().setText(selectIsbn);
					editInfo.getTfTitle().setText(selectTitle);
					editInfo.getTfAuthor().setText(selectAuthor);
					editInfo.getTfPublisher().setText(selectPublisher);
					editInfo.getTfCategory().setText(selectCategory);
					editInfo.getTfKeywords().setText(selectKeywords);

					editInfo.setButtonUpdateActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								String isbn = editInfo.getTfISBN().getText();
								String title = editInfo.getTfTitle().getText();
								String author = editInfo.getTfAuthor().getText();
								String pub = editInfo.getTfPublisher().getText();
								String cate = editInfo.getTfCategory().getText();
								String key = editInfo.getTfKeywords().getText();

								// update table
								mainView.getBookInfo().setValueAt(isbn, row, 2);
								mainView.getBookInfo().setValueAt(title, row, 3);
								mainView.getBookInfo().setValueAt(author, row, 4);
								mainView.getBookInfo().setValueAt(pub, row, 5);
								mainView.getBookInfo().setValueAt(cate, row, 6);
								mainView.getBookInfo().setValueAt(key, row, 7);

								if (isbn.equals(selectIsbn) & title.equals(selectTitle) & author.equals(selectAuthor)
										& pub.equals(selectPublisher) & cate.equals(selectCategory)
										& key.equals(selectKeywords)) {
									editInfo.dispose();
								} else {
									// set pdf metadata
									int a = 0;
									try {
										PdfReader reader1 = new PdfReader(selectPath);
										String newFile = selectPath.replace(".pdf", "_updated.pdf");
										// System.out.println("Tampered? " +
										// reader1.isTampered());
										PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream(newFile));
										HashMap<String, String> info = reader1.getInfo();
										info.put("Category", cate);
										info.put("Author", author);
										info.put("Keywords", key);
										info.put("Title", title);
										info.put("Publisher", pub);
										info.put("ISBN", isbn);
										stamper.setMoreInfo(info);
										ByteArrayOutputStream baos = new ByteArrayOutputStream();
										XmpWriter xmp = new XmpWriter(baos, info);
										xmp.close();
										stamper.setXmpMetadata(baos.toByteArray());
										stamper.close();
										reader1.close(); // close process around
															// oldFile
										a = 1;
									} catch (Exception ex) {
										ex.printStackTrace();
									}
									if (a == 1) {

										File oldFile = new File(selectPath);
										oldFile.delete();
										File updateFile = new File(selectPath.replace(".pdf", "_updated.pdf"));
										updateFile.renameTo(new File(selectPath));
									}
									editInfo.dispose();
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					});
				}
			}
		});

		// handle event for select row on table
		mainView.setBookDetailsML(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				row = mainView.getBookInfo().getSelectedRow();
				if (row != -1) {
					try {
						selectPath = (String) mainView.getBookInfo().getValueAt(row, 0);
						selectPDF = (String) mainView.getBookInfo().getValueAt(row, 1);
						selectIsbn = (String) mainView.getBookInfo().getValueAt(row, 2);
						selectTitle = (String) mainView.getBookInfo().getValueAt(row, 3);
						selectAuthor = (String) mainView.getBookInfo().getValueAt(row, 4);
						selectPublisher = (String) mainView.getBookInfo().getValueAt(row, 5);
						selectCategory = (String) mainView.getBookInfo().getValueAt(row, 6);
						selectKeywords = (String) mainView.getBookInfo().getValueAt(row, 7);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					mainView.getBookInfo().clearSelection();
				}
			}
		});

		//
		mainView.setDescriptionML(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				mainView.getBookInfo().clearSelection();
				selectPath = null;
			}
		});

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateBook() {
		data = new Vector(); // NOTE THIS STATEMENT
		for (int i = 0; i < bookList.size(); i++) {
			// System.out.println("\n" + bookList.get(i));
			// read metadata from PDF
			try {
				PdfReader reader = new PdfReader(bookList.get(i));
				HashMap<String, String> info = reader.getInfo();
				String path = bookList.get(i);
				String pdf = pdfList.get(i);
				String isbn = info.get("ISBN");
				String title = info.get("Title");
				String author = info.get("Author");
				String publisher = info.get("Publisher");
				String category = info.get("Category");
				String keywords = info.get("Keywords");

				Vector tempData = new Vector();
				tempData.addElement(path);
				tempData.addElement(pdf);
				tempData.addElement(isbn);
				tempData.addElement(title);
				tempData.addElement(author);
				tempData.addElement(publisher);
				tempData.addElement(category);
				tempData.addElement(keywords);
				data.add(tempData);
				reader.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			((MainView) mainView).refreshTable(data);

		}
	}

	public static void main(String[] args) {
		new MainViewController();
	}
}
