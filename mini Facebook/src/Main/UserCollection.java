package Main;
import java.util.ArrayList;

public class UserCollection {
	
	static ArrayList<User> list_users = new ArrayList<User>();
	private static int givingUserID = 1;	//to determine userID;
	private static String currentUser;
	/**
	 * initially adding user from users.txt
	 * @param inputUsersTXT	list which contains all lines in users.txt
	 */
	public static void addInitialUsers(ArrayList<ArrayList<String>> inputUsersTXT){
		for(ArrayList<String> i : inputUsersTXT){
			if (!takenUserName(i.get(1).toString())){
				getList_users().add(new User(getGivingUserID(), i.get(0).toString() ,i.get(1).toString(), i.get(2).toString(), i.get(3).toString(), i.get(4).toString(), i.get(5).toString()));
				setGivingUserID();
			}
		}
	}
	
	public static void addInitialCommands1(ArrayList<ArrayList<String>> inputCommandsTXT){
		for(int i=0; i<inputCommandsTXT.size();i++){
			ArrayList<String> arr = inputCommandsTXT.get(i);
			
			if(arr.get(0).equals("ADDUSER")){
				getList_users().add(new User(getGivingUserID(), arr.get(1).toString() ,arr.get(2).toString(), arr.get(3).toString(), arr.get(4).toString(), arr.get(5).toString(), arr.get(6).toString()));
				setGivingUserID();
			}
			else if(arr.get(0).equals("ADDFRIEND")){
				if (inUser(arr.get(1).toString())){
					UserCollection.findUser(arr.get(1).toString()).addFriend(arr.get(2).toString());
				}
			}
			else if(arr.get(0).equals("ADDPOST-TEXT")){
				UserCollection.findUser(arr.get(1).toString()).addTextPost(arr.get(2).toString(), arr.get(3).toString(), arr.get(4).toString(),arr.get(5).toString());
			}
			else if(arr.get(0).equals("ADDPOST-IMAGE")){
				UserCollection.findUser(arr.get(1).toString()).addImagePost(arr.get(2).toString(), arr.get(3).toString(), arr.get(4).toString(), arr.get(5).toString(), arr.get(6).toString(), arr.get(7).toString().split("x")[0],arr.get(7).toString().split("x")[1]);
			}
			else if(arr.get(0).equals("ADDPOST-VIDEO")){
				UserCollection.findUser(arr.get(1).toString()).addVideoPost(arr.get(2).toString(), arr.get(3).toString(), arr.get(4).toString(), arr.get(5).toString(), arr.get(6).toString(), arr.get(7).toString());
			}
			else if(arr.get(0).equals("BLOCKFRIEND")){
				UserCollection.findUser(arr.get(1).toString()).blockFriend((arr.get(2).toString()));
			}
		}
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
	/**
	 * controlling if user is exist or not using user name
	 * @param userName	name which is taken
	 * @return
	 */
	public static boolean inUser(String userName){
		for (User i : getList_users()){
			if (i.getUserName().equals(userName))
				return true;
		}
		return false;
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
		return new User(-1,"","","","0/0/0","","Single");
	}
	//getter and setter methods
	public static int getGivingUserID() {
		return givingUserID;
	}
	/**
	 * setting givenUserID is adding 1 to value givingUserID; so, method will not take any parameter
	 */
	public static void setGivingUserID() {
		UserCollection.givingUserID += 1;
	}
	public static ArrayList<User> getList_users() {
		return list_users;
	}
	public static void setList_users(ArrayList<User> list_users) {
		UserCollection.list_users = list_users;
	}
	public static String getCurrentUser() {
		return currentUser;
	}
	public static void setCurrentUser(String curUser) {
		currentUser = curUser;
	}
}
