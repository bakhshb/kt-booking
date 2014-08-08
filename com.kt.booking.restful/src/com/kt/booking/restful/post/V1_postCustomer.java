package com.kt.booking.restful.post;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.kt.booking.controller.CustomerManager;
import com.kt.booking.model.Customer;

@Path ("v1/customer")
public class V1_postCustomer {
	
	/*@Path("/post.json")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllTest(String incomingData) throws Exception {
		String returnString = null;
		Response rb = null;	
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject(incomingData);
		
		try{
			JSONObject partsData = new JSONObject(incomingData);
			CustomerManager manager = new CustomerManager();
			Customer customer = new Customer ();
			customer.setFirstName(partsData.optString("first_name"));
			int http_code  =manager.addCustomer(customer);
			if( http_code == 200 ) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been entered successfully, Version 3");
				returnString = jsonArray.put(jsonObject).toString();
			}else {
				return Response.status(500).entity("Unable to enter Item").build();
			}
		}catch (Exception ex){
			ex.printStackTrace();
			//return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(returnString).build();
	}
*/
}
