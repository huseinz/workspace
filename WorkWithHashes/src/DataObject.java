
public class DataObject 
{
	public DataObject(String strKey)
	{
		m_strKey = strKey;
	}
	
	private String m_strKey = "";
	// Other data here.
	
	public String GetKey() 
	{
		return( m_strKey );
	}
	
}
