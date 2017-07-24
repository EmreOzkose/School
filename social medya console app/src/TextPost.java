import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 * an object class stands for text-post
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class TextPost extends Post{
	/**
	 * class constructor
	 * @param text	post's text
	 * @param longitude	post's location information about longitude
	 * @param latitude	post's location information about latitude
	 * @param taggedFriends	tagged friends who are tagged by users
	 */
	public TextPost(String text, String longitude, String latitude, ArrayList<String> taggedFriends) {
		super(text, longitude, latitude, taggedFriends);
	}
	
	@Override
	public String toString(){
		String taggedFriendsText = "Friends tagged in this post: ";
		for(int i=0; i < super.getTaggedFriends().size();i++){
			if (i != super.getTaggedFriends().size()-1)
				taggedFriendsText += ((ArrayList<String>) super.getTaggedFriends()).get(i) + ", ";
			else
				taggedFriendsText += ((ArrayList<String>) super.getTaggedFriends()).get(i);
		}
		
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		
		if (getTaggedFriends().isEmpty()){
			return getPostText() + 
					"\nDate: " +df.format(getPostDate())  + 
				    "\nLocation: " + postlocation.getLongitude() + ", " + postlocation.getLatitude();
		}
		else{
			return getPostText() + 
					"\nDate: " +df.format(getPostDate())  + 
				    "\nLocation: " + postlocation.getLongitude() + ", " + postlocation.getLatitude() +
				    "\n" + taggedFriendsText;
		}
		
	}
}