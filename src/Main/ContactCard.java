package Main;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import DataStructures.List.ArrayList;
import DataStructures.List.DoublyLinkedList;
import DataStructures.List.List;
import java.util.Date;
/**
 * Contact Card Class to hold all the information 
 * regarding one contact inside the directory
 * 
 * @author bermed28 & Jose Luis Quinones Velez(joseito3000)
 */



/**
 * The ContactCard class
 * 
 * Has the Contact valuable Information stored
 * 
 * */
public class ContactCard  {

	//-----------Private fileds-------------
	private int id;
	private String name;
	private String jobTitle;
	private String phoneNumber;
	private String email;
	private List<Integer> friends;
	private Date birthday;
	
	
	//------------Constructors--------------------	
	ContactCard(){
		this.id = -1;
		this.name = null;
		this.jobTitle = null;
		this.phoneNumber= null;
		this.email = null;
		this.friends = null;
		this.birthday = null;
	}
	
	ContactCard(int id, String name, String jobTitle, String phoneNumber, String email, List<Integer> friends, Date bDay){
		this.id = id;
		this.name = name;
		this.jobTitle = jobTitle;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.friends = friends;
		this.birthday = bDay;
	}
	
	
	//--------Getters and Setters-----------
	public int getID() {return id;}
	public String getName() {return name;}
	public String getJobTitle() {return jobTitle;}
	public String getPhone() {return phoneNumber;}
	public String getEmail() {return email;}
	public Date getBirthday() {return birthday;}
	public List<Integer> getFriends() {return friends;}
	
	
	public void setID(int id) {this.id = id;}
	public void setName(String name) {this.name = name;}
	public void setJobTitle(String jobTitle) {this.jobTitle = jobTitle;}
	public void setPhone(String phoneNumber) {this.phoneNumber = phoneNumber;}
	public void setEmail(String email) {this.email = email;}
	public void setFriends(List<Integer> friends) {this.friends = friends;}
	
	
	

	
	
}
