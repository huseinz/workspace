import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek1 
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

	// Directions: Return the number of even ints in the given 
	//   array. Note: the % "mod" operator computes the remainder, 
	//   e.g. 5 % 2 is 1. 

	// CountEvenNumbersInArray({2, 1, 2, 3, 4}) → 3
	// CountEvenNumbersInArray({2, 2, 0}) → 3
	// CountEvenNumbersInArray({1, 3, 5}) → 0
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		int with the number of even numbers in NumberList
	 */
	static int CountEvenNumbersInArray(int[] NumberList) 
	{
		//iterate through array, checked if each element is even, and incrementing nEven if it is. 
		int nEven = 0;
		for(int i = 0; i < NumberList.length; i++)
		{
			if(NumberList[i] %2 == 0)
				nEven++;
		}
		//return nEven
		return nEven;
	}

	// Given an array of ints, return true if the array contains no 
	//   1's and no 3's.

	// LookForLucky13({0, 2, 4}) → true
	// LookForLucky13({1, 2, 3}) → false
	// LookForLucky13({1, 2, 4}) → false
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		returns false if there is a 1 or 3 in the list.
	 * 		returns true if there are no 1s or 3s in the list.
	 */
	static boolean LookForLucky13(int[] NumberList) 
	{
		//simply iterate through array and check if each element is either a 1 or a 3 and return false if it is
		for(int i = 0; i < NumberList.length; i++)
		{
			if(NumberList[i] == 1 || NumberList[i] == 3)
				return false;
		}
		return true;
	}	

	// Given arrays NumberList1 and NumberList2 of the same length, 
	//   for every element in NumberList1, consider the 
	//   corresponding element in NumberList2 (at the same index). 
	//   Return the count of the number of times that the two 
	//   elements differ by 2 or less, but are not equal. 

	// MatchUpLists({1, 2, 3}, {2, 3, 10}) → 2
	// MatchUpLists({1, 2, 3}, {2, 3, 5}) → 3
	// MatchUpLists({1, 2, 3}, {2, 3, 3}) → 2
	
	/**
	 * 
	 * @param NumberList1
	 * 		first int[] list containing some numbers.
	 * 
	 * @param NumberList2
	 * 		second int[] list containing some numbers.
	 * 
	 * @return
	 * 		returns the number if instances where the difference between two non-equal corresponding elements
	 * 		is less than or equal to 2
	 */
	static int MatchUpLists(int[] NumberList1, int[] NumberList2) 
	{
		int nCount = 0;
		
		//iterate through both lists
		//check if the two corresponding elements are not equal, and if they aren't, calculate their absolute difference
		//if their difference is less than or equal to 2, increment nCount
		for(int i = 0; i < NumberList1.length; i++)
		{
			if(NumberList1[i] != NumberList2[i] && Math.abs(NumberList1[i] - NumberList2[i]) <= 2)
				nCount++;
		}
		return nCount;
	}	

	// Given an array of ints, return true if the array 
	//   contains either 3 even or 3 odd values all next 
	//   to each other. 

	// ModThreeNumbers({2, 1, 3, 5}) → true
	// ModThreeNumbers({2, 1, 2, 5}) → false
	// ModThreeNumbers({2, 4, 2, 5}) → true
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		return true if there are three consecutive events
	 * 			or three consecutive odds
	 * 
	 * 		otherwise returns false
	 */
	public static boolean ModThreeNumbers(int[] NumberList) 
	{
		//simply iterate over list-2 so you don't go out of bounds
		//check if the mod 2 of the two next elements is equal and return true if it is
		for(int i = 0; i < NumberList.length-2; i++)
		{
			if(NumberList[i] % 2 == NumberList[i + 1] % 2 && NumberList[i] % 2 == NumberList[i + 2] % 2)
				return true;
		}
		return false;
	}

	// Return the "centered" average of an array of ints, 
	//   which we'll say is the mean average of the values, 
	//   except ignoring the largest and smallest values in 
	//   the array. If there are multiple copies of the 
	//   smallest value, ignore just one copy, and likewise 
	//   for the largest value. Use int division to produce 
	//   the final average. You may assume that the array is 
	//   length 3 or more. 

	// FindCenteredAverage({1, 2, 3, 4, 100}) → 3
	// FindCenteredAverage({1, 1, 5, 5, 10, 8, 7}) → 5
	// FindCenteredAverage({-10, -4, -2, -4, -2, 0}) → -3	
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		Average of the list of numbers without the
	 * 		first of the lowest numbers and the last of the
	 * 		highest numbers.
	 */
	static int FindCenteredAverage(int[] NumberList) 
	{
		int high = 0;
		
		//find highest
		for(int i = 0; i < NumberList.length; i++)
		{
			if(NumberList[i] > high)
				high = NumberList[i];
		}
		//find lowest
		int low = high;
		for(int i = 0; i < NumberList.length; i++)
		{
			if(NumberList[i] < low)
				low = NumberList[i];
		}
		//get sum of NumberList
		int sum = 0;
		for(int i = 0; i < NumberList.length; i++)
			sum += NumberList[i];
		
		//calculate and return result
		return (sum - high - low) / (NumberList.length - 2);
	}
	
	// Given an array of ints, return true if every 2 that 
	//   appears in the array is next to another 2. 

	// LookForTwoTwo({4, 2, 2, 3}) → true
	// LookForTwoTwo({2, 2, 4}) → true
	// LookForTwoTwo({2, 2, 4, 2}) → false
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		true if every 2 is adjacent to another 2
	 * 		otherwise false
	 */
	static boolean LookForTwoTwo(int[] NumberList) 
	{
		//this boolean turns true if a 2 is found
		//this exists to cover the case in which there are no 2's in the array at all
		boolean Twos = false;
		
		//simply iterate over list and return false is the current element is 2 but neither of the adjacent elements is 2
		//return false if there are no twos at all
		for(int i = 1; i < NumberList.length-1; i++)
		{
			if(NumberList[i] == 2)
				Twos = true;
			if(NumberList[i] == 2 && (NumberList[i+1] != 2 && NumberList[i - 1] != 2))
				return false;
		}
		return Twos;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{

	}
	
}