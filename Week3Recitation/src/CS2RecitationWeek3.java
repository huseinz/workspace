import java.util.Scanner;

public class CS2RecitationWeek3 
{
    ///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////
	
        /*
            Note: All of the following assignments must be completed without the use of any loops including (for, while, and do-while).
            You may not utilize them at any point in your programming to help enhance your use of recursion.
        */
    
    
    
	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return( "Husein,Zubir,z3084147");
	}    
        
        /*  Directions: Recursive function that returns the value of a desired Fibonatchi number
            Note: For all recursive functions you should always be some calls that do
            not result in a recursive call without one your function will end up in an
            infinite loop.
        */
        //FibonatchiNumber(1) -> 1
        //FibonatchiNumber(7) -> 13
        //FibonatchiNumber(17) -> 1597
    
        /*
           @param FibonatchiNumber
                            int of desired fibonatchi number
           @return
                            int value of the fibonatchi number
        */
        
        static int getFibonatchiNumber(int FibonatchiNumber)
        {
        	if(FibonatchiNumber <= 0 )
        		return 0;
        	if (FibonatchiNumber == 1)
        		return 1;
        	return getFibonatchiNumber(FibonatchiNumber - 1) + getFibonatchiNumber(FibonatchiNumber - 2); 
        }
    
        
        //  Directions: Recursive function that returns the value of a desired factorial
      
        //FactorialNumber(1) -> 1
        //FactorialNumber(7) -> 5040
        //FactorialNumber(12) -> 479001600
    
        /*
           @param FactorialNumber
                            int of desired Factorial number
           @return
                            int value of the Factoiral number
        */
    
        static int getFactorialNumber(int FactorialNumber)
        {
        	if(FactorialNumber <= 1)
        		return 1;
        	return FactorialNumber * getFactorialNumber(FactorialNumber - 1);
        }
    
        /*  Directions: Recursive function that adds up the the squares of a range of two integer values
            Example: SumSquaresOfRange(5, 10);
            (5*5)+(6*6)+(7*7)+(8*8)+(9*9)+(10*10)=355
        */
      
        
        /*
           @param int x , int y
                            two integer values of the desired ranage
           @return
                            int value of the sum of all the numbers in the range squared
        */
        
        static int SumSquaresOfRange(int x , int y)
        {
        	//check if range has closed
        	if (x >= y + 1)
        		return 0;
        	return y * y + SumSquaresOfRange(x, y - 1);
        }
	
        public static void main(String[] args)
		{       
        	
        	System.out.println(getFibonatchiNumber(17));
        	System.out.println(getFactorialNumber(12));
        	System.out.println(SumSquaresOfRange(5, 10));
        }//End of main

}
