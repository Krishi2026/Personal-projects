package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controllers.UserActivityController;

public class UserActivityView extends AbstractView {

	private String columnNames[] = new String[] { "Row #", "User", "Date", "Session", "Action", "Input", "Output" };

	private JButton btnQuery = new JButton("Query");

	private JButton btnSignOut = new JButton("Sign Out");

	private JTable table = new JTable(new String[0][7], columnNames);

	public UserActivityView() {
		UserActivityController controller = new UserActivityController(this);
		TitledBorder border = new TitledBorder("User Activities");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);
		setBorder(border);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		add(btnQuery, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		add(btnSignOut, c);
        table.setPreferredScrollableViewportSize(new Dimension(850, 350));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weightx = 1;
		c.weighty = 1;
        add(scrollPane,c);
        setVisible(true);
        btnQuery.addActionListener(controller);
        btnSignOut.addActionListener(controller);
        
	}

	@Override
	public void reset() {

	}

	
	public JButton getSignOutButton() {
		return btnSignOut;
	}
	
	public JButton getQueryButton() {
		return btnQuery;
	}
	
	public void updateTable(String[][] data) {
		table.setModel(new DefaultTableModel(data, columnNames));
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
			
	}
}
