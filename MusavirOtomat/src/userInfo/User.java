package userInfo;

public class User {

	private String userName;
	private String userNumber;
	private String userPassword;
	
	public User(String userNamep, int userNumberp,String userPasswordp) {
		userName=userNamep;
		userNumber=Integer.toString(userNumberp);
		userPassword=userPasswordp;
	}
	

	public String getUserName() {
		return userName;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public String getUserPassword() {
		return userPassword;
	}
}
