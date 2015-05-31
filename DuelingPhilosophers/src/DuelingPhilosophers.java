import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Edge {

    Vertex m_objTarget;
    public Edge( Vertex objTarget )
    {
        m_objTarget = objTarget;
    }
    public Vertex GetTarget()
    {
        return( m_objTarget );
    }
}

class Vertex {

    String m_strName;
    int m_nIndex;
    Edge[] m_objAdjacencies;
    Vertex m_objParent;
    boolean m_bVisited = false;
    public Vertex( String strName, int nIndex )
    {
        m_strName = strName;
        m_nIndex = nIndex;
    }
    public void SetAdjacencies( Edge[] objAdjacencies )
    {
        m_objAdjacencies = objAdjacencies;
    }
    public Vertex GetParent()
    {
        return( m_objParent );
    }
    public void SetParent( Vertex objParent )
    {
        m_objParent = objParent;
    }
    public Edge[] GetAdjacencies()
    {
        return( m_objAdjacencies );
    }
    public String GetName()
    {
        return( m_strName );
    }
    public boolean GetVisited()
    {
        return( m_bVisited );
    }
    public void SetVisited( boolean bStatus )
    {
        m_bVisited = bStatus;
    }
    public int GetIndex()
    {
        return( m_nIndex );
    }
}

public class DuelingPhilosophers {

    public static void main() throws FileNotFoundException {
    	Scanner objScanner = new Scanner(new File("input.txt"));
    	
    	int n = objScanner.nextInt();
		int m = objScanner.nextInt();
		
    	while(n != 0)
    	{
    		
    		Vertex[] vertices = new Vertex[n];
    		
    		for(int i = 0; i < m; i++)
    		{
    			
    		}
    	}
    	
    }
}
