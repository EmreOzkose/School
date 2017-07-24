package PackageUsers;
/**
 * a class which stands for real customers
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
import java.util.*;

import PackageItems.*;
import PackageShopping.*;

public class Customer extends Users{
	private int customerID;
	private String customerPassword;
	private float customerBalance;
	private static enum Status{CLASSIC,SILVER,GOLDEN};
	private Status customerStatu;

	private ArrayList <Integer> shoppingCart = new ArrayList<Integer>();
	private static ArrayList <Order> orderHistory = new ArrayList<Order>();
	
	/**
	 * class constructor
	 * @param name
	 * @param email
	 * @param dateOfBirth
	 * @param customerBalance
	 * @param password
	 * @param customerId
	 */
	public Customer(String name, String email, String dateOfBirth, String customerBalance, String password, int customerId) {
		super.setUserName(name);
		super.setUserEmail(email);
		super.setUserDateOfBirth(dateOfBirth);
		this.customerBalance = Float.parseFloat(customerBalance);
		this.customerPassword = password;
		this.setCustomerID(customerId);
		this.customerStatu = Status.CLASSIC;
	}
	
	public String getCustomerStatu() {
		return this.customerStatu.toString();
	}

	public void setCustomerStatu(Status status) {
		this.customerStatu = status;
	}
	
	public float getCustomerBalance() {
		return customerBalance;
	}
	
	public void setCustomerBalance(float customerBalance) {
		this.customerBalance = customerBalance;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}
	
	public ArrayList<Integer> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ArrayList<Integer> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public static ArrayList<Order> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(ArrayList<Order> orderHistory) {
		Customer.orderHistory = orderHistory;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public int getCustomerID() {
		return this.customerID;
	}
	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	/**
	 * changing password by customer
	 * @param customerID	customer which want to change her/his password
	 * @param oldPasswd	customer's effective password
	 * @param newPasswd	password which is wanted to change with old password by customer
	 */
	public static void changePassword(int customerID, String oldPasswd, String newPasswd){
		if (inCustomer(customerID)){
			if (Admin.getList_customer().get(customerID-1).getCustomerPassword().equals(oldPasswd)){
				Admin.getList_customer().get(customerID-1).setCustomerPassword(newPasswd);
				System.out.println("The password has been succesfully changed.");
			}
			else
				System.out.println("The given password does not match the current password. Please try again.");
		}
		else
			System.out.println("No customer with ID number " + customerID + " exists!");	
	}
	/**
	 * displaying customer information name/e-mail/birthday/Statu
	 */
	public void displayUser(){
		System.out.println("----------- Customer info -----------");
		System.out.println("Customer name : " + super.getUserName());
		System.out.println("Customer e-mail : " + super.getUserEmail());
		System.out.println("Customer date of birth : " + super.getUserDateOfBirth());
		System.out.println("Customer status : " + this.getCustomerStatu());
	}
	/**
	 * controlling if a customer is exists
	 * @param customerId	customer's (who is being controlling) id
	 * @return	true/false
	 */
	public static boolean inCustomer(int customerId){
		for (int i : Admin.getList_customerID()){
			if (customerId == i)
				return true;
		}
		return false;
	}
	/**
	 * loading money to customer account
	 * @param customerId	customer (who want to load money) id
	 * @param loadAmount	amount of money which customer load
	 */
	public static void loadMoney(int customerId, float loadAmount){
		if(inCustomer(customerId))
			Admin.getList_customer().get(customerId-1).setCustomerBalance(Admin.getList_customer().get(customerId-1).getCustomerBalance() + loadAmount);
		else
			System.out.println("No customer with ID number " + customerId + " exists!");
	}
	/**
	 * a process which is done by customer, adding an item to customer shopping cart
	 * @param customerId	customer which is shopping
	 * @param itemId	added item id
	 */
	public static void addCart(int customerId, int itemId){
		if (inCustomer(customerId)){
			if(Items.inItem(itemId)){
				if(!Items.inBuyedItems(itemId)){
					Admin.getList_customer().get(customerId-1).getShoppingCart().add(itemId);
					System.out.println("The item " + Items.getList_all_items().get(itemId-1).getClass().getSimpleName() + " has been successfully added to your cart.");
				}
				else
					System.out.println("We are sorry. The item is temporarily unavailable.");
			}
			else
				System.out.println("Invalid item ID");
		}
		else
			System.out.println("No customer with ID number " + customerId + " exists!");
	}
	/**
	 * emptying exactly a shopping cart
	 * @param customerId	customer (which want to clear his/her shopping cart) id
	 */
	public static void emptyCart(int customerId){
		if(inCustomer(customerId)){
				Admin.getList_customer().get(customerId-1).setShoppingCart(new ArrayList<Integer>());
				System.out.println("The cart has been emptied.");
		}
		else
			System.out.println("No customer with ID number " + customerId + " exists!");
	}
	/**
	 * After all shopping process; lastly, placing an order 
	 * @param customerId
	 * @param customerPasswd
	 */
	public static void placeOrder(int customerId, String customerPasswd){
		//check if customer is exist or not
		if (inCustomer(customerId)){
			//check if shopping cart is empty or not
			if (Admin.getList_customer().get(customerId-1).getShoppingCart().size() != 0){
				//check if entered password is matching customer's password
				if (customerPasswd.equals(Admin.getList_customer().get(customerId-1).getCustomerPassword())){
					
					double totalCost = 0;
					double customerSpends = 0;
					double disRate = 0;
					
					//calculate total cost with campaigns disrates
					for(int j : Admin.getList_customer().get(customerId-1).getShoppingCart()){
						for (int i=0;i<Campaigns.getAll_campaigns().size();i++){
							if(Items.getList_all_items().get(j-1).getClass().getSimpleName().toLowerCase().equals(Campaigns.getAll_campaigns().get(i).getItemType().toLowerCase())){
								disRate = Campaigns.getAll_campaigns().get(i).getDisCountRate();
								break;
							}
							else
								disRate = 0;
						}
						totalCost += ((Items) Items.getList_all_items().get(j-1)).getCost() * (1 - disRate/100);
					}

					//calculate total cost with status disrates
					for (int i=0;i<Customer.getOrderHistory().size();i++) {
						if (customerId == Customer.getOrderHistory().get(i).getBuyerId())
							customerSpends += Customer.getOrderHistory().get(i).getTotalCost();
					}
					
					if(customerSpends > 5000){
						Admin.getList_customer().get(customerId-1).setCustomerStatu(Status.GOLDEN);
						disRate = (double) 0.15;
					}
					else if(customerSpends > 1000){
						Admin.getList_customer().get(customerId-1).setCustomerStatu(Status.SILVER);
						disRate += (double) 0.10;
					}
					totalCost = totalCost * (1 - disRate/100);
					
					//if customer has enough money, order will be placed	
					if (totalCost <= Admin.getList_customer().get(customerId-1).getCustomerBalance()){
						//placing order
						orderHistory.add(new Order(customerId, Admin.getList_customer().get(customerId-1).getShoppingCart(), totalCost));
						
						System.out.println("Done! Your order will be delivered as soon as possible. Thank you!");
						//reload customer balance
						Admin.getList_customer().get(customerId-1).setCustomerBalance((float) (Admin.getList_customer().get(customerId-1).getCustomerBalance() - totalCost));
						
						double customerAllSpends = 0;
						
						//after shopping 
						for (int i=0;i<Customer.getOrderHistory().size();i++){
							if (customerId == Customer.getOrderHistory().get(i).getBuyerId())
								customerAllSpends += Customer.getOrderHistory().get(i).getTotalCost();
						}
						
						if (customerAllSpends > 5000){
							Admin.getList_customer().get(customerId-1).setCustomerStatu(Status.GOLDEN);
							System.out.println("Congratulations! You have been upgraded to a " + Status.GOLDEN + " MEMBER! You have earned a discount of 15% on all purchases.");
						}
						else if (customerAllSpends > 1000){
							Admin.getList_customer().get(customerId-1).setCustomerStatu(Status.SILVER);
							System.out.println("Congratulations! You have been upgraded to a " + Status.SILVER + " MEMBER! You have earned a discount of 10% on all purchases.");
							System.out.println("You need to spend " + (5000 - customerAllSpends) + " more TL to become a " + Status.GOLDEN + " MEMBER.");
						}
						else
							System.out.println("You need to spend " + (1000 - customerAllSpends) + " more TL to become a " + Status.SILVER + " MEMBER.");
						
						
						//listing buyed items ;
						for (int i : Admin.getList_customer().get(customerId-1).getShoppingCart())
							Items.getBuyedItemId().add(i);
						
						//Admin.getList_customer().get(customerId-1).getShoppingCart().clear();
						
					}
					else
						System.out.println("Order could not be placed. Insufficient funds.");
				}
				else
					System.out.println("Order could not be placed. Invalid password.");
			}
			else
				System.out.println("You should add some items to your cart before order request!");
		}
		else
			System.out.println("No customer with ID number " + customerId + " exists!");
	}
	
	@Override
	public String toString(){
		System.out.println("Customer name : " + super.getUserName());
		System.out.println("Customer e-mail : " + super.getUserEmail());
		System.out.println("Customer date of birth : " + super.getUserDateOfBirth());
		System.out.println("Customer status : " + this.getCustomerStatu());
		return super.getUserName();
	}
}
