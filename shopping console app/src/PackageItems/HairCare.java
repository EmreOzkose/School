package PackageItems;
/**
 * an object which is an item
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class HairCare extends Cosmetic{
	
	private boolean medicated;
	/**
	 * class constructor
	 * @param cost	item's price
	 * @param manufacterer	item's manufacterer
	 * @param brand	item's brand
	 * @param isOrganic	a feature about item about item is organic or not
	 * @param exprationYear	item's expression year
	 * @param weight	item's weight
	 * @param isMedicated	a feature about item about item is medicated or not
	 * @param ID	item's id
	 */
	public HairCare(String cost, String manufacterer, String brand, String isOrganic, String exprationYear, String weight, String isMedicated, String ID) {
		super.setItemId(Integer.parseInt(ID));
		super.setCost(Float.parseFloat(cost));
		super.setManufacterer(manufacterer);
		super.setBrand(brand);
		super.setOrganic((Integer.parseInt(isOrganic) == 1));
		super.setExprirationDate(exprationYear);
		super.setWeight(Float.parseFloat(weight));
		this.medicated = (Integer.parseInt(isMedicated) == 1);
	}
	/**
	 * showing all information about hair care item
	 */
	public void displayHairCare(){
		System.out.println("Type: HairCare");
		System.out.println("Item ID: " + super.getItemId());
		System.out.println("Price: " + super.getCost() + " $");
		System.out.println("Manufacterer: " + super.getManufacterer());
		System.out.println("Brand: " + super.getBrand());
		System.out.println("Organic: " + super.boolToString(isOrganic()));
		System.out.println("Expiration Date: " + super.getExprirationDate());
		System.out.println("Weight: " + (int)super.getWeight() + " g.");
		System.out.println("Medicated: " + super.boolToString(isMedicated()));
	}

	public boolean isMedicated() {
		return medicated;
	}

	public void setMedicated(boolean medicated) {
		this.medicated = medicated;
	}
	
	
}
