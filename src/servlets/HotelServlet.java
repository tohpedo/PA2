package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import connectors.SQLConnector;
import models.Customer;

@WebServlet(
		description = "Hotel Reservation System", 
		urlPatterns = { 
				"/Hotel", 
				"/HotelReservation", 
				"/HotelReservationSystem"
		})
	public class HotelServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private static SQLConnector sql_conn = new SQLConnector();
		Connection conn = sql_conn.getConnection();
	
	
	
    public HotelServlet() {
        super();
 
    }


	public void init(ServletConfig config) throws ServletException {
		sql_conn.initializeDB(conn);

	
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
		
		PrintWriter output = response.getWriter();
		SQLConnector conn = new SQLConnector();
		Integer option = Integer.parseInt(request.getParameter("option"));
		//Get information about a customer an ID
		if(option == 4){
			int cust_id = Integer.parseInt(request.getParameter("customer_id"));
			Customer match = conn.getCustomer(cust_id);
			output.println("Name: " + match.getFname() + " " + match.getLname() + "\n"                       
					+ "Address: " + match.getAddress() + " " + match.getCity() + " " + match.getState() + " " + match.getZip() + "\n"
					+ "Phone Number: " + match.getPhone_num() + "\n"
					+ "Check In: " + match.getCheckin() +  "\n"
					+ "Check Out: " + match.getCheckout() + " \n"); 
				
		}
		if(option == 5){
			String input_name = request.getParameter("name");
			List<Customer> results = conn.getCustomersByName(input_name);
			
			Iterator<Customer> iter = results.iterator();
			while(iter.hasNext()){
				Customer x = iter.next();
				output.println("Name: " + x.getFname() + " " + x.getLname() + "\n" 
						+ "Phone Number: " + x.getPhone_num() + "\n" 
						+ "ID: " + x.getCust_id());
				
			}
			
			
		}
		
		if(option == 6){
			List<Customer> results = conn.getCurrentCustomers();
			Iterator<Customer> iter = results.iterator();
			while(iter.hasNext()){
				Customer x = iter.next();
				output.println("Name: " + x.getFname() + " " + x.getLname() + "\n" 
						+ "Phone Number: " + x.getPhone_num() + "\n" 
						+ "ID: " + x.getCust_id() + "\n");
				
			}
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

//-----------------------------------------------------------------------------------------------------------------------------------
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter output = response.getWriter();
		SQLConnector conn = new SQLConnector();
		Integer option = Integer.parseInt(request.getParameter("option"));
		
		
		if(option == 1){
			Customer customer = new Customer();
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			customer.setFname(fname);
			customer.setLname(lname);
			customer.setPhone_num(request.getParameter("pnum"));
			customer.setAddress(request.getParameter("address"));
			customer.setCity(request.getParameter("city"));
			customer.setState(request.getParameter("state"));
			customer.setZip(request.getParameter("zip"));
			customer.setCheckin(request.getParameter("checkin"));
			customer.setCheckout(request.getParameter("checkout"));
			response.setContentType("text/html");
			if(conn.insertCustomer(customer)){
				int id = customer.getCust_id();
				output.println("<h2>" + "Customer <b>" + fname + " " + lname + " </b> has been inserted into the system with an ID number of : <b>" + id + "</b></h2>");
			}
			else{
				output.println("<h1>" + "Error in adding customer " + fname + " " + lname + " " + "to the database </h1>");
			}
			
			
		}
		
		//----------------------------------------------------------------	
		else if(option == 2){
			int cust_id = Integer.parseInt(request.getParameter("customer_id"));
			int room_num = Integer.parseInt(request.getParameter("room_num"));
			response.setContentType("text/html");
			if(conn.reserveRoom(cust_id, room_num)){
			      output.println("<h1> Congratulations! Your room has successfully been booked!</h1>");
			}
			else{
				output.println("We were unable to process your request, please try again later");
				
			}
		}
//----------------------------------------------------------------
		else if(option == 3){
			
			
			System.out.println("This features has yet to be implemented");
			System.exit(0);
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
			
		}
	
	

}
