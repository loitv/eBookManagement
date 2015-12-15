package reference;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings({ "serial", "unused" })
public class SetImageJTableCell extends JFrame
{
    public SetImageJTableCell()
    {
        ImageIcon aboutIcon = new javax.swing.ImageIcon(getClass().getResource("/Icon/login.png"));
        ImageIcon addIcon = new ImageIcon("D:\\Documents\\Java\\MyProject\\src\\Icon\\account.png");
        ImageIcon copyIcon = new ImageIcon("D:\\Documents\\Java\\MyProject\\src\\account.png");

        String[] columnNames = {"Picture", "Description"};
        Object[][] data =
        {
            {aboutIcon, "About"},
            {addIcon, "Add"},
            {copyIcon, "Copy"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable( model )
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            @SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane( table );
        getContentPane().add( scrollPane );
    }

    public static void main(String[] args)
    {
    	SetImageJTableCell frame = new SetImageJTableCell();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible(true);
    }

}