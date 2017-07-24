package Main;
import java.util.UUID;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
/**
 * an object class stands for post
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public abstract class Post implements PostInterface{
	
	private UUID postID;
	private String postText;
	private Date postDate;
	Collection<String> taggedFriends = new ArrayList<String>();
	protected Location postlocation = new Location(0, 0);
	/**
	 * class constructor
	 * @param text	post's text
	 * @param longitude	post's location information about longitude
	 * @param latitude	post's location information about latitude
	 * @param taggedFriends	tagged friends who are tagged by users
	 */
	public Post(String text, String longitude, String latitude, ArrayList<String> taggedFriends) {
		postlocation.setLongitude(Double.parseDouble(longitude));
		postlocation.setLatitude(Double.parseDouble(latitude));
		setPostID(UUID.randomUUID());
		setPostText(text);
		Date localDate = new Date();
		setPostDate(localDate);
		setTaggedFriends(taggedFriends);
		
	}
	
	@Override
	public String toString(){
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return getPostText() + 
				"\nDate: " + df.format(getPostDate()) + 
			   "\nLocation: " + postlocation.getLongitude() + ", " + postlocation.getLatitude();
	}
	//from PostInterface
	@Override
	public void setPostText(String postText) {
		this.postText = postText;
	}
	@Override
	public String getPostText(){
		return postText;
	}
	@Override
	public UUID getPostID(){
		return postID;
	}
	@Override
	public Date getPostDate(){
		return postDate;
	}
	
	//setter and getter methods
	public void setPostID(UUID postID) {
		this.postID = postID;
	}

	public void setPostDate(Date localDate) {
		this.postDate = localDate;
	}

	public Collection<String> getTaggedFriends() {
		return taggedFriends;
	}

	public void setTaggedFriends(ArrayList<String> taggedFriends) {
		this.taggedFriends = taggedFriends;
	}

}
