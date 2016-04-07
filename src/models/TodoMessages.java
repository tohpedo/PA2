import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/")
public class TodoMessages{
	
	public TodoMessages() {
	}

	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/create")
	public Response createMessage(@FormParam("id") String p_id, @FormParam("message") String p_message){
		int id = Integer.parseInt(p_id);
		System.out.println("Creating Todo with id: " + id);
		System.out.println("And message: " + p_message);
		List<TodoMessage> m = TodoMessageList.getMessages();
		TodoMessage tm = new TodoMessage();
		tm.setMessage(p_message);
		tm.setId(id);
		ServerResponse s = new ServerResponse();
		try{
			m.add(tm);
			TodoMessageList.setMessages(m);
			s.code = "Success";
			s.message = "Your message has successfully been created!";
		}
		catch(Exception e){
			s.code = "Error";
			s.message = "Unable to insert your message";
		}
		String json = "";	
		try {
			json = new ObjectMapper().writeValueAsString(s);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(json,"application/json").build();
	}
	
	

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/json/{id}")
	public Response getMessageJSON(@PathParam("id") String p_id) {
		System.out.println("Getting message with id#" + p_id + " using JSON...");
		int id = Integer.parseInt(p_id);
		List<TodoMessage> m = TodoMessageList.getMessages();
		TodoMessage tmp = new TodoMessage();
		tmp.setId(id);
		int index = m.indexOf(tmp);
		TodoMessage todo;
		if(index >= 0) {
			todo = m.get(index);
		}else{
			todo = new TodoMessage();
			todo.setMessage("Unable to retrieve your message");
		}
		String json = "";	
		System.out.println("Your message is:" + todo.getMessage());
		try {
			json = new ObjectMapper().writeValueAsString(todo);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(json,"application/json").build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	@Path("/xml/{id}")
	public Response getMessageXML(@PathParam("id") String p_id) {
		System.out.println("Getting message with id#" + p_id + " using XML...");
		int id = Integer.parseInt(p_id);
		List<TodoMessage> m = TodoMessageList.getMessages();
		TodoMessage tmp = new TodoMessage();
		tmp.setId(id);
		int index = m.indexOf(tmp);
		TodoMessage todo;
		if(index >= 0) {
			todo = m.get(index);
		}else{
			todo = new TodoMessage();
			todo.setMessage("Unable to retrieve your message");
		}
		String json = "";	
		System.out.println("Your message is:" + todo.getMessage());
		try {
			json = new ObjectMapper().writeValueAsString(todo);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(json,"application/xml").build();
	}
	
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/delete/{id}")
	public Response deleteId(@PathParam("id") String p_id) {
		System.out.println("Attempting to delete Todo with id = " + p_id);
		int id = Integer.parseInt(p_id);
		List<TodoMessage> m = TodoMessageList.getMessages();
		TodoMessage tmp = new TodoMessage();
		tmp.setId(id);
		int index = m.indexOf(tmp);
		ServerResponse s = new ServerResponse();
		if(index >= 0) {
			m.remove(index);
			s.code = "Success";
			s.message = "Todo deleted successfully";
			System.out.println("Message deleted succesfully.");
		}
		else{
			s.code = "Error";
			s.message = "Unable to delete Todo";
			System.out.println("Error deleting message");
		}
		String json = "";	
		try {
			json = new ObjectMapper().writeValueAsString(s);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(json,"application/json").build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/all/json")
	public Response getAllMessagesJSON() {
		System.out.println("Getting all messages using JSON...");
		List<TodoMessage> m = TodoMessageList.getMessages();
		m.forEach(todo->System.out.println("ID# " + todo.getId() + " : " + todo.getMessage()));
		String json = "";	
		try {
			json = new ObjectMapper().writeValueAsString(m);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(json,"application/json").build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	@Path("/all/xml")
	public Response getAllMessagesXML() {
		System.out.println("Getting all messages using XML...");
		List<TodoMessage> m = TodoMessageList.getMessages();
		m.forEach(todo->System.out.println("ID# " + todo.getId() + " : " + todo.getMessage()));
		String xml = "";	
		try {
			xml = new ObjectMapper().writeValueAsString(m);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(xml,"application/xml").build();
	}
	
	
	private JAXBElement<TodoMessage> toXml(TodoMessage message){
		return new JAXBElement<TodoMessage>(new QName("todoMessage"), TodoMessage.class, message);
	}
	

}
