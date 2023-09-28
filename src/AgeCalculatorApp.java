
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.DimensionUIResource;

import common.Constants;
import common.Session;
import dao.DAOManager;
import models.User;
import models.UserActivity;
import views.AgeCalculatorView;
import views.MyProfileView;
import views.SignInView;
import views.SignUpView;
import views.UserActivityView;

public class AgeCalculatorApp extends AbstractAgeCalculatorApp implements ActionListener {

	private Session session;
	
	private JMenuBar menubar;

	public AgeCalculatorApp() {
		super();
		signInView = new SignInView();
		signInView.setSignUpListener(this);
		signInView.setSignInListener(this);
		signUpView = new SignUpView();
		signUpView.setSignUpListener(this);
		ageCalculatorView = new AgeCalculatorView();
		ageCalculatorView.setSignInListener(this);
		userActivityView = new UserActivityView();
		userActivityView.setSignInListener(this);
		myProfileView = new MyProfileView();
		myProfileView.setSignInListener(this);
		removeAllViews();
		setLayout(new GridBagLayout());
		setTitle("Age Calculator");
		add(signInView);
		add(signUpView);
		add(ageCalculatorView);
		add(userActivityView);
		add(myProfileView);
		
		signInView.setVisible(true);
		setMinimumSize(new DimensionUIResource(500, 320));
		setPreferredSize(new DimensionUIResource(500, 320));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close this window?", "Close Window?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (session != null) {
						onSignOut();
					}
					System.exit(0);
				}
			}
		});
	}

	@Override
	public void onSignInSuccess(User user) {
		session = new Session(user.getUsername(), user.getDob(), LocalDateTime.now());
		UserActivity signInActivity = new UserActivity();
		signInActivity.setUsername(session.getUserName());
		signInActivity.setActivityDate(LocalDateTime.now());
		signInActivity.setSessionid(String.valueOf(session.getSessionId()));
		signInActivity.setUseraction(Constants.ACTIVITY_SIGNIN_SUCCESS);
		DAOManager.getUserUserActivityDAO().insert(signInActivity);
		setTitle("Age Calculator - "+user.getUsername());
		if (!isSuperUser(user)) {
			ageCalculatorView.setSession(session);
			myProfileView.setSession(session);

			super.onSignInSuccess(user);
			ageCalculatorView.updateDOB();
			menubar = new JMenuBar();
			JMenu menu = new JMenu("Options");
			JMenuItem myProfile = new JMenuItem("My Profile");
			myProfile.addActionListener(this);
			JMenuItem signout = new JMenuItem("Sign Out");
			signout.addActionListener(this);
			menu.add(myProfile);
			menu.add(signout);
			menubar.add(menu);
			setJMenuBar(menubar);
		} else {
			setMinimumSize(new DimensionUIResource(1000, 500));
			setPreferredSize(new DimensionUIResource(1000, 500));
			pack();
			setLocationRelativeTo(null);

			super.onSignInSuccess(user);
		}

	}

	@Override
	public void onSignInFailure(User user) {
		UserActivity signInActivity = new UserActivity();
		signInActivity.setUsername(user.getUsername());
		signInActivity.setActivityDate(LocalDateTime.now());
		signInActivity.setSessionid("");
		signInActivity.setUseraction(Constants.ACTIVITY_SIGNIN_FAILURE);
		DAOManager.getUserUserActivityDAO().insert(signInActivity);
		super.onSignInFailure(user);
	}

	@Override
	public void onSignOut() {
		UserActivity signOutActivity = new UserActivity();
		signOutActivity.setUsername(session.getUserName());
		signOutActivity.setActivityDate(LocalDateTime.now());
		signOutActivity.setSessionid(String.valueOf(session.getSessionId()));
		signOutActivity.setUseraction(Constants.ACTIVITY_SIGNOUT);
		DAOManager.getUserUserActivityDAO().insert(signOutActivity);
		session = null;
		ageCalculatorView.setSession(session);
		myProfileView.setSession(session);
		setJMenuBar(null);
		super.onSignOut();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void createAndShowGUI() throws Exception {

		new AgeCalculatorApp();
	}

	@Override
	public void onSignUpSuccess(User user) {
		UserActivity signUpActivity = new UserActivity();
		signUpActivity.setUsername(user.getUsername());
		signUpActivity.setActivityDate(LocalDateTime.now());
		signUpActivity.setSessionid("");
		signUpActivity.setUseraction(Constants.ACTIVITY_SIGNUP);
		signUpActivity.setUserinput(user.getFirstName() + "," + user.getLastName() + "," + user.getDob().toString());
		DAOManager.getUserUserActivityDAO().insert(signUpActivity);
		super.onSignInFailure(user);
	}

	@Override
	public void onSignUpFailure(User user) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JMenuItem)e.getSource()).getText().equals("My Profile")) {
			removeAllViews();
			myProfileView.showMyProfile();
		}
		else if(((JMenuItem)e.getSource()).getText().equals("Sign Out")) {
			onSignOut();
		}
	}
}
