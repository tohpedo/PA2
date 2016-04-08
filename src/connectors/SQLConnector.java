package connectors;

import java.io.IOException;
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
			return conn;
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}
	
	
	public void initializeDB(){
		Connection conn = getConnection();
		createDb(conn);
		useDB(conn);
		createTables(conn);
		
	}
	
	public  boolean createDb(Connection conn){
		try {
			PreparedStatement stmt = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS pa2;");
			stmt.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.getStackTrace();
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			System.out.println("Error in creating database pa2");
			return false;
		}
	}
	
	public boolean createTables(Connection conn) {

	
		PreparedStatement stmt = null;
		
		//CREATE Customer table --------------------------------------------------------------------------------------------------------
			try {
				stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS pa2.customers ( "
						+ "cust_id INT NOT NULL, fname VARCHAR(20) NOT NULL,"
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
				
				stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS pa2.rooms "
						+ "(type VARCHAR(20) NOT NULL, price INT NOT NULL,"
						+ " occupant_id INT, room_num INT NOT NULL AUTO_INCREMENT,"
						+ " FOREIGN KEY (occupant_id) REFERENCES customers(cust_id), PRIMARY KEY(room_num))");
				stmt.executeUpdate();
				
				//Now to initialize the empty rooms
				
				stmt = conn.prepareStatement("INSERT INTO pa2.rooms (type, price, occupant_id) VALUES('Single', 100, null)");
				for(int i = 0; i < 40; i++){
					stmt.executeUpdate();
				}
				stmt = conn.prepareStatement("INSERT INTO pa2.rooms (type, price, occupant_id) VALUES('Double', 150, null)");
				for(int i = 0; i < 50; i++){
					stmt.executeUpdate();
				}
				stmt = conn.prepareStatement("INSERT INTO pa2.rooms (type, price, occupant_id) VALUES('Presidential', 300, null)");
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
				stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS pa2.transactions ( trans_id INT NOT NULL,"
						+ " payee_id INT NOT NULL, room	INT NOT NULL,"
						+ " amount VARCHAR(10) NOT NULL, cc_numb VARCHAR(30) NOT NULL,"
						+ " exp_date VARCHAR(20) NOT NULL, FOREIGN KEY (payee_id) REFERENCES customers(cust_id), FOREIGN KEY (room) REFERENCES rooms(room_num), PRIMARY KEY(trans_id))");
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
	
	public String getCustomer(int id){
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = getConnection();
		try {
			stmt = con.prepareStatement("SELECT * FROM pa2.customers "
					+ "WHERE cust_id = ?");
			
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs!=null){
			rs.next();
			Customer match = new Customer(rs.getString("fname"),
					rs.getString("lname"), rs.getString("phone_num"), rs.getString("address"),
					rs.getString("city"),rs.getString("state"), rs.getString("zip"),
					rs.getString("checkin"),rs.getString("checkout"));
			match.setCust_id(match.getCust_id() - 1);
			return match.toString();
			}
			return "unable to find customer";
			
		} catch(SQLException e){
				e.printStackTrace();
				System.out.println("Invalid format, please try again");
				return "Unable to find customer";
		}	
	}
	
	
	public int insertCustomer(Customer customer) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = getConnection();
		String id = "";
		try {
			stmt = con.prepareStatement("INSERT INTO pa2.customers (fname, lname, phone_num, address, city, state, zip, checkin, checkout, cust_id) VALUES (?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, customer.getFname());
			stmt.setString(2, customer.getLname());
			stmt.setString(3, customer.getPhone_num());
			stmt.setString(4, customer.getAddress());
			stmt.setString(5, customer.getCity());
			stmt.setString(6, customer.getState());
			stmt.setString(7, customer.getZip());
			stmt.setString(8, customer.getCheckin());
			stmt.setString(9, customer.getCheckout());
			stmt.setInt(10, customer.getCust_id());
			System.out.println(stmt.executeUpdate() > 0);
			return customer.getCust_id();
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
		return 0;
	};
		
	
	public boolean makePayment(Transaction trans) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = getConnection();
		try {
	
			stmt = con.prepareStatement("INSERT INTO pa2.transactions (payee_id, room, amount, cc_numb, exp_date, trans_id) VALUES (?,?,?,?,?,?)");
			stmt.setInt(1,trans.getPayee_id());
			stmt.setInt(2, trans.getRoom_num());
			stmt.setString(3, trans.getAmount());
			stmt.setString(4, trans.getCc());
			stmt.setString(5, trans.getExp_date());
			stmt.setInt(6, trans.getTrans_id());
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

	public static boolean useDB(Connection conn){
		try {
			PreparedStatement stmt = conn.prepareStatement("USE pa2");
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

	public String getCustomersByName(String pName) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = getConnection();
		String output = "";
		try {
			stmt = con.prepareStatement("SELECT cust_id, fname, lname, phone_num FROM pa2.customers WHERE fname = ? OR lname = ?");
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
				String custString = cust.toStringSimple() + " <br/>";
				output += custString;
			}	
			return output;
			
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
		return output;
	}

	public boolean reserveRoom(int id, int room_num) {
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
			try { // CHECK TO SEE IF ITS OCCUPIED"
				stmt = conn.prepareStatement("UPDATE pa2.rooms SET occupant_id = ? WHERE room_num = ?");
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
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException sqlEx) {
					} // ignore
					rs = null;
				}

			}
		return false;
	};
	

	public String getCurrentCustomers(){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = getConnection();
		String output = "";
		try {
			stmt = con.prepareStatement("SELECT c.cust_id, c.fname, c.lname, c.phone_num from pa2.rooms as r, pa2.customers as c where r.occupant_id = c.cust_id");
			rs = stmt.executeQuery();
			rs.next();
			while (rs.next()) {
				Customer cust = new Customer();
				cust.setFname(rs.getString("fname"));
				cust.setLname(rs.getString("lname"));
				cust.setPhone_num(rs.getString("phone_num"));
				cust.setCust_id(rs.getInt("cust_id"));
				String custString = cust.toStringSimple() + " <br/>";
				output += custString;
			}
	}catch (SQLException ex) {
		// handle any errors
		System.out.println("SQLException: " + ex.getMessage());
		System.out.println("SQLState: " + ex.getSQLState());
		System.out.println("VendorError: " + ex.getErrorCode());
	} 
	return output;
	
	
}
}