package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditInfoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbISBN, lbTitle, lbAuthor, lbPublisher, lbCategory, lbKeywords;
	private JTextField tfISBN, tfTitle, tfAuthor, tfPublisher, tfCategory, tfKeywords;
	private JButton update;

	public EditInfoView() {
		super("Edit book informations");
		setSize(450, 290);
		setLayout(new BorderLayout(5, 5));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(5, 5));
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 5, 5));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0, 1, 5, 5));
		lbISBN = new JLabel("ISBN: ", JLabel.TRAILING);
		lbTitle = new JLabel("Title: ", JLabel.TRAILING);
		lbAuthor = new JLabel("Author: ", JLabel.TRAILING);
		lbPublisher = new JLabel("Publisher: ", JLabel.TRAILING);
		lbCategory = new JLabel("Category: ", JLabel.TRAILING);
		lbKeywords = new JLabel("Keywords: ", JLabel.TRAILING);
		tfISBN = new JTextField();
		tfTitle = new JTextField();
		tfAuthor = new JTextField();
		tfPublisher = new JTextField();
		tfCategory = new JTextField();
		tfKeywords = new JTextField();
		panel1.add(lbISBN);
		panel2.add(tfISBN);
		panel1.add(lbTitle);
		panel2.add(tfTitle);
		panel1.add(lbAuthor);
		panel2.add(tfAuthor);
		panel1.add(lbPublisher);
		panel2.add(tfPublisher);
		panel1.add(lbCategory);
		panel2.add(tfCategory);
		panel1.add(lbKeywords);
		panel2.add(tfKeywords);
		panel.add(panel1, BorderLayout.WEST);
		panel.add(panel2, BorderLayout.CENTER);
		add(panel, BorderLayout.CENTER);

		JPanel panelb = new JPanel();
		panelb.setLayout(new GridLayout(0, 1, 5, 5));
//		panelb.add(new JLabel());
		panelb.add(new JLabel());
		update = new JButton("Update");
		panelb.add(update);
		add(panelb, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	// SET ACTION LISTENER
	public void setButtonUpdateActionListener(ActionListener al) {
		this.update.addActionListener(al);
	}

	// GETTER METHODS
	public JTextField getTfISBN() {
		return tfISBN;
	}

	public JTextField getTfTitle() {
		return tfTitle;
	}

	public JTextField getTfAuthor() {
		return tfAuthor;
	}

	public JTextField getTfPublisher() {
		return tfPublisher;
	}

	public JTextField getTfCategory() {
		return tfCategory;
	}

	public JTextField getTfKeywords() {
		return tfKeywords;
	}

	public JButton getUpdate() {
		return update;
	}

	public static void main(String[] args) {
		new EditInfoView();
	}
}
