package PackageItems;
/**
 * an object which is an item
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class SkinCare extends Cosmetic{
	
	private boolean babySensitive;
	/**
	 * class constructor
	 * @param cost	item's price
	 * @param manufacterer	item's manufacterer
	 * @param brand	item's brand
	 * @param isOrganic	a feature about item about item is organic or not
	 * @param exprationYear	item's expression year
	 * @param weight	item's weight
	 * @param babySensitive	a feature about item about item has baby sensitive or not
	 * @param ID	item's id
	 */
	public SkinCare(String cost, String manufacterer, String brand, String isOrganic, String exprationYear, String weight, String babySensitive, String ID) {
		super.setItemId(Integer.parseInt(ID));
		super.setCost(Float.parseFloat(cost));
		super.setManufacterer(manufacterer);
		super.setBrand(brand);
		super.setOrganic((Integer.parseInt(isOrganic) == 1));
		super.setExprirationDate(exprationYear);
		super.setWeight(Float.parseFloat(weight));
		this.babySensitive = (Integer.parseInt(babySensitive) == 1);
	}
	/**
	 * showing information about item
	 */
	public void displaySkinCare(){
		System.out.println("Type: SkinCare");
		System.out.println("Item ID: " + super.getItemId());
		System.out.println("Price: " + super.getCost() + " $");
		System.out.println("Manufacterer: " + super.getManufacterer());
		System.out.println("Brand: " + super.getBrand());
		System.out.println("Organic: " + super.boolToString(isOrganic()));
		System.out.println("Expiration Date: " + super.getExprirationDate());
		System.out.println("Weight: " + (int)super.getWeight() + " g.");
		System.out.println("Baby Sensitive: " + super.boolToString(isBabySensitive()));
	}

	public boolean isBabySensitive() {
		return babySensitive;
	}

	public void setBabySensitive(boolean babySensitive) {
		this.babySensitive = babySensitive;
	}
	
}
