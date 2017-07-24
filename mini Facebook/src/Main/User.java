package Main;

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
	private enum relationships {IN_RELATIONSHIP{@Override public String toString(){return "in relationship";}},
								DIVORCED {@Override public String toString(){return "divorced";}},
								COMPLICATED {@Override public String toString(){return "compliceted";}},
								SINGLE {@Override public String toString(){return "single";}} };
	private relationships relationshipStatu;
	
	ArrayList<String> listFriends = new ArrayList<String>();
	ArrayList<String> blockedUsers = new ArrayList<String>();
	ArrayList<Post> listPosts = new ArrayList<Post>();
	
	/**
	 * 
	 * @param userID	user's id 
	 * @param name	user's name
	 * @param userName	user's user name
	 * @param password	user's password
	 * @param dateOfBirth	user's date of birth
	 * @param schoolInfo	user's school information
	 */
	public User(int userID, String name, String userName, String password, String dateOfBirth, String schoolInfo, String relationship) {
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
		this.relationshipStatu = relationships.valueOf(removeUnderscore(relationship.toUpperCase().replace("İ", "I")));
	}
	
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
	public void addImagePost(String textContent, String longitude, String latitude, String taggedUsers,String filePath, String width, String height){
		ArrayList<String> taggedFriends = new ArrayList<String>();
		for (String i : taggedUsers.split(":")){
			if (inFriend(i)){
				taggedFriends.add(i);
			}
			else
				System.out.println("Username " + i + " is not your friend, and will not be tagged!");
		}
		
		getListPosts().add(new ImagePost(textContent, longitude, latitude, taggedFriends,filePath, width,height));
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
	
	public void removeFriend(String friend){
		getListFriends().remove(friend);
		UserCollection.findUser(friend).getListFriends().remove(getUserName());
	}
	
	public void blockFriend(String friend){
		getBlockedUsers().add(friend);
		UserCollection.findUser(friend).getBlockedUsers().add(getUserName());
		
		getListFriends().remove(friend);
		UserCollection.findUser(friend).getListFriends().remove(getUserName());
	}
	/**
	 * ensuring if a user is a friend or not
	 * @param controllingFriend	user name which is wanted to control
	 * @return
	 */
	public boolean inFriend(String controllingFriend){
		for (String i : getListFriends()){
			if (i.equals(controllingFriend))
				return true;
		}
		return false;
	}
	
	public String removeUnderscore(String input){
		String [] iv = input.split(" ");
		String output = "";
		for (int i=0; i < iv.length ;i++){
			
			if (i != iv.length-1){output += output += iv[i] + "_";} else output += iv[i];;
		}
			
		return output;
	}
	
	public void addFriend(String userName){
			if(!getListFriends().contains(userName))
				getListFriends().add(userName);
			if(!UserCollection.findUser(userName).getListFriends().contains(getUserName()))
				UserCollection.findUser(userName).getListFriends().add(getUserName());
		
	}
	
	//getter and setter methods
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
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
	public Collection<String> getListFriends() {
		return listFriends;
	}
	public void setListFriends(ArrayList<String> listFriends) {
		this.listFriends = listFriends;
	}
	public ArrayList<String> getBlockedUsers() {
		return blockedUsers;
	}
	public void setBlockedUsers(ArrayList<String> blockedUsers) {
		this.blockedUsers = blockedUsers;
	}
	public ArrayList<Post> getListPosts() {
		return listPosts;
	}
	public void setListPosts(ArrayList<Post> listPosts) {
		this.listPosts = listPosts;
	}

	public String getRelationshipStatu() {
		return relationshipStatu.toString();
	}

	public void setRelationshipStatu(String relationshipStatu) {
		this.relationshipStatu = relationships.valueOf(removeUnderscore(relationshipStatu.toUpperCase().replace("İ", "I")));
	}
	
	
}