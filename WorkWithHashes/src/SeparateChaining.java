import java.util.LinkedList;

public class SeparateChaining
{
	int m_nTableSize = 10000;
	LinkedList<DataObject>[] m_ObjectArray;
	
	@SuppressWarnings("unchecked")
	public SeparateChaining()
	{
		m_ObjectArray = new LinkedList[m_nTableSize];
		for(int i = 0; i < m_nTableSize; i++)
			m_ObjectArray[i] = new LinkedList<DataObject>();
	}
	
	@SuppressWarnings("unchecked")
	public SeparateChaining(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new LinkedList[m_nTableSize];
		for(int i = 0; i < m_nTableSize; i++)
			m_ObjectArray[i] = new LinkedList<DataObject>();
	}
	
	public void put( String strKey, DataObject objData )
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		
		m_ObjectArray[(int)lHash%m_nTableSize].add(objData);
	}

	public DataObject get( String strKey )
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		
		for(DataObject obj : m_ObjectArray[(int)lHash%m_nTableSize])
		{
			if (obj.m_strKey == strKey)
				return obj;
		}

		return( new DataObject("not found"));
	}
}
