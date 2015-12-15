package reference;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings("unused")
public class TableTest {
	final int FIT = 0, FILL = 1;

	public TableTest() {
		BufferedImage[] images = loadImages();
		BufferedImage[] scaled = getScaledImages(images, FIT);
		JTable table = createTable(scaled);
		table.setRowHeight(scaled[0].getHeight());
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getDefaultRenderer(String.class);
		renderer.setHorizontalAlignment(JLabel.CENTER);
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(getUIPanel(table, images), "North");
		f.getContentPane().add(new JScrollPane(table));
		f.setSize(400, 340);
		f.setLocation(200, 200);
		f.setVisible(true);
	}

	private BufferedImage[] loadImages() {
		String[] fileNames = { "demo.jpg", "account.png", "allbook2.png", "Bookmenu.png" };
		BufferedImage[] images = new BufferedImage[fileNames.length];
		for (int j = 0; j < images.length; j++)
			try {
				URL url = getClass().getResource("/Icon/" + fileNames[j]);
				images[j] = ImageIO.read(url);
			} catch (MalformedURLException mue) {
				System.err.println("url: " + mue.getMessage());
			} catch (IOException ioe) {
				System.err.println("read: " + ioe.getMessage());
			}
		return images;
	}

	private BufferedImage[] getScaledImages(BufferedImage[] in, int type) {
		int WIDTH = 40;
		int HEIGHT = 40;
		BufferedImage[] out = new BufferedImage[in.length];
		for (int j = 0; j < out.length; j++) {
			out[j] = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = out[j].createGraphics();
			g2.setColor(Color.white);
			g2.fillRect(0, 0, WIDTH, HEIGHT);
			double width = in[j].getWidth();
			double height = in[j].getHeight();
			double xScale = WIDTH / width;
			double yScale = HEIGHT / height;
			double scale = 1.0;
			switch (type) {
			case FIT:
				scale = Math.min(xScale, yScale); // scale to fit
				break;
			case FILL:
				scale = Math.max(xScale, yScale); // scale to fill
			}
			double x = (WIDTH - width * scale) / 2;
			double y = (HEIGHT - height * scale) / 2;
			AffineTransform at = AffineTransform.getTranslateInstance(x, y);
			at.scale(scale, scale);
			g2.drawRenderedImage(in[j], at);
			g2.dispose();
		}
		return out;
	}

	@SuppressWarnings("serial")
	private JTable createTable(BufferedImage[] images) {
		String[] names = { "lynx", "owl", "fox", "goat" };
		String[] dobs = { "15 Jan 2003", "18 Mar 1997", "16 Apr 2001", "8 Feb 2000" };
		String[] headers = { "Name", "Birth", "Photo" };
		final Object[][] allData = { names, dobs, images };
		int rows = 4, cols = 3;
		Object[][] data = new Object[rows][cols];
		for (int row = 0; row < rows; row++)
			for (int col = 0; col < cols; col++)
				data[row][col] = allData[col][row];
		DefaultTableModel model = new DefaultTableModel(data, headers) {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int col) {
				return allData[col][0].getClass();
			}

			public boolean isCellEditable(int row, int col) {
				return allData[col][row] != allData[2][row];
			}
		};
		JTable table = new JTable(model);
		table.setDefaultRenderer(BufferedImage.class, new ImageRenderer());

		return table;
	}

	private JPanel getUIPanel(final JTable table, final BufferedImage[] orig) {
		String[] ids = { "fit", "fill" };
		final JRadioButton[] rbs = new JRadioButton[ids.length];
		ButtonGroup group = new ButtonGroup();
		ActionListener l = new ActionListener() {
			int type = -1;

			public void actionPerformed(ActionEvent e) {
				JRadioButton rb = (JRadioButton) e.getSource();
				String ac = rb.getActionCommand();
				if (ac.equals("fit"))
					type = FIT;
				else
					type = FILL;
				BufferedImage[] images = getScaledImages(orig, type);
				for (int row = 0; row < images.length; row++)
					table.setValueAt(images[row], row, 2);
			}
		};
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("image scaling to"));
		for (int j = 0; j < rbs.length; j++) {
			rbs[j] = new JRadioButton(ids[j]);
			rbs[j].setActionCommand(ids[j]);
			rbs[j].addActionListener(l);
			group.add(rbs[j]);
			panel.add(rbs[j]);
		}
		rbs[0].setSelected(true);
		return panel;
	}

	public static void main(String[] args) {
		new TableTest();
	}
}

@SuppressWarnings("serial")
class ImageRenderer extends DefaultTableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		setIcon(new ImageIcon((BufferedImage) value));
		setHorizontalAlignment(JLabel.CENTER);
		setText("");
		return this;
	}
}