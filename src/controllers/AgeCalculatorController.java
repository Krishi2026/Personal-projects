package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import javax.swing.JOptionPane;

import common.Constants;
import common.Session;
import dao.DAOManager;
import models.UserActivity;
import views.AgeCalculatorView;

public class AgeCalculatorController implements ActionListener {

	private AgeCalculatorView ageCalculatorView;

	public AgeCalculatorController(AgeCalculatorView ageCalculatorView) {
		this.ageCalculatorView = ageCalculatorView;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(ageCalculatorView.getButtonCalcuate())) {
			LocalDate now = LocalDate.now();
			LocalDate dobDate = ageCalculatorView.getDOB();
			LocalDate ageAtDate = ageCalculatorView.getAgeAtDate();
			if (dobDate.compareTo(now) > 0) {
				JOptionPane.showMessageDialog(null, "DOD must be lesser than today", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (ageAtDate.compareTo(dobDate) < 0) {
				JOptionPane.showMessageDialog(null, "Age at Date must be greater than DOB", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Period period = Period.between(dobDate, ageAtDate);
				ageCalculatorView.setAge(
						period.getYears() + " Years " + period.getMonths() + " Months " + period.getDays() + " Days");
				
				Session session = ageCalculatorView.getSession();
				if (session != null) {
					UserActivity calculateActivity = new UserActivity();
					calculateActivity.setUsername(session.getUserName());
					calculateActivity.setActivityDate(LocalDateTime.now());
					calculateActivity.setSessionid(String.valueOf(session.getSessionId()));
					calculateActivity.setUseraction(Constants.ACTIVITY_CALCULATE_AGE);
					calculateActivity.setUserinput(ageCalculatorView.getDOB() + " - " + ageCalculatorView.getAgeAtDate());
					calculateActivity.setUseroutput(ageCalculatorView.getAge());
					DAOManager.getUserUserActivityDAO().insert(calculateActivity);
				}
			}
		} else if (e.getSource().equals(ageCalculatorView.getButtonSignOut())) {
			ageCalculatorView.getSignInListener().onSignOut();
		}
	}

}
