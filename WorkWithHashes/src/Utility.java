
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
	
	
}
