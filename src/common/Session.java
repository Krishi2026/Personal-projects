package common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;

public class Session {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyyhhmmssSSS");

	private String userName;

	private LocalDate dob;

	private LocalDateTime signinDateTime;

	private String sessionId;

	public Session(String userName, LocalDate dob, LocalDateTime signinDateTime) {
		this.userName = userName;
		this.dob = dob;
		this.signinDateTime = signinDateTime;
		sessionId = signinDateTime.format(FORMATTER);
	}

	public String getUserName() {
		return userName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public LocalDateTime getLoginDateTime() {
		return signinDateTime;
	}

	public String getSessionId() {
		return sessionId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dob, signinDateTime, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		return Objects.equals(dob, other.dob) && Objects.equals(signinDateTime, other.signinDateTime)
				&& Objects.equals(userName, other.userName);
	}

}
