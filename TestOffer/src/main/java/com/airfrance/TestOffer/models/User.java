package com.airfrance.TestOffer.models;

import java.util.Date;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;
/**
  * <b> User is the class representing a member of the application for example. </ b>
  * <p>
  * A user is characterized by the following information:
  * <ul>
  * <li> A unique identifier permanently assigned. </ li>
  * <li> A name </ li>
  * <li> A surname </ li>
  * <li> A date birth </ li>
  * <li> A country of residence </ li>
  * <li> A phone number </ li>
  * </ ul>
  * </ p>
  *
  * @author Cedric Ortega
  * @version 1.0
  */
public class User {
	/**
	   * The ID of User.
	   *
	   *@see User#getId()
	*/
	@ApiModelProperty(notes = "ID User")
	private String id;
	
	/**
	    * The "name" of User. This "name" can be changed.
	    *
	    * @see User#getName()
	 */
	private String name;
	
	/**
	    * The "surName" of User. This "surName" can be changed.
	    *
	    * @see User#getSurName()
	 */
	private String surName;
	
	/**
	    * The "dateBirth" of User. This "dateBirth" can be changed.
	    * <p>
	    * "dateBirth" supports a specific format
	    * <p>
	    *
	    * @see User#getLevel ()
	 */
	@ApiModelProperty(notes = "Date birthday in format yyyy-MM-dd")
	private Date dateBirth;
	
	/**
	    * The "country" of user is country of residence . This "country" can be changed.
	    *
	    * @see User#getCountry()
	 */
	private String country;
	
	/**
	    * The "phoneNumber" of Zero. This "phoneNumber" is facultative and can be changed
	    *
	    * @see User#getPhoneNumber()
	 */
	private String phoneNumber;

	/**
	          * User constructor.
	          *
	          * @param name
	          * The unique identifier of User.
	          * @param surName
	          * The surname of User.
	          * @param dateBirth
	          * The date birth of User.
	          * @param country
	          * The country of User.
	          * @param phoneNumber
	          * The phone number of User.
	          *
	          * @see User#id
	          * @see User#name
	          * @see User#surName
	          * @see User#dateBirth
	          * @see User#country
	          * @see User#phoneNumber
	*/
	public User(String name, String surName, Date dateBirth, String country, String phoneNumber) {
		this.id = UUID.randomUUID().toString();
	    this.name = name;
	    this.surName = surName;
	    this.dateBirth = dateBirth;
	    this.country = country;
	    this.phoneNumber = phoneNumber;
	  }
	
	/**
	          * Returns the name of user.
	          *
	          * @return The user's name, in the form of a string of characters.
	*/
	  public String getName() { 
		  return name; 
	  }
	 
		/**
		          * Returns the surname of user.
		          *
		          * @return The user's surName, in the form of a string of characters.
		*/
	  public String getSurName() { return surName; }
	 
		/**
		          * Returns the date birth of user.
		          *
		          * @return The user's dateBirth, in the form of a string of characters.
		*/
	  public Date getDateBirth(){ 
		  return dateBirth; 
	  }
	 
		/**
		          * Returns the country of user.
		          *
		          * @return The user's country, in the form of a string of characters.
		*/
	  public String getCountry() { 
		  return country; 
	  }
	  
		/**
		          * Returns the phone number of user.
		          *
		          * @return The user's phoneNumber, in the form of a string of characters.
		*/
	  public String getPhoneNumber() { 
		  return phoneNumber; 
	  }
	  
		/**
		          * Returns the id of user.
		          *
		          * @return The user's id, in the form of a string of characters.
		*/
	  public String getId() {
		  return id;
	  }
	  
		/**
		          * Returns the String of user object.
		          *
		          * @return The user's object, in the form of a string of characters.
		*/
	  public String toString() {
		  return "Name : "+name+"\n"+ "Surname : "+surName+"\n"+ "Date birth : "+dateBirth+"\n"+ "Country : "+country+"\n"+"Phone number "+phoneNumber;
		  
	  }
}
