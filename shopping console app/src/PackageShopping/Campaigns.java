package PackageShopping;
/**
 * shopping campaigns which is created by admins
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
import java.util.ArrayList;

import PackageUsers.Customer;

public class Campaigns {
	static ArrayList <Campaigns> all_campaigns = new ArrayList <Campaigns> ();
	
	private String startDate;
	private String endDate;
	private String itemType;
	private double disCountRate;
	/**
	 * class constructor
	 * @param startDate	campaign's start date
	 * @param endDate	campaign's end date
	 * @param itemType	item types which campaigns are included
	 * @param disCountRate	campaign's discount rate
	 */
	public Campaigns(String startDate, String endDate, String itemType, double disCountRate){
		this.startDate = startDate;
		this.endDate = endDate;
		this.itemType = itemType;
		this.disCountRate = disCountRate;
	}
	/**
	 * showing created campaigns
	 * @param customerId	customer id who want to display campaigns
	 */
	public static void displayCampaigns(int customerId){
		if (Customer.inCustomer(customerId)){
			System.out.println("Active campaigns:");
			for(int i=0;i<all_campaigns.size();i++){
				System.out.println((int)all_campaigns.get(i).disCountRate + "% sale of " + all_campaigns.get(i).itemType.toUpperCase() + " until " + dotToSlash(all_campaigns.get(i).getEndDate()));
			}
		}
	}

	public static ArrayList<Campaigns> getAll_campaigns() {
		return all_campaigns;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public double getDisCountRate() {
		return disCountRate;
	}

	public void setDisCountRate(double disCountRate) {
		this.disCountRate = disCountRate;
	}
	/**
	 * converting date format dd.mm.yy to format dd/mm/yy
	 * @param OldDate
	 * @return	date which is converted
	 */
	public static String dotToSlash(String OldDate){
		String[] arr_date = OldDate.split("\\.");	
		return arr_date[0] + "/" + arr_date[1] + "/" + arr_date[2];
	}
}//class
