package PackageItems;
/**
 * an object which is an item
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class Perfume extends Cosmetic{
	
	private String lastDuration;
	/**
	 * class constructor
	 * @param cost	item's price
	 * @param manufacterer	item's manufacterer
	 * @param brand	item's brand
	 * @param isOrganic	a feature about item about item is organic or not
	 * @param exprationYear	item's expression year
	 * @param weight	item's weight
	 * @param lastingDuration	item's last duration 
	 * @param ID	item's id
	 */
	public Perfume(String cost, String manufacterer, String brand, String isOrganic, String exprationYear, String weight, String lastingDuration, String ID) {
		super.setItemId(Integer.parseInt(ID));
		super.setCost(Float.parseFloat(cost));
		super.setManufacterer(manufacterer);
		super.setBrand(brand);
		super.setOrganic((Integer.parseInt(isOrganic) == 1));
		super.setExprirationDate(exprationYear);
		super.setWeight(Float.parseFloat(weight));
		this.lastDuration = lastingDuration;
	}
	/**
	 * showing item perfume information
	 */
	public void displayPerfume(){
		System.out.println("Type: Perfume");
		System.out.println("Item ID : " + super.getItemId());
		System.out.println("Price : " + super.getCost() + " $");
		System.out.println("Manufacterer: " + super.getManufacterer());
		System.out.println("Brand: " + super.getBrand());
		System.out.println("Organic: " + super.boolToString(isOrganic()));
		System.out.println("Expiration Date: " + super.getExprirationDate());
		System.out.println("Weight: " + (int)super.getWeight() + " g.");
		System.out.println("lasting Duration: " + this.getLastDuration() + " min.");
	}

	public String getLastDuration() {
		return lastDuration;
	}

	public void setLastDuration(String lastDuration) {
		this.lastDuration = lastDuration;
	}
	
}