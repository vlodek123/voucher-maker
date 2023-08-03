package cz.tallavla.vouchermaker.model.controller;

public class NewLogin {


	private String userNameOrEmail;
	private String password;

	public NewLogin() {
	}

	public NewLogin(String userNameOrEmail, String password) {
		this.userNameOrEmail = userNameOrEmail;
		this.password = password;
	}

	public String getUserNameOrEmail() {
		return userNameOrEmail;
	}

	public void setUserNameOrEmail(String userNameOrEmail) {
		this.userNameOrEmail = userNameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
