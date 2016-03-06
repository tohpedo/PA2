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
				int id = conn.getCustomerId(fname,lname);
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
