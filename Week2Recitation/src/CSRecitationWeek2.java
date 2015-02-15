import java.util.Random;
import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 recitation assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CSRecitationWeek2
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
	
	
	static int[] DoBubbleSort( int[] DataIn )
	{
		boolean swapped = true;
	    int j = 0;
	    int tmp;

	    while (swapped) {

	    	swapped = false;
	        j++;
	        for (int i = 0; i < DataIn.length - j; i++) 
	        {                                       
	        	if (DataIn[i] > DataIn[i + 1]) 
	        	{                          
	        		tmp = DataIn[i];
	                DataIn[i] = DataIn[i + 1];
	                DataIn[i + 1] = tmp;
	                swapped = true;
	            }
	        }                
	    }
		return DataIn;
	}
    
    static int[] DoInsertSort( int[] DataIn )
	{
    	for(int i = 1; i < DataIn.length; i++)
    	{
    		int temp = DataIn[i];
    		int j;
    		
    		for(j = i - 1; j >= 0 && temp < DataIn[j]; j--)
    			DataIn[j+1] = DataIn[j];
    		DataIn[j+1] = temp;
   		}
    	
    	return DataIn;
	}

    static int[] DoHeapSort( int[] DataIn )
	{
    	//set nHeap to input data, set heapsize to input data length
    	nHeap = DataIn;
    	heapSize = DataIn.length;
    	
    	//declare result array
    	int[] result = new int[DataIn.length];
    	
    	//create min heap
    	BuildHeap();
    	
    	//do heapsort
    	for(int i = 0; i < DataIn.length; i++)
    	{
    		//copy top of heap to result
    		result[i] = nHeap[0];
    		//swap rightmost element with top of heap
    		swap(nHeap, heapSize - 1, 0);
    		//reduce heapsize to 'delete' smallest element
    		heapSize--;
    		//rebuild heap
    		BuildHeap();
    	}
    	
    	return result;
	}

	// The heap (min-heap).
	static int[] nHeap;

	// Randomly generated numbers to be used as input.
	static int[] nRandomNumbers;

	// The size of the heap, nHeap. The size of the heap may be smaller than
	// nHeap.length. The heap is comprised of the elements at indexes between 0
	// and heapSize-1 of nHeap.
	static int heapSize;

	// Methods to get index of parent, left child, and right child of node at
	// index nIndex of the heap  (zero-based index). The indexes outputted by
	// GetLeft and GetRight must be verified to actually exist within the heap
	// (use heapSize to determine this).

	private static int GetParentIndex(int nIndex)
	{
		return (nIndex - 1) / 2;
	}

	static int GetLeft( int nIndex )
	{
		return nIndex * 2 + 1;
	}
	
	static int GetRight( int nIndex )
	{
		return nIndex * 2 + 2;
	}

	// Function to swap two numbers in an array. Auxiliary method to SiftUp and
	// Heapify.
    	public static void swap(int arr[], int i, int j)
    	{
    		if(i > arr.length || j > arr.length || i < 0 || j < 0)
    			return;
    		
    		int temp = arr[i];
    		arr[i] = arr[j];
    		arr[j] = temp;
    	}
	
	static void BuildHeap()
	{
		// Input: static field nHeap, an array of ints, representing a full
		// binary tree (not a BST), except for the last level which may be
		// only partially filled from the left

		// Input: static field heapSize 

		// Output: modified nHeap such that it represents a min-heap  

		// Complexity: O(heapSize)

		// Start at the parent of the last node (i.e., the right-most,
		// bottom-most node in the tree that is not a leaf)
		//    - the last node is heapSize - 1
		//    - the parent of any node is (nodeIdx - 1) / 2
		//    - therefore the parent of the last node is
		//      (heapSize - 2) / 2
		//    - equivalent to GetParentIndex(heapSize-1)

		// Algorithm:

		// for i = GetParentIndex(heapSize-1) DOWN TO  0:
		//      Heapify(i)
		
		for(int i = GetParentIndex(heapSize - 1); i >= 0; i--)
		{
			Heapify(i);
		}
	}
	
	
	static void Heapify( int nIndex )
	{
		// Input: static field nHeap, an array of ints, representing a full
		// binary tree (not a BST), except for the last level which may be
		// only partially filled from the left

		// Input: nIndex, an index into nHeap

		// Output: modified nHeap such that element nIndex has been "sifted
		// down" until that element is less than its children or that element
		// is a leaf 

		// Another name for this method might be "SiftDown".

		// Algorithm:

		// If nIndex is a leaf node (has no children), then there is nothing to
		// do (use heapSize to determine this)

		// leftIdx <- GetLeft(nIndex)
		// rightIdx <- GetRight(nIndex)

		// smallest <- either the index leftIdx, rightIdx, or nIndex whose
		// corresponding heap node value is the smallest out of these three
		// (however, ensure that leftIdx and rightIdx are actually children;
		// use heapSize to determine this)
		
		// if smallest != nIndex:
		//    COMMENT: This means the node at nIndex has a child smaller than
		//    itself at index smallest. This smallest child needs to come up
		//    and replace the node at nIndex, and the node at nIndex needs to
		//    come down to replace the smallest child. We then repeat the
		//    process recursively at index smallest.

		//    swap node values at indexes smallest and nIndex
		//    Heapify(smallest)
		
		//check if nIndex is leaf
			if(GetLeft(nIndex) > heapSize && GetRight(nIndex) > heapSize)
				return;
			
			//get children
			int leftIdx  = GetLeft(nIndex);
			int rightIdx = GetRight(nIndex);
			//holds smallest index
			int minIdx = nIndex;
			//check if left node is smaller than parent
			if(leftIdx < heapSize && nHeap[leftIdx] < nHeap[nIndex])
				minIdx = leftIdx;
			else
				minIdx = nIndex;
			//check if right node is smaller than parent
			if(rightIdx < heapSize && nHeap[rightIdx] < nHeap[minIdx])
				minIdx = rightIdx;
			//swap is one of the children is smaller, heapify again
			if(minIdx != nIndex)
			{
				swap(nHeap, minIdx, nIndex);
				Heapify(minIdx);
			}
				
			/*//check if leftIdx is valid
			if(leftIdx < heapSize)
			{
				//check if rightIdx is valid
				if(rightIdx <= heapSize)
				{
					//do comparisons and swap if necessary
					if(nHeap[leftIdx] <= nHeap[rightIdx] && nHeap[leftIdx] < nHeap[nIndex])
					{
						swap(nHeap, leftIdx, nIndex);
						Heapify(leftIdx);
					}
					else if (nHeap[leftIdx] >= nHeap[rightIdx] && nHeap[rightIdx] < nHeap[nIndex])
					{
						swap(nHeap, rightIdx, nIndex);
						Heapify(rightIdx);
					}
				}
				else
				{
					if(nHeap[leftIdx] <= nHeap[nIndex])
					{
						swap(nHeap, leftIdx, nIndex);
						Heapify(leftIdx);
					}
				}
			}*/

	}


	// The following methods, AddElement and its auxiliary method SiftUp, are
	// not used with DoHeapSort. 

	// DoHeapSort uses BuildHeap instead, which is a more efficient way (O(n))
	// to build a heap starting from a list of numbers than AddElement. The way
	// to build a heap using AddElement is to start with an empty heap and
	// repeatedly add each element into the heap. This way is O(nlogn).
	// However, AddElement is useful to add new elements into an existing heap
	// once it has been built with BuildHeap.

	static void AddElement( int nNumber )
	{
		// Input: static fields nHeap, heapSize. nHeap must already be a valid
		// min-heap.

		// Input: nNumber, a new node to add to the heap

		// Output: heap with nNumber added, heapSize inceased by 1

		// Complexity: O(log(heapSize))

		// Precondition: heapSize+1 <= nHeap.length
		// If this is not the case, before running the algorithm, create a new
		// array that is double the size of nHeap, copy the elements of nHeap
		// into it, and assign nHeap to be the new larger array

		// Algorithm:

		// Increase heapSize by 1
		// Copy nNumber to index heapSize-1 of the heap
		// SiftUp(heapSize-1)
		
		//increase size of heap if needed
		if(heapSize + 1 > nHeap.length)
		{
			int[] newarray = new int[nHeap.length * 2];
			for(int i = 0; i < nHeap.length; i++)
				newarray[i] = nHeap[i];
			nHeap = newarray;
			return;
		}
		
		//increment heapsize
		heapSize++;
		//add element to heap
		nHeap[heapSize - 1] = nNumber;
		//sift up to restore heap
		SiftUp(heapSize - 1);
	}
	
	static void SiftUp( int nNodeIndex )
	{
		// repeatedly sift up the element at nNodeIndex as long as its parent
		// node is greater or the element is the root (this is similar to
		// Heapify but it moves the element upwards instead of downwards)
		
		/*int nIndex = nNodeIndex;
		while(nIndex != 0 && nHeap[nIndex] < GetParentIndex(nIndex))
		{
			int parentIndex = GetParentIndex(nIndex);
			swap(nHeap, nIndex, parentIndex);
			nIndex = parentIndex;
		}*/
		
		//if node is root/has no parent
		if(nNodeIndex == 0)
			return;
		//get parent
		int parentIndex = GetParentIndex(nNodeIndex);
		//if parent is greater than nNodeIndex, swap and sift up again
		if(nHeap[parentIndex] > nHeap[nNodeIndex])
		{
			swap(nHeap, parentIndex, nNodeIndex);
			SiftUp(parentIndex);
		}
	}

	/** TA PLEASE READ :O 
	 * 
	 *  I wasn't sure if we needed to include our own DisplayIntegerArray function or not,
	 *  so it's down below. I didn't want a compilation error due to a duplicate function.
	 */
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////

	static void DisplayIntegerArray(int[] DataIn)
	{
		for(int i : DataIn)
			System.out.print(i + " ");
		System.out.print("\n");
	}
	
	public static void setRandomArray(int n)
	{
		Random rnd = new Random();
		nRandomNumbers = new int[n];
		for( int i=0; i<nRandomNumbers.length; i++ )
		{
			nRandomNumbers[i] = rnd.nextInt( 500 );
		}
	}
	
	public static void main(String[] args)
	{
		// Code to test bubble sort.
		setRandomArray(15);
		int[] bubbleStudentOut1 = DoBubbleSort(nRandomNumbers);
		//int[] bubbleStudentOut2 = DoBubbleSort(BubbleIn);
	//	DisplayIntegerArray(bubbleStudentOut1);
	//	DisplayIntegerArray(bubbleStudentOut2);
		
		// Code to test insert sort.
		setRandomArray(15);
		int[] insertStudentOut1 = DoInsertSort(nRandomNumbers);
		//int[] insertStudentOut2 = DoInsertSort(InsertIn);
	//	DisplayIntegerArray(insertStudentOut1);
		//DisplayIntegerArray(insertStudentOut2);
		
		setRandomArray(15);
		//nHeap = new int[]{368, 179, 280, 264, 398, 146, 42, 377, 368, 477, 319, 55, 274, 75, 498 };
		//heapSize = 15;
//		DisplayIntegerArray(nRandomNumbers);
		
//		DisplayIntegerArray(DoHeapSort(nRandomNumbers));
		
		// Code to test heap sort.
		setRandomArray(15);
		int[] heapStudentOut1 = DoHeapSort(nRandomNumbers);
//		int[] heapStudentOut2 = DoHeapSort(HeapIn);
	//	DisplayIntegerArray(heapStudentOut1);
//		DisplayIntegerArray(heapStudentOut2);

		// Code to test AddElement
		setRandomArray(15);
		nHeap = new int[nRandomNumbers.length];
		heapSize = 0;
		DisplayIntegerArray(nRandomNumbers);
		for (int i = 0; i < nRandomNumbers.length; i++)
		{
			AddElement(nRandomNumbers[i]);
		}

		// Show the array representation of the min-heap
		DisplayIntegerArray(nHeap);
	}
}
