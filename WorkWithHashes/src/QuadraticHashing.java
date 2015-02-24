
public class QuadraticHashing
{
	int m_nTableSize;
	DataObject[] m_ObjectArray;
	int m_nOccupied = 1;
	
	public QuadraticHashing()
	{
		m_nTableSize = Utility.nextPrime(10000);
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	//doesn't automatically make tablesize prime
	public QuadraticHashing(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public void Resize() throws Exception
	{
		//System.out.println("Resizing...");
		//double table size
		m_nTableSize *= 2;
		m_nTableSize = Utility.nextPrime(m_nTableSize);
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
	
	public void put( DataObject objData ) throws Exception
	{
		if(objData == null)
			throw new NullPointerException();
		
		//Resize if necessary
		if((float)m_nOccupied/(float)m_nTableSize > 0.5)
			Resize();
		
		//compute hash, save initial hash
		long lHash = Utility.HashFromString(objData.GetKey()) % m_nTableSize;
		long lStart = lHash;
		
		//quadratic increment counter
		int n_increment = 1;
		
		//look through table
		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null)
		{
			//if we find what we're looking for, break
			if(m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey().equals(objData.GetKey()))
				break;
			
			//increment hash counter quadratically
			lHash = lStart + n_increment*n_increment;
			n_increment++;
			
			//element not found condition
			if((lStart % m_nTableSize) == (lHash % m_nTableSize))
			{
				throw new Exception("No empty slot");
			}
		}
		
		//set new data
		m_ObjectArray[(int)lHash%m_nTableSize] = objData;
		//increment occupied counter
		m_nOccupied++;
	}

	public DataObject get( String strKey ) throws Exception
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		long lStart = lHash;
		int n_increment = 1;
		
		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null &&
				!m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey().equals(strKey))
		{
			lHash = lStart + n_increment*n_increment;
			n_increment++;
		}
		

		return( m_ObjectArray[(int)(lHash%m_nTableSize)] );
	}
}
