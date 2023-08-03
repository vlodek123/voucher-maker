package cz.tallavla.vouchermaker.model.service;


public class LoginDTO {

	private String userNameOrEmail;
	private String password;

	public LoginDTO() {
	}

	public LoginDTO(String userNameOrEmail, String password) {
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
