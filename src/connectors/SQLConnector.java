package connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Customer;
import models.Transaction;
import models.Room;

public class SQLConnector {

	public SQLConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("JDBC driver registered");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/?" + "user=root&password=greatsqldb&useSSL=false");
			System.out.println("Got Mysql database connection");
			initializeDB(conn);
			return conn;
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}
	
	
	public void initializeDB(Connection conn){
		createDb(conn);
		useDB(conn);
		createTables(conn);
		
	}
	
	public boolean createDb(Connection conn){
		try {
			PreparedStatement stmt = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS pa1;");
			stmt.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.getStackTrace();
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			System.out.println("Error in creating database pa1");
			return false;
		}
	}
	
	public boolean createTables(Connection conn) {

	
		PreparedStatement stmt = null;
		
		//CREATE Customer table --------------------------------------------------------------------------------------------------------
			try {
				stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS pa1.customers ( "
						+ "cust_id INT NOT NULL AUTO_INCREMENT, fname VARCHAR(20) NOT NULL,"
						+ " lname VARCHAR(30) NOT NULL, phone_num VARCHAR(20), address VARCHAR(100),"
						+ " city VARCHAR(30), state VARCHAR(20), zip VARCHAR(20),"
						+ "  checkin VARCHAR(25),  checkout VARCHAR(25), PRIMARY KEY(cust_id))");
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.getStackTrace();
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
				System.out.println("Error in creating customers table");
				return false;
			}
		//CREATE Room table ----------------------------------------------------
			try {
				
				stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS pa1.rooms "
						+ "(type VARCHAR(20) NOT NULL, price INT NOT NULL,"
						+ " occupant_id INT, room_num INT NOT NULL AUTO_INCREMENT,"
						+ " FOREIGN KEY (occupant_id) REFERENCES customers(cust_id), PRIMARY KEY(room_num))");
				stmt.executeUpdate();
				
				//Now to initialize the empty rooms
				
				stmt = conn.prepareStatement("INSERT INTO pa1.rooms (type, price, occupant_id) VALUES('Single', 100, null)");
				for(int i = 0; i < 40; i++){
					stmt.executeUpdate();
				}
				stmt = conn.prepareStatement("INSERT INTO pa1.rooms (type, price, occupant_id) VALUES('Double', 150, null)");
				for(int i = 0; i < 50; i++){
					stmt.executeUpdate();
				}
				stmt = conn.prepareStatement("INSERT INTO pa1.rooms (type, price, occupant_id) VALUES('Presidential', 300, null)");
				for(int i = 0; i < 10; i++){
					stmt.executeUpdate();
				} 
				
			} catch (SQLException e) {
				e.getStackTrace();
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
				System.out.println("Error in creating rooms table");
				return false;
			}
			
			//Create transaction table
			try {
				stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS pa1.transactions ( trans_id INT NOT NULL AUTO_INCREMENT,"
						+ " payee_id INT NOT NULL, room	INT NOT NULL,"
						+ " amount INT NOT NULL, cc_numb VARCHAR(30) NOT NULL,"
						+ " exp_date VARCHAR(20) NOT NULL, FOREIGN KEY (payee_id) REFERENCES pa1.customers(cust_id), FOREIGN KEY (room) REFERENCES pa1.rooms (room_num), PRIMARY KEY(trans_id))");
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.getStackTrace();
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
				System.out.println("Error in creating transactions table");
				return false;
			} 
			
		return true;
	
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean insertCustomer(Customer customer) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = getConnection();
		try {
	
			stmt = con.prepareStatement("INSERT INTO pa1.customers (fname, lname, phone_num, address, city, state, zip, checkin, checkout) VALUES (?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, customer.getFname());
			stmt.setString(2, customer.getLname());
			stmt.setString(3, customer.getPhone_num());
			stmt.setString(4, customer.getAddress());
			stmt.setString(5, customer.getCity());
			stmt.setString(6, customer.getState());
			stmt.setString(7, customer.getZip());
			stmt.setString(8, customer.getCheckin());
			stmt.setString(9, customer.getCheckout());
			System.out.println(stmt);
			System.out.println(stmt.executeUpdate() > 0);
			return true;
		} catch (SQLException ex) {
			// handle any errors
			ex.printStackTrace();
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			System.out.println("An error occured inserting the customer");
		} finally {
		
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} 
				stmt = null;
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException sqlEx) {
				} 
				con = null;
			}
		}
		return false;
	};
		
		
		
		

	public boolean useDB(Connection conn){
		try {
			PreparedStatement stmt = conn.prepareStatement("USE pa1");
			stmt.executeUpdate();
			return true;
			} 
		catch (SQLException e) {
			e.getStackTrace();
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			System.out.println("Error in accessing DB");
			return false;
			}
	}

	public List<Customer> getCustomersByName(String pName) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = getConnection();
		List<Customer> results = null;
		try {
			results = new ArrayList<Customer>();
			
			stmt = con.prepareStatement("SELECT lname,fname,cust_id,phone_num FROM pa1.customers WHERE fname = ? OR lname = ?");
			stmt.setString(1, pName);
			stmt.setString(2, pName);
			rs = stmt.executeQuery();
			rs.next();
			while (rs.next()) {
				Customer cust = new Customer();
				cust.setFname(rs.getString("fname"));
				cust.setLname(rs.getString("lname"));
				cust.setPhone_num(rs.getString("phone_num"));
				cust.setCust_id(rs.getInt("cust_id"));
				results.add(cust);
			}	
				return results;
			
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
		
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException sqlEx) {
				} // ignore
				con = null;
			}
		}
		return results;
	}

	public boolean reserveRoom(int id, int room_num) {
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
			try { // CHECK TO SEE IF ITS OCCUPIED"
				stmt = conn.prepareStatement("SELECT * FROM pa1.rooms WHERE (occupant_id IS NOT NULL) AND (room_num = ?);");
				stmt.setInt(1, room_num);
				rs = stmt.executeQuery();
				if(rs.next() == false){
					System.out.println("This room is unfortunately not available");
					stmt.close();
					return false;
				}
				stmt = conn.prepareStatement("UPDATE pa1.rooms SET occupant_id = ?  WHERE room_number = ?");
				stmt.setInt(1, id);
				stmt.setInt(2, room_num);
				boolean success = stmt.executeUpdate() > 0;
				return success;
			} catch (SQLException ex) {
				// handle any errors
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			} finally {
		

				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException sqlEx) {
					} // ignore

					stmt = null;
				}
				if(conn != null){
					try {
						conn.close();
					} catch (SQLException sqlEx) {
					} // ignore

					conn = null;
				}

			}
		return false;
	};
	

	
	
	
	
	
}