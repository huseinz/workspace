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
	
	//I don't think a resize is necessary for a separate chained hash table
	//but here it is anyway
	@SuppressWarnings("unchecked")
	public void Resize() throws Exception
	{
			//System.out.println("Resizing...");
			m_nTableSize *= 2;
			//keep old array
			LinkedList<DataObject>[] OldObjectArray = m_ObjectArray;
			
			//declare and initialize new enlarged array
			m_ObjectArray = new LinkedList[m_nTableSize];
			for(int i = 0; i < m_nTableSize; i++)
				m_ObjectArray[i] = new LinkedList<DataObject>();
			
			//reput all elements from old array to new array
			for(int i = 0; i < m_nTableSize / 2; i++)
			{
				if(OldObjectArray[i] != null)
					for(DataObject obj : m_ObjectArray[i])
					{
						this.put(new DataObject(obj.GetKey()));
					}
			}
	}
	
	public void put( DataObject objData ) throws Exception
	{
		if(objData == null)
			throw new NullPointerException();
		
		long lHash = Utility.HashFromString(objData.GetKey()) % m_nTableSize;
		
		for(DataObject obj : m_ObjectArray[(int)lHash%m_nTableSize])
		{
			if (obj.GetKey().equals(objData.GetKey()))
			{
				obj = objData;
				return;
			}
		}
		
		m_ObjectArray[(int)lHash%m_nTableSize].add(objData);
	}

	public DataObject get( String strKey )
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		
		for(DataObject obj : m_ObjectArray[(int)lHash%m_nTableSize])
		{
			if (obj.GetKey().equals(strKey))
				return obj;
		}
		return null;
	}
}
