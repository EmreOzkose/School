package PackageUsers;
/**
 * managing all users which are admins, technicians and customers
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class Users {
	
	private String userName;
	private String userEmail;
	private String userDateOfBirth;
	/**
	 * class constructor
	 */
	public Users() {
		
	}

	public String getUserDateOfBirth() {
		return userDateOfBirth;
	}
	public void setUserDateOfBirth(String userDateOfBirth) {
		this.userDateOfBirth = userDateOfBirth;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * displaying user's informations
	 */
	public void displayUser(){
		System.out.println("----------- User info -----------");
		System.out.println("Admin name : " + this.userName);
		System.out.println("Admin e-mail : " + this.userEmail);
		System.out.println("Admin date of birth : " + this.userDateOfBirth);
	}

}
