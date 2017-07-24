import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
/**
 * class of system's main object
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class User {
	
	private int userID;
	private String name;
	private String userName;
	private String password;
	private Date dateOfBirth;
	private String schoolInfo;
	private Date lastLoginDate;
	
	static Collection<String> listFriends = new ArrayList<String>();
	static Collection<String> blockedUsers = new ArrayList<String>();
	static Collection<Post> listPosts = new ArrayList<Post>();
	/**
	 * 
	 * @param userID	user's id 
	 * @param name	user's name
	 * @param userName	user's user name
	 * @param password	user's password
	 * @param dateOfBirth	user's date of birth
	 * @param schoolInfo	user's school information
	 */
	public User(int userID, String name, String userName, String password, String dateOfBirth, String schoolInfo) {
		this.userID = userID;
		this.name = name;
		this.userName = userName;
		this.password = password;
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		try {
			this.dateOfBirth = df.parse(dateOfBirth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.schoolInfo = schoolInfo;
		Date date = new Date();
		this.lastLoginDate = date;
	}
	/**
	 * to login to the system
	 * @param userName	controlling user name
	 * @param password	controlling password
	 */
	public void signIn(String userName, String password){
		if (UserCollection.userSignIn(userName, password)){
			System.out.println("You have successfully signed in.");
		}
		else if(UserCollection.getList_users().contains(UserCollection.findUser(userName)))
			System.out.println("Invalid username or password! Please try again.");
	}
	/**
	 * to login out from the system
	 */
	public void signOut(){
		System.out.println("You have successfully signed out.");
	}
	/**
	 * updating profile by user
	 * @param name	new or old name
	 * @param dateOfBirth	new or old password
	 * @param schoolInfo	new or old school information
	 */
	public void updateProfile(String name, String dateOfBirth, String schoolInfo){
		setName(name);
		setSchoolInfo(schoolInfo);
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		try {
			Date date = df.parse(dateOfBirth);
			setDateOfBirth(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Your user profile has been successfully updated.");
	}
	/**
	 * changing password providing with old password
	 * @param oldPass old password
	 * @param newPass new password
	 */
	public void changePass(String oldPass,String newPass){
		if (getPassword().equals(oldPass))
			setPassword(newPass);
		else
			System.out.println("Password mismatch! Please, try again.");
	}
	/**
	 * adding new friend
	 * @param userName	user name of friend who is wanted to add
	 */
	public void addFriend(String userName){
		if (!inFriend(userName)){
			if(UserCollection.inUser(userName)){
				getListFriends().add(userName);
				System.out.println(userName + " has been successfully added to your friend list.");
			}
			else
				System.out.println("No such user!");
		}
		else
			System.out.println("This user is already in your friend list!");
	}
	/**
	 * removing friend
	 * @param userName	user name of friend who is wanted to remove
	 */
	public void removeFriend(String userName){
		if(inFriend(userName)){
			for(int i = 0 ; i < getListFriends().size();i++){
				if(((ArrayList<String>) getListFriends()).get(i).equals(userName))
					((ArrayList<String>)getListFriends()).remove(i);
			}
			System.out.println(userName + " has been successfully removed from your friend list.");
		}
		else
			System.out.println("No such friend!");
	}
	/**
	 * adding new text-post
	 * @param textContent	post's text
	 * @param longitude	post's location information about longitude
	 * @param latitude	post's location information about latitude
	 * @param taggedUsers	tagged user's name
	 */
	public void addTextPost(String textContent, String longitude, String latitude, String taggedUsers){
		ArrayList<String> taggedFriends = new ArrayList<String>();
		for (String i : taggedUsers.split(":")){
			if (inFriend(i)){
				taggedFriends.add(i);
			}
			else
				System.out.println("Username " + i + " is not your friend, and will not be tagged!");
		}
		
		getListPosts().add(new TextPost(textContent, longitude, latitude, taggedFriends));
		System.out.println("The post has been successfully added.");
	}
	/**
	 * adding new image-post
	 * @param textContent	post's text
	 * @param longitude	post's location information about longitude
	 * @param latitude	post's location information about latitude
	 * @param taggedUsers	tagged user's name
	 * @param filePath	post's file path
	 * @param resolution	post's resolution
	 */
	public void addImagePost(String textContent, String longitude, String latitude, String taggedUsers,String filePath, String resolution){
		ArrayList<String> taggedFriends = new ArrayList<String>();
		for (String i : taggedUsers.split(":")){
			if (inFriend(i)){
				taggedFriends.add(i);
			}
			else
				System.out.println("Username " + i + " is not your friend, and will not be tagged!");
		}
		
		getListPosts().add(new ImagePost(textContent, longitude, latitude, taggedFriends,filePath, resolution));
		System.out.println("The post has been successfully added.");
	}
	/**
	 * adding new video-post
	 * @param textContent	post's text
	 * @param longitude	post's location information about longitude
	 * @param latitude	post's location information about latitude
	 * @param taggedUsers	tagged user's name
	 * @param filePath	post's file path
	 * @param videoDuration	video's duration
	 */
	public void addVideoPost(String textContent, String longitude, String latitude, String taggedUsers,String filePath, String videoDuration){
		ArrayList<String> taggedFriends = new ArrayList<String>();
		for (String i : taggedUsers.split(":")){
			if (inFriend(i)){
				taggedFriends.add(i);
			}
			else
				System.out.println("Username " + i + " is not your friend, and will not be tagged!");
		}
		if (Double.parseDouble(videoDuration) <= 10.0){
			getListPosts().add(new VideoPost(textContent, longitude, latitude, taggedFriends,filePath,videoDuration));
			System.out.println("The post has been successfully added.");
		}
		else
			System.out.println("Error: Your video exceeds maximum allowed duration of 10 minutes.");
	}
	/**
	 * removing last post of user
	 */
	public void removeLastPost(){
		if(!getListPosts().isEmpty()){
			((ArrayList<Post>)getListPosts()).remove(getListPosts().size()-1);
			System.out.println("Your last post has been successfully removed.");
		}
		else
			System.out.println("Error: You don't have any posts.");
	}
	/**
	 * blocking a user 
	 * @param userName	user name who is wanted to block
	 */
	public void blockUser(String userName){
		if (UserCollection.inUser(userName)){
			getBlockedUsers().add(userName);
			System.out.println(userName + " has been successfully blocked.");
		}
		else
			System.out.println("No such user!");
	}
	/**
	 * unblocking user
	 * @param userName	user name who is wanted to unblock
	 */
	public void unBlockUser(String userName){
		String userName2 = new String(userName);
		if(getBlockedUsers().contains(userName2)){
			for (int i = 0 ; i < getBlockedUsers().size();i++){
				if (((ArrayList<String>) getBlockedUsers()).get(i).equals(userName)){
					((ArrayList<String>)getBlockedUsers()).remove(i);
					System.out.println(userName + " has been successfully unblocked.");
				}
			}
		}
		else
			System.out.println("No such user in your blocked users list!");
	}
	/**
	 * listing friends
	 */
	public void listFriends(){
		if(getListFriends().size() != 0){
			for (String i : getListFriends()){
				User friend = UserCollection.findUser(i);
				System.out.println(friend.toString() + "\n---------------------------");
			}
		}
		else
			System.out.println("You haven’t added any friends yet!");
	}
	/**
	 * listing users
	 */
	public void listUsers(){
		for (User i : UserCollection.getList_users()){
			System.out.println(i.toString());
			System.out.println("---------------------------");
		}
	}
	/**
	 * listing blocked friends
	 */
	public void listBlockedFriends(){
		for (String i : getBlockedUsers()){
			if (inFriend(i)){
				User user = UserCollection.findUser(i);
				System.out.println(user.toString() + "\n---------------------------");
			}
		}
	}
	/**
	 * listing blocked users
	 */
	public void listBlockedUsers(){
		if(getBlockedUsers().size() != 0)
		for (String i : getBlockedUsers()){
			User user = UserCollection.findUser(i);
			System.out.println(user.toString() + "\n---------------------------");
		}
		else
			System.out.println("You haven’t blocked any users yet!");
	}
	/**
	 * ensuring if a user is a friend or not
	 * @param controllingFriend	user name which is wanted to control
	 * @return false/true
	 */
	public static boolean inFriend(String controllingFriend){
		for (String i : getListFriends()){
			if (i.equals(controllingFriend))
				return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
		return "Name: " + this.getName() +  
			   "\nUserName: " + getUserName() + 
			   "\nDate of Birth: " + df.format(getDateOfBirth()) +
			   "\nSchool: " + getSchoolInfo();
	}
	
	//setter-getter methods
	public int getUserId() {
		return userID;
	}

	public void setUserId(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSchoolInfo() {
		return schoolInfo;
	}

	public void setSchoolInfo(String schoolInfo) {
		this.schoolInfo = schoolInfo;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public static Collection<String> getListFriends() {
		return listFriends;
	}

	public void setListFriends(ArrayList<String> listFriends) {
		User.listFriends = listFriends;
	}

	public Collection<String> getBlockedUsers() {
		return blockedUsers;
	}

	public void setBlockedUsers(ArrayList<String> blockedUsers) {
		User.blockedUsers = blockedUsers;
	}

	public Collection<Post> getListPosts() {
		return listPosts;
	}

	public void setListPosts(ArrayList<Post> posts) {
		User.listPosts = posts;
	}

	

}
