package models;

public class Transaction {
	
	private static int id_gen = 1;
	
	private int trans_id;
	private int payee_id;
	private int room;
	private String amount;
	private String cc;
	private String exp_date;
	
	//CONSTRUCTORS
	
	public Transaction(int pTrans_id, int pPayee_id, int pRoom, String pAmount, String pCC, String pExp_date){
		this.trans_id = getNextId();
		this.payee_id = pPayee_id;
		this.room = pRoom;
		this.amount = pAmount;
		this.cc = pCC;
		this.exp_date = pExp_date;
	}
	
	public Transaction(){
		this.trans_id = getNextId();
	}
	
	public static int getNextId(){
		id_gen++;
		int i = id_gen;
		return i;
	}
	
	
	
	// GETTER AND SETTER METHODS
	
	
	
	public String getExp_date() {
		return exp_date;
	}
	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getAmount() {
		return this.amount;
	}
	public void setAmount(String amount) {
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
