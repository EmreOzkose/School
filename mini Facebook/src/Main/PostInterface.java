package Main;
import java.util.UUID;
import java.util.Date;
/**
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public interface PostInterface {
	
	public void setPostText(String postText);
	public String getPostText();
	public UUID getPostID();
	public Date getPostDate();	
}
