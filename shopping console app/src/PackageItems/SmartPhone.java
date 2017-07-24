package PackageItems;
/**
 * an object which is an item
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class SmartPhone extends Electronic{
	
	private String screenType;
	/**
	 * class constructor
	 * @param cost	item's price
	 * @param manufacterer	item's manufacterer
	 * @param brand	item's brand
	 * @param maxVolt	maximum volt value which item can be taken
	 * @param maxPower	maximum power value which item can be taken
	 * @param screenType	item's screen type
	 * @param ID	item's id
	 */
	public SmartPhone(String cost, String manufacterer, String brand, String maxVolt, String maxPower, String screenType, String ID) {
		super.setItemId(Integer.parseInt(ID));
		super.setCost(Float.parseFloat(cost));
		super.setManufacterer(manufacterer);
		super.setBrand(brand);
		super.setMaxVoltage(Float.parseFloat(maxVolt));
		super.setMaxPower(Float.parseFloat(maxPower));
		this.screenType = screenType;
	}
	
	/**
	 * showing informations about item
	 */
	public void displaySmartPhone(){
		System.out.println("Type: SmartPhone");
		System.out.println("Item ID: " + super.getItemId());
		System.out.println("Price: " + super.getCost() + " $");
		System.out.println("Manufacterer: " + super.getManufacterer());
		System.out.println("Brand: " + super.getBrand());
		System.out.println("Max Volt: " + (int)super.getMaxVoltage() + " V.");
		System.out.println("Max Watt: " + (int)super.getMaxPower() + " W.");
		System.out.println("Screen Type: " + this.screenType);
	}
	
	
}
