package PackageUsers;
/**
 * a class which stands for employee
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
import PackageItems.Book;
import PackageItems.CdDvd;
import PackageItems.Desktop;
import PackageItems.HairCare;
import PackageItems.Items;
import PackageItems.Laptop;
import PackageItems.Perfume;
import PackageItems.SkinCare;
import PackageItems.SmartPhone;
import PackageItems.Tablet;
import PackageItems.Tv;

public class Employee extends Users{
	private float salary ;
	/**
	 * class constructor
	 */
	public Employee() {
		
	}

	public float getSalary() {
		return salary;
	}
	
	public void setSalary(float salary) {
		this.salary = salary;
	}
	/**
	 * displaying VIP customer's informations
	 * @param name
	 */
	public static void displayVIPPersons(String name){
		if(Admin.isAdmin(name) || Admin.isTech(name)){
			for(int i=0;i<Admin.getList_customer().size();i++){
				if (Admin.getList_customer().get(i).getCustomerStatu().equals("GOLDEN"))
					Admin.getList_customer().get(i).displayUser();
			}
		}
		else
			System.out.println("No admin or technician person named " + name + " exists!");
	}
	/**
	 * displaying item's stock
	 * @param ControlName	controlling if the admin is exist or not
	 */
	public static void displayItemsLowOnStock(String ControlName){
		if (Admin.isAdmin(ControlName) || Admin.isTech(ControlName)){
			String [] arr1 = {"Book","CDDVD","Desktop","Laptop","Tablet","TV","SmartPhone","HairCare","Perfume","SkinCare"};
			Integer[] arr2 ={0,0,0,0,0,0,0,0,0,0};
			for(int i=0 ; i < Items.getList_all_items().size();i++){
				for (int j = 0;j<arr1.length;j++){
					if (! Items.inBuyedItems(i+1) && Items.getList_all_items().get(i).getClass().getSimpleName().toLowerCase().equals(arr1[j].toLowerCase()))
						arr2[j] ++;
				}
			}
			for (int i=0;i < arr1.length;i++){
				System.out.println(arr1[i] + ": " + arr2[i]);
			}
		}
		else{
			System.out.println("No admin or technician person named " + ControlName + " exists!");
		}
	}
	/**
	 * displaying item's stock with limit
	 * @param ControlName
	 * @param limit
	 */
	public static void displayItemsLowOnStock(String ControlName, int limit){
		if (Admin.isAdmin(ControlName) || Admin.isTech(ControlName)){
			String [] arr1 = {"Book","CDDVD","Desktop","Laptop","Tablet","TV","SmartPhone","HairCare","Perfume","SkinCare"};
			Integer[] arr2 ={0,0,0,0,0,0,0,0,0,0};
			for(int i=0 ; i < Items.getList_all_items().size();i++){
				for (int j = 0;j<arr1.length;j++){
					if (! Items.inBuyedItems(i+1) && Items.getList_all_items().get(i).getClass().getSimpleName().toLowerCase().equals(arr1[j].toLowerCase()))
						arr2[j] ++;
				}
			}
			for (int i=0;i < arr1.length;i++){
				if (arr2[i] <= limit)
					System.out.println(arr1[i] + ": " + arr2[i]);
			}
		}
		else{
			System.out.println("No admin or technician person named " + ControlName + " exists!");
		}
	}
	/**
	 * displaying all items
	 */
	public static void displayItems(){
		for(int i=0;i<Items.getList_all_items().size();i++){
			System.out.println("-----------------------");
			if (Items.getList_all_items().get(i).getClass().getSimpleName().equals("Book"))
				((Book)Items.getList_book().get(i)).displayBook();
			else if (Items.getList_all_items().get(i).getClass().getSimpleName().equals("CdDvd"))
				((CdDvd) Items.getList_all_items().get(i)).displayCdDvd();
			else if (Items.getList_all_items().get(i).getClass().getSimpleName().equals("Desktop"))
				((Desktop) Items.getList_all_items().get(i)).displayDesktop();
			else if (Items.getList_all_items().get(i).getClass().getSimpleName().equals("Laptop"))
				((Laptop) Items.getList_all_items().get(i)).displayLaptop();
			else if (Items.getList_all_items().get(i).getClass().getSimpleName().equals("HairCare"))
				((HairCare) Items.getList_all_items().get(i)).displayHairCare();
			else if (Items.getList_all_items().get(i).getClass().getSimpleName().equals("Perfume"))
				((Perfume) Items.getList_all_items().get(i)).displayPerfume();
			else if (Items.getList_all_items().get(i).getClass().getSimpleName().equals("SkinCare"))
				((SkinCare) Items.getList_all_items().get(i)).displaySkinCare();
			else if (Items.getList_all_items().get(i).getClass().getSimpleName().equals("SmartPhone"))
				((SmartPhone) Items.getList_all_items().get(i)).displaySmartPhone();
			else if (Items.getList_all_items().get(i).getClass().getSimpleName().equals("Tv"))
				((Tv) Items.getList_all_items().get(i)).displayTv();
			else if (Items.getList_all_items().get(i).getClass().getSimpleName().equals("Tablet"))
				((Tablet) Items.getList_all_items().get(i)).displayTablet();
		}
	}
	
}//class
