package PackageItems;
/**
 * an object which is an item
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class CdDvd extends OfficeSupplies{
	
	private String composerName;
	private String song;
	
	/**
	 * Class constructor
	 * @param cost	item's price
	 * @param releaseDate
	 * @param coverTitle
	 * @param composerName
	 * @param song
	 * @param ID
	 */
	public CdDvd(String cost, String releaseDate, String coverTitle, String composerName, String song, String ID) {
		super.setItemId(Integer.parseInt(ID));
		super.setCost(Float.parseFloat(cost));
		super.setReleaseDate(releaseDate);
		super.setCoverTitle(coverTitle);
		this.composerName = composerName;
		this.song = song;
	}
	/**
	 * showing information about item
	 */
	public void displayCdDvd(){
		System.out.println("Type: CDDVD");
		System.out.println("Item ID: " + super.getItemId());
		System.out.println("Price: " + super.getCost() + "  $");
		System.out.println("Release Date: " + super.getReleaseDate());
		System.out.println("Title: " + super.getCoverTitle());
		System.out.println("songs: " + this.song);
		System.out.println("Composer: " + this.composerName);
	}
	
}
