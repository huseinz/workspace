


public class HashExperiments 
{
	public static void main(String[] args) throws Exception 
	{
		
		LinearHash lh = new LinearHash();
		SeparateChaining sh = new SeparateChaining();
		QuadraticHashing qh = new QuadraticHashing();

		// Start the clock.	
		long start = System.currentTimeMillis();
		
		for( int i=0; i<Lists.ListOne.length; i++ )
		{
			lh.put( new DataObject( Lists.ListOne[i] ));
			sh.put( new DataObject( Lists.ListOne[i] ) );
			qh.put( new DataObject( Lists.ListOne[i] ) );
		}

		long end = System.currentTimeMillis();
		// Print out the time it took.
		System.out.println("Took "+(end-start)+" ms.");
		
	/*	for(int i = 0; i < Lists.ListOne.length; i++)
		{
			System.out.println(qh.get(Lists.ListOne[i]).GetKey());
		}*/
	}

}
