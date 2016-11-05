package com.questions;

/**
 * This class applies a set of rules to a string passed to it's method demystify
 * in the following order
 * 
 * If a character has the same character to its left and right, it should be
 * replaced with that other character (i.e. AWA becomes AAA) unless the
 * surrounding character is a space
 * 
 * Any sequence of six characters should be replaced with a single character,
 * i.e. AAAAAA becomes A
 * 
 * Any instance of the exclamation mark (!) character causes the string to be
 * reversed, and the exclamation mark character removed
 * 
 * @author varun
 *
 */

public class StringDemystifier {

	// Encoded String
	private String mystifiedString;

	// Constructor to initialise mystifiedString
	public StringDemystifier(String mystifiedString) {
		this.mystifiedString = mystifiedString;
	}

	/**
	 * This method takes in a mystifiedString and demystifies it
	 * 
	 * @return demystified String
	 */
	public String demystify() {
		String demystifiedString = "";

		if (mystifiedString != null) {

			// Calculate length of the demystifiedString
			int length = mystifiedString.length();
			// Convert the string into a string of characters
			char[] chars = mystifiedString.toCharArray();

			// Previous Index
			int previousIndex = 0;
			// Current Index
			int index = 1;
			// Next Index
			int nextIndex = 2;

			// Iterate through all character tokens to apply the following rule
			// If a character has the same character to its left and right, it
			// should be replaced with that
			// other character
			while (index < length) {
				if (nextIndex < length) {
					// Check if a character has the same character to its left
					// and right
					if (chars[previousIndex] == chars[nextIndex] && chars[previousIndex] != ' ') {
						// If yes, replace it with the other character
						chars[index] = chars[previousIndex];
					}

				}

				previousIndex++;
				index++;
				nextIndex++;
			}
			
			

			demystifiedString = new String(chars);
		}

		return demystifiedString;
	}

	public static void main(String args[])
	{
		StringDemystifier stringDemystifier = new StringDemystifier("!YTIRCO!IQIIQIDEMGMMIM FO YMJMMSM!RA !EGEEJEHT ROEOOSOF PAEJEEBEL TN!AIKIITIG ENVNNMNO ,GQGGCGN!ILEKIZIISIRT A RJRRDROF PETOTTJTS LLZLLEL!AMSXSSMS ENODOOSO");
		
		System.out.println(stringDemystifier.demystify());
	}
}
