package com.airfrance.TestOffer.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.springframework.boot.logging.java.SimpleFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airfrance.TestOffer.models.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


/**
  * <b> UserController is the class to execute request of API. </ b>
  *
  * @author Cedric Ortega
  * @version 1.0
  */
@Api(description="Management Users")
@RestController
public class UserController {
	
	static Logger logger =  Logger.getLogger("logger");
	
	static { 
		FileHandler fh = null;
		try {
			fh = new FileHandler("UserController.log");
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter(); 
		fh.setFormatter(formatter);
	}
	
	
    /**
     * Create a user with POST request
     * 
     * @param name
     * @param surName
     * @param dateBirth
     * @param country
     * @param phoneNumber
     * 
     */
	@PostMapping(value = "/users",produces="application/json")
	@ApiOperation(response = User.class, value ="Create a new user")
	public ResponseEntity<?> createUser(
			
			@RequestBody(required=true) @RequestParam("name") String name, 
			@RequestBody(required=true) @RequestParam("sur-name") String surName,
			@ApiParam(value="Date birth in format yyyy-MM-dd") @RequestBody(required=true) @RequestParam("date-birth") String dateBirth,
			@RequestBody(required=true) @RequestParam("country") String country,
			@RequestBody(required=false) @RequestParam("phone-number") String phoneNumber
			
			){
		
				Date actualDate = new Date();
				
				String request = "\nPOST /users/ with body params : \n"+"name :"+name+"\nsur-name : "+surName+"\ndate-birth : "+dateBirth+"\ncountry : "+country+"\nphone-number : "+phoneNumber+"\n";
		
				ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Status 500 : Internal error.");

				if(phoneNumber.contentEquals("")) {
					phoneNumber = "No phone number";
				}
				else if (!phoneNumber.matches("[0-9]+")){
					responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Body parameter phone-number must be only numeric");
					return responseEntity;
				}
				
				
				
				DateFormat DATEFORMAT  = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat DATEFORMAT2  = new SimpleDateFormat("yyyyMMdd");
				Date dateBirthDate = null;
				try {
					dateBirthDate = DATEFORMAT.parse(dateBirth);
				} catch (ParseException e) {
					responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot parsing DateBirth to yyyy-MM-dd format");
					e.printStackTrace();
					return responseEntity;
				}
				
				// Obtain age user                	
			    int d1 = Integer.parseInt(DATEFORMAT2.format(dateBirthDate));                        	
			    int d2 = Integer.parseInt(DATEFORMAT2.format(actualDate));                      	
			    int age = (d2 - d1) / 10000;   

			    if(age<18) {
			    	responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User must be over 18 years old.");
			    	return responseEntity;
			    }
			    
			    String countryLowerCase = country.toLowerCase();
			    
			    if(!countryLowerCase.contentEquals("france")) {
			    	responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User must come from France");
			    	return responseEntity;
			    }
				
				User user =  new User(name,surName,dateBirthDate,countryLowerCase,phoneNumber);
				responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(user);
				Date dateFinish = new Date();
				long diffInMillies = dateFinish.getTime() - actualDate.getTime();
				logger.info("\nTime to process : "+diffInMillies+ " milliseconds\n"+request);
				return responseEntity;
				
		
	}
	
    /**
     * Displays the details of an registered user
     * 
     * @param idUser
     * 
     */
    @GetMapping(value = "/user-id/{id-user}",produces= "application/json")
    @ApiOperation(response = User.class, value = "View a user with id")
    public ResponseEntity<?> getUser(@PathVariable("id-user") String idUser){
    	String request = null;
		Date actualDate = new Date();
		
    	// init
    	
    	ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Status 500 : Internal Error");
    	
    	// authentification
    	
    	// check params
    	int idUserInt = -1;
    	try {
    		idUserInt = Integer.parseInt(idUser);
    	}
    	catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status 400 : parameter id-user must be an integer");
		}
    	
    	// Obtain data JSON
    	
    	JSONParser parser = new JSONParser();
    	
    	JSONArray a = null;
		try {
			a = (JSONArray) parser.parse(new FileReader("userData.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
    	  
		for (Object o : a)
    	  {
			DateFormat DATEFORMAT  = new SimpleDateFormat("yyyy-MM-dd");
    		  
    	    JSONObject user = (JSONObject) o;
      	    System.out.println(idUser);

    		  if(idUser.contentEquals((String) user.get("id"))) {
    			  System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    	    String name = (String) user.get("name");
    	    System.out.println(name);
    	    String surName = (String) user.get("surName");

    	    String dateBirth = (String) user.get("dateBirth");
    	    
			Date dateBirthDate = null;
		    try {
				dateBirthDate = DATEFORMAT.parse(dateBirth);
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	    
    	    String country = (String) user.get("country");
    	    
    	    String phoneNumber = (String) user.get("phoneNumber");
    	    
    	    User userGET = new User(name,surName,dateBirthDate,country,phoneNumber);
    	    request = "\nGET /users/ with body params : \n"+"name :"+name+"\nsur-name : "+surName+"\ndate-birth : "+dateBirth+"\ncountry : "+country+"\nphone-number : "+phoneNumber+"\n";
    	    responseEntity = ResponseEntity.status(HttpStatus.OK).body(userGET);
    		  }
    	  }
    	

		// process

		Date dateFinish = new Date();
		long diffInMillies = dateFinish.getTime() - actualDate.getTime();
		logger.info("\nTime to process : "+diffInMillies+ " milliseconds\n"+request);
		return responseEntity;
		}
    }
	


