import java.util.ArrayList;
/**
 * created for handling all user processes
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */

public class UserCollection {
	
	static ArrayList<User> list_users = new ArrayList<User>();
	private static int givingUserID = 1;	//to determine userID;
	
	public UserCollection(){
		
	}
	
	
	/**
	 * system functionality - adding a user
	 * @param name	user name
	 * @param userName	name which each user have different one
	 * @param password	user password
	 * @param dateOfBirth	user date of birth
	 * @param schoolInfo	user school information
	 */
	public static void addUser(String name, String userName, String password, String dateOfBirth, String schoolInfo){
		if (!takenUserName(userName)){
			getList_users().add(new User(getGivingUserID() ,name, userName, password, dateOfBirth, schoolInfo));
			setGivingUserID();
			System.out.println(name + " has been successfully added.");
		}
	}
	/**
	 * removing user using her/him id
	 * @param userID	ID of user who will be removed
	 */
	public static void removeUser(int userID){
		if (inUser(userID)){
			int removingIndex = 0;
			for(User i : getList_users()){
				if (i.getUserId() != userID){
					removingIndex += 1;
				}
				else
					break;
			}
			getList_users().remove(removingIndex);
			System.out.println("User has been successfully removed.");
		}
		else
			System.out.println("No such user!");
	}
	/**
	 * to login to the system by users with providing user name and password
	 * @param userName	name of user who want to sign in
	 * @param password	password of user who want to sign in
	 * @return false/true
	 */
	public static boolean userSignIn(String userName, String password){
		for (User i : getList_users()){
			if (i.getUserName().equals(userName) && i.getPassword().equals(password))
				return true;
		}
		
		return false;
	}
	/**
	 * system functionality - showing users' posts
	 * @param userName	owner of posts which is wanted to show
	 */
	public static void displayUserPosts(String userName){
		if(inUser(userName)){
			User postUser = findUser(userName);
			System.out.println("**************\n" + userName + "’s Posts\n**************");
			for (Post i : postUser.getListPosts())
				System.out.println(i.toString() + "\n----------------------");
		}
		else
			System.out.println("No such user!");
	}
	/**
	 * initially adding user from users.txt
	 * @param inputUsersTXT	list which contains all lines in users.txt
	 */
	public static void addInitialUsers(ArrayList<ArrayList<String>> inputUsersTXT){
		for(ArrayList<String> i : inputUsersTXT){
			if (!takenUserName(i.get(1).toString())){
				getList_users().add(new User(getGivingUserID(), i.get(0).toString() ,i.get(1).toString(), i.get(2).toString(), i.get(3).toString(), i.get(4).toString()));
				setGivingUserID();
			}
		}
	}
	/**
	 * finding user object using his/her user name
	 * @param userName	user name
	 * @return	a User object
	 */
	public static User findUser(String userName){
		for(User i : getList_users()){
			if (i.getUserName().equals(userName)){
				return i;
			}
		}
		return new User(-1,"","","","0/0/0","");
	}
	/**
	 * controlling if user is exist or not using user id
	 * @param userID	id which is taken
	 * @return false/true
	 */
	public static boolean inUser(int userID){
		for (User i : getList_users()){
			if (i.getUserId() == userID)
				return true;
		}
		return false;
	}
	//Overload
	/**
	 * controlling if user is exist or not using user name
	 * @param userName	name which is taken
	 * @return false/true
	 */
	public static boolean inUser(String userName){
		for (User i : getList_users()){
			if (i.getUserName().equals(userName))
				return true;
		}
		return false;
	}
	/**
	 * controlling if a user name is taken or not.
	 * @param userName	controlling user name
	 * @return	true/false	user name is already taken or not
	 */
	public static boolean takenUserName(String userName){
		for (User i : getList_users()){
			if (i.getUserName().equals(userName)){
				System.out.println(userName + " is already taken, please write another user name!");
				return true;
			}
		}
		return false;
	}
	
	//setter and getter methods
	public static ArrayList<User> getList_users() {
		return UserCollection.list_users;
	}

	public void setList_users(ArrayList<User> list_users) {
		UserCollection.list_users = list_users;
	}

	public static int getGivingUserID() {
		return givingUserID;
	}
	/**
	 * setting givenUserID is adding 1 to value givingUserID; so, method will not take any parameter
	 */
	public static void setGivingUserID() {
		UserCollection.givingUserID += 1;
	}
	
}
