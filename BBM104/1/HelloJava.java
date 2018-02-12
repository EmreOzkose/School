 /* @author yunus emre ozkose 21527271
 *  @subject = bbm102 assignment1
 * 
 */

import java.math.*;
import java.util.Scanner;
import java.io.*;

public class HelloJava {
	public static void main(String [] array_input) {
		
		try{
			Scanner scanner = new Scanner(new File(array_input[0]));
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				
				String array_input1[] = new String[5]; 					// store input arguments
				
				int index = 0; 				// array_input1's index 
				for (String url : line.split(" ")){		// striping input line and store datas in array_input_1
					array_input1 [index] = url;
					index += 1;
				}
				
				String method = array_input1[0];		// determining which method are used : Riemann/Arcsinh/Armstrong 
					
				if (method.equals("IntegrateReimann")){
					String func = array_input1[1];		//if method is Riemann, you have to input one of three function:
																									//Func1/Func2/Func3
					double a = Double.parseDouble(array_input1[2]);		//lower limit for integral
					double b = Double.parseDouble(array_input1[3]);		//upper limit for integral , ***integral from a to b
					double partition = Integer.parseInt(array_input1[4]);	// # of partition
					
					double one_part = (b-a) / partition;	//one part of all integral parts
					
					double all_area = 0;		// area : answer for all functions
					all_area = IntegrateRiemann(func, a, one_part,partition);
					
					//Printing the result
					//Function Func3 has double limits, so I used "if" to determine if limits(a and b) are double or integer
					if (func.equals("Func3")){
						if (Math.abs(a)<1 && Math.abs(b) < 1)
							System.out.println("IntegrateRiemann " + func + " "+ (double)a + " " + (double)b + " " + (int)partition + " Result: " + all_area);
					}
					else
						System.out.println("IntegrateRiemann " + func + " "+ (int)a + " " + (int)b + " " + (int)partition + " Result: " + all_area);
					
				}
				else if(method.equals("Arcsinh")){
					// this case takes number of arcsin , Arcsinh() method takes this number and return result.
					double num_of_arcsinh = Double.parseDouble(array_input1[1]);
					double answer = Arcsinh(num_of_arcsinh);
					if (Math.abs(num_of_arcsinh)<1)
						System.out.println("Arcsinh " + num_of_arcsinh + " Result: " + answer);
				
				}
				else if (method.equals("Armstrong")){
					int digit = Integer.parseInt(array_input1[1]);
					
					System.out.print("Armstrong " + digit + " Result: ");
					
					// for loop processes from 0 to 10**digits, and send value to Armstrong() one by one.
					for (int i=0; i < Math.pow(10,digit);i++){
						int testNum = Armstrong(i); // Armstrong() takes a number and test if it is a Armstrong number or not
						if (testNum == 1) 
							System.out.print(i + " ");
					}
				}
			}
			scanner.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("No File Found!"); return;
		}
	}
	
	public static double IntegrateRiemann(String function,double a, double one_part,double partition){
		
		//Func1 and Func2 are similar. 
		//IntegrateRiemann() takes which function, lower limit, one part of all parts between lower limit and upper limit and given number partition
		//IntegrateRiemann() returns area of function with given limit and one part.
		//if my area is a rectangular, I must know my x and y coordinates and  ı already know x which is i, and I put this value to function
		//and find y. then multiply x any y, so ı find area of one part.
		
		if (function.equals("Func1") || function.equals("Func2")){
			double area = 0;
			
			for (int i = 0 ; i < partition ; i++){
				
				double orta_x = (2*a+one_part) / 2;		//recctangular's x coordinate
				double orta_y = 0;						//recctangular's y coordinate
				
				if (function.equals("Func1"))
					orta_y  = (double) Math.pow(orta_x, 2) - orta_x + 3;	// function1 = x**2 -x + 3
				else if (function.equals("Func2"))
					orta_y = (double) Math.pow(3*Math.sin(orta_x)-4, 2);	// function2 = (3*sin(x)-4)**2
				
				area += one_part * orta_y;		// recctangular's x coordinate x recctangular's y coordinate
				a += one_part;					//I increase a with one_part in every cycle; 
												//		in this way, lower limit changes in every cycle
			} 
			return area;
		}
		
		//Func3 has only one difference. Again, if case calculates area but calculate 30 times differently with number from 1 to 30
		else if (function.equals("Func3")){
			// function3 = arcsinh(x)
			double area = 0;
			
			for (int i = 0 ; i < partition ; i++){
				
				double orta_x = (2*a+one_part) / 2;
				double orta_y = 0;
				
				if (Math.abs(a) < 1 || Math.abs(a + one_part*partition) < 1){
					for (int n=0 ; n < 30 ; n++)
						orta_y += (Math.pow(-1, n) * factorial(2*n) * Math.pow(orta_x, 2 * n + 1)) / (Math.pow(4, n) * Math.pow(factorial(n), 2) * (2*n+1));
				}
				else {
					System.out.println("WARNİNG !!!! -->> x : range -> |x| < 1 your input is wrong for Func3");
					break;
				}
				area += one_part * orta_y;
				a += one_part;
			} 
			return area;
		}
		return 0;
	}
	
	public static int factorial(int num){
	//factorial() is basicly a function which calculates factorial of a number
		int answer=1;
		
		for (int i=1;i<=num;i++)
			answer *= i;
		
		return answer;
	}
	
	public static double Arcsinh(double num_of_arcsinh){
	//Arcsinh() takes a number which is wanted to calculate arcsinh.
	//again calculating processes 30 times.
		double orta_nokta_sum = 0;
		if (Math.abs(num_of_arcsinh ) < 1){
			for (int n=0 ; n < 30 ; n++)
				orta_nokta_sum += (Math.pow(-1, n) * factorial(2*n) * Math.pow(num_of_arcsinh, 2 * n + 1)) / (Math.pow(4, n) * Math.pow(factorial(n), 2) * (2*n+1));
		}
		else{
			System.out.println("WARNİNG !!!! -->> x : range -> |x| < 1 your input is wrong for Arcsinh: " + num_of_arcsinh);
		}
		return orta_nokta_sum;
	}
	
	public static int Armstrong(int test){
		//Armstrong() takes a number which is wanted to know if it is armstrong number or not
		//I parse integer to string because of knowing how many numbers test number contain. I check it with length() method.
		String testNum = Integer.toString(test);
		int digits = testNum.length();
		
		//takes digits one by one from test which is string , takes power of each digits and sum all of them
		int sum =0;
		for(int i=0 ; i<testNum.length();i++){
			int a = Character.getNumericValue(testNum.charAt(i));
			sum += Math.pow(a,digits);
		}
		
		//if the number is armstrong number, Armstrong() returns 1 wihch show that the number is armstrong number, Armstrong() else returns 0.
		if (sum == test)
			return 1;
		else
			return 0;
	}
}
