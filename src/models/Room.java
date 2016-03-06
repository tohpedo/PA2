package models;



public class Room {
	
	private String type;
	private int price;
	private int occupant_id;
	private int room_num;
	
	
	
	
	//CONSTRUCTORS
	
	public Room(String pType, int pPrice, int pOccupant){
		this.type = pType;
		this.price = pPrice;
		this.occupant_id = pOccupant;
	}
	
	public Room(){
	
	}
	

	
	
	//SETTERS AND GETTERS
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRoom_num() {
		return room_num;
	}
	public void setRoom_num(int room_number) {
		this.room_num = room_number;
	}
	public int getOccupant_id() {
		return occupant_id;
	}
	public void setOccupant_id(int customer_id) {
		this.occupant_id = customer_id;
	}
	
	
}