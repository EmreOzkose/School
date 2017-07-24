import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 * an object class stands for image-post
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class ImagePost extends TextPost{
	
	private String filePath;
	private String [] resolution;
	/**
	 * class constructor
	 * @param text	post's text
	 * @param longitude	post's location information about longitude
	 * @param latitude	post's location information about latitude
	 * @param taggedFriends	tagged friends who are tagged by users
	 * @param filepath	post's file path
	 * @param resolution	image's resolution
	 */
	public ImagePost(String text, String longitude, String latitude, ArrayList<String> taggedFriends, String filepath, String resolution) {
		super(text, longitude, latitude, taggedFriends);
		setFilePath(filepath);
		setResolution(resolution.split("x"));
	}
	
	public String toString(){
		String taggedFriendsText = "Friends tagged in this post: ";
		for(int i=0; i < super.getTaggedFriends().size();i++){
			if (i != super.getTaggedFriends().size()-1)
				taggedFriendsText += ((ArrayList<String>) super.getTaggedFriends()).get(i) + ", ";
			else
				taggedFriendsText += ((ArrayList<String>) super.getTaggedFriends()).get(i);
		}
		
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		
		if(getTaggedFriends().isEmpty()){
			return getPostText() + 
					"\nDate: " + df.format(getPostDate()) + 
				    "\nLocation: " + postlocation.getLongitude() + ", " + postlocation.getLatitude() +
				    "\nImage: " + getFilePath() +
					"\nImage resolution: " + getResolution()[0] + "x" + getResolution()[1];
		}
		else{
			return getPostText() + 
					"\nDate: " + df.format(getPostDate()) + 
				    "\nLocation: " + postlocation.getLongitude() + ", " + postlocation.getLatitude() +
				    "\nImage: " + getFilePath() +
					"\nImage resolution: " + getResolution()[0] + "x" + getResolution()[1] +
				    "\n" + taggedFriendsText;
		}
	}
	
	//setter and getter methods
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String [] getResolution() {
		return resolution;
	}
	public void setResolution(String[] strings) {
		this.resolution = strings;
	}

}
