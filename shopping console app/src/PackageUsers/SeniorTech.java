package PackageUsers;
/**
 * a subclass wchich is about a technician is senior or not
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
import PackageItems.Book;
import PackageItems.CdDvd;
import PackageItems.Desktop;
import PackageItems.Items;
import PackageItems.Laptop;
import PackageItems.Perfume;
import PackageItems.SmartPhone;
import PackageItems.Tablet;
import PackageItems.Tv;

public class SeniorTech extends Technician{
	/**
	 * class constructor
	 */
	public SeniorTech(){
		
	}
	/**
	 * displaying orders
	 * @param techName	controlling technician name
	 */
	public static void displayOrders(String techName){
		if(Admin.isTech(techName) && Admin.isSenior(techName)){
			System.out.println("Order History:");
			for(int i=0 ; i < Customer.getOrderHistory().size();i++){
				System.out.println("Order Date: " + Customer.getOrderHistory().get(i).getOrderDate() + 
						"\tCustomer ID: " + Customer.getOrderHistory().get(i).getBuyerId() + 
						"\tTotal Cost: " + Customer.getOrderHistory().get(i).getTotalCost() + 
						"\tNumber of purchased items: " + Customer.getOrderHistory().get(i).getPurchasedItems().size());
			}
		}
		else
			System.out.println("No technician person named " + techName + " exists!");
	}
	/**
	 * adding an item by technician
	 * @param controlName	controlling name who can add an item
	 * @param item	a string which contains adding item informations
	 */
	public static void addItem(String controlName, String item){
		if (Admin.isTech(controlName)){
			String[] arr_item = item.split(":");
			if (arr_item[0].equals("BOOK")){
				Items.getList_book().add(new Book(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6],Integer.toString(Items.getList_all_items().size() + 1)));
				Items.getList_all_items().add(new Book(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6],Integer.toString(Items.getList_all_items().size() + 1)));
			}
			else if (arr_item[0].equals("CDDVD")){
				Items.getList_cdDvd().add(new CdDvd(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5],Integer.toString(Items.getList_all_items().size() + 1)));
				Items.getList_all_items().add(new CdDvd(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5],Integer.toString(Items.getList_all_items().size() + 1)));
			}
			else if (arr_item[0].equals("DESKTOP")){
				Items.getList_desktop().add(new Desktop(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6], arr_item[7], arr_item[8], arr_item[9], arr_item[10],Integer.toString(Items.getList_all_items().size() + 1)));
				Items.getList_all_items().add(new Desktop(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6], arr_item[7], arr_item[8], arr_item[9], arr_item[10],Integer.toString(Items.getList_all_items().size() + 1)));
			}
			else if (arr_item[0].equals("LAPTOP")){
				Items.getList_laptop().add(new Laptop(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6], arr_item[7], arr_item[8], arr_item[9], arr_item[10],Integer.toString(Items.getList_all_items().size() + 1)));
				Items.getList_all_items().add(new Laptop(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6], arr_item[7], arr_item[8], arr_item[9], arr_item[10],Integer.toString(Items.getList_all_items().size() + 1)));
			}
			else if (arr_item[0].equals("PERFUME")){
				Items.getList_perfume().add(new Perfume(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6], arr_item[7],Integer.toString(Items.getList_all_items().size() + 1)));
				Items.getList_all_items().add(new Perfume(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6], arr_item[7],Integer.toString(Items.getList_all_items().size() + 1)));
			}
			else if (arr_item[0].equals("SMARTPHONE")){
				Items.getList_smartPhone().add(new SmartPhone(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6],Integer.toString(Items.getList_all_items().size() + 1)));
				Items.getList_all_items().add(new SmartPhone(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6],Integer.toString(Items.getList_all_items().size() + 1)));
			}
			else if (arr_item[0].equals("TABLET")){
				Items.getList_tablet().add(new Tablet(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6], arr_item[7], arr_item[8], arr_item[9], arr_item[10],Integer.toString(Items.getList_all_items().size() + 1)));
				Items.getList_all_items().add(new Tablet(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6], arr_item[7], arr_item[8], arr_item[9], arr_item[10],Integer.toString(Items.getList_all_items().size() + 1)));
			}
			else if (arr_item[0].equals("TV")){
				Items.getList_tv().add(new Tv(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6],Integer.toString(Items.getList_all_items().size() + 1)));
				Items.getList_all_items().add(new Tv(arr_item[1], arr_item[2], arr_item[3], arr_item[4], arr_item[5], arr_item[6],Integer.toString(Items.getList_all_items().size() + 1)));
			}
			else
				System.out.println("No item type " + arr_item[0] + " found");
		}
		else
			System.out.println("No technician person named " + controlName + " exists!");
		
	}
	
}//class
