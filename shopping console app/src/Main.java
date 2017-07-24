import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import PackageShopping.Campaigns;
import PackageUsers.*;
import PackageItems.*;
/**
 * The main method of assignment
 * @author Ali Yunus Emre OZKOSE
 * @version 1.8
 */
public class Main {
	public static void main(String[] args) {
		
		ArrayList<ArrayList<String>> input_command_list = new  ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> input_user_list = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> input_item_list = new ArrayList<ArrayList<String>>();
		
		try {
			 for (int i=0;i<3;i++){
				 Scanner scanner = new Scanner(new File(args[i]));
				 while(scanner.hasNextLine()){
					 String line = scanner.nextLine();
					 ArrayList  <String> row = new ArrayList  <String> ();
					 for (String url : line.split("\t")){		
						 row.add(url);
					 }
	
					 if (i == 0)
						 input_user_list.add(row);
					 else if (i == 1)
						 input_item_list.add(row);
					 else if (i == 2)
						 input_command_list.add(row);
				 }
				 scanner.close();
			 }
		 }
		 catch (FileNotFoundException ex) {
			 System.out.println("No File Found!");
			 return;
		 }
		
		Admin.takeInput(input_user_list);
		Items.takeInput(input_item_list);
		
		for (int i=0; i<input_command_list.size();i++){
			ArrayList<?> arr1 = (ArrayList<?>) input_command_list.get(i);
			
			String commanLine =  input_command_list.get(i).toString().replace("[","<").replace("]",  ">");
			System.out.print("\nCOMMAND TEXT\t");
			for(String j : commanLine.split(","))
				System.out.print(j + "\t");
			System.out.println("\n");
			
			
			if (arr1.get(0).equals("ADDCUSTOMER")){
				Admin.addCostumer(arr1.get(1).toString(), arr1.get(2).toString(), arr1.get(3).toString(), arr1.get(4).toString(), arr1.get(5).toString(), arr1.get(6).toString());
			}
			else if (arr1.get(0).equals("SHOWCUSTOMER")){
				Admin.displayCustomer(arr1.get(1).toString(), Integer.parseInt(arr1.get(2).toString()));
			}
			else if (arr1.get(0).equals("SHOWCUSTOMERS")){
				Admin.displayAllCustomers(arr1.get(1).toString());
			}
			else if (arr1.get(0).equals("ADDADMIN")){
				Admin.addAdmin(arr1.get(1).toString(), arr1.get(2).toString(), arr1.get(3).toString(), arr1.get(4).toString(), arr1.get(5).toString(), arr1.get(6).toString());
			}
			else if (arr1.get(0).equals("SHOWADMININFO")){
				Admin.displayUser(arr1.get(1).toString());
			}
			else if (arr1.get(0).equals("ADDTECH")){
				Admin.addtechnician(arr1.get(1).toString(), arr1.get(2).toString(), arr1.get(3).toString(), arr1.get(4).toString(), arr1.get(5).toString(),( arr1.get(6).toString().equals("1")));
			}
			else if (arr1.get(0).equals("ADDITEM")){
				SeniorTech.addItem(arr1.get(1).toString(), arr1.get(2).toString());
			}
			else if (arr1.get(0).equals("LISTITEM")){
				Employee.displayItems();
			}
			else if(arr1.get(0).equals("SHOWITEMSLOWONSTOCK")){
				try{
					Employee.displayItemsLowOnStock(arr1.get(1).toString(),Integer.parseInt(arr1.get(2).toString()));
				}
				catch(Exception e){
					Employee.displayItemsLowOnStock(arr1.get(1).toString());
				}
			}
			else if(arr1.get(0).equals("DISPITEMSOF")){
				Technician.displayItemsSoft(arr1.get(1).toString(), arr1.get(2).toString());
			}
			else if (arr1.get(0).equals("CHPASS")){
				Customer.changePassword(Integer.parseInt(arr1.get(1).toString()), arr1.get(2).toString(), arr1.get(3).toString());
			}
			else if (arr1.get(0).equals("DEPOSITMONEY")){
				Customer.loadMoney(Integer.parseInt(arr1.get(1).toString()), Float.parseFloat(arr1.get(2).toString()));
			}
			else if (arr1.get(0).equals("CREATECAMPAIGN")){
				Admin.launchCampaign(arr1.get(1).toString(), arr1.get(2).toString(), arr1.get(3).toString(), arr1.get(4).toString(), arr1.get(5).toString());
			}
			else if (arr1.get(0).equals("SHOWCAMPAIGNS")){
				Campaigns.displayCampaigns(Integer.parseInt(arr1.get(1).toString()));
			}
			else if (arr1.get(0).equals("ADDTOCART")){
				Customer.addCart(Integer.parseInt(arr1.get(1).toString()), Integer.parseInt(arr1.get(2).toString()));
			}
			else if (arr1.get(0).equals("EMPTYCART")){
				Customer.emptyCart(Integer.parseInt(arr1.get(1).toString()));
			}
			else if (arr1.get(0).equals("ORDER")){
				Customer.placeOrder(Integer.parseInt(arr1.get(1).toString()), arr1.get(2).toString());
			}
			else if (arr1.get(0).equals("SHOWVIP")){
				Employee.displayVIPPersons(arr1.get(1).toString());
			}
			else if (arr1.get(0).equals("SHOWORDERS")){
				SeniorTech.displayOrders(arr1.get(1).toString());
			}
			
		}//command for
	}//main
}//class
