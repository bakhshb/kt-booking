package com.kiwianatours.ktbooking.web.rest.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadServlet extends HttpServlet {
	private final Logger log = LoggerFactory.getLogger(FileUploadServlet.class);

	private static final long serialVersionUID = 2857847752169838915L;
	int BUFFER_LENGTH = 4096;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Start uploading Files to {}", request.getServerName());
		
		LocalTime time = new LocalTime();
		LocalDate  date = new LocalDate();
		int parseTime = time.getHourOfDay() +time.getMillisOfSecond() ;
		String parseDate =date.toString();
		String parseDateNTime = parseTime +"_"+ parseDate;
		
		String finalPath = null;
		//check which profile is running
		if (request.getServerName().equals("localhost")){
			// files
			File currentDirFile = new File("");
			String absolutePath = currentDirFile.getAbsolutePath();	
			File newDirFile = new File(absolutePath);
			finalPath = newDirFile.getParent();
		}else{
			finalPath = System.getenv("OPENSHIFT_DATA_DIR");
		}
		boolean success = new File(finalPath+ "/upload").mkdir();
    	boolean exist = new File (finalPath +"/upload").exists();
    	log.debug("File Location File Upload Servlet" ,finalPath );
    	if (success || exist){
			for (Part part : request.getParts()) {
				InputStream is = request.getPart(part.getName()).getInputStream();
				String fileName = getFileName(part);
				String fileType = new MimetypesFileTypeMap().getContentType(fileName).toString().replace("/", ".");
				FileOutputStream os = new FileOutputStream(finalPath +"/upload/" +parseDateNTime+ fileType);
				response.addHeader("fileName", parseDateNTime+ fileType);
				byte[] bytes = new byte[BUFFER_LENGTH];
				int read = 0;
				while ((read = is.read(bytes, 0, BUFFER_LENGTH)) != -1) {
					os.write(bytes, 0, read);
				}
				os.flush();
				is.close();
				os.close();
				log.debug(parseDateNTime+ fileType + " was uploaded to " + finalPath +"/upload/");
			}
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath = request.getRequestURI();
		String finalPath = null;
		if (request.getServerName().equals("localhost")){
			// files
			File currentDirFile = new File("");
			String absolutePath = currentDirFile.getAbsolutePath();	
			File newDirFile = new File(absolutePath);
			finalPath = newDirFile.getParent();
		}else{
			finalPath = System.getenv("OPENSHIFT_DATA_DIR");
		}
		log.info("Reading Files from {}", finalPath +"/upload/");
		File file = new File(finalPath +"/upload/" + filePath.replace("/uploads/",""));
		InputStream input = new FileInputStream(file);

		response.setContentLength((int) file.length());
		//response.setContentType(new MimetypesFileTypeMap().getContentType(file));

		OutputStream output = response.getOutputStream();
		byte[] bytes = new byte[BUFFER_LENGTH];
		int read = -1;
		while ((read = input.read(bytes, 0, BUFFER_LENGTH)) != -1) {
			output.write(bytes, 0, read);
			output.flush();
		}

		input.close();
		output.close();
	}

	private String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}
}
