package PackageItems;
/**
 * an item category
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class Cosmetic extends Items{
	
	private String exprirationDate;
	private float weight;
	private boolean organic;
	/**
	 * Class constructor
	 */
	public Cosmetic() {
		
	}
	
	public String getExprirationDate() {
		return exprirationDate;
	}

	public void setExprirationDate(String exprirationDate) {
		this.exprirationDate = exprirationDate;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public boolean isOrganic() {
		return organic;
	}

	public void setOrganic(boolean organic) {
		this.organic = organic;
	}
}
