package PackageShopping;
/**
 * a shoppping event which is done by customers
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import PackageUsers.Admin;

public class Order {

	private Date orderDate;
	private double totalCost;
	private ArrayList<Integer> purchasedItems = new ArrayList<Integer>();
	private int buyerId;
	/**
	 * class constructor
	 * @param buyerId	buyer id who buyed something
	 * @param purchasedItems	item's id list which is buyed by customers
	 * @param totalCost	total cost which is spent during shopping by customer
	 */
	public Order(int buyerId, ArrayList<Integer> purchasedItems, double totalCost) {
		this.buyerId = buyerId;
		this.purchasedItems = purchasedItems;
		this.totalCost = totalCost;
		this.orderDate = Calendar.getInstance().getTime();
	}
	/**
	 * showing order informations
	 * @param techName	controlling if technician is exist or not
	 */
	public void displayOrder(String techName){
		if (Admin.isTech(techName)){
			System.out.println("Order Date: " + this.orderDate);
			System.out.println("Customer ID:" + this.buyerId);
			System.out.println("Total Cost: " + this.totalCost);
			System.out.println("Number of purchased items: " + purchasedItems.size());
		}
		else
			System.out.println("No technician person named " + techName + " exists!");
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public ArrayList<Integer> getPurchasedItems() {
		return purchasedItems;
	}

	public void setPurchasedItems(ArrayList<Integer> purchasedItems) {
		this.purchasedItems = purchasedItems;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	
}
