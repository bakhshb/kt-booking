package com.kt.booking.restful.get;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.kt.booking.util.ToJSON;

@Path("/v1/customer/")
public class V1_getCustomer {

	@Path ("/get.json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllTest() throws Exception {
		String returnString = null;
		Response rb = null;	
		JSONArray json = new JSONArray();
		ToJSON converter = new ToJSON();
		try {
			
			json = converter.queryReturnCustomerNames();
			
			returnString = json.toString();
			rb = Response.ok(returnString).build();
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return rb;
	}
}
