import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek6
{
	
	///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////
	
	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return("Husein,Zubir,z3084147");
	}
	
	//	Problem #1
	//	Given a string, count the number of words ending in 'y' 
	//	or 'z' -- so the 'y' in "heavy" and the 'z' in "fez" count, 
	//	but not the 'y' in "yellow" (not case sensitive). We'll say 
	//	that a y or z is at the end of a word if there is not an 
	//	alphabetic letter immediately following it. (Note: 
	//	Character.isLetter(char) tests if a char is an alphabetic letter.) 

	//	wordEndYZ("fez day") → 2
	//	wordEndYZ("day fez") → 2
	//	wordEndYZ("day fyyyz") → 2
	
	/**
	 * 
	 * @param str
	 * 		str containing the original string
	 * 
	 * @return int
	 * 		int containing the # of words that end in y or z
	 */
	static int wordEndYZ(String str) 
	{
		//base case
		if(str.length() == 0)
			return 0;
		
		//check if current char is y or z
		if(str.charAt(0) == 'y' || str.charAt(0) == 'z')
		{
			//if it's the end of the string, return 1
			if(str.length() == 1)
				return 1;
			//else if the next character is not a letter, return 1 + recursive call on substring(2)
			if(!Character.isLetter(str.charAt(1)))
				return 1 + wordEndYZ(str.substring(2));
		}
		
		//end of word not found, recurse on substring(1)
		return wordEndYZ(str.substring(1));
	}

	//	Problem #2
	//	Given two strings, base and remove, return a version of the base 
	//	string where all instances of the remove string have been removed 
	//	(not case sensitive). You may assume that the remove string is length 
	//	1 or more. Remove only non-overlapping instances, so with "xxx" 
	//	removing "xx" leaves "x".

	//	removeFromBase("Hello there", "llo") → "He there"
	//	removeFromBase("Hello there", "e") → "Hllo thr"
	//	removeFromBase("Hello there", "x") → "Hello there"
	
	/**
	 * 
	 * @param base, remove
	 * 		base contains original string of characters
	 * 		remove contains original string that is to be removed from base
	 * 
	 * @return
	 * 		String containing the base with all instances of remove taken out
	 */
	static String removeFromBase(String base, String remove) 
	{
		//base case
		if(base.length() == 0)
			return "";
		//check if string begins with remove
		//if it does, recurse on substring with remove ommitted
		if(base.startsWith(remove))
			return removeFromBase(base.substring(remove.length()), remove);
		//remove string not found, return first character and recurse on substring(1)
		return base.charAt(0) + removeFromBase(base.substring(1), remove);
	}	
//
//	//	Problem #3
//	//	Given a string, return true if the number of appearances of 
//	//	"is" anywhere in the string is equal to the number of appearances 
//	//	of "not" anywhere in the string (case sensitive). 
//
//	//	equalAppearance("This is not") → false
//	//	equalAppearance("This is notnot") → true
//	//	equalAppearance("noisxxnotyynotxisi") → true
//	
//	/**
//	 * 
//	 * @param str
//	 * 		str contains the original string of characters
//	 * 
//	 * @return
//	 * 		returns true if "is" appears as many times as "not"
//	 * 		returns false if "is" does not appear as many times as "not"
//	 */
	static boolean equalAppearance(String str) 
	{
		//counters
		int n_is = 0;
		int n_not = 0;
		
		//iterate, checking if substring(i) starts with is or not and increment counter if it does
		for(int i = 0; i < str.length(); i++)
		{
			if(str.substring(i).startsWith("is"))
				n_is++;
			else if(str.substring(i).startsWith("not"))
				n_not++;
		}
		
		//return
		return n_is == n_not;
	}	
//
//	//	Problem #4
//	//	We'll say that a lowercase 'g' in a string is "happy" if there 
//	//	is another 'g' immediately to its left or right. Return true if 
//	//	all the g's in the given string are happy. 
//
//	//	gisHappy("xxggxx") → true
//	//	gisHappy("xxgxx") → false
//	//	gisHappy("xxggyygxx") → false
//	
//	/**
//	 * 
//	 * @param str
//	 * 		String containing original string of characters
//	 * 
//	 * @return
//	 * 		returns true if 'g' appears with another 'g' to it's right or left
//	 * 		returns false if this is not the case
//	 */
	static boolean gisHappy(String str) 
	{
		//return true if string is just gg
		if(str == "gg")
			return true;
		//return false if string is 2 chars or less
		if(str.length() <= 2)
			return false;
		
		//iterate, checking if current char is g and if its left or right is g
		for(int i = 1; i < str.length() - 1; i++)
		{
			if(str.charAt(i) == 'g')
				if(str.charAt(i-1) == 'g'|| str.charAt(i+1) == 'g')
					continue;
				else return false;
		}
		return true;
	}
//	
//	//	Problem #5
//	//	We'll say that a "triple" in a string is a char appearing three times in a row. 
//	//	Return the number of triples in the given string. The triples may overlap. 
//
//	//	numberOfTriples("abcXXXabc") → 1
//	//	numberOfTriples("xxxabyyyycd") → 3
//	//	numberOfTriples("a") → 0	
//	
//	/**
//	 * 
//	 * @param str
//	 * 		String containing the original string of characters
//	 * 
//	 * @return
//	 * 		Integer containing the # of "triples" in str
//	 */
	static int numberOfTriples(String str) 
	{
		//base case
		if(str.length() <= 2)
			return 0;
		//convert char to string
		String s = Character.toString(str.charAt(0));
		//check if string starts with s+s+s
		if(str.startsWith(s + s + s))
		{
			//if it does, return 1 plus recursive call on substring(1) 
			return 1 + numberOfTriples(str.substring(1));
		}
		//no triple found, recurse on substring(1)
		return numberOfTriples(str.substring(1));
	}
//	
//	//	Problem #6
//	//	Given a string, return the sum of the digits 0-9 that appear in the 
//	//	string, ignoring all other characters. Return 0 if there are no digits 
//	//	in the string. (Note: Character.isDigit(char) tests if a char is one 
//	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.) 
//
//	//	addUpDigits("aa1bc2d3") → 6
//	//	addUpDigits("aa11b33") → 8
//	//	addUpDigits("Chocolate") → 0
//	
//	/**
//	 * 
//	 * @param str
//	 * 		String containing the original string of characters
//	 * 
//	 * @return 
//	 * 		Integer containing the # sum of all digits that appear in str
//	 */
	static int addUpDigits(String str) 
	{	
		//base case
		if(str.length() == 0)
			return 0;
		//if current character is digit, return it as an int + recursive call on substring(1)
		if(Character.isDigit(str.charAt(0)))
			return Integer.parseInt(Character.toString(str.charAt(0))) + addUpDigits(str.substring(1));
		//current char is not digit, recurse on substring(1)
		return addUpDigits(str.substring(1));
	}
//	
//	//	Problem #7
//	//	Given a string, return the longest substring that appears at 
//	//	both the beginning and end of the string without overlapping. 
//	//	For example, beginningAndEndOfString("abXab") is "ab". 
//
//	//	beginningAndEndOfString("abXYab") → "ab"
//	//	beginningAndEndOfString("xx") → "x"
//	//	beginningAndEndOfString("xxx") → "x"
//	
//	/**
//	 * 
//	 * @param string
//	 * 		String containing the original string of characters
//	 * 
//	 * @return 
//	 * 		String containing the beginning and ending substrings that are the same
//	 */
	static String beginningAndEndOfString(String string) 
	{
		
		//if string is length 1 or less, return it
		if(string.length() <= 1)
			return string;
		
		//holds substring we want
		String s_beg = "";
		
		//iterate, starting in the middle and working outwards
		for(int i = string.length()/2; i >= 0; i--)
		{
			//get substring from beginning to i
			s_beg = string.substring(0, i);
			//check if string both starts and ends with s_beg
			if(string.startsWith(s_beg) && string.endsWith(s_beg))
				return s_beg;
		}
		//nothing found
		return "";
	}
//	
//	//	Problem #8
//	//	Given a string, look for a mirror image (backwards) string at both 
//	//	the beginning and end of the given string. In other words, zero or more 
//	//	characters at the very beginning of the given string, and at the very 
//	//	end of the string in reverse order (possibly overlapping). For example, 
//	//	the string "abXYZba" has the mirror end "ab". 
//
//	//	beginningMirrorEnd("abXYZba") → "ab"
//	//	beginningMirrorEnd("abca") → "a"
//	//	beginningMirrorEnd("aba") → "aba"
//	
//	/**
//	 * 
//	 * @param string
//	 * 		String containing the original string of characters
//	 * 
//	 * @return 
//	 * 		String containing the beginning of the string that is mirrored at the end
//	 */
	static String beginningMirrorEnd(String string) 
	{
		//if string is two characters or less, return it
		if(string.length() <= 2)
			return string;
		
		String s = "";
		//iterate over whole string
		for(int i = 1; i <= string.length() ; i++)
		{
			//get beginning substring
			String s_beg = string.substring(0,i);
			//make a new string which is the beginning substring reversed
			String s_rev = new StringBuilder(s_beg).reverse().toString();
			
			//check if string ends with s_rev
			//if it does, set s to s_beg
			//if it doesn't, break
			if(string.endsWith(s_rev))
				s = s_beg;
			else break;
			
		}
		return s;
	}
//	
//	//	Problem #9
//	//	Given a string, return the length of the largest "block" in the string. 
//	//	A block is a run of adjacent chars that are the same. 
//
//	//	largestBlock("hoopla") → 2
//	//	largestBlock("abbCCCddBBBxx") → 3
//	//	largestBlock("") → 0
//	
//	/**
//	 * 
//	 * @param str
//	 * 		String containing the original string of characters
//	 * 
//	 * @return 
//	 * 		Integer containing the # of chars in the largest "block" in str
//	 */
	static int largestBlock(String str) 
	{
		int result = 0;
		
		//iterate over entire string
		for(int i = 0; i < str.length(); i++)
		{
			int counter = 0;
			//check if the next character is the same as the first character
			//if it is, increment counter and i
			//if not, break out of while loop
			while(true)
			{
				if(i + counter < str.length() && str.charAt(i) == str.charAt(i + counter)){
					counter++;
					i++;
				}
				else break;
			}
			
			//set result to counter if counter > result
			result = counter > result ? counter : result; 
		}
		
		return result;
	}
//	
//	//	Problem #10
//	//	Given a string, return the sum of the numbers appearing in the string, 
//	//	ignoring all other characters. A number is a series of 1 or more digit 
//	//	chars in a row. (Note: Character.isDigit(char) tests if a char is one 
//	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.)
//
//	//	addUpNumbers("abc123xyz") → 123
//	//	addUpNumbers("aa11b33") → 44
//	//	addUpNumbers("7 11") → 18
//	
//	/**
//	 * 
//	 * @param str
//	 * 		String containing the original string of characters
//	 * 
//	 * @return 
//	 * 		Integer containing the sum of all the numbers that appear in str
//	 */
	static int addUpNumbers(String str) 
	{
		int result = 0;
		
		//iterate over string, starting at end
		for(int i = str.length() - 1; i >= 0; i--)
		{
			int number = 0;
			
			//check if current character is digit
			//if it is, increment number by digit * j
			//j is the loop counter, which increments by j*10
			for(int j = 1;; j *= 10)
			{
				if(i >= 0 && Character.isDigit(str.charAt(i)))
				{
					number += Character.getNumericValue(str.charAt(i)) * j;
					i--;
				}
				else break;
			}
			
			//increment result by number
			result += number;
		}
		
		return result;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		System.out.println(wordEndYZ("onez twoy")); //2
		System.out.println(removeFromBase("Hello World", "el")); //Hlo World
		System.out.println(equalAppearance("This is notnot")); //true
		System.out.println(gisHappy("g"));	//false
		System.out.println(numberOfTriples("xxxabyyyycd")); //3
		System.out.println(addUpDigits("p00p90e")); // 1
		System.out.println(beginningAndEndOfString("axa"));
		/*System.out.println(beginningAndEndOfString("abababab"));
		System.out.println(beginningAndEndOfString("xxx"));
		
		System.out.println(beginningMirrorEnd("abXYZba"));
		System.out.println(beginningMirrorEnd("abca"));
		System.out.println(beginningMirrorEnd("aba"));
		System.out.println(largestBlock("aa"));
		System.out.println(addUpNumbers("aa11b33"));*/
	}
	
}