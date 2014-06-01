package edu.univdhaka.iit.echo.domain;

import java.util.List;


/**
 * this class contains the attributes of a user object...
 *
 */
public class UserAccount {
	private int id;
	private int version;

	private String firstName;
	private String lastName;
	private String emailAddress;
	private String userName;
	private String password;
	private String confirmedPassword;
	private List<Echo> allEchos;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Echo> getAllEchos() {
		return allEchos;
	}

	public void setAllEchos(List<Echo> allEchos) {
		this.allEchos = allEchos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", firstName='" + firstName + '\''
				+ ", lastName='" + lastName + '\'' + ", emailAddress='"
				+ emailAddress + '\'' + ", userName='" + userName + '\''
				+ ", password='" + password + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserAccount user = (UserAccount) o;

		if (id != user.id)
			return false;
		if (allEchos != null ? !allEchos.equals(user.allEchos)
				: user.allEchos != null)
			return false;
		if (emailAddress != null ? !emailAddress.equals(user.emailAddress)
				: user.emailAddress != null)
			return false;
		if (firstName != null ? !firstName.equals(user.firstName)
				: user.firstName != null)
			return false;
		if (lastName != null ? !lastName.equals(user.lastName)
				: user.lastName != null)
			return false;
		if (userName != null ? !userName.equals(user.userName)
				: user.userName != null)
			return false;
		if (password != null ? !password.equals(user.password)
				: user.password != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result
				+ (emailAddress != null ? emailAddress.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (allEchos != null ? allEchos.hashCode() : 0);
		return result;
	}

	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}
}