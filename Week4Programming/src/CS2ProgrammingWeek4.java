
///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek4
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
    //	Given an array of ints, is it possible to choose a group
    //	of some of the ints, such that the group sums to the given
    //	target? This is a classic backtracking recursion problem.
    //	Once you understand the recursive backtracking strategy in
    //	this problem, you can use the same pattern for many problems to
    //	search a space of choices. Rather than looking at the whole array,
    //	our convention is to consider the part of the array starting at
    //	index start and continuing to the end of the array. The caller
    //	can specify the whole array simply by passing start as 0. No loops
    //	are needed -- the recursive calls progress down the array.

    //	groupSumsTarget(0, {2, 4, 8}, 10) → true
    //	groupSumsTarget(0, {2, 4, 8}, 14) → true
    //	groupSumsTarget(0, {2, 4, 8}, 9) → false

    /**
     *
     * @param start, nums, target
     * 		int start tells you where to start in the array nums
     * 		int[] nums is the given array
     * 		int target is the value to which the group should sum to
     *
     * @return
     * 		returns true if there is a group that sums to target
     * 		returns false if there is no group that sums to target
     */

    static boolean groupSumsTarget(int start, int[] nums, int target)
    {
    	//subset found, return true
        if (target == 0)
            return true;
	//sum has been exceeded, backtrack
        if (target <  0)
            return false;
	//reached end of array, backtrack
        if (start  == nums.length)
            return false;
	//recurse on next element of array and check the subset that derives from its path
	//if it returns true, then we know it is a solution, so return true
        if(groupSumsTarget(start + 1, nums, target - nums[start]) == true)
            return true;
	//if the previous statement doesn't return true, then skip the element
        if(groupSumsTarget(start + 1, nums, target) == true)
            return true;
	
        return false;
    }


    //	Problem #2
    //	Given an array of ints, is it possible to choose a group of
    //	some of the ints, beginning at the start index, such that
    //	the group sums to the given target? However, with the additional
    //	constraint that all 6's must be chosen. (No loops needed.)

    //	groupSumsTarget6(0, {5, 6, 2}, 8) → true
    //	groupSumsTarget6(0, {5, 6, 2}, 9) → false
    //	groupSumsTarget6(0, {5, 6, 2}, 7) → false

    /**
     *
     * @param start, nums, target
     * 		int start tells you where to start in the array nums
     * 		int[] nums is the given array
     * 		int target is the value to which the group should sum to
     *
     * @return
     * 		returns true if there is a group that sums to target including all 6's in the group
     * 		returns false if there is no group that sums to target
     */
    static boolean groupSumsTarget6(int start, int[] nums, int target)
    {
    	//if reached end of array 
        if (start == nums.length) {
		//if last element is a solution, then return true
            if (target == 0) 
                return true;
            //else return false
            return false;
        }
	//if a number is a 6, we must include it
        if (nums[start] == 6) 
            return groupSumsTarget6(start + 1, nums, target - nums[start]);
        //do stuff
        if (groupSumsTarget6(start + 1, nums, target - nums[start])) 
            return true;
        
        return groupSumsTarget6(start + 1, nums, target);
    }



    //	Problem #3
    //	Given an array of ints, is it possible to choose a group of some
    //	of the ints, such that the group sums to the given target with this
    //	additional constraint: If a value in the array is chosen to be in
    //	the group, the value immediately following it in the array
    //	must not be chosen. (No loops needed.)

    //	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 12) → true
    //	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 14) → false
    //	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 7) → false

    /**
     *
     * @param start, nums, target
     * 		int start tells you where to start in the array nums
     * 		int[] nums is the given array
     * 		int target is the value to which the group should sum to
     *
     * @return
     * 		returns true if there is a group that sums to target including the specified constraints
     * 		returns false if there is no group that sums to target
     */
    static boolean groupSumsTargetNoAdj(int start, int[] nums, int target)
    {
     	//subset found, return true
        if (target == 0)
            return true;
	//sum has been exceeded, backtrack
        if (target <  0)
            return false;
	//reached end of array, backtrack
        if (start  >= nums.length)
            return false;
	//recurse on next element of array and check the subset that derives from its path
	//if it returns true, then we know it is a solution, so return true
        if(groupSumsTarget(start + 2, nums, target - nums[start]) == true)
            return true;
	//if the previous statement doesn't return true, then skip the element
        if(groupSumsTarget(start + 1, nums, target) == true)
            return true;
	
        return false;
    }

    //	Problem #4
    //	Given an array of ints, is it possible to choose a group of some
    //	of the ints, such that the group sums to the given target with these
    //	additional constraints: all multiples of 5 in the array must be
    //	included in the group. If the value immediately following a multiple
    //	of 5 is 1, it must not be chosen. (No loops needed.)

    //	groupSumsTarget5(0, {2, 5, 10, 4}, 19) → true
    //	groupSumsTarget5(0, {2, 5, 10, 4}, 17) → true
    //	groupSumsTarget5(0, {2, 5, 10, 4}, 12) → false

    /**
     *
     * @param start, nums, target
     * 		int start tells you where to start in the array nums
     * 		int[] nums is the given array
     * 		int target is the value to which the group should sum to
     *
     * @return
     * 		returns true if there is a group that sums to target including the specified constraints
     * 		returns false if there is no group that sums to target
     */
    static boolean groupSumsTarget5(int start, int[] nums, int target)
    {
     	//if reached end of array 
        if (start == nums.length) {
		//if a solution, then return true
            if (target == 0) 
                return true;
            //else return false
            return false;
        }
	//if a number is divisible by 5, we must include it
        if (nums[start] % 5 == 0){ 
		//skip next number if it's 1
		if(start + 1 <= nums.length && nums[start + 1] == 1)
            		return groupSumsTarget6(start + 2, nums, target - nums[start]);
		return groupSumsTarget6(start + 1, nums, target - nums[start]);
	}
        //continue recursing
        if (groupSumsTarget6(start + 1, nums, target - nums[start])) 
            return true;
        
        return groupSumsTarget6(start + 1, nums, target);
   	
    }


    //	Problem #5
    //	Given an array of ints, is it possible to choose a group of some of
    //	the ints, such that the group sums to the given target, with this
    //	additional constraint: if there are numbers in the array that are adjacent
    //	and the identical value, they must either all be chosen, or none of
    //	them chosen. For example, with the array {1, 2, 2, 2, 5, 2}, either all
    //	three 2's in the middle must be chosen or not, all as a group. (one loop
    //	can be used to find the extent of the identical values).

    //	groupSumsTargetClump(0, {2, 4, 8}, 10) → true
    //	groupSumsTargetClump(0, {1, 2, 4, 8, 1}, 14) → true
    //	groupSumsTargetClump(0, {2, 4, 4, 8}, 14) → false

    /**
     *
     * @param start, nums, target
     * 		int start tells you where to start in the array nums
     * 		int[] nums is the given array
     * 		int target is the value to which the group should sum to
     *
     * @return
     * 		returns true if there is a group that sums to target including the specified constraints
     * 		returns false if there is no group that sums to target
     */
    static boolean groupSumsTargetClump(int start, int[] nums, int target)
    {
        //we found a solution, backtrack
        if (target == 0)
            return true;
        //sum has been exceeded, backtrack
        if (target <  0)
            return false;
        //reached end of array, backtrack
        if (start == nums.length)
        	return false;
        //check for repeats
		int clumpsize = 0;
		for(int i = start; i < nums.length && nums[start] == nums[i]; i++)
		{
			clumpsize++;
		}
	    
		//include current elements, adjust for clumps
		if(groupSumsTargetClump(start + clumpsize, nums, target - (nums[start] * clumpsize)) == true)
        		    return true;
		//exclude current element, adjust for clumps
        if(groupSumsTargetClump(start + clumpsize, nums, target) == true)
            return true;
	
        return false;
    }

    //	Problem #6
    //	Given an array of ints, is it possible to divide the ints into two
    //	groups, so that the sums of the two groups are the same. Every int must
    //	be in one group or the other. Write a recursive helper method that takes
    //	whatever arguments you like, and make the initial call to your recursive
    //	helper from splitArray(). (No loops needed.)

    //	divideArray({2, 2}) → true
    //	divideArray({2, 3}) → false
    //	divideArray({5, 2, 3}) → true

    /**
     *
     * @param nums
     * 		int[] nums is the given array
     *
     * @return
     * 		returns true if the array can be divided so that the constraints are met
     * 		returns false if the array cannot be divided so that the constraints are met
     */
    static boolean divideArray(int[] nums)
    {
        return divideArray1(0, nums, 0, 0);
    }

    static boolean divideArray1(int start, int[] nums, int group1, int group2)
    {
    	//if we reached the end of the array and both groups are equal, we've found a solution
    	//return true
        if ( start == nums.length && group1 == group2)
            return true;
        //we reached end of array and solution not found, return false
        if (start == nums.length)
            return false;
        //Check if adding current element to group one results in solution
        //if so return true
        if(divideArray1(start + 1, nums, group1 + nums[start], group2) == true)
            return true;
        //Check if adding current element to group two results in solution
        //if so return true
        if(divideArray1(start + 1, nums, group1, group2 + nums[start]) == true)
            return true;
	
        return false;
    }

    //	Problem #7
    //	Given an array of ints, is it possible to divide the ints into two groups,
    //	so that the sum of one group is a multiple of 10, and the sum of the
    //	other group is odd. Every int must be in one group or the other. Write
    //	a recursive helper method that takes whatever arguments you like, and
    //	make the initial call to your recursive helper from
    //	splitOdd10(). (No loops needed.)

    //	oddDivide10({5, 5, 5}) → true
    //	oddDivide10({5, 5, 6}) → false
    //	oddDivide10({5, 5, 6, 1}) → true

    /**
     *
     * @param nums
     * 		int[] nums is the given array
     *
     * @return
     * 		returns true if the array can be divided so that the constraints are met
     * 		returns false if the array cannot be divided so that the constraints are met
     */
    static boolean oddDivide10(int[] nums)
    {
        return oddDivide101(0, nums, 0, 0);
    }
    static boolean oddDivide101(int start, int[] nums, int group1, int group2)
    {
    	//if we reached the end of the array and both groups are equal, we've found a solution
    	//return true
        if ( start == nums.length && (group1 % 10 == 0) && (group2 % 2 != 0))
            return true;
        //we reached end of array and solution not found, return false
        if (start == nums.length)
            return false;
        //Check if adding current element to group one results in solution
        //if so return true
        if(oddDivide101(start + 1, nums, group1 + nums[start], group2) == true)
            return true;
        //Check if adding current element to group two results in solution
        //if so return true
        if(oddDivide101(start + 1, nums, group1, group2 + nums[start]) == true)
            return true;
	
        return false;
    }

    //	Problem #8
    //	Given an array of ints, is it possible to divide the ints into
    //	two groups, so that the sum of the two groups is the same, with
    //	these constraints: all the values that are multiple of 5 must
    //	be in one group, and all the values that are a multiple of 3
    //	(and not a multiple of 5) must be in the other. (No loops needed.)

    //	divide53({1,1}) → true
    //	divide53({1, 1, 1}) → false
    //	divide53({2, 4, 2}) → true

    /**
     *
     * @param nums
     * 		int[] nums is the given array
     *
     * @return
     * 		returns true if the array can be divided so that the constraints are met
     * 		returns false if the array cannot be divided so that the constraints are met
     */
    static boolean divide53(int[] nums)
    {
        return divide531(0,nums,0,0);
    }

    static boolean divide531(int start, int[] nums, int group5s, int group3s)
    {
    	//if we reached the end of the array and both groups are equal, we've found a solution
    	//return true
        if ( start == nums.length && group5s == group3s)
            return true;
        //we reached end of array and solution not found, return false
        if (start == nums.length)
            return false;
        
        //if number is multiple of 5, we must add it to group5s
        if(nums[start] % 5 == 0)
        	if(divide531(start + 1, nums, group5s + nums[start], group3s) == true)
        		return true;
        //if number is multiple of 3, we must add it to group3s
        if(nums[start] % 5 == 0)
        	if(divide531(start + 1, nums, group5s, group3s + nums[start]) == true)
        		return true;
        
        //if neither, do the usual backtrack calls
        if(divide531(start + 1, nums, group5s + nums[start], group3s) == true)
    		return true;
        if(divide531(start + 1, nums, group5s, group3s + nums[start]) == true)
    		return true;
        
        return false;
    }

    ///////////////////////////////////////////
    //
    // End of assignment code.
    //
    ///////////////////////////////////////////

    public static void main(String[] args)
    {
    int[] t    = new int[] {20,3,4,11,3};
	int[] six  = new int[] {2,4,6,6,7};
	int[] adj  = new int[] {2,3,4,5,3,2,5,6};
	int[] mult = new int[] {3,4,10,1};
	int[] div  = new int[] {2,2,2,2};
	int[] odd  = new int[] {20,5};
	int[] fives= new int[] {10,10,6,9,5};
	int[] clump= new int[] {1, 2, 4,4, 8, 1,1,1,1};
    /*System.out.println(groupSumsTarget(0, t, 10));
	System.out.println(groupSumsTarget6(0, six, 15));
	System.out.println(groupSumsTargetNoAdj(0, adj, 13));
	System.out.println(groupSumsTarget5(0, mult, 12));*/
	System.out.println(groupSumsTargetClump(0, clump, 14));
	System.out.println(divideArray(div));
	System.out.println(oddDivide10(odd));
	System.out.println(divide53(fives));
	System.out.println(groupSumsTarget(0, new int[]{2, 4, 3,5,1,6}, 18));
    }

}