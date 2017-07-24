package PackageItems;
/**
 * an item category
 * @author Ali Yunus Emre OZKOSE
 * @version java1.8
 */
public class Electronic extends Items{
	
	private float maxVoltage;
	private float maxPower;
	/**
	 * class constructor
	 */
	public Electronic() {
	
	}
	
	public float getMaxVoltage() {
		return maxVoltage;
	}

	public void setMaxVoltage(float maxVoltage) {
		this.maxVoltage = maxVoltage;
	}

	public float getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(float maxPower) {
		this.maxPower = maxPower;
	}

	

}
