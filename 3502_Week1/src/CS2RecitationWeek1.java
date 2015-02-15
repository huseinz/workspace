import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 recitation assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2RecitationWeek1 
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
	
	/**
	 * @return
	 * 		returns student name and PID
	 */
	static String GetNameAndPID()
	{
		return( "Husein,Zubir,z3084147");
	}
	
	// Write a method that recursively prints out all the 
	//   items in a linked list, in regular order. Fill in 
	//   the prototype below:
	
	/**
	 * 
	 * @param ds
	 * 		DataStruct object which is an integer linked list
	 */

	static void PrintRec(DataStruct ds) 
	{
		if (ds == null)
			return;
		else
		{
			System.out.println(ds.KeyValue);
			PrintRec(ds.Next);
		}
	}

	// Write a function that recursively prints out all 
	//   the items in a linked list, in reverse order. 
	//   Fill in the prototype below:
	
	/**
	 * 
	 * @param ds
	 * 		DataStruct object which is an integer linked list
	 */
	static void PrintBackRec(DataStruct ds) 
	{
		if (ds != null && ds.Next != null)
			PrintBackRec(ds.Next);
		System.out.println(ds.KeyValue);
	}

	// Write a function that iterates through a linked list 
	//   and adds 5 to each even number in the list and 
	//   subtracts 4 from each odd number in the list. For
	//   each member of the list print the before and after values.

	/**
	 * 
	 * @param ds
	 * 		DataStruct object which is an integer linked list
	 */
	static void EditList(DataStruct ds) 
	{
		//base case
		if (ds == null)
			return;
		else
		{	
			//print before value, check if even or odd and increment value respectively
			System.out.print("Before: " + ds.KeyValue );
			if(ds.KeyValue % 2 == 0)
				ds.KeyValue += 5;
			else
				ds.KeyValue -= 4;
			//print after value, recurse
			System.out.print(" After: " + ds.KeyValue + "\n");
			EditList(ds.Next);
		}
	}
	
	// Write a recursive function that takes in two linked 
	//   lists and determines if the lists are equivalent. 
	//   For two lists to be equivalent, they must have the 
	//   same number of items and each corresponding item must 
	//   be equal. Thus, the lists 3, 5, 7 and 3, 5, 7 are 
	//   equal, but 3, 7, 5 does not equal 3, 5, 7 or 3, 7. 
	//   (Hint: As a base case, two lists are equal if they 
	//   are both null, and not equal if one is null and the 
	//   other isn’t.) Return 1 if the two lists passed in are 
	//   equal, and 0 otherwise.
	
	/**
	 * 
	 * @param list1
	 * 		DataStruct object which is the first integer linked list
	 * @param list2
	 * 		DataStruct object which is the first integer linked list
	 * 
	 * @return
	 * 		return 1 if lists are equal
	 * 		return 0 if lists are different
	 */
	static int EqualLists(DataStruct list1, DataStruct list2) 
	{
		//base case (reached end of list and both are null), return 0
		if (list1 == null && list2 == null)     
			return 1;  
		//lists have different lengths, return 0
		if (list1 == null && list2 != null)  
			return 0; 
		if (list1 != null && list2 == null)  
			return 0;  
		//list element has different value, return 0
		if (list1.KeyValue != list1.KeyValue)
			return 0;  
		
		//recurse
		return EqualLists(list1.Next, list2.Next);    
	}

	// Write a function that takes in a pointer to the front 
	//   of a linked list and returns 1 if all the nodes in 
	//   the linked list are in sorted order (from smallest 
	//   to largest, with repeats allowed), and 0 otherwise. 
	//   The prototype is given below:

	/**
	 * 
	 * @param list
	 * 		DataStruct object which is an integer linked list
	 * 
	 * @return
	 * 		return 1 if list is in order
	 * 		return 0 if list is not in oder
	 */
	static int InOrder(DataStruct list) 
	{
		//reached end of list, so we can return 1
		if(list == null || list.Next == null)
			return 1;
		else
		{
			//if the next element is greater than the current element, recurse
			if(list.Next.KeyValue > list.KeyValue)
				return InOrder(list.Next);
			//else, list is not in order, return 0
			else
				return 0;
		}
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		DataStruct ds = new DataStruct();
		for( int i=0; i<4; i++ )
		{
			ds.AddToEndOfList(new DataStruct());
		}
		
		DataStruct ds2 = new DataStruct();
		ds2.KeyValue = 1;
		for( int i=0; i<4; i++ )
		{
			DataStruct ds3 = new DataStruct();
			ds3.KeyValue = 2 + i;
			ds2.AddToEndOfList(ds3);
		}

		// Test code here...
		PrintRec( ds );

		PrintBackRec( ds );

		EditList( ds );

		int nEqual = EqualLists( ds, ds );

		int nInOrder = InOrder(ds);

		nInOrder = InOrder(ds2);

		System.out.println(nEqual + " " + nInOrder);
		
	}

}