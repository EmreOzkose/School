package PackageItems;
/**
 * an object which is an item
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class Tv extends Electronic{
	
	private float screenSize;
	/**
	 * class constructor
	 * @param cost	item's price
	 * @param manufacterer	item's manufacterer
	 * @param brand	item's brand
	 * @param maxVolt	maximum volt value which item can be taken
	 * @param maxPower	maximum power value which item can be taken
	 * @param screenSize	tv's size
	 * @param ID	item's id
	 */
	public Tv(String cost, String manufacterer, String brand, String maxVolt, String maxPower, String screenSize, String ID) {
		super.setItemId(Integer.parseInt(ID));
		super.setCost(Float.parseFloat(cost));
		super.setManufacterer(manufacterer);
		super.setBrand(brand);
		super.setMaxVoltage(Float.parseFloat(maxVolt));
		super.setMaxPower(Float.parseFloat(maxPower));
		this.screenSize = Float.parseFloat(screenSize);
	}
	/**
	 * showing informations about item
	 */
	public void displayTv(){
		System.out.println("Type: Tv");
		System.out.println("Item ID: " + super.getItemId());
		System.out.println("Price: " + super.getCost() + " $");
		System.out.println("Manufacterer: " + super.getManufacterer());
		System.out.println("Brand: " + super.getBrand());
		System.out.println("Max Volt: " + (int)super.getMaxVoltage() + " V.");
		System.out.println("Max Watt: " + (int)super.getMaxPower() + " W.");
		System.out.println("Screen Size: " + (int)this.getScreenSize() + "\"");
	}

	public float getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(float screenSize) {
		this.screenSize = screenSize;
	}

}
