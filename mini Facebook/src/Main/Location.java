package Main;
/**
 * class which is used for location informations
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class Location {
	
	private double longitude;
	private double latitude;
	/**
	 * class constructor
	 * @param longitude
	 * @param latitude
	 */
	public Location(double longitude, double latitude) {
		this.longitude  = longitude;
		this.latitude = latitude;
	}
	
	//setter and getter methods
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
