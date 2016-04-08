package clients;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Customer;



public class HotelReservationSystem {

	public static void main(String[] args) {
		
		int option = 0;
		Scanner sc = new Scanner(System.in);
		
		//MENU
		while(true){
			System.out.println("Please choose one of the options listed below:");
			System.out.println("1 -- CREATE A CUSTOMER");
			System.out.println("2 -- RESERVE A ROOM");
			System.out.println("3 -- CREATE A PAYMENT TRANSACTION");
			System.out.println("4 -- GET CUSTOMER INFORMATION FROM ID");
			System.out.println("5 -- GET CUSTOMERS A SPECIFIC NAME");
			System.out.println("6 -- GET CURRENT CUSTOMERS");
			System.out.println("7 -- GET TRANSACTION(S) FOR CUSTOMER FROM ID");
			System.out.println("8 -- GET ROOM VACANCIES");
			System.out.println("9 -- GET RESEREVATIONS");
			System.out.println("10 -- EXIT");
			System.out.println("ENTER AN OPTION 1-10");
			try{option = Integer.parseInt(sc.nextLine());}
			catch(NumberFormatException e){System.out.println("Invalid entry. Entries must be an integer between 1 and 10");}
			
			
			// TO CREATE A NEW CUSTOMER
			if(option==1){
				System.out.println("Enter the appropriate information to create a new customer:");
				System.out.println("First Name:");
				String fname = sc.nextLine();
				System.out.println("Last Name:");
				String lname = sc.nextLine();
				System.out.println("Phone Number: (No dashes or parenthesis; only digits)");
				String pnum = sc.nextLine();
				System.out.println("Billing Address: ");
				String address = sc.nextLine();
				System.out.println("Billing City: ");
				String city = sc.nextLine();
				System.out.println("Billing State: ");
				String state = sc.nextLine();
				System.out.println("Billing ZIP: ");
				String zip = sc.nextLine();
				System.out.println("Check in Date: ");
				String checkin = sc.nextLine();
				System.out.println("Check out Date: ");
				String checkout = sc.nextLine();
				
				
				try{
					
					String urlParameters  = "option=" + Integer.toString(option)
							+ "&fname=" + fname 
							+ "&lname=" + lname 
							+ "&pnum=" + pnum 
							+ "&address=" + address 
							+ "&city=" + city 
							+ "&state=" + state 
							+ "&zip=" + zip 
							+ "&checkin=" + checkin 
							+ "&checkout=" + checkout;
					
					byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
					int    postDataLength = postData.length;
					String request        = "http://localhost:8080/PA1/Hotel";
					URL    url            = new URL( request );
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();   
					conn.setDoOutput( true );
					conn.setInstanceFollowRedirects( false );
					conn.setRequestMethod( "POST" );
					conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
					conn.setRequestProperty( "charset", "utf-8");
					conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
					conn.setUseCaches( false );
					try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
						   wr.write( postData );
						}
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String next_record = null;
					while ((next_record = reader.readLine()) != null) {
						System.out.println(next_record);
					}
				}
					
				catch(IOException e){
					e.printStackTrace();
					throw new RuntimeException("Please try again. \n" + e);
					
				}
			//continue; // or continue;?
				
			}
//---------------------------------------------------------------------------------------
			else if(option==2){
				
				System.out.println("Enter your information to reserve a room:");
				System.out.print("Unique customer id: ");
				String cust_id = sc.nextLine();
				System.out.println("Desired room number:");
				String room_num = sc.nextLine();
				try {
				
					String urlParameters  = "option=" + option + "&customer_id=" + cust_id + "&room_num=" + room_num;
					byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
					int    postDataLength = postData.length;
					String request        = "http://localhost:8080/PA1/Hotel";
					URL    url            = new URL( request );
					HttpURLConnection conn= (HttpURLConnection) url.openConnection();   
					conn.setDoOutput( true );
					conn.setInstanceFollowRedirects( false );
					conn.setRequestMethod( "POST" );
					conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
					conn.setRequestProperty( "charset", "utf-8");
					conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
					conn.setUseCaches( false );
					try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
					   wr.write( postData );
					}
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String next_record = null;
					while ((next_record = reader.readLine()) != null) {
						System.out.println(next_record);
					}
				} catch (IOException e) {
					throw new RuntimeException("Please try again. \n" + e);
				}
				// break;
				
				
				
				
				
			}
//-------------------------------------------------------------------------------------
			else if(option == 4){
				
				System.out.println("Get customer information from ID:");
				System.out.print("Enter the ID below");
				String cust_id = sc.nextLine();
				
				try {
					String request = "http://localhost:8080/PA1/Hotel" + "?option=" + option + "&customer_id=" + cust_id;
					URL url = new URL( request );
					HttpURLConnection conn= (HttpURLConnection) url.openConnection();  
					conn.setInstanceFollowRedirects( false );
					conn.setRequestMethod( "GET" );
					conn.setUseCaches( false );
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String next_record = null;
					while ((next_record = reader.readLine()) != null) {
						System.out.println(next_record);
							
				}
						
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException();
				} 
				break;
				
				
				
			}
			
//--------------------------------------------------------------------------
				
 // WASNT SURE IF WE WERE TO SEARCH FOR BOTH NAMES OR JUST ONE, SO I JUST DID 1
			else if(option==5){
				System.out.println("Please enter the name of the customer you are looking for:");
				String inputName = sc.nextLine();

				try {
					String request = "http://localhost:8080/PA1/Hotel" + "?option=" + option + "&name=" + inputName;
					URL url = new URL( request );
					HttpURLConnection conn= (HttpURLConnection) url.openConnection();  
					conn.setInstanceFollowRedirects( false );
					conn.setRequestMethod( "GET" );
					conn.setUseCaches( false );
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String next_record = null;
					while ((next_record = reader.readLine()) != null) {
						System.out.println(next_record);
				}
						
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException();
				} 
				break;
				
				
				
				
			}
//-------------------------------------------------------------------------------------
			else if(option==6){
				
				System.out.println("Retrieving all customers who are currently checked in...");

				try {
					String request = "http://localhost:8080/PA1/Hotel" + "?option=" + option;
					URL url = new URL( request );
					HttpURLConnection conn= (HttpURLConnection) url.openConnection();  
					conn.setInstanceFollowRedirects( false );
					conn.setRequestMethod( "GET" );
					conn.setUseCaches( false );
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String next_record = null;
					while ((next_record = reader.readLine()) != null) {
						System.out.println(next_record);
				}
						
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException();
				} 
				
				
				
				
			}
//-------------------------------------------------------------------------------------
			else if(option==7){
				
			}
			
//-------------------------------------------------------------------------------------
			else if(option==8){
				
			}
//-------------------------------------------------------------------------------------
			else if(option==9){
				
			}
//-------------------------------------------------------------------------------------
			else if(option==10){
				System.out.println("Goodbye");
				System.exit(0);
			}
//-------------------------------------------------------------------------------------			
			else{
				continue;
				}
			
			
			
			
		}
	}
}
			
			
			
			
			
			
			
			
			
