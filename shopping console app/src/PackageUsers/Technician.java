package PackageUsers;
/**
 * a class which stands for technician
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
import PackageItems.*;

public class Technician extends Employee{
	private boolean isSenior;
	
	public boolean isSenior() {
		return this.isSenior;
	}

	public void setSenior(boolean isSenior) {
		this.isSenior = isSenior;
	}
	/**
	 * class constructor
	 */
	public Technician(){
		
	}
	/**
	 * class constructor
	 * @param name	technician name
	 * @param email	technician e-mail
	 * @param dateOfBirth	technician birthday
	 * @param salary	technician salary
	 * @param isSenior	technician's feature
	 */
	public Technician(String name, String email, String dateOfBirth, String salary, boolean isSenior) {
		super.setUserName(name);
		super.setUserEmail(email);
		super.setUserDateOfBirth(dateOfBirth);
		super.setSalary(Float.parseFloat(salary));
		this.isSenior = isSenior;
	}
	/**
	 * displaying user's information
	 */
	public void displayUser(){
		System.out.println("----------- Technician info -----------");
		System.out.println("Admin name : " + super.getUserName());
		System.out.println("Admin e-mail : " + super.getUserEmail());
		System.out.println("Admin date of birth : " + super.getUserDateOfBirth());
	}
	/**
	 * displaying wanted items
	 * @param ControlName	controlling admin name 
	 * @param itemTypes	items's type
	 */
	public static void displayItemsSoft(String ControlName, String itemTypes){
		String [] softItems = itemTypes.split(":");
		
		if (Admin.isAdmin(ControlName) || Admin.isTech(ControlName)){
			for (int j=0;j<softItems.length;j++){						//length of wanted items's size
				for(Object obj1 : Items.getList_all_items()){						//each object in all items
					if (obj1.getClass().getSimpleName().replace("I", "i").toLowerCase().equals(softItems[j].replace("I", "i").toLowerCase())){
						System.out.println("-----------------------");
						if(obj1.getClass().getSimpleName().replace("I", "i").toLowerCase().equals("book"))
							((Book) obj1).displayBook();
						else if(obj1.getClass().getSimpleName().replace("I", "i").toLowerCase().equals("haircare"))
							((HairCare) obj1).displayHairCare();
						else if(obj1.getClass().getSimpleName().replace("I", "i").toLowerCase().equals("cddvd"))
							((CdDvd) obj1).displayCdDvd();
						else if(obj1.getClass().getSimpleName().replace("I", "i").toLowerCase().equals("desktop"))
							((Desktop) obj1).displayDesktop();
						else if(obj1.getClass().getSimpleName().replace("I", "i").toLowerCase().equals("laptop"))
							((Laptop) obj1).displayLaptop();
						else if(obj1.getClass().getSimpleName().replace("I", "i").toLowerCase().equals("perfume"))
							((Perfume) obj1).displayPerfume();
						else if(obj1.getClass().getSimpleName().replace("I", "i").toLowerCase().equals("skincare"))
							((SkinCare) obj1).displaySkinCare();
						else if(obj1.getClass().getSimpleName().replace("I", "i").toLowerCase().equals("smartphone"))
							((SmartPhone) obj1).displaySmartPhone();
						else if(obj1.getClass().getSimpleName().replace("I", "i").toLowerCase().equals("tablet"))
							((Tablet) obj1).displayTablet();
						else if(obj1.getClass().getSimpleName().replace("I", "i").toLowerCase().equals("tv"))
							((Tv) obj1).displayTv();
					}
				}
			}
		}
		else
			System.out.println("No admin or technician person named " + ControlName + " exists!");
	}
	
}//class
