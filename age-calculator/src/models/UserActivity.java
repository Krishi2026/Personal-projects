package models;

import java.time.LocalDateTime;

public class UserActivity {

	private int id;
	
	private LocalDateTime activityDate;
	
	private String username;
	
	private String sessionid;
	
	private String useraction;
	
	private String userinput = "";
	
	private String useroutput = "";
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(LocalDateTime activityDate) {
		this.activityDate = activityDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getUseraction() {
		return useraction;
	}

	public void setUseraction(String useraction) {
		this.useraction = useraction;
	}

	public String getUserinput() {
		return userinput;
	}

	public void setUserinput(String userinput) {
		this.userinput = userinput;
	}

	public String getUseroutput() {
		return useroutput;
	}

	public void setUseroutput(String useroutput) {
		this.useroutput = useroutput;
	}

	
	
}
