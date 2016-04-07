import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "todoMessageList")
public class TodoMessageList {

	@XmlElementWrapper(name="todoMessage")
	private static List<TodoMessage> messages = new ArrayList<TodoMessage>();
	
	public static List<TodoMessage> getMessages() {
		return messages;
	}
	public static void setMessages(List<TodoMessage> p_messages) {
		messages = p_messages;
	}

	//HELPER METHOD TO GET INDEX IN MESSAGE LIST
	//public int findMessage(int p_id){
		//messages.indexOf(o)
	//}
}