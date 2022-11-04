package Main;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import DataStructures.List.ArrayList;
import DataStructures.List.DoublyLinkedList;
import DataStructures.List.List;
import DataStructures.List.SinglyLinkedList;

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
public class ContactCard extends Directory  {

	//-----------Private fileds-------------
	private int id;
	private String name;
	private String jobTitle;
	private String phoneNumber;
	private String email;
	private List<Integer> friends;
	private Date birthday;
	private List<ContactCard> f;
	
	
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
	public List<Integer> getFriendsID() {return friends;}
	
	//Goes from ID to ContactCard of Friends
	public List<ContactCard> getFriends(){
		SinglyLinkedList<ContactCard> friendsList = new SinglyLinkedList<ContactCard>();
		for(int i=0; i< getFriendsID().size(); i++) {
			ContactCard friendCC = findContact(getFriendsID().get(i));	//findContact(ID) return ContactCard of ID   (location -> directory class) 
			friendsList.add(friendCC);
		}
		return friendsList;
	}
	
	public void setID(int id) {this.id = id;}
	public void setName(String name) {this.name = name;}
	public void setJobTitle(String jobTitle) {this.jobTitle = jobTitle;}
	public void setPhone(String phoneNumber) {this.phoneNumber = phoneNumber;}
	public void setEmail(String email) {this.email = email;}
	public void setFriends(List<Integer> friends) {this.friends = friends;}
	
	
	

	
	
}
