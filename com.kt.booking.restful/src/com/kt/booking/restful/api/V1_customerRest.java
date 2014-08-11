package com.kt.booking.restful.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;


import com.kt.booking.controller.CustomerCtrl;
import com.kt.booking.model.Customer;
import com.kt.booking.util.ToJSON;

@Path("/v1/customer")
public class V1_customerRest {

	// variabel using singalton 
	private CustomerCtrl manager = CustomerCtrl.getInstance();
	
	/**
	 * This method will allow you to insert data the Customer table.
	 * This is a example of using JSONArray and JSONObject
	 * @param incomingData
	 * @return
	 * @throws Exception
	 */

	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllTest(String incomingData) throws Exception {
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject(incomingData);

		try{
			JSONObject partsData = new JSONObject(incomingData);
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
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(returnString).build();
	}
	
	/**
	 * This method will return all Customer name that are listed
	 * in Customer table.
	 * 
	 * @return - JSON array string
	 * @throws Exception
	 */
	@Path ("/returnlist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllTest() throws Exception {
		String returnString = null;
		Response rb = null;	
		JSONArray json = new JSONArray();
		ToJSON converter = new ToJSON();
		try {

			json = converter.returnJSONCustomerList(manager.getCustomersList());

			returnString = json.toString();
			rb = Response.ok(returnString).build();

		}catch (Exception ex){
			ex.printStackTrace();
		}
		return rb;
	}

	/**
	 * This method does a search oncustomerID.
	 * It uses PathParam to bring in a parameter.
	 * 
	 * Example:
	 * http://localhost:7001/com.kt.booking.restful/api/v1/customer/1
	 * 
	 * @param id - customer id
	 * @return - json array results list from the database
	 * @throws Exception
	 */
	@Path ("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnSpecificBrandItem(@PathParam("id") Long id) throws Exception {
		String returnString = null;
		JSONArray json = new JSONArray();
		ToJSON converter = new ToJSON();
		try{
			json = converter.returnJSONCustomerList(manager.searchCustomerById(id));
			returnString = json.toString();

		}catch (Exception ex){
			ex.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}

		return Response.ok(returnString).build();
	}
}
