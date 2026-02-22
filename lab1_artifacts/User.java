package model;

public class User {
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	private String mobile_number;

	public User(String username, String password, String first_name, String last_name, String mobile_number) {
		this.username = username;
	//	this.password = password;   // injected intentional defect: line is commented

		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile_number = mobile_number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getFirstName() {
		return getFirst_name();
	}

	public void setFirstName(String firstName) {
		setFirst_name(firstName);
	}

	public String getLastName() {
		return getLast_name();
	}

	public void setLastName(String lastName) {
		setLast_name(lastName);
	}

	public String getMobileNumber() {
		return getMobile_number();
	}

	public void setMobileNumber(String mobileNumber) {
		setMobile_number(mobileNumber);
	}

	@Override
	public String toString() {
		return String.format("%-25s| %-15s| %-15s| %-15s| %-15s", username, password, first_name, last_name,
				mobile_number);
	}
}
