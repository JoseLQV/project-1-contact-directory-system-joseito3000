package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

import javax.sound.sampled.Line;

import DataStructures.List.ArrayList;
import DataStructures.List.List;
import DataStructures.List.SinglyLinkedList;




public class Directory extends ContactCard implements BaseDirectory {
	
	public void createDirectory(String path) {
		// TODO Auto-generated method stub
		
		String line = "";
		// Read each line from Excel to eclipse
				try {
					BufferedReader br = new BufferedReader(new FileReader(path));
					
					ArrayList<ContactCard> allContact = new ArrayList<ContactCard>(20);
					while((line = br.readLine()) != null) {
						
						//ArrayList<ContactCard> contactInfo = new ArrayList<ContactCard>(10); //new ArrayList
						String[] columnValue = line.split(",");//excel into an array of strings 

						
						int id = Integer.parseInt(columnValue[0]);
						String name = columnValue[1];
						String jobTitle = columnValue[2];
						String phoneNumber = columnValue[3];
						String email = columnValue[4];
						
						allContact.add(new ContactCard(id,name,jobTitle,phoneNumber,email));
					}
				}catch (FileNotFoundException e){
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
	}

	@Override
	public List<ContactCard> recommendedFriends(ContactCard contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContactCard> commonFriends(ContactCard c1, ContactCard c2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContactCard> shareBirthdays(ContactCard contact) {
		// TODO Auto-generated method stub
		return null;
	}

}
