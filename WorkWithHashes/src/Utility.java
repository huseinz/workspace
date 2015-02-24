
public class Utility 
{
	
	public static long HashFromString( String strString)
	{
		long lHashValue = 0;
		
		for( int i=0; i<strString.length(); i++ )
		{
			lHashValue = (long)strString.charAt(i) + (lHashValue << 6) + (lHashValue << 16) - lHashValue;
		}
		
		return( lHashValue & 0x7fffffff );
	}
	
	//find next prime number after n
	public static int nextPrime(int n){
		
		while(!isPrime(n))
			n++;
		return n;
	}
	
	public static boolean isPrime(int n) {
		
	    if (n%2==0) return false;
	    
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	
}
