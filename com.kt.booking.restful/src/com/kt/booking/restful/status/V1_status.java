package com.kt.booking.restful.status;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path ("v1/")
public class V1_status {

	private static final String api_version = "00.0.1.00";
	@Path ("/status")
	@GET 
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String returnTitle (){
		return "<p>Java Web Service</p>";
	}
	
	@Path("/version")
	@GET 
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String returnVersion(){
		return "<p>Version: </p>" + api_version + " Beta";
	}
}
