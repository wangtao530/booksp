package book.entity;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import book.util.Constants;

@WebListener
public class UserInfo implements HttpSessionBindingListener {
	private String userName;
	private String password;
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserInfo [userName=" + userName + ", password=" + password + ", email=" + email + "]";
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		Constants.ONLINE_USER_COUNT++;
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		Constants.ONLINE_USER_COUNT--;
	}
}
