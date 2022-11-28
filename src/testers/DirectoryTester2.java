package testers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import DataStructures.List.List;
import Main.ContactCard;
import Main.Directory;

public class DirectoryTester2 {
	private Directory dir;
	
	@Before
	public void setup() {
		this.dir = new Directory();
		dir.createDirectory("inputFiles/directory2.csv");
	}
	/*
	 * TESTING THE DIRECTORY CLASS
	 */
	/*
	 * Testing common friends
	 */
	@Test
	public void testCommonFriends_BFFsOnly() {
		// Common friends? All of them
		ContactCard ashleyTomassian = findContact(398);
		ContactCard ashleyArmbuster = findContact(177);

		int[] theAshleys = new int[] {59, 146, 58};

		List<ContactCard> CFs = dir.commonFriends(ashleyArmbuster, ashleyTomassian);
		assertTrue("Failed to return a list {59, 146, 58} when calling "
				+ "commonFriends(398, 177).", checkContent(theAshleys, CFs));
	}
	@Test
	public void testCommonFriends_CodenameFriendship() {
		// They're mutual friend
		ContactCard nigelUno = findContact(111);
		ContactCard abigalLincoln = findContact(555);

		int[] suggIds = new int[] {444, 333, 456, 856, 279};
		List<ContactCard> CFs = dir.commonFriends(nigelUno, abigalLincoln);
		assertTrue("Failed to return a list with the contacts of IDs {444, 333, 456, 856, 279} when calling "
				+ "commonFriends(111, 555).", checkContent(suggIds, CFs));
	}
	@Test
	public void testCommonFriends_KnowThemByProxy() {
		// Two strangers that have the same social circles
		ContactCard arnold = findContact(456);
		ContactCard helga = findContact(856);

		int[] suggIds = new int[] {111, 555, 315};
		List<ContactCard> CFs = dir.commonFriends(arnold, helga);
		assertTrue("Failed to return a list with the contacts of IDs {111, 555, 315} when calling "
				+ "commonFriends(456, 856).", checkContent(suggIds, CFs));
	}
	/*
	 * TESTING CONTACT CARDS
	 */
	@Test
	public void testingContactCard1() {
		ContactCard shaggy = findContact(315);
		// Check each field
		boolean checkID = shaggy.getID() == 315;
		boolean checkName = shaggy.getName().equals("Norvile Rogers");
		boolean checkJobTitle = shaggy.getJobTitle().equals("Detective");
		boolean checkPhone = shaggy.getPhone().equals("7875967410");
		boolean checkEmail = shaggy.getEmail().equals("norvile.rogers@mail.com");
		
		boolean checkFriends = checkContent(new int[] {138,745,444,456,856}, shaggy.getFriends());
		// Not checking birthday. If birthday method works the birthday should be fine.
		assertTrue("There's incorrect data in the contact card.", checkID && checkName && 
				checkJobTitle && checkPhone && checkEmail && checkFriends);
	}
	@Test
	public void testingContactCard2() {
		ContactCard carmen = findContact(202);
		// Check each field
		boolean checkID = carmen.getID() == 202;
		boolean checkName = carmen.getName().equals("Carmen Sandiego");
		boolean checkJobTitle = carmen.getJobTitle().equals("Museum Curator");
		boolean checkPhone = carmen.getPhone().equals("7874563256");
		boolean checkEmail = carmen.getEmail().equals("carmen.sandiego@mail.com");
		
		boolean checkFriends = carmen.getFriends().isEmpty();
		// Not checking birthday. If birthday method works the birthday should be fine.
		assertTrue("There's incorrect data in the contact card.", checkID && checkName && 
				checkJobTitle && checkPhone && checkEmail && checkFriends);
	}
	@Test
	public void testingContactCard3() {
		ContactCard abigal = findContact(555);
		// Check each field
		boolean checkID = abigal.getID() == 555;
		boolean checkName = abigal.getName().equals("Abigal Lincoln");
		boolean checkJobTitle = abigal.getJobTitle().equals("Lieutenant");
		boolean checkPhone = abigal.getPhone().equals("7874587536");
		boolean checkEmail = abigal.getEmail().equals("abigal.lincoln@mail.com");
		
		boolean checkFriends = checkContent(new int[] {111, 444, 333, 279, 456, 856}, abigal.getFriends());
		// Not checking birthday. If birthday method works the birthday should be fine.
		assertTrue("There's incorrect data in the contact card.", checkID && checkName && 
				checkJobTitle && checkPhone && checkEmail && checkFriends);
	}
	@Test
	public void testingContactCard4() {
		//Two contact, same name
		ContactCard ben10 = findContact(10);
		ContactCard ben10000 = findContact(10000);
		// Check each field for Ben 10
		boolean checkID1 = ben10.getID() == 10;
		boolean checkName1 = ben10.getName().equals("Ben Tennyson");
		boolean checkJobTitle1 = ben10.getJobTitle().equals("Security");
		boolean checkPhone1 = ben10.getPhone().equals("78775153595");
		boolean checkEmail1 = ben10.getEmail().equals("ben.tennyson10@mail.com");
		
		boolean checkFriends1 = checkContent(new int[] {10000}, ben10.getFriends());
		
		// Check each field for Ben 10000
		boolean checkID2 = ben10000.getID() == 10000;
		boolean checkName2 = ben10000.getName().equals("Ben Tennyson");
		boolean checkJobTitle2 = ben10000.getJobTitle().equals("Security");
		boolean checkPhone2 = ben10000.getPhone().equals("78775153596");
		boolean checkEmail2 = ben10000.getEmail().equals("ben.tennyson10000@mail.com");
		
		boolean checkFriends2 = checkContent(new int[] {10}, ben10000.getFriends());
		
		boolean ben10Check = checkID1 && checkName1 && checkJobTitle1 && checkPhone1 && checkEmail1 && checkFriends1;
		boolean ben10000Check = checkID2 && checkName2 && checkJobTitle2 && checkPhone2 && checkEmail2 && checkFriends2;
		
		// Not checking birthday. If birthday method works the birthday should be fine.
		assertTrue("There's incorrect data in the contact card.", ben10Check && ben10000Check);
	}
	/*
	 * AUXILIARY METHODS 
	 */
	/**
	 * Returns the contact of the given ID from the directory.
	 * @param id - (int) Id of the contact we wish to find.
	 * @return (ContactCard) The contact with ID id
	 */
	private ContactCard findContact(int id) {
		for(ContactCard c: dir.getContacts()) {
			if(c.getID() == id)
				return c;
		}
		return null;
	}
	/**
	 * Returns whether all the IDs given are present in the List and it contains exclusively those IDs.
	 * @param ids - (int[]) IDs of the contacts expected to be in the list
	 * @param contacts - (List<ContactCard>) List of contacts that we will compare to the IDs given
	 * @return (boolean) True if all the IDs are present in the List and both are the same size, false otherwise
	 */
	private boolean checkContent(int[] ids, List<ContactCard> contacts) {
		// By default they cannot be the same
		if(ids.length != contacts.size())
			return false;
		// Check if the ids are present
		for(int id: ids) {
			boolean found = false;
			for(ContactCard c: contacts) {
				if(c.getID() == id) {
					found = true;
					break;
				}
			}
			if(!found)
				return false;
		}
		return true;
	}
}

