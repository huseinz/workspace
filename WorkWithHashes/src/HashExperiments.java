


public class HashExperiments 
{
	public static void main(String[] args) 
	{
		
		LinearHash lh = new LinearHash(300);
		SeparateChaining sh = new SeparateChaining();
		QuadraticHashing qh = new QuadraticHashing();

		// Start the clock.	
		long start = System.currentTimeMillis();
		
		for( int i=0; i<Lists.ListOne.length; i++ )
		{
			lh.put( Lists.ListOne[i], new DataObject( Lists.ListOne[i] ) );
			sh.put( Lists.ListOne[i], new DataObject( Lists.ListOne[i] ) );
			qh.put( Lists.ListOne[i], new DataObject( Lists.ListOne[i] ) );
		}

		long end = System.currentTimeMillis();
		// Print out the time it took.
		System.out.println("Took "+(end-start)+" ms.");
		
		/*for(int i = 0; i < Lists.ListOne.length; i++)
		{
			System.out.println(qh.get(Lists.ListOne[i]).m_strKey);
		}*/
	}

}
