
package testers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import DataStructures.List.List;
import Main.ContactCard;
import Main.Directory;

public class DirectoryTester1 {
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
	 * Shared birthday tests
	 */
	@Test
	public void testSharedBirthday_ValentineBabies() {
		ContactCard jhonny = findContact(141);
		int[] valentineBabies = new int[] {505, 528, 138, 444, 279};
		
		List<ContactCard> sharedbday = dir.shareBirthdays(jhonny);
		assertTrue("Failed to return a list {505, 528, 138, 444, 279}"
				+ "when calling sharedBirthdays(141).", checkContent(valentineBabies, sharedbday));
	}
	@Test
	public void testSharedBirthday_NotSharedWithFriends() {
		// Shares birthday with someone in the directory, but they aren't his friend
		ContactCard nigelUno = findContact(111);
		
		List<ContactCard> sharedbday = dir.shareBirthdays(nigelUno);
		assertTrue("Failed to return empty list for sharedBirthdays(111).", 
				sharedbday.isEmpty());
	}
	@Test
	public void testSharedBirthday_ExactSameBirthday() {
		// Shares exact same birthday date
		ContactCard ben10 = findContact(10);
		
		List<ContactCard> sharedbday = dir.shareBirthdays(ben10);
		assertTrue("Failed to return a list {10000}"
				+ "when calling sharedBirthdays(10).", checkContent(new int[] {10000}, sharedbday));
	}
	/*
	 * Testing recommended friends
	 */
	@Test
	public void testRecommendedFriends_ForeverAlone() {
		ContactCard carmenSandiego = findContact(202);
		// Should be empty, she has no friends, only trusts herself
		List<ContactCard> FoFs = dir.recommendedFriends(carmenSandiego);
		assertTrue("Failed to return empty list when calling "
				+ "recommendedFriends(202).", FoFs.isEmpty());
	}
	@Test
	public void testRecommendedFriends_FriendWithSocialButterflies() {
		// Has few friends, but those friends have too many friends (most of which are in common)
		ContactCard stevenDemayo = findContact(505);
		List<ContactCard> FoFs = dir.recommendedFriends(stevenDemayo);
		int[] suggIds = new int[] {976, 555, 528, 333, 138, 444, 111};
		assertTrue("Failed to return the list {976, 555, 528, 333, 138, 444, 111} when calling "
				+ "recommendedFriends(505).", checkContent(suggIds, FoFs));
	}
	@Test
	public void testRecommendedFriends_WhoNeedsMoreFriends() {
		// Had same friends since kindergarden, doesn't want any more
		ContactCard ashleySpinelli = findContact(59);

		List<ContactCard> FoFs = dir.recommendedFriends(ashleySpinelli);
		assertTrue("Failed to return an empty list "
				+ "recommendedFriends(59).", FoFs.isEmpty());
	}

	/*
	 * Testing contact list
	 */
	@Test
	public void testContactList() {
		List<ContactCard> dirContacts = dir.getContacts();
		int[] expectedIDs = new int[] {177, 111, 146, 315, 505, 528, 555, 138, 976, 
				58, 202, 444, 279, 398, 745, 59, 333, 456, 856, 141, 10, 10000};
		assertTrue("Failed to return all the expected contacts.", checkContent(expectedIDs, dirContacts));
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
