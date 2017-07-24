package PackageItems;
/**
 * an object which is an item
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class Laptop extends Computer{
	
	private boolean HDDMSupport;
	/**
	 * class constructor
	 * @param cost	item's price
	 * @param manufacterer	item's manufacterer
	 * @param brand	item's brand
	 * @param maxVolt	maximum volt value which item can be taken
	 * @param maxPower	maximum power value which item can be taken
	 * @param OS	item's operating system
	 * @param CPUType	item's CPU type
	 * @param RAMCapacity	item's ram capacity
	 * @param HDDCapacity	item's hdd capacity
	 * @param HDDMSupport	a feature about if an item has HDDM support or not
	 * @param ID	item's id
	 */
	public Laptop(String cost, String manufacterer, String brand, String maxVolt, String maxPower, String OS, String CPUType, String RAMCapacity, String HDDCapacity, String HDDMSupport, String ID) {
		super.setItemId(Integer.parseInt(ID));
		super.setCost(Float.parseFloat(cost));
		super.setManufacterer(manufacterer);
		super.setBrand(brand);
		super.setMaxVoltage(Float.parseFloat(maxVolt));
		super.setMaxPower(Float.parseFloat(maxPower));
		super.setOS(OS);
		super.setCPUType(CPUType);
		super.setRAMCapacity(RAMCapacity);
		super.setHDDCapacity(HDDCapacity);
		this.HDDMSupport = Boolean.valueOf(HDDMSupport);
	}
	/**
	 * showing item's all information
	 */
	public void displayLaptop(){
		System.out.println("Type: Laptop");
		System.out.println("Item ID: " + super.getItemId());
		System.out.println("Price: " + super.getCost() + " $");
		System.out.println("Manufacterer: " + super.getManufacterer());
		System.out.println("Brand: " + super.getBrand());
		System.out.println("Max Volt: " + (int)super.getMaxVoltage() + " V.");
		System.out.println("Max Watt: " + (int)super.getMaxPower() + " W.");
		System.out.println("Operating System: " + super.getOS());
		System.out.println("CPU Type: " + super.getCPUType());
		System.out.println("RAM Capacity: " + super.getRAMCapacity() + " GB.");
		System.out.println("HDD Capacity: " + super.getHDDCapacity() + " GB.");
		System.out.println("HDDM Support: " + boolToString(isHDDMSupport()));
	}

	public boolean isHDDMSupport() {
		return HDDMSupport;
	}

	public void setHDDMSupport(boolean hDDMSupport) {
		HDDMSupport = hDDMSupport;
	}

}
