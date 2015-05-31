import java.util.Arrays;
import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek10
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
	//	Consider the leftmost and rightmost appearances of some value in an array. 
	//	We'll say that the "span" is the number of elements between the two inclusive. 
	//	A single value has a span of 1. Returns the largest span found in the 
	//	given array. (Efficiency is not a priority.) 

	//	maximumSpan({1, 2, 1, 1, 3}) → 4
	//	maximumSpan({1, 4, 2, 1, 4, 1, 4}) → 6
	//	maximumSpan({1, 4, 2, 1, 4, 4, 4}) → 6
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers
	 * 
	 * @return 
	 * 		int representing the largest span of a specific int in nums
	 */
	static int maximumSpan(int[] nums) 
	{
		int span = 1;
		
		for(int i = 0; i < nums.length; i++)
		{
			for(int j = nums.length - 1; j > i; j--)
			{
				if(nums[i] == nums[j])
				{
					if(j - i + 1 > span)
						span = j - i + 1;
				}
			}
		}
		return span;
	}

	//	Problem #2
	//	Given a non-empty array, return true if there is a place to split the 
	//	array so that the sum of the numbers on one side is equal to the sum of 
	//	the numbers on the other side. 

	//	canStabilize({1, 1, 1, 2, 1}) → true
	//	canStabilize({2, 1, 1, 2, 1}) → false
	//	canStabilize({10, 10}) → true
	
	/** 
	 * 
	 * @param nums
	 * 		int[] containing original integers
	 * 
	 * @return
	 * 		returns true if nums can be split so that sum of one side equals sum on other side
	 * 		returns false if this is not the case
	 */
	static boolean canStabilize(int[] nums) 
	{
		for(int i = 0; i < nums.length-1; i++)
		{
			if(array_sum(nums, 0, i) == array_sum(nums, i+1, nums.length-1))
				return true;
		}
		return false;
	}
	
	static int array_sum(int[] nums, int start_index, int end_index)
	{
		int result = 0;
		for(int i = start_index; i < end_index; i++)
			result += nums[i];
		return result;
	}

	//	Problem #3
	//	Given n>=0, create an array with the pattern {1,    1, 2,    1, 2, 3,   ... 1, 2, 3 .. n} 
	//	(spaces added to show the grouping). Note that the length of the array will be 
	//	1 + 2 + 3 ... + n, which is known to sum to exactly n*(n + 1)/2. 

	//	arithmeticSeries(3) → {1, 1, 2, 1, 2, 3}
	//	arithmeticSeries(4) → {1, 1, 2, 1, 2, 3, 1, 2, 3, 4}
	//	arithmeticSeries(2) → {1, 1, 2}
	
	/**
	 * 
	 * @param n
	 * 		int with the value of the length of the series
	 * 
	 * @return
	 * 		int [] containing the arithmetic series starting at 1 and repeating to n
	 */
	static int[] arithmeticSeries(int n) 
	{
		int result[] = new int[n * (n + 1)/2];
		int current_index = 0;
		
		for(int i = 1; i <= n; i++)
		{
			for(int j = 1; j <= i; j++, current_index++)
				result[current_index] = j;
		}
		return result;
	}	

	//	Problem #4
	//	Return an array that contains exactly the same numbers as the given array, 
	//	but rearranged so that every 3 is immediately followed by a 4. Do not move 
	//	the 3's, but every other number may move. The array contains the same number of 
	//	3's and 4's, every 3 has a number after it that is not a 3 or 4, and a 3 appears 
	//	in the array before any 4. 

	//	follow3with4({1, 3, 1, 4}) → {1, 3, 4, 1}
	//	follow3with4({1, 3, 1, 4, 4, 3, 1}) → {1, 3, 4, 1, 1, 3, 4}
	//	follow3with4({3, 2, 2, 4}) → {3, 4, 2, 2}
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers with the specified conditions above
	 * 
	 * @return
	 * 		int[] containing a modified array where every occurrence of 3 is followed by a 4
	 */
	static int[] follow3with4(int[] nums) 
	{
		for(int i = 0; i < nums.length; i++)
		{
			if(nums[i] == 3)
			{
				//find a 4 and swap
				for(int j = 0; j < nums.length; j++)
				{
					if(nums[j] == 4 && nums[j-1] != 3){
						int tmp = nums[i+1];
						nums[i+1] = 4;
						nums[j] = tmp;
						break;
					}
				}
			}
		}
		return nums;
	}
	
	//	Problem #5
	//	Given two arrays of ints sorted in increasing order, outer and inner,
	//	return true if all of the numbers in inner appear in outer. The best 
	//	solution makes only a single "linear" pass of both arrays, taking 
	//	advantage of the fact that both arrays are already in sorted order. 

	//	innerAppearsInOuter({1, 2, 4, 6}, {2, 4}) → true
	//	innerAppearsInOuter({1, 2, 4, 6}, {2, 3, 4}) → false
	//	innerAppearsInOuter({1, 2, 4, 4, 6}, {2, 4}) → true
	
	/**
	 * 
	 * @param outer, inner
	 * 		int[] containing original integers in increasing order
	 * 		int[] containing original integers in increasing order
	 * 
	 * @return
	 * 		returns true if all contents of inner appear in outer
	 * 		returns false if not all of the contents inner appear in outer
	 */
	static boolean innerAppearsInOuter(int[] outer, int[] inner) 
	{
		int ptr = 0;
		for(int n : outer){
			if(ptr >= inner.length)
				return true;
			if(inner[ptr] == n ){
				ptr++;
				continue;
			}
		}
		return ptr >= inner.length;
	}
	
	//	Problem #6
	//	We'll say that a "mirror" section in an array is a group of contiguous elements 
	//	such that somewhere in the array, the same group appears in reverse order. For 
	//	example, the largest mirror section in {1, 2, 3, 8, 9, 3, 2, 1} is length 
	//	3 (the {1, 2, 3} part). Return the size of the largest mirror section found in the given array. 

	//	maximumMirrorSpan({1, 2, 3, 8, 9, 3, 2, 1}) → 3
	//	maximumMirrorSpan({1, 2, 1, 4}) → 3
	//	maximumMirrorSpan({7, 1, 2, 9, 7, 2, 1}) → 2
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers
	 * 
	 * @return 
	 * 		int containing the value of the maximum span of ints that are mirrored in nums
	 */
	static int maximumMirrorSpan(int[] nums) 
	{
		int span, max_Span = 0, left, right;
		for(int i = 0; i < nums.length; i++)
		{
			left = i;
			right = lastOccurrence(nums, nums[i], nums.length - 1);
			while(right != -1)
			{
				span = 0;
				left = i;
				
				while(left < nums.length && right >= 0 && nums[left] == nums[right])
				{
					left++;
					right--;
					span++;
				}	
	
				if(span > max_Span)
					max_Span = span;
				
				right = lastOccurrence(nums, nums[i], right);
			}
		}
		return max_Span;
	}
	static int lastOccurrence(int[] nums, int value, int index)
	{
		for(; index >= 0; index--)
		{
			if(nums[index] == value)
				return index;
		}
		return -1;
	}
	
	//	Problem #7
	//	(This is a slightly harder version of the fix34 problem.) Return an array that contains 
	//	exactly the same numbers as the given array, but rearranged so that every 4 is immediately 
	//	followed by a 5. Do not move the 4's, but every other number may move. The array contains 
	//	the same number of 4's and 5's, and every 4 has a number after it that is not a 4. In this 
	//	version, 5's may appear anywhere in the original array. 

	//	follow4with5({5, 4, 9, 4, 9, 5}) → {9, 4, 5, 4, 5, 9}
	//	follow4with5({1, 4, 1, 5}) → {1, 4, 5, 1}
	//	follow4with5({1, 4, 1, 5, 5, 4, 1}) → {1, 4, 5, 1, 1, 4, 5}
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers with the specifications declared above
	 * 
	 * @return 
	 * 		int[] containing a modified nums where every 4 is followed by a 5
	 */
	static int[] follow4with5(int[] nums) 
	{
		for(int i = 0; i < nums.length; i++)
		{
			if(nums[i] == 4)
			{
				//find a 5 and swap
				for(int j = 0; j < nums.length; j++)
				{
					if(nums[j] == 5 && nums[j-1] != 4){
						int tmp = nums[i+1];
						nums[i+1] = 5;
						nums[j] = tmp;
						break;
					}
				}
			}
		}
		return nums;
	}
	
	//	Problem #8
	//	Given n>=0, create an array length n*n with the following pattern, shown here 
	//	for n=3 : {0, 0, 1,    0, 2, 1,    3, 2, 1} (spaces added to show the 3 groups). 

	//	reverseArithSeries(3) → {0, 0, 1, 0, 2, 1, 3, 2, 1}
	//	reverseArithSeries(2) → {0, 1, 2, 1}
	//	reverseArithSeries(4) → {0, 0, 0, 1, 0, 0, 2, 1, 0, 3, 2, 1, 4, 3, 2, 1}
	
	/**
	 * 
	 * @param n
	 * 		int containing original integer value
	 * 
	 * @return 
	 * 		int [] containing the arithmetic series in reverse order
	 */
	static int[] reverseArithSeries(int n) 
	{
		int result[] = new int[n*n];
		int current_index = 0;
		
		for(int i = 1; i <= n; i++)
		{
			for(int j = n - i; j > 0; j--, current_index++)
			{
					result[current_index] = 0;
			}
			for(int j = 1; j <= i; j++, current_index++)
			{
				result[current_index] = j;
			}
		}
		return result;
	}
	
	//	Problem #9
	//	Say that a "clump" in an array is a series of 2 or more adjacent elements of 
	//	the same value. Return the number of clumps in the given array. 

	//	largestClump({1, 2, 2, 3, 4, 4}) → 2
	//	largestClump({1, 1, 2, 1, 1}) → 2
	//	largestClump({1, 1, 1, 1, 1}) → 1
	
	/**
	 * 
	 * @param nums
	 * 		int[] containing original integers
	 * 
	 * @return 
	 * 		int containing the number of the largest clump in nums
	 */
	static int largestClump(int[] nums) 
	{
		int clumps = 0;
		for(int i = 0; i < nums.length - 1; i++){
			if(nums[i] == nums[i+1]){
				clumps++;
				for(; i < nums.length -1 && nums[i] == nums[i+1]; i++);
			}
		}
		return clumps;
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