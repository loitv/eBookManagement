package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
//import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class MainView extends JFrame {

	private JTextField pathName;
	private JButton fileBorrow, scan, open, edit, openLoc, delete;
	private JTable bookInfo;
	private JScrollPane scrollPanel;
	private JPanel descrip, panel1, preview;
	private JSplitPane splitPane;
//	private JPanel gui;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MainView() {
		super("Book View");
		setLayout(new BorderLayout(5, 5));
		setSize(1000, 500);

		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 4, 5, 5));
		panel1.add(new JLabel("Select directory to scan"));
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		pathName = new JTextField();
		panel1.add(pathName);
		JPanel panel1a = new JPanel();
		panel1a.setLayout(new GridLayout(1, 3, 5, 5));
		fileBorrow = new JButton("...");
		panel1a.add(fileBorrow);
		scan = new JButton("Scan");
		panel1a.add(scan);
		panel1a.add(new JLabel());
		panel1.add(panel1a);
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		add(panel1, BorderLayout.NORTH);

		Vector colsName = new Vector();
		colsName.addElement("Full Path");
		colsName.addElement("File name");
		colsName.addElement("ISBN");
		colsName.addElement("Tittle");
		colsName.addElement("Author");
		colsName.addElement("Publisher");
		colsName.addElement("Category");
		colsName.addElement("Keywords");
		Vector data = new Vector();

		bookInfo = new JTable(data, colsName);
		bookInfo.setPreferredScrollableViewportSize(new Dimension(500, 100));
		bookInfo.setFillsViewportHeight(true);
		bookInfo.setAutoCreateRowSorter(true);
		scrollPanel = new JScrollPane(bookInfo);

		preview = new JPanel();
		preview.setLayout(new BorderLayout(5,5));
		JPanel preview1 = new JPanel();
		preview1.setLayout(new BorderLayout(5,5));
		JPanel btnGroup = new JPanel();
		btnGroup.setLayout(new GridLayout(0,2,5,5));
		open = new JButton("Open");
		edit = new JButton("Edit");
		openLoc = new JButton("File Location");
		delete = new JButton("Delete");
		btnGroup.add(open);
		btnGroup.add(edit);
		btnGroup.add(openLoc);
		btnGroup.add(delete);
		preview1.add(btnGroup,BorderLayout.NORTH);
		descrip = new JPanel();
		descrip.add(new JLabel("Description"));
		preview1.add(descrip,BorderLayout.CENTER);
		preview.add(preview1, BorderLayout.NORTH);
		JPanel bookInfo = new JPanel();
		preview.add(bookInfo, BorderLayout.CENTER);
		

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPanel, preview);
		splitPane.setResizeWeight(0.9);
		add(splitPane, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public MainView(int a) {
		
	}

	// SET ACTION LISTENER
	public void setButtonBorrowActionListener(ActionListener al) {
		this.fileBorrow.addActionListener(al);
	}

	public void setButtonScanActionListener(ActionListener al) {
		this.scan.addActionListener(al);
	}
	public void setButtonOpenActionListener(ActionListener al) {
		this.open.addActionListener(al);
	}
	public void setButtonEditActionListener(ActionListener al) {
		this.edit.addActionListener(al);
	}
	public void setButtonOpenLocActionListener(ActionListener al) {
		this.openLoc.addActionListener(al);
	}
	public void setButtonDeleteActionListener(ActionListener al) {
		this.delete.addActionListener(al);
	}
	public void setBookDetailsML(MouseListener ml) {
		this.bookInfo.addMouseListener(ml);
	}
	public void setDescriptionML(MouseListener ml) {
//		this.descrip.addMouseListener(ml);
		this.panel1.addMouseListener(ml);
		this.splitPane.addMouseListener(ml);
	}
	

	// GETTER METHOD
	public JTextField getPathName() {
		return pathName;
	}

	public JTable getBookInfo() {
		return bookInfo;
	}

	/////////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refreshTable(Vector list) {
		TableModel model = bookInfo.getModel();
		Vector colsName = new Vector();
		colsName.addElement("Full Path");
		colsName.addElement("File name");
		colsName.addElement("ISBN");
		colsName.addElement("Tittle");
		colsName.addElement("Author");
		colsName.addElement("Publisher");
		colsName.addElement("Category");
		colsName.addElement("Keywords");
		model = new DefaultTableModel(list, colsName);
		bookInfo.setModel(model);
		bookInfo.repaint();
	}

	public static void main(String[] args) {
		new MainView();
	}
}
