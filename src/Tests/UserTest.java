package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Models.User;

public class UserTest {
	@Test
	public void testConstructor1() {
		User u = new User(1, "Sheldon", "dr.cooper@gmail.com", "01.09.2016", 15, 2, null);
		assertTrue(u.getId()==1);
		assertTrue(u.getUserName().equals("Sheldon"));
		assertTrue(u.getEmail().equals("dr.cooper@gmail.com"));
		assertTrue(u.getRegDate().equals("01.09.2016"));
		assertTrue(u.getQuizNumber()==15);
		assertTrue(u.getType()==2);
		assertTrue(u.getFriends().size()==0);
	}
	
	@Test
	public void testConstructor2() {
		ArrayList<String> friends = new ArrayList<>();
		friends.add("Leonard");
		friends.add("Raj");
		friends.add("Hovard");
		friends.add("Penny");
		User u = new User(1, "Sheldon", "dr.cooper@gmail.com", "01.09.2016", 15, 2, friends);
		assertTrue(u.getFriends().size()==4);
		assertTrue(u.hasFriendByUserName("Leonard"));
		assertTrue(u.hasFriendByUserName("Hovard"));
		assertTrue(u.hasFriendByUserName("Raj"));
		assertTrue(u.hasFriendByUserName("Penny"));
		assertTrue(!u.hasFriendByUserName("Amy"));
	}
	
	@Test
	public void testAddFriend() {
		User u = new User(1, "Sheldon", "dr.cooper@gmail.com", "01.09.2016", 15, 2, null);
		u.addFriend("Leonard");
		u.addFriend("Raj");
		assertTrue(u.hasFriendByUserName("Leonard")==true);
		assertTrue(u.hasFriendByUserName("Raj")==true);
		assertTrue(u.hasFriendByUserName("Hovard")==false);
		assertTrue(u.getFriends().size()==2);
		
	}
	
	@Test
	public void testRemoveFriend() {
		ArrayList<String> friends = new ArrayList<>();
		friends.add("Leonard");
		friends.add("Raj");
		friends.add("Hovard");
		friends.add("Penny");
		User u = new User(1, "Sheldon", "dr.cooper@gmail.com", "01.09.2016", 15, 2, friends);
		u.removeFriend("Hovard");
		u.removeFriend("Penny");
		assertTrue(!u.hasFriendByUserName("Hovard"));
		assertTrue(!u.hasFriendByUserName("Penny"));
		u.removeFriend("Amy");
		assertTrue(!u.hasFriendByUserName("Amy"));
		assertTrue(u.hasFriendByUserName("Leonard"));
		assertTrue(u.hasFriendByUserName("Raj"));
		
	}
	
	@Test
	public void testAddFriends1() {
		ArrayList<String> friends = new ArrayList<>();
		friends.add("Leonard");
		User u = new User(1, "Sheldon", "dr.cooper@gmail.com", "01.09.2016", 15, 2, friends);
		ArrayList<String> newFriends = new ArrayList<>();
		newFriends.add("Penny");
		newFriends.add("Amy");
		newFriends.add("Bernadette");
		u.addFriends(newFriends);
		assertTrue(u.hasFriendByUserName("Penny"));
		assertTrue(u.hasFriendByUserName("Amy"));
		assertTrue(u.hasFriendByUserName("Bernadette"));
	}
	
	@Test
	public void testAddFriends2() {
		ArrayList<String> friends = new ArrayList<>();
		friends.add("Leonard");
		User u = new User(1, "Sheldon", "dr.cooper@gmail.com", "01.09.2016", 15, 2, friends);
		u.addFriends(null);
		assertTrue(u.hasFriendByUserName("Leonard"));
		assertTrue(!u.hasFriendByUserName("Amy"));
	}
}
