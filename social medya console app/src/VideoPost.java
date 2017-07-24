import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 * an object class stands for video-post
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class VideoPost extends TextPost{
	
	private String filePath;
	private double videoDuration;
	/**
	 * class constructor
	 * @param text	post's text
	 * @param longitude	post's location information about longitude
	 * @param latitude	post's location information about latitude
	 * @param taggedFriends	tagged friends who are tagged by users
	 * @param filePath	post's file path
	 * @param videoDuration	video's duration
	 */
	public VideoPost(String text, String longitude, String latitude, ArrayList<String> taggedFriends, String filePath, String videoDuration) {
		super(text, longitude, latitude, taggedFriends);
		this.filePath = filePath;
		this.videoDuration = Double.parseDouble(videoDuration);
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
				    "\nVideo: " + getFilePath() +
					"\nVideo duration: " + getVideoDuration() + " minutes";
		}
		else{
			return getPostText() + 
					"\nDate: " + df.format(getPostDate()) + 
				    "\nLocation: " + postlocation.getLongitude() + ", " + postlocation.getLatitude() +
				    "\nVideo: " + getFilePath() +
					"\nVideo duration: " + getVideoDuration() + " minutes" +
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

	public double getVideoDuration() {
		return videoDuration;
	}

	public void setVideoDuration(double videoDuration) {
		this.videoDuration = videoDuration;
	}

}
