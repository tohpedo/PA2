

import java.sql.Connection;
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

import connectors.SQLConnector;
import models.Customer;
import models.Transaction;


@Path("/")
public class TodoMessages{
	
	private static final long serialVersionUID = 1L;
	private static SQLConnector sql_conn = new SQLConnector();
	
	
	public TodoMessages() {
		sql_conn.initializeDB();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.TEXT_HTML})
	@Path("/create")
	public Response createMessage(@FormParam("address") String address, @FormParam("checkin") String checkin,
								@FormParam("checkout") String checkout, @FormParam("fname") String fname,
								@FormParam("lname") String lname, @FormParam("phone_num") String phone_num,
								@FormParam("state") String state, @FormParam("city") String city,
								@FormParam("zip") String zip){
		Customer tmp = new Customer();
		tmp.setAddress(address);
		tmp.setCheckin(checkin);
		tmp.setCheckout(checkout);
		tmp.setCity(city);
		tmp.setFname(fname);
		tmp.setLname(lname);
		tmp.setPhone_num(phone_num);
		tmp.setState(state);
		tmp.setZip(zip);
		try{
			sql_conn.insertCustomer(tmp);
		}
		catch(Exception e){
			String success = "<h3> Error occured while creating customer </h3>";
			return Response.ok(success,"text/html").build();
		}
		String success = "<h3> You have successfully inserted a customer with id#" + tmp.getCust_id() + "</h3>";
		return Response.ok(success,"text/html").build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.TEXT_HTML})
	@Path("/reserve")
	public Response reserveRoom(@FormParam("cust_id") String cust_id, @FormParam("room_num") String room_num){	
		try{
			sql_conn.reserveRoom(Integer.parseInt(cust_id),Integer.parseInt(room_num));
		}
		catch(Exception e){
			String success = "<h3> Error making reservation </h3>";
			return Response.ok(success,"text/html").build();
		}
		String success = "<h3> You have successfully made your reservation</h3>";
		return Response.ok(success,"text/html").build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.TEXT_HTML})
	@Path("/findId")
	public Response getCustomerId(@FormParam("cust_id") String cust_id){	
		String output = "";
		try{
			output = sql_conn.getCustomer(Integer.parseInt(cust_id));
		}
		catch(Exception e){
			output = "<h3> Error while finding customer </h3>";
			return Response.ok(output,"text/html").build();
		}
		String success = "<h3> " + output + "</h3>";
		return Response.ok(output,"text/html").build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.TEXT_HTML})
	@Path("/findName")
	public Response getCustomerName(@FormParam("cust_name") String cust_name){	
		String output = "";
		try{
			output = sql_conn.getCustomersByName(cust_name);
		}
		catch(Exception e){
			output = "<h3> Error while finding customer </h3>";
			return Response.ok(output,"text/html").build();
		}
		String success = "<h3> " + output + "</h3>";
		return Response.ok(output,"text/html").build();
	}
	
	@GET
	@Produces({ MediaType.TEXT_HTML })
	@Path("/all")
	public Response getCurrentCustomers() {
		String output = "";
		try{
			output = sql_conn.getCurrentCustomers();
		}
		catch(Exception e){
			output = "<h3> Error while finding customers </h3>";
			return Response.ok(output,"text/html").build();
		}
		String success = "<h3> " + output + "</h3>";
		return Response.ok(output,"text/html").build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.TEXT_HTML})
	@Path("/pay")
	public Response createPayment(@FormParam("cust_id") String cust_id, @FormParam("room_num") String room_num,
								@FormParam("card_num") String card_num, @FormParam("exp_date") String exp_date,
								@FormParam("amount") String amount){
		Transaction tmp = new Transaction();
		tmp.setAmount(amount);
		tmp.setCc(card_num);
		tmp.setExp_date(exp_date);
		tmp.setPayee_id(Integer.parseInt(cust_id));
		tmp.setRoom_num(Integer.parseInt(room_num));
		try{
			sql_conn.makePayment(tmp);
		}
		catch(Exception e){
			String success = "<h3> Error occured while making payment </h3>";
			return Response.ok(success,"text/html").build();
		}
		String success = "<h3> You have successfully made a payment. Your transaction ID is  " + tmp.getTrans_id() + "</h3>";
		return Response.ok(success,"text/html").build();
	}
	
}
