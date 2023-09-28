package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.DimensionUIResource;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import common.Session;
import controllers.AgeCalculatorController;

public class AgeCalculatorView extends AbstractView {

	private DatePicker dobDatePicker;
	
	private DatePicker ageAtDatePicker;
	
	private JButton btnCalculate ;
	
	private JButton btnSignOut ;
	
	private JLabel lblAge;
	
	public AgeCalculatorView() {
	    TitledBorder border = new TitledBorder("Age Calculator");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
	    setBorder(border);
		JLabel lblDOB = new JLabel("Date Of Birth :");
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dateSettings.setAllowEmptyDates(false);
		dobDatePicker = new DatePicker(dateSettings);
		JLabel lblAgeAt = new JLabel("Age at the Date of :");
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dateSettings.setAllowEmptyDates(false);
		ageAtDatePicker = new DatePicker(dateSettings);
		ageAtDatePicker.setDateToToday();
		btnCalculate = new JButton();
		btnCalculate.setText("Caclulate");
		btnSignOut = new JButton();
		btnSignOut.setText("Sign Out");
		lblAge = new JLabel("0 Years 0 Months 0 Days");
		lblAge.setOpaque(true);
		lblAge.setBackground(new Color(252, 231, 134));
		lblAge.setFont(new Font("Arial", Font.BOLD, 24));
		lblAge.setBorder(new EmptyBorder(5, 5, 5, 5));
		lblAge.setMinimumSize(new DimensionUIResource(500, 50));
		lblAge.setPreferredSize(new DimensionUIResource(500, 50));
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setVerticalAlignment(SwingConstants.CENTER);
		AgeCalculatorController controller = new AgeCalculatorController(this);
		btnCalculate.addActionListener(controller);
		btnSignOut.addActionListener(controller);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		add(lblDOB, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		add(dobDatePicker, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 1;
		add(lblAgeAt, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 1;
		add(ageAtDatePicker, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		add(btnCalculate, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		add(btnSignOut, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		add(lblAge, c);
		setVisible(true);
	}
	
	public void updateDOB() {
		Session session = getSession();
		if(session!=null && session.getDob()!=null) {
			
			dobDatePicker.setDate(session.getDob());
			btnCalculate.doClick();
		}
	}
	
	public LocalDate getDOB() {
		return dobDatePicker.getDate();
	}
	
	public LocalDate getAgeAtDate() {
		return ageAtDatePicker.getDate();
	}
	
	public void setAge(String age) {
		lblAge.setText(age);
	}
	
	public String getAge() {
		return lblAge.getText();
	}
		

	public JButton getButtonCalcuate() {
		return btnCalculate;
	}
	
	public JButton getButtonSignOut() {
		return btnSignOut;
	}

	@Override
	public void reset() {
		dobDatePicker.setDate(LocalDate.now());
		ageAtDatePicker.setDate(LocalDate.now());
		lblAge.setText("");
	}
}
