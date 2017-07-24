import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
/**
 * main class of assignment
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class Main {
	public static void main(String[] args) {
		ArrayList<ArrayList<String>> inputUsersTXT = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> inputCommandsTXT = new ArrayList<ArrayList<String>>();
		
		try {
			 for (int i=0;i<2;i++){
				 Scanner scanner = new Scanner(new File(args[i]), "ISO-8859-1" );
				 while(scanner.hasNextLine()){
					 String line = scanner.nextLine();
					 Collection  <String> row = new ArrayList  <String> ();
					 
					 for (String url : line.split("\t")){		
						 row.add(url);
					 }
					
					 if (i == 0)
						 inputUsersTXT.add((ArrayList<String>) row);
					 else if (i == 1)
						 inputCommandsTXT.add((ArrayList<String>) row);
				 }
				 scanner.close();
			 }
		 }
		 catch (FileNotFoundException ex) {
			 System.out.println("No File Found!");
			 return;
		 }//try-catch
		
		UserCollection.addInitialUsers(inputUsersTXT);
		
		for(int i = 0 ; i < inputCommandsTXT.size();i++){
			ArrayList<String> arrL = inputCommandsTXT.get(i);
			
			System.out.println("-----------------------");
			System.out.print("Command:");
			for(String k : arrL)
				System.out.print(k + "\t");
			System.out.println();
			
			if(arrL.get(0).equals("SIGNIN")){
				User currentUser = UserCollection.findUser(arrL.get(1));
				
				if(currentUser.getUserId() == -1)
					System.out.println("No such user!");
				else if (currentUser.getUserId() != -1 && UserCollection.userSignIn(arrL.get(1).toString(), arrL.get(2).toString())){
					currentUser.signIn(arrL.get(1).toString(), arrL.get(2).toString());
					for (int j = i+1 ; j <inputCommandsTXT.size();j++){
						ArrayList<String> arrL2 = inputCommandsTXT.get(j);
						i++;
						
						System.out.println("-----------------------");
						System.out.print("Command:");
						for(String l : arrL2)
							System.out.print(l + "\t");
						System.out.println();
						
						////User functionalities
						if(arrL2.get(0).equals("LISTUSERS")){
							currentUser.listUsers();
						}
						else if(arrL2.get(0).equals("UPDATEPROFILE")){
							currentUser.updateProfile(arrL2.get(1).toString(), arrL2.get(2).toString(), arrL2.get(3).toString());
						}
						else if(arrL2.get(0).equals("CHPASS")){
							currentUser.changePass(arrL2.get(1).toString(),arrL2.get(2).toString());
						}
						else if(arrL2.get(0).equals("ADDFRIEND")){
							currentUser.addFriend(arrL2.get(1).toString());
						}
						else if(arrL2.get(0).equals("REMOVEFRIEND")){
							currentUser.removeFriend(arrL2.get(1).toString());
						}
						else if(arrL2.get(0).equals("LISTFRIENDS")){
							currentUser.listFriends();
						}
						else if(arrL2.get(0).equals("BLOCK")){
							currentUser.blockUser(arrL2.get(1).toString());
						}
						else if(arrL2.get(0).equals("UNBLOCK")){
							currentUser.unBlockUser(arrL2.get(1).toString());
						}
						else if(arrL2.get(0).equals("SHOWBLOCKEDFRIENDS")){
							currentUser.listBlockedFriends();
						}
						else if(arrL2.get(0).equals("SHOWBLOCKEDUSERS")){
							currentUser.listBlockedUsers();
						}
						else if(arrL2.get(0).equals("ADDPOST-TEXT")){
							currentUser.addTextPost(arrL2.get(1).toString(), arrL2.get(2).toString(), arrL2.get(3).toString(), arrL2.get(4).toString());
						}
						else if(arrL2.get(0).equals("ADDPOST-IMAGE")){
							currentUser.addImagePost(arrL2.get(1).toString(), arrL2.get(2).toString(), arrL2.get(3).toString(), arrL2.get(4).toString(), arrL2.get(5).toString(), arrL2.get(6).toString());
						}
						else if(arrL2.get(0).equals("ADDPOST-VIDEO")){
							currentUser.addVideoPost(arrL2.get(1).toString(), arrL2.get(2).toString(), arrL2.get(3).toString(), arrL2.get(4).toString(), arrL2.get(5).toString(), arrL2.get(6).toString());
						}
						else if(arrL2.get(0).equals("REMOVELASTPOST")){
							currentUser.removeLastPost();
						}
						else if(arrL2.get(0).equals("SIGNIN")){
							System.out.println("Hey! You can not sign in until " + currentUser.getName() + " is out");
						}
						else if(arrL2.get(0).equals("SIGNOUT")){
							currentUser.signOut();
							break;
						}
						
						//System functionalities
						else if(arrL2.get(0).equals("SHOWPOSTS")){
							UserCollection.displayUserPosts(arrL.get(1).toString());
						}
						else if (arrL.get(0).equals("ADDUSER")){
							UserCollection.addUser(arrL.get(1).toString(), arrL.get(2).toString(), arrL.get(3).toString(), arrL.get(4).toString(), arrL.get(5).toString());
						}
						else if(arrL.get(0).equals("REMOVEUSER")){
							UserCollection.removeUser(Integer.parseInt(arrL.get(1)));
						}
						
					}
				}
				
			}
			else if (arrL.get(0).equals("ADDUSER")){
				UserCollection.addUser(arrL.get(1).toString(), arrL.get(2).toString(), arrL.get(3).toString(), arrL.get(4).toString(), arrL.get(5).toString());
			}
			else if(arrL.get(0).equals("REMOVEUSER")){
				UserCollection.removeUser(Integer.parseInt(arrL.get(1)));
			}
			else if(arrL.get(0).equals("SHOWPOSTS")){
				UserCollection.displayUserPosts(arrL.get(1).toString());
			}
			else
				System.out.println("Error: Please sign in and try again.");
			
		}
		
	}//main
}//class
