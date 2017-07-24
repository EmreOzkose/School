package PackageItems;
/**
 * a category item
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class OfficeSupplies extends Items{
	
	private String releaseDate;
	private String coverTitle;
	/**
	 * class  constructor
	 */
	public OfficeSupplies() {
		
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCoverTitle() {
		return coverTitle;
	}

	public void setCoverTitle(String coverTitle) {
		this.coverTitle = coverTitle;
	}

	

}
