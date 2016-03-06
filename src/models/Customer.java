package models;


public class Customer {

	
	private String fname;
	private String lname;
	private String phone_num;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String checkin;
	private String checkout;
	private int cust_id; 
	
	
	//CONSTRUCTORS
	
	public Customer(String pFname, String pLname, String pNum, String pAddress, String pCity, 
			String pState, String pZip, String pCheckin, String pCheckout){
		this.fname = pFname;
		this.lname= pLname;
		this.phone_num = pNum;
		this.address = pAddress;
		this.city = pCity;
		this.state = pState;
		this.zip = pZip;
		this.checkin = pCheckin;
		this.checkout = pCheckout;
		
	}
	
	

	public Customer(){
	
	}
	
	
	
	
	
	
	
	
	
	
	//getters and setters
	
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_num;
	}
	public void setPhone_number(String phone_number) {
		this.phone_num = phone_number;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	
	
	
	
	


	/*
	
	
	
	
	public static void main(String args[]){
		
		
		
		Customer torbir = new Customer("","","","","","","","","");
		Customer terror = new Customer();
		
		System.out.println(torbir.getCustomer_id());
		System.out.println(terror.getCustomer_id());
		
		
		
		
		
		
		
		
	}
*/




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}





















































