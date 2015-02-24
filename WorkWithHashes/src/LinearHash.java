
public class LinearHash 
{
	int m_nTableSize = 10000;
	DataObject[] m_ObjectArray;
	//keeps a count of how many items are in hash table
	int m_nOccupied = 1;
	
	public LinearHash()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public LinearHash(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	//resize hash table
	public void Resize() throws Exception
	{
			//System.out.println("Resizing...");
		
		//double table size
		m_nTableSize *= 2;
		//rename old table
		DataObject[] OldObjectArray = m_ObjectArray;
		//declare a new array in m_ObjectArray's place
		m_ObjectArray = new DataObject[m_nTableSize];
		
		//rehash and replace every element in the old array into the new array
		for(int i = 0; i < m_nTableSize / 2; i++)
		{
			if(OldObjectArray[i] != null)
				this.put( new DataObject(OldObjectArray[i].GetKey()));
		}
	}
	
	
	/**
	 *  @param strKey
	 *  	key to be hashed
	 *  @param 
	 *  	DataObject to be stored
	 *  
	 */
	public void put(DataObject objData) throws Exception
	{
		if(objData == null)
			throw new NullPointerException();
		
		//Resize if necessary
		if((float)m_nOccupied/(float)m_nTableSize > 0.5)
			Resize();
		
		long lHash = Utility.HashFromString(objData.GetKey()) % m_nTableSize;
		long lStart = lHash;

		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null)
		{
			if(m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey().equals(objData.GetKey()))
				break;
			
			lHash++;
			
			if((lStart % m_nTableSize) == (lHash % m_nTableSize))
			{
				throw new Exception("No empty slot");
			}
		}
		
		m_ObjectArray[(int)lHash%m_nTableSize] = objData;
		m_nOccupied++;
	}

	/**
	 * 
	 * @param strKey
	 * 	key to be searched for
	 * @return
	 * 	found DataObject
	 * @throws Exception 
	 */
	public DataObject get( String strKey ) throws Exception
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		
		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null &&
				!m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey().equals(strKey))
		{
			lHash++;
		}

		return( m_ObjectArray[(int)(lHash%m_nTableSize)] );
	}
}
