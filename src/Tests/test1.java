package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Models.Password;
import Models.Question;
import Models.Quiz;
import Models.User;

public class test1 {
	@Test
	public void testUserClass(){
		User user = new User(1, "satesto", "sdsa@gmail.com", "2016:09:12", 3, 1, null);
		assertEquals("satesto", user.getUserName());
		assertEquals("sdsa@gmail.com", user.getEmail());
		assertEquals(1, user.getId());
		assertEquals(3, user.getQuizNumber());
		assertEquals("2016:09:12", user.getRegDate());
		assertEquals(1, user.getType());
		assertTrue(!user.hasFriendById(2));
		user.addFriend("abc");
		assertTrue(user.hasFriendByUserName("abc"));
	}
	
	@Test
	public void testQuizClass(){
		Quiz quiz = new Quiz(1,"ravi me", "desc", "aut", "221", 2, false, false, new ArrayList<Question>(), true);
		assertEquals(1,quiz.getID());
		assertEquals("aut",quiz.getAuthor());
		assertEquals("ravi me",quiz.getTitle());
		assertEquals("desc",quiz.getDescription());
		assertEquals(0,quiz.getCurrentQuestionIndex());
		assertEquals("221",quiz.getCreateTime());
		assertEquals(0,quiz.getScore());
		assertEquals(2,quiz.getTimesWritten());
		assertTrue(!quiz.isRandomized());
		quiz.increaseQuestionCounter();
		assertEquals(1,quiz.getCurrentQuestionIndex());
		assertEquals(1,quiz.getID());
		assertTrue(!quiz.hasMoreQuestions());
	}
	
	@Test
	public void testPasswords(){
		assertEquals("adeb6f2a18fe33af368d91b09587b68e3abcb9a7", Password.getHash("fm"));
	}
}


