import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek3 
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
		return( "Husein,Zubir,z3084147");
	}
	
	//	Problem #1
	//	Given a string and a non-empty substring sub, compute recursively if at 
	//	least n copies of sub appear in the string somewhere, possibly with 
	//	overlapping. N will be non-negative.

	//	subCopies("catcowcat", "cat", 2) → true
	//	subCopies("catcowcat", "cow", 2) → false
	//	subCopies("catcowcat", "cow", 1) → true
	
	/**
	 * 
	 * @param str, sub, n
	 * 		String str contains the original string to be tested against
	 * 		String sub contains the string that is used to test against str
	 * 		int n specifies how many copies of sub to check for
	 * 
	 * @return
	 * 		returns true if there are n copies of sub in str
	 * 		returns false if there are not n copies of sub in str
	 */
	static boolean subCopies(String str, String sub, int n) 
	{
		//found n copies, return true
		if(n == 0)
			return true;
		//search string is now smaller than sub string, return false
		if(str.length() < sub.length())
			return false;
		//recurse, chopping off the first character of string each time
		//if a copy is found, recurse with n -1 instead of n
		if(str.startsWith(sub) || str == sub)
			return subCopies(str.substring(1), sub, n - 1);
		return subCopies(str.substring(1), sub, n);
	}

	//	Problem #2
	//	Given a non-negative int n, return the sum of its digits recursively 
	//	(no loops). Note that mod (%) by 10 yields the rightmost 
	//	digit (126 % 10 is 6), while divide (/) by 10 removes the 
	//	rightmost digit (126 / 10 is 12).

	//	sumDigitsInNumber(126) → 9
	//	sumDigitsInNumber(49) → 13
	//	sumDigitsInNumber(12) → 3
	
	/**
	 * 
	 * @param n
	 * 		int n contains integer to deal with.
	 * 
	 * @return integer
	 * 		integer that is the sum of each digit in int n.
	 */
	static int sumDigitsInNumber(int n) 
	{
		if(n == 0)
			return 0;
		return n % 10 + sumDigitsInNumber(n / 10);
	}	

	//	Problem #3
	//	Given base and n that are both 1 or more, compute recursively (no loops) 
	//	the value of base to the n power, so powerN(3, 2) is 9 (3 squared).

	//	exponential(3, 1) → 3
	//	exponential(3, 2) → 9
	//	exponential(3, 3) → 27
	
	/**
	 * 
	 * @param base, n
	 * 		int base containing the base of the term
	 * 		int n containing the exponent of the term
	 * 
	 * @return integer
	 * 		integer that corresponds with equating base to the n power
	 */
	static int exponential(int base, int n) 
	{
		if (n == 0)
			return 1;
		return base * exponential(base, n - 1);
	}	

	//	Problem #4
	//	Given a string, compute recursively (no loops) a new string 
	//	where all the lowercase 'x' chars have been changed to 'y' chars. 

	//	changeXtoY("codex") → "codey"
	//	changeXtoY("xxhixx") → "yyhiyy"
	//	changeXtoY("xhixhix") → "yhiyhiy"
	
	/**
	 * 
	 * @param str
	 * 		String containing original string of chars to deal with
	 * 
	 * @return String
	 * 		String of characters where the lowercase x's have been changed to y's
	 */
	public static String changeXtoY(String str) 
	{
		//reached end of string, done
		if(str.isEmpty())
			return "";
		//check each character to see if it's an x
		//if it's not, return that character + recursive call on the remainder of string
		//if it is, return y + recursive call on remainder of string
		if(str.charAt(0) == 'x')
			return "y" + changeXtoY(str.substring(1));
		return str.charAt(0) + changeXtoY(str.substring(1));
	}
	
	//	Problem #5
	//	Given an array of ints, compute recursively if the array 
	//	contains a 6. We'll use the convention of considering only 
	//	the part of the array that begins at the given index. 
	//	In this way, a recursive call can pass index+1 to move down 
	//	the array. The initial call will pass in index as 0. 

	//	find6({1, 6, 4}, 0) → true
	//	find6({1, 4}, 0) → false
	//	find6({6}, 0) → true	
	
	/**
	 * 
	 * @param nums, index
	 * 		int[] list containing the original array of numbers
	 * 		int containing the position to start in nums
	 * 
	 * @return boolean
	 * 		returns true if a 6 is found in the array
	 * 		returns false if no 6 is found in the array
	 */
	static boolean find6(int[] nums, int index) 
	{
		if(index == nums.length)
			return false;
		if(nums[index] == 6)
			return true;
		return find6(nums, index + 1);
	}
	
	//	Problem #6
	//	Given a string, compute recursively a new string where all 
	//	the adjacent chars are now separated by a "*".   

	//	insertAsterisk("hello") → "h*e*l*l*o"
	//	insertAsterisk("abc") → "a*b*c"
	//	insertAsterisk("ab") → "a*b"
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return AbridgedList
	 * 		String with an asterisk between each char
	 */
	static String insertAsterisk(String str) 
	{
		//reached last character, return it and end
		if(str.length() == 1)
			return str;
		//return each character + * + recursive call to remainder of string
		return str.charAt(0) + "*" + insertAsterisk(str.substring(1));
	}
	
	//	Problem #7
	//	We'll say that a "pair" in a string is two instances of 
	//	a char separated by a char. So "AxA" the A's make a pair. 
	//	Pair's can overlap, so "AxAxA" contains 3 pairs -- 2 for 
	//	A and 1 for x. Recursively compute the number of 
	//	pairs in the given string.  

	//	numberPairs("axa") → 1
	//	numberPairs("axax") → 2
	//	numberPairs("axbx") → 1
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars provided
	 * 
	 * @return 
	 * 		int with the number of pairs as defined above
	 */
	static int numberPairs(String str) 
	{
		//two characters remaining, so no more pairs, return 0
		if(str.length() - 2 == 0)
			return 0;
		//if pair is found, return 1 + recursive call to remainder of string
		//else, recurse on remainder of string
		if(str.charAt(0) == str.charAt(2))
			return 1 + numberPairs(str.substring(1));
		return numberPairs(str.substring(1));
	}
	
	//	Problem #8
	//	Given a string, return recursively a "cleaned" string where 
	//	adjacent chars that are the same have been reduced 
	//	to a single char. So "yyzzza" yields "yza".  

	//	reduceChars("yyzzza") → "yza"
	//	reduceChars("abbbcdd") → "abcd"
	//	reduceChars("Hello") → "Helo"
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return 
	 * 		String with all repeated, adjacent chars are removed
	 */
	static String reduceChars(String str) 
	{
		//reached end of string, end recursion
		if(str.isEmpty())
			return "";
		//check if next character is the same as the current one
		//if so, recurse on remainder of string
		//if not, return current character + recurse on remainder of string
		if(str.charAt(0) == str.charAt(1))
			return reduceChars(str.substring(1));
		return str.charAt(0) + reduceChars(str.substring(1));
	}
	
	//	Problem #9
	//	Given a string, return true if it is a nesting of zero or more 
	//	pairs of parenthesis, like "(())" or "((()))". Suggestion: 
	//	check the first and last chars, and then recur on what's inside them.  

	//	nestedParens("(())") → true
	//	nestedParens("((()))") → true
	//	nestedParens("(((x))") → false
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return 
	 * 		returns true if there are zero or more pairs of parenthesis
	 * 		returns false if there are not zero or more pairs of parenthesis
	 */
	/*static boolean nestedParens(String str) 
	{
	}*/
	
	//	Problem #10

	//	Given a string and a non-empty substring sub, compute 
	//	recursively the largest substring which starts and 
	//	ends with sub and return its length.  

	//	subStrSub("catcowcat", "cat") → 9
	//	subStrSub("catcowcat", "cow") → 3
	//	subStrSub("cccatcowcatxx", "cat") → 9
	
	/**
	 * 
	 * @param str, sub
	 * 		String containing the original chars to be tested against
	 * 		String containing the original chars to test with
	 * 
	 * @return 
	 * 		integer containing the largest number of chars in string 
	 * 		that start and end with sub
	 */
	static int subStrSub(String str, String sub) 
	{
		//if the string does not start with sub, chop off the first character
		//i.e. recurse on remainder
		if(str.startsWith(sub) == false)
			return subStrSub(str.substring(1), sub);
		//since the string now starts with sub, check if it ends with it
		//if it doesn't, chop off the last character and recurse on the remainder
		if(str.endsWith(sub) == false)
			return subStrSub(str.substring(0, str.length() - 1), sub);
		//string now starts and ends with sub, so just return the length of this string
		return str.length();
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		int[] nums = new int[]{2,4,6,3,2};
		System.out.println(subCopies("catcowcat", "cat", 2));
		System.out.println(sumDigitsInNumber(5023));
		System.out.println(exponential(2, 8));
		System.out.println(changeXtoY("sexy moose xxx daxxy"));
		System.out.println(find6(nums, 0));
		System.out.println(insertAsterisk("dicksquad"));
		System.out.println(numberPairs("AxAxA"));
		System.out.println(subStrSub("adawdcookieasscookie","cookie"));
	}
	
}