
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
	public void Resize()
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
				this.put(OldObjectArray[i].m_strKey, new DataObject(OldObjectArray[i].m_strKey));
		}
	}
	
	
	/**
	 *  @param strKey
	 *  	key to be hashed
	 *  @param 
	 *  	DataObject to be stored
	 *  
	 */
	public void put( String strKey, DataObject objData )
	{
		//Resize if necessary
		if((float)m_nOccupied/(float)m_nTableSize > 0.7)
			Resize();
		
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;

		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null)
		{
			if(m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey().equals(strKey))
				break;

			lHash++;
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
	 */
	public DataObject get( String strKey )
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
