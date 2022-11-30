/**
 * 
 */
package cmegradtest;

import java.util.Arrays;

/**
 * @author Gavin Davis
 *
 */
public class Submission implements Anagram {

	private String userName;
	private String textInput1;
	private String textInput2;
	
	public Submission() {
		
	}

	public Submission(String userName, String textInput1, String textInput2) {
		this.setUserName(userName);
		this.setTextInput1(textInput1);
		this.setTextInput2(textInput2);
	}

	/**
	 * Validation checks that only allowed characters are present in the input.
	 * Add additional characters to the allowed string to make them valid
	 * 
	 * @param input
	 * @return boolean, true if there are only allowed characters present
	 */
	private boolean noSpacesOrNumbers(String input) {

		//ensure all letters of alphabet are included
		String allowed = "abcdefghijklmnopqrstuvwxyz";
		boolean flag = true;

		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (allowed.contains(Character.toString(ch).toLowerCase())) {
				//do nothing
			} else {
				flag = false;
			}
			;
		}
		return flag;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) throws IllegalArgumentException {

		// add call to validation method(s) here
		// add additional validation method calls here
		// All validation methods must be configured to return a true if input is
		// considered valid

		Boolean check1 = noSpacesOrNumbers(userName);

		if (check1) {
			this.userName = userName;
		} else {
			throw new IllegalArgumentException("Invalid input " + userName + ", please check input rules");
		}
	}

	/**
	 * @return the textInput1
	 */
	public String getTextInput1() {
		return textInput1;
	}

	/**
	 * @param textInput1 the textInput1 to set
	 */
	public void setTextInput1(String textInput1) throws IllegalArgumentException{
		// add call to validation method(s) here
		// add additional validation method calls here
		// All validation methods must be configured to return a true if input is
		// considered valid

		Boolean check1 = noSpacesOrNumbers(textInput1);

		if (check1) {
			this.textInput1 = textInput1;
		} else {
			throw new IllegalArgumentException("Invalid input " + textInput1 + ", please check input rules");
		}
	}

	/**
	 * @return the textInput2
	 */
	public String getTextInput2() {
		return textInput2;
	}

	/**
	 * @param textInput2 the textInput2 to set
	 */
	public void setTextInput2(String textInput2) throws IllegalArgumentException {
		// add call to validation method(s) here
		// add additional validation method calls here
		// All validation methods must be configured to return a true if input is
		// considered valid

		Boolean check1 = noSpacesOrNumbers(textInput2);

		if (check1) {
			this.textInput2 = textInput2;
		} else {
			throw new IllegalArgumentException("Invalid input " + textInput2 + ", please check input rules");
		}
	}

	@Override
	public String toString() {
		return  userName + "," + textInput1 + "," + textInput2 + "," + checkForAnagram();
	}


	/**
	 * Check if values submitted to the submission object are an anagram returns
	 * true if inputs match in length and letter content
	 */
	public boolean checkForAnagram() {
		//input word changed to lower case format here to ensure case is consistent for comparison
		String word1 = this.textInput1.toLowerCase();
		String word2 = this.textInput2.toLowerCase();
		boolean flag = false;

		// create character array and compare
		char[] array1 = word1.toCharArray();
		char[] array2 = word2.toCharArray();

		// assisted with debugging
//		System.out.println(array1);
//		System.out.println(array2);

		Arrays.sort(array1);
		Arrays.sort(array2);

		// assisted with debugging
//		System.out.println(array1);
//		System.out.println(array2);

		if (Arrays.equals(array1, array2)) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((textInput1 == null) ? 0 : textInput1.hashCode());
		result = prime * result + ((textInput2 == null) ? 0 : textInput2.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Submission other = (Submission) obj;
		if (textInput1 == null) {
			if (other.textInput1 != null)
				return false;
		} else if (!textInput1.equals(other.textInput1))
			return false;
		if (textInput2 == null) {
			if (other.textInput2 != null)
				return false;
		} else if (!textInput2.equals(other.textInput2))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}
