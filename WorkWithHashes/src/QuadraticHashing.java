
public class QuadraticHashing
{
	int m_nTableSize = 10000;
	DataObject[] m_ObjectArray;
	int m_nOccupied = 1;
	
	public QuadraticHashing()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public QuadraticHashing(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public void Resize()
	{
			System.out.println("Resizing...");
			m_nTableSize *= 2;
			DataObject[] OldObjectArray = m_ObjectArray;
			m_ObjectArray = new DataObject[m_nTableSize];
			
			for(int i = 0; i < m_nTableSize / 2; i++)
			{
				if(OldObjectArray[i] != null)
					this.put(OldObjectArray[i].m_strKey, new DataObject(OldObjectArray[i].m_strKey));
			}
	}
	
	public void put( String strKey, DataObject objData )
	{
		//Resize if necessary
		if((float)m_nOccupied/(float)m_nTableSize > 0.7)
			Resize();
		
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		int n_increment = 1;
		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null)
		{
			if(m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey().equals(strKey))
				break;

			lHash += n_increment*n_increment;
			n_increment++;
		}
		
		m_ObjectArray[(int)lHash%m_nTableSize] = objData;
		m_nOccupied++;
	}

	public DataObject get( String strKey )
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		int n_increment = 1;
		
		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null &&
				!m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey().equals(strKey))
		{
			lHash += n_increment*n_increment;
			n_increment++;
		}

		return( m_ObjectArray[(int)(lHash%m_nTableSize)] );
	}
}
