package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Models.Quiz;
import Models.Question;

public class QuizTest {
	@Test
	public void testConstructor() {
		ArrayList<Question> questions = new ArrayList<Question>();
		Quiz q = new Quiz(1, "quiz1", "first test quiz", "Raj", "06.07.2016", 1, true, true, questions, false);
		assertTrue(q.getID()==1);
		assertTrue(q.getTitle().equals("quiz1"));
		assertTrue(q.getDescription().equals("first test quiz"));
		assertTrue(q.getAuthor().equals("Raj"));
		assertTrue(q.isImmediateCorrection());
		assertTrue(q.isRandomized());
		assertTrue(!q.displaySingleQuestion());
	}
}
