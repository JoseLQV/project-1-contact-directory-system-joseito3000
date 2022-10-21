package Main;

import DataStructures.List.ArrayList;

/**
 * Contact Card Class to hold all the information 
 * regarding one contact inside the directory
 * 
 * @author bermed28 & Jose Luis Quinones Velez(joseito3000)
 */
public class ContactCard{

	//-----------Private fileds-------------
	private int id;
	private String name;
	private String jobTitle;
	private String phoneNumber;
	private String email;
	
	//Birthday and Friend id different class
	
	
	
	//------------Constructor--------------------
//	ContactCard(String[] columnValue){
//		
//	}
	
	//excel to individual info
//	ContactCard(String[] columnValue){
//		id = Integer.parseInt(columnValue[0]);
//		name = columnValue[1];
//		jobTitle = columnValue[2];
//		phoneNumber = Integer.parseInt(columnValue[3]);
//		email = columnValue[4];
//	}
	
	ContactCard(){
		this.id = (Integer) null;
		this.name = null;
		this.jobTitle = null;
		this.phoneNumber= null;
		this.email = null;
	}
	
	ContactCard(int id, String name, String jobTitle, String phoneNumber, String email){
		this.id = id;
		this.name = name;
		this.jobTitle = jobTitle;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	
	//--------Getters and Setters-----------
	public int getID() {return id;}
	public String getName() {return name;}
	public String getJobTitle() {return jobTitle;}
	public String getPhone() {return phoneNumber;}
	public String getEmail() {return email;}
	
	public void setID(int id) {this.id = id;}
	public void setName(String name) {this.name = name;}
	public void setJobTitle(String jobTitle) {this.jobTitle = jobTitle;}
	public void setPhone(String phoneNumber) {this.phoneNumber = phoneNumber;}
	public void setEmail(String email) {this.email = email;}


}
