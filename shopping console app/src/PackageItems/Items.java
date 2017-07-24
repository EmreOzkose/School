package PackageItems;
/**
 * a managing class which all items are connected this class with inheritance
 * The main method of assignment
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
import java.util.ArrayList;

public class Items {

	private static ArrayList <Perfume> list_perfume = new ArrayList <Perfume>();
	private static ArrayList <HairCare> list_hairCare = new ArrayList <HairCare>();
	private static ArrayList <SkinCare> list_skinCare = new ArrayList <SkinCare>();
	private static ArrayList <Desktop> list_desktop = new ArrayList <Desktop>();
	private static ArrayList <Laptop> list_laptop = new ArrayList <Laptop>();
	private static ArrayList <Tablet> list_tablet = new ArrayList <Tablet>();
	private static ArrayList <Tv> list_tv = new ArrayList <Tv>();
	private static ArrayList <SmartPhone> list_smartPhone = new ArrayList<SmartPhone>();
	private static ArrayList <Book> list_book = new ArrayList<Book>();
	private static ArrayList <CdDvd> list_cdDvd = new ArrayList<CdDvd>();
	private static ArrayList <Object> list_all_items = new ArrayList <Object> ();
	private static ArrayList <Integer> buyedItemId = new ArrayList <Integer> ();
	
	private int ItemId;
	private float cost;
	private int numStock=0;
	private String manufacterer;
	private String brand;
	/**
	 * class constructor
	 */
	public Items() {
	
	}
	public int getItemId() {
		return ItemId;
	}

	public void setItemId(int itemId) {
		ItemId = itemId;
	}

	public int getNumStock() {
		return numStock;
	}
	
	public float getCost(){
		return this.cost;
	}
	
	public void setCost(float cost){
		this.cost = cost;
	}
	
	public String getManufacterer(){
		return this.manufacterer;
	}

	public void setManufacterer(String manufacterer){
		this.manufacterer = manufacterer;
	}
	
	public String getBrand(){
		return this.brand;
	}
	
	public void setBrand(String brand){
		this.brand = brand;
	}
	
	public static ArrayList<Integer> getBuyedItemId() {
		return buyedItemId;
	}

	public static void setBuyedItemId(ArrayList<Integer> buyedItem) {
		buyedItemId = buyedItem;
	}
	/**
	 * controlling if an item is buyed or not
	 * @param itemId	item's id
	 * @return false/true
	 */
	public static boolean inBuyedItems(int itemId){
		for (int i : Items.getBuyedItemId()){
			if(itemId == i)
				return true;
		}
		return false;
	}
	/**
	 * seperating all items and listing with special names
	 * @param input_item_list	a list which contains all initial items one by one
	 */
	public static void takeInput(ArrayList<?> input_item_list){
		for (int i=0;i<input_item_list.size();i++){
			@SuppressWarnings("unchecked")
			ArrayList <Object> array_itemTXT = (ArrayList <Object>) input_item_list.get(i);
			
			if(array_itemTXT.get(0).equals("PERFUME")){
				list_perfume.add(new Perfume(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),array_itemTXT.get(7).toString(),Integer.toString(list_all_items.size() + 1)));
				list_all_items.add(new Perfume(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),array_itemTXT.get(7).toString(),Integer.toString(list_all_items.size() + 1)));
			}
			else if(array_itemTXT.get(0).equals("HAIRCARE")){
				list_hairCare.add(new HairCare(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),array_itemTXT.get(7).toString(),Integer.toString(list_all_items.size() + 1)));
				list_all_items.add(new HairCare(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),array_itemTXT.get(7).toString(),Integer.toString(list_all_items.size() + 1)));
			}
			else if(array_itemTXT.get(0).equals("SKINCARE")){
				list_skinCare.add(new SkinCare(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),array_itemTXT.get(7).toString(),Integer.toString(list_all_items.size() + 1)));
				list_all_items.add(new SkinCare(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),array_itemTXT.get(7).toString(),Integer.toString(list_all_items.size() + 1)));
			}
			else if(array_itemTXT.get(0).equals("DESKTOP")){
				list_desktop.add(new Desktop(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),array_itemTXT.get(7).toString(),array_itemTXT.get(8).toString(),array_itemTXT.get(9).toString(),array_itemTXT.get(10).toString(),Integer.toString(list_all_items.size() + 1)));
				list_all_items.add(new Desktop(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),array_itemTXT.get(7).toString(),array_itemTXT.get(8).toString(),array_itemTXT.get(9).toString(),array_itemTXT.get(10).toString(),Integer.toString(list_all_items.size() + 1)));
			}
			else if(array_itemTXT.get(0).equals("LAPTOP")){
				list_laptop.add(new Laptop(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),array_itemTXT.get(7).toString(),array_itemTXT.get(8).toString(),array_itemTXT.get(9).toString(),array_itemTXT.get(10).toString(),Integer.toString(list_all_items.size() + 1)));
				list_all_items.add(new Laptop(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),array_itemTXT.get(7).toString(),array_itemTXT.get(8).toString(),array_itemTXT.get(9).toString(),array_itemTXT.get(10).toString(),Integer.toString(list_all_items.size() + 1)));
			}
			else if(array_itemTXT.get(0).equals("TABLET")){
				list_tablet.add(new Tablet(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),array_itemTXT.get(7).toString(),array_itemTXT.get(8).toString(),array_itemTXT.get(9).toString(),array_itemTXT.get(10).toString(),Integer.toString(list_all_items.size() + 1)));
				list_all_items.add(new Tablet(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),array_itemTXT.get(7).toString(),array_itemTXT.get(8).toString(),array_itemTXT.get(9).toString(),array_itemTXT.get(10).toString(),Integer.toString(list_all_items.size() + 1)));
			}
			else if(array_itemTXT.get(0).equals("TV")){
				list_tv.add(new Tv(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),Integer.toString(list_all_items.size() + 1)));
				list_all_items.add(new Tv(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),Integer.toString(list_all_items.size() + 1)));
			}
			else if(array_itemTXT.get(0).equals("SMARTPHONE")){
				list_smartPhone.add(new SmartPhone(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),Integer.toString(list_all_items.size() + 1)));
				list_all_items.add(new SmartPhone(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),Integer.toString(list_all_items.size() + 1)));
			}
			else if(array_itemTXT.get(0).equals("BOOK")){
				list_book.add(new Book(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),Integer.toString(list_all_items.size() + 1)));
				list_all_items.add(new Book(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),array_itemTXT.get(6).toString(),Integer.toString(list_all_items.size() + 1)));
			}
			else if(array_itemTXT.get(0).equals("CDDVD")){
				list_cdDvd.add(new CdDvd(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),Integer.toString(list_all_items.size() + 1)));
				list_all_items.add(new CdDvd(array_itemTXT.get(1).toString(),array_itemTXT.get(2).toString(),array_itemTXT.get(3).toString(),array_itemTXT.get(4).toString(),array_itemTXT.get(5).toString(),Integer.toString(list_all_items.size() + 1)));
			}
		}
	}
	
	public static ArrayList<Object> getList_all_items() {
		return list_all_items;
	}

	public static ArrayList<Laptop> getList_laptop() {
		return list_laptop;
	}

	public static ArrayList<Perfume> getList_perfume() {
		return list_perfume;
	}

	public static ArrayList<HairCare> getList_hairCare() {
		return list_hairCare;
	}

	public static ArrayList<SkinCare> getList_skinCare() {
		return list_skinCare;
	}

	public static ArrayList<Desktop> getList_desktop() {
		return list_desktop;
	}

	public static ArrayList<Tablet> getList_tablet() {
		return list_tablet;
	}

	public static ArrayList<Tv> getList_tv() {
		return list_tv;
	}

	public static ArrayList<SmartPhone> getList_smartPhone() {
		return list_smartPhone;
	}

	public static ArrayList<Book> getList_book() {
		return list_book;
	}

	public static ArrayList<CdDvd> getList_cdDvd() {
		return list_cdDvd;
	}
	
	/**
	 * converting boolean true/false -> String Yes/No
	 * @param zeroOrOne
	 * @return	Yes/No
	 */
	public static String boolToString(boolean zeroOrOne){
		if(zeroOrOne == true){
			return "Yes";
		}
		else
			return "No";
	}
	/**
	 * ensuring if an item is exists before
	 * @param itemId ID
	 * @return false/true
	 */
	public static boolean inItem(int itemId){
		for (int i=0;i<getList_all_items().size();i++){
			if (((Items)getList_all_items().get(i)).getItemId() == itemId){
				return true;
			}
		}
		return false;
	}
}//class
