

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "todoMessage")
@XmlType(propOrder ={"id","message"})
public class TodoMessage {
	private int id;
	private String message;
	
	public TodoMessage(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString(){
		return "Message #" + this.id + ": " + this.message;
	}
	
	//HELPER METHOD TO USE INDEX_OF FOR LIST
	 @Override
	 public boolean equals(Object o) {
	     if(o == null) {
	    	 return false;
	     }else if( o instanceof TodoMessage){
	        int a_id = (Integer) ((TodoMessage) o).getId();
	        if(this.getId() == a_id){
	        	return true;
	        }
	        else {
	        return false;
	        }       
	    }
	     return false;
	    }
}
