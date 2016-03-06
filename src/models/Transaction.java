package models;

public class Transaction {
	
	private int trans_id;
	private int payee_id;
	private int room;
	private int amount;
	private int cc;
	private int exp_date;
	
	//CONSTRUCTORS
	
	public Transaction(int pTrans_id, int pPayee_id, int pRoom, int pAmount, int pCC, int pExp_date){
		this.payee_id = pPayee_id;
		this.room = pRoom;
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
		return this.amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getRoom_num() {
		return this.room;
	}
	public void setRoom_num(int room_num) {
		this.room = room_num;
	}
	public int getPayee_id() {
		return this.payee_id;
	}
	public void setPayee_id(int payee_id) {
		this.payee_id = payee_id;
	}
	public int getTrans_id() {
		return this.trans_id;
	}
	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
