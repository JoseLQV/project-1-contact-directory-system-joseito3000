package Main;

import DataStructures.List.List;

public class MyTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] fechas = {"2020","12","5"};
//		Date d = new Date(fechas);
		
		
		Directory d1 = new Directory();
		d1.createDirectory("inputFiles/directory.csv");
		List<ContactCard> temp = d1.getContacts();
		ContactCard testBday = temp.get(8);
		
		for(ContactCard b: d1.shareBirthdays(testBday)) {
			System.out.println(b.getName() + " : " + b.getID() );
		}
		
		
//		for (ContactCard c : temp) {
//			System.out.println(c.getName());
//		}
		
		
		
	}

}
