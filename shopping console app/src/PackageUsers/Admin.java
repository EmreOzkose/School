package PackageUsers;
/**
 * System controller class
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import PackageShopping.Campaigns;

public class Admin extends Employee{
	static ArrayList <Admin> list_admin = new ArrayList<Admin>();
	static ArrayList <Customer> list_customer = new ArrayList<Customer>();
	static ArrayList <Technician> list_tech = new ArrayList<Technician>();
	
	private String adminPassword;
	/**
	 * class constructor
	 */
	public Admin(){
		
	}
	/**
	 * class constructor
	 * @param name	Admin's name
	 * @param email	Admin's e-mail
	 * @param dateOfBirth	Admin's birthday
	 * @param salary	Admin's salary
	 * @param password	Admin's password
	 */
	public Admin(String name, String email, String dateOfBirth, String salary, String password) {
		super.setUserName(name);
		super.setUserEmail(email);
		super.setUserDateOfBirth(dateOfBirth);
		super.setSalary(Integer.parseInt(salary));
		this.adminPassword = password;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}
	
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	public static ArrayList<Customer> getList_customer() {
		return list_customer;
	}

	public static void setList_customer(ArrayList<Customer> list_customer) {
		Admin.list_customer = list_customer;
	}
	/**
	 * adding new customer
	 * @param controlAdmin	controlling admin name which an admin who add a customer is exist
	 * @param name	customer name
	 * @param email	customer e-mail
	 * @param dateOfBirth	customer birthday
	 * @param customerBalance	customer balance
	 * @param password	customer password
	 */
	public static void addCostumer(String controlAdmin, String name, String email, String dateOfBirth, String customerBalance, String password){
		
		if(isAdmin(controlAdmin)){
			list_customer.add(new Customer(name, email, dateOfBirth, customerBalance, password,list_customer.size() + 1));
		}
		else{
			System.out.println("No admin person named " + controlAdmin +" exists!");
		}
	}
	/**
	 * adding new admin
	 * @param controlAdmin	controlling admin name which an admin who can add an another admin is exist 
	 * @param name	admin name
	 * @param email	admin e-mail
	 * @param dateOfBirth	admin birthday
	 * @param salary	admin salary
	 * @param password	admin password
	 */
	public static void addAdmin(String controlAdmin ,String name, String email, String dateOfBirth, String salary, String password){
		if(isAdmin(controlAdmin)){
			list_admin.add(new Admin(name, email, dateOfBirth, salary, password));
		}
		else{
			System.out.println("No admin person named " + controlAdmin +" exists!");
		}
	}
	/**
	 * adding new technician
	 * @param controlAdmin	controlling admin name which an admin who can add a technician is exist 
	 * @param name	technician name
	 * @param email	technician e-mail
	 * @param dateOfBirth	technician birthday
	 * @param salary	technician salary
	 * @param isSenior	if technician is senior or not
	 */
	public static void addtechnician(String controlAdmin ,String name, String email, String dateOfBirth, String salary, boolean isSenior){
		
		if(isAdmin(controlAdmin)){
			list_tech.add(new Technician(name, email, dateOfBirth, salary, isSenior));
		}
		else{
			System.out.println("No admin person named " + controlAdmin +" exists!");
		}
	}
	/**
	 * showing customer informations
	 * @param controlAdmin	controlling admin name which an admin who can display customer informations 
	 * @param customerId	customer (who is wanted to show his/her informations) id
	 */
	public static void displayCustomer(String controlAdmin, int customerId){
		
		if (isAdmin(controlAdmin)){
			System.out.println("Customer name: " + list_customer.get(customerId-1).getUserName() + "\tID : " + customerId + "\te-mail: " + list_customer.get(customerId-1).getUserEmail() + "\tDate of Birth: " + convertStringToDate(list_customer.get(customerId-1).getUserDateOfBirth()) + "\tStatus: " + list_customer.get(customerId-1).getCustomerStatu());
		}
		else{
			System.out.println("No admin person named " + controlAdmin +" exists!");
		}
	}
	/**
	 * showing all customers informations
	 * @param controlAdmin	controlling admin name which an admin who can display all customer informations 
	 */
	public static void displayAllCustomers(String controlAdmin){
		int customerId = 1 ;
		if (isAdmin(controlAdmin)){
			for(int i = 0 ; i < list_customer.size() ; i++){
				System.out.println("Customer name: " + list_customer.get(customerId-1).getUserName() + "\tID : " + customerId + "\te-mail: " + list_customer.get(customerId-1).getUserEmail() + "\tDate of Birth: " + convertStringToDate(list_customer.get(customerId-1).getUserDateOfBirth()) + "\tStatus: " + list_customer.get(customerId-1).getCustomerStatu());
				customerId +=1;
			}
		}
		else{
			System.out.println("No admin person named " + controlAdmin +" exists!");
		}
	}
	/**
	 * 
	 * @param adminName	controlling admin name, if he/she is exist
	 */
	public static void displayUser(String adminName){
		if (adminBul(adminName) != -1){
			System.out.println("----------- Admin info -----------");
			System.out.println("Admin name : " + list_admin.get(adminBul(adminName)).getUserName());
			System.out.println("Admin e-mail : " + list_admin.get(adminBul(adminName)).getUserEmail());
			System.out.println("Admin date of birth : " + list_admin.get(adminBul(adminName)).getUserDateOfBirth());
		}
		else{
			System.out.println("No admin person named " + adminName + " exists!");
		}
	}
	/**
	 * taking lines about users from user.txt and adding new objects to lists
	 * @param input_user_list	lines from user.txt
	 */
	public static void takeInput(ArrayList<?> input_user_list){
		
		for (int i = 0; i < input_user_list.size();i++){
			ArrayList<?> array_userTXT = (ArrayList<?>) input_user_list.get(i);
			
			if (array_userTXT.get(0).equals("ADMIN")){
				list_admin.add(new Admin(array_userTXT.get(1).toString(), array_userTXT.get(2).toString() ,array_userTXT.get(3).toString() ,array_userTXT.get(4).toString() ,array_userTXT.get(5).toString()));
			}
			else if (array_userTXT.get(0).equals("CUSTOMER")){
				list_customer.add(new Customer(array_userTXT.get(1).toString(), array_userTXT.get(2).toString() ,array_userTXT.get(3).toString() ,array_userTXT.get(4).toString() ,array_userTXT.get(5).toString(),list_customer.size()+1));
			}
			else if (array_userTXT.get(0).equals("TECH")){
				list_tech.add(new Technician(array_userTXT.get(1).toString(), array_userTXT.get(2).toString() ,array_userTXT.get(3).toString() ,array_userTXT.get(4).toString() ,Boolean.parseBoolean(array_userTXT.get(5).toString())));
			}
		}
	}
	/**
	 * ensuring if an admin is exist or not
	 * @param name	controlling admin name
	 * @return	true/false
	 */
	public static boolean isAdmin(String name){
		for (int i = 0 ; i < list_admin.size();i++){
			if (name.equals(list_admin.get(i).getUserName())){
				return true;
			}
		}
		return false;
	}
	/**
	 * ensuring if a technician is exist or not
	 * @param techName	controlling technician name
	 * @return	true/false
	 */
	public static boolean isTech(String techName){
		for (int i = 0 ; i < list_tech.size();i++){
			if(techName.equals(list_tech.get(i).getUserName()))
				return true;
		}
		return false;
	}
	/**
	 * ensuring if a technician is a senior or not
	 * @param techName	controlling technician name
	 * @return	true/false
	 */
	public static boolean isSenior(String techName){
		for (int i = 0 ; i < list_tech.size();i++){
			if (techName.equals(list_tech.get(i).getUserName()) && list_tech.get(i).isSenior()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * finding given admin's id
	 * @param	adminName	given admin who is wanted to find his/her id
	 * @return	adminId
	 */
	public static int adminBul(String adminName){
		for (int i = 0; i < list_admin.size();i++){
			if (adminName.equals(list_admin.get(i).getUserName())){
				return i;
			}
		}
		return -1;
	}
	
	public static ArrayList<Integer> getList_customerID() {
		ArrayList <Integer> list_customer_ID = new ArrayList<Integer>();
		for (int i=0;i<list_customer.size();i++){
			list_customer_ID.add(list_customer.get(i).getCustomerID());
		}
		return list_customer_ID;
	}
	/**
	 * adding a campaign by admin
	 * @param adminName	admin who add campaign
	 * @param startDate	campaign's start date
	 * @param endDate	campaign's end date
	 * @param itemType	item types which campaign include
	 * @param rate	item's discount rate which campaign include
	 */
	public static void launchCampaign(String adminName, String startDate, String endDate, String itemType,String rate){
		if (Admin.isAdmin(adminName) && Double.parseDouble(rate) <= 50)
			Campaigns.getAll_campaigns().add(new Campaigns(startDate, endDate, itemType, Double.parseDouble(rate)));
		else if (Admin.isAdmin(adminName) && Double.parseDouble(rate) > 50)
			System.out.println("Campaign was not created. Discount rate exceeds maximum rate of 50%.");
		else
			System.out.println("No admin person named Alper exists!");
	}
	/**
	 * converting given string to date
	 * @param indate	given date which has String format
	 * @return	date which has date format
	 */
	public static String convertStringToDate(String indate)
	{
	    String expectedPattern = "dd.MM.yyyy";
	    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
	    try
	    {
	      java.util.Date date = formatter.parse(indate);
	      return date.toString();
	    }
	    catch (ParseException e)
	    {
	      e.printStackTrace();
	    }
	   return "can NOT be converted";
	}
	
}
