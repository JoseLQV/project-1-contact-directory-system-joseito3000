package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


import javax.sound.sampled.Line;

import DataStructures.List.ArrayList;
import DataStructures.List.DoublyLinkedList;
import DataStructures.List.List;
import DataStructures.List.SinglyLinkedList;

public class Directory implements BaseDirectory {
	
	private List<ContactCard> allContacts;
	
	
	
	/**
	 * The createDirectory method
	 * 
	 * Takes in a path for it to be extracted into the ContactCard and Store all Contacts in a List
	 */
	public void createDirectory(String path) {
		// TODO Auto-generated method stub
				String line = "";
				// Read each line from Excel to eclipse
				ArrayList<ContactCard> allContact = new ArrayList<ContactCard>(20);
						try {
							BufferedReader br = new BufferedReader(new FileReader(path));
							
							
							while((line = br.readLine()) != null) {
								
								String[] columnValue = line.split(",");//excel into an array of strings 
								
								// ---------ContactCard fields-----------
								int id = Integer.parseInt(columnValue[0]);
								String name = columnValue[1];
								String jobTitle = columnValue[2];
								String phoneNumber = columnValue[3];
								String email = columnValue[4];
								
								//creating Date obj
								String birthdate = columnValue[5];
								String[] convertedBday = birthdate.split("-");
								int year = Integer.parseInt(convertedBday[0]);
								int month = Integer.parseInt(convertedBday[1]);
								int day = Integer.parseInt(convertedBday[2]);
								Date newBday = new Date(year, month, day);
								
								//creating friends List of the contact
								List<Integer> friends = new DoublyLinkedList<Integer>();
								for(int i = 6 ; i< columnValue.length;i++) {
									if(columnValue[i].equals("")) {
										//ignore it
									}
									else {
										friends.add(Integer.valueOf(columnValue[i]));
									}
								}
								
								//---------Store all contacts in a ArrayList------------
								allContact.add(new ContactCard(id,name,jobTitle,phoneNumber,email, friends, newBday));
								
							}
						}catch (FileNotFoundException e){
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						this.allContacts = allContact;
	}
	
	/**
	 * The recommenedFriends method
	 * 
	 * Takes a contact card and finds friends of friends that contact DONT have in common
	 */
	@Override
	public List<ContactCard> recommendedFriends(ContactCard contact){
		// TODO Auto-generated method stub
		DoublyLinkedList<ContactCard> recommendedList = new DoublyLinkedList<ContactCard>();
		
		for(int i=0; i<contact.getFriends().size();i++) {
			
			ContactCard hisFriend = findContact(contact.getFriends().get(i));// going through all contact friends ids
			
			if(!hisFriend.getFriends().isEmpty()) {
				for(int id: hisFriend.getFriends()) {// friends of friend
					if(id != contact.getID() && !contact.getFriends().contains(id) && !recommendedList.contains(findContact(id))) {
						recommendedList.add(findContact(id));
					}
				}
			}
		}
		return recommendedList;
	}

	/**
	 * The commonFriends method
	 * 
	 * Takes a contact card and finds friends of friends that contact HAVE in common
	 */
	@Override
	public List<ContactCard> commonFriends(ContactCard c1, ContactCard c2) {
		// TODO Auto-generated method stub
		DoublyLinkedList<ContactCard> commonList = new DoublyLinkedList<ContactCard>();
		
		if(c1.getFriends().isEmpty() || c2.getFriends().isEmpty()) { //if empty no need to check
			return null;
		}
		
		for(int id: c1.getFriends()) {//checking common friends and adding them in commonList
			if(c2.getFriends().contains(id)) {
				commonList.add(findContact(id));
			}
		}
		
		return commonList;
	}

	/**
	 * The shareBirthdays method
	 * 
	 * Takes in Contact and checks all contacts for same birthday 
	 * and return List of those contacts
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<ContactCard> shareBirthdays(ContactCard contact) {
		// TODO Auto-generated method stub
		DoublyLinkedList<ContactCard> sharedBday = new DoublyLinkedList<ContactCard>();
		for(ContactCard c : getContacts()) {
			if(c.getID() != contact.getID() && c.getBirthday().getDate() == contact.getBirthday().getDate() && c.getBirthday().getMonth() == contact.getBirthday().getMonth()) {
				sharedBday.add(c);
			}
	
		}
		return sharedBday;
	}
	
	
	/**
	 * The getContacts method
	 * 
	 * returns Arraylist that contains all contactCards that is extracted from createDirectory
	 */
	public List<ContactCard> getContacts() {
		// TODO Auto-generated method stub
		return this.allContacts;
	}
	
	/** 
	 * The findContact method
	 * 
	 * Converts from ID to ContactCard
	 */
	public ContactCard findContact(int id) {
		for(int i = 0; i<getContacts().size(); i++) {
			if(getContacts().get(i).getID() == id) {
				return getContacts().get(i);
			}
		}
		return null;
		
	}

	
}
