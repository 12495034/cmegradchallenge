package cmegradtest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmegradtest.Submission;

class SubmissionTest {

	String validUserName1;
	String invalidUsernameNumber;
	String invalidUsernameSpace;
	String validWord1;
	String validWord2;
	String InvalidWord1;
	String InvalidWord2;
	String anagramWord1;
	String anagramWord2;
	static Submission s;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		validUserName1 = "Gavin";
		invalidUsernameNumber = "Gav1n";
		invalidUsernameSpace = "Gavin ";
		validWord1 = "friend";
		validWord2 = "finder";
		InvalidWord1 = "friend ";
		InvalidWord2 = "fr1nd";
		//valid inputs that are a known anagram
		anagramWord1 = "friend";
		anagramWord2 = "finder";

		s = new Submission();
	}

	//testing getters and setters
	@Test
	void testGetSetUserNameValid() {
		s.setUserName(validUserName1);
		assertEquals("Gavin", s.getUserName());
	}

	@Test
	void testGetSetWord1Valid() {
		s.setTextInput1(validWord1);
		assertEquals("friend", s.getTextInput1());
	}

	@Test
	void testGetSetWord2Valid() {
		s.setTextInput2(validWord2);
		assertEquals("finder", s.getTextInput2());
	}

	@Test
	void testGetSetUserNameInvalid() {
		IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> {
			s.setUserName(invalidUsernameNumber);
		});
		
		assertEquals("Invalid input Gav1n, please check input rules",exception1.getMessage());
		
		IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {
			s.setUserName(invalidUsernameSpace);
		});
		
		assertEquals("Invalid input Gavin , please check input rules",exception2.getMessage());
	}
	
	@Test
	void testGetSetTextInputInvalid() {
		IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, () -> {
			s.setTextInput1(InvalidWord1);
		});
		
		assertEquals("Invalid input friend , please check input rules",exception3.getMessage());
		
		IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class, () -> {
			s.setTextInput2(InvalidWord2);
		});
		
		assertEquals("Invalid input fr1nd, please check input rules",exception4.getMessage());
	}
	
	//testing constructor for valid and invalid values
	
	@Test
	void testSubmissionConstructorValid() {
		Submission s = new Submission(validUserName1, validWord1, validWord2);
		assertEquals("Gavin",s.getUserName());
		assertEquals("friend",s.getTextInput1());
		assertEquals("finder",s.getTextInput2());
	}
	
	@Test
	void testSubmissionConstructorInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			Submission s1 = new Submission(invalidUsernameNumber,validWord1, validWord2);
		});
		
		
		 assertThrows(IllegalArgumentException.class, () -> {
			Submission s2 = new Submission(validUserName1,InvalidWord1, validWord2);
		});
		
		
		assertThrows(IllegalArgumentException.class, () -> {
			Submission s3 = new Submission(validUserName1,validWord1, InvalidWord2);
		});
		
	}
	
	//testing instance method to check for anagram
	
	@Test
	void testisAnagram() {
		boolean result;
		s.setTextInput1(anagramWord1);
		s.setTextInput2(anagramWord2);
		result = s.checkForAnagram();
		assertTrue(result);
	}

}
