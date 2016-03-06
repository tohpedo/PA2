package models;

public class Transaction {
	
	private int trans_id;
	private int cust_id;
	private int room_id;
	private int amount;
	private int cc;
	private int exp_date;
	
	//CONSTRUCTORS
	
	public Transaction(int pTrans_id, int pCust_id, int pRoom_id, int pAmount, int pCC, int pExp_date){
		this.cust_id = pCust_id;
		this.room_id = pRoom_id;
		this.amount = pAmount;
		this.cc = pCC;
		this.exp_date = pExp_date;
	}
	
	public Transaction(){
		
	}
	
	// GETTER AND SETTER METHODS
	
	
	
	public int getExp_date() {
		return exp_date;
	}
	public void setExp_date(int exp_date) {
		this.exp_date = exp_date;
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public int getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
