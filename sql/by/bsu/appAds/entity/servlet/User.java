package by.bsu.appAds.entity.servlet;

public class User {

	private String username;
	private String login;
	private String password;
	private String avatar;


	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getAvatar() {
		return avatar;
	}



	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}



	@Override
	public String toString() {
		return "User [username=" + username + ", login=" + login + ", password=" + password + ", avatar=" + avatar
				+ "]";
	}

	public User(String name, String login, String password,String avatar) {
		this.username = name;
		this.login = login;
		this.password = password;
		this.avatar = avatar;
	}

}


	