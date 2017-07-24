package PackageItems;
/**
 * an item category
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class Computer extends Electronic{
	
	private String OS;
	private String CPUType;
	private String RAMCapacity;
	private String HDDCapacity;
	/**
	 * Class constructor
	 */
	public Computer() {
		
	}
	
	public String getOS() {
		return OS;
	}

	public void setOS(String oS) {
		OS = oS;
	}

	public String getCPUType() {
		return CPUType;
	}

	public void setCPUType(String cPUType) {
		CPUType = cPUType;
	}

	public String getRAMCapacity() {
		return RAMCapacity;
	}

	public void setRAMCapacity(String rAMCapacity) {
		RAMCapacity = rAMCapacity;
	}

	public String getHDDCapacity() {
		return HDDCapacity;
	}

	public void setHDDCapacity(String hDDCapacity) {
		HDDCapacity = hDDCapacity;
	}

	
}
