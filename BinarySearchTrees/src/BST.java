
public class BST 
{
	// This is the root node, which starts off as null
	//   when the BST is empty.
	BSTNode m_objRootNode;
	int k;
	int height;
	
	// Class constructor.
	public BST()
	{
		// Not really necessary, provided for clarity.
		m_objRootNode = null;
		k = 3; //k = 3 by default
	}

	// Method to see if the tree is empty.
	public boolean IsEmpty()
	{
		// Return a boolean indicating whether the
		//   three is empty or not.
		return( m_objRootNode == null );
	}

	/* Functions to search for an element */
    public BSTNode Search( int nKeyValue )
    {
        return( Search( m_objRootNode, nKeyValue ) );
    }
    
    // Method to search for an element recursively.
    private BSTNode Search( BSTNode objNode, int nKeyValue )
    {
    	
    	if( objNode == null )
    	{
    		return( null );
    	}
    	
    	// First, we get the key value for this node.
    	int nThisKeyValue = objNode.GetKeyValue();
            
    	// See if the passed in key value is less. If so,
    	//   this indicates that we need to go left.
    	if( nKeyValue < nThisKeyValue )
    	{
    		// Get the left node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetLeftNode();
    	}
            
    	// See if the passed in key value is greater. If so,
    	//   this indicates that we need to go right.
    	else if( nKeyValue > nThisKeyValue)
    	{
    		// Get the right node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetRightNode();
    	}

    	// Here we have found the node with the key
    	//   value that was passed in.
    	else
    	{
    		return( objNode );
    	}
            
    	// Now call Search recursively.
    	return( Search( objNode, nKeyValue ) );
	}
    
    public void print_inorder(BSTNode m_objRootNode)
    {
    	if(m_objRootNode == null)
    		return;
    	print_inorder(m_objRootNode.GetLeftNode());
    	System.out.println(m_objRootNode.GetKeyValue());
    	print_inorder(m_objRootNode.GetRightNode());
    }
    
    public int GetSubTreeSize(BSTNode m_objRootNode)
    {
    	if(m_objRootNode == null)
    		return 0;
    	return GetSubTreeSize(m_objRootNode.GetLeftNode()) + 1 + GetSubTreeSize(m_objRootNode.GetRightNode());
    }
    
    public int FindRank(BSTNode m_objRootNode, int nKeyValue)
    {
    	BSTNode tmp = Search(nKeyValue);
    	tmp.rank = 0;
    	return GetRank(m_objRootNode, tmp);
    }
    
    private int GetRank(BSTNode m_objRootNode, BSTNode tmp)
    {
    	if(m_objRootNode == null)
    		return 0;
    	GetRank(m_objRootNode.GetLeftNode(), tmp);
    	if(m_objRootNode.GetKeyValue() < tmp.GetKeyValue())
    		tmp.rank++;
    	GetRank(m_objRootNode.GetRightNode(), tmp);
    	return tmp.rank;
    }
    
    private void UpdateSubTreeSize(BSTNode m_objRootNode)
    {
    	if(m_objRootNode == null)
    		return ;
    	UpdateSubTreeSize(m_objRootNode.GetLeftNode());
    	m_objRootNode.subtree_size = GetSubTreeSize(m_objRootNode);
    	UpdateSubTreeSize(m_objRootNode.GetRightNode());
    }
    
    // This method inserts a node based on the key value.
    public void Insert( int nKeyValue ) 
    {
    	// The root node is returned to m_objRootNode from Insert()
    	m_objRootNode = Insert( nKeyValue, m_objRootNode );
    	UpdateSubTreeSize(m_objRootNode);
    }    

    // Class protected (internal) method to insert nodes. This method
    //   will be called recursively.
    protected BSTNode Insert( int nKeyValue, BSTNode objNode ) 
    {
 
    	// This node is null and simply needs to be allocated.
        if( objNode == null )
        {
        	objNode = new BSTNode( nKeyValue );
        }
        
        // Here we need to walk left.
        else if( nKeyValue < objNode.GetKeyValue() && Math.abs(nKeyValue - objNode.GetKeyValue()) >= k )
        {
        	// Set the left node of this object by recursively walking left.
        	objNode.SetLeftNode( Insert( nKeyValue, objNode.GetLeftNode() ) );
        }
        
        // Here we need to talk right.
        else if( nKeyValue > objNode.GetKeyValue() && Math.abs(nKeyValue - objNode.GetKeyValue()) >= k )
        {
        	// Set the right node of this object by recursively walking right.
        	objNode.SetRightNode( Insert( nKeyValue, objNode.GetRightNode() ) );
        }
        
        return( objNode );
    }
    
    public void Delete( int nKeyValue){
    	m_objRootNode = Delete( m_objRootNode, nKeyValue );
    	height = GetHeight(m_objRootNode);
    	UpdateSubTreeSize(m_objRootNode);
    }
    
    private BSTNode Delete( BSTNode m_objRootNode, int nKeyValue )
    {
        if( m_objRootNode == null )
            return m_objRootNode;   // Item not found; do nothing
        if( m_objRootNode.GetKeyValue() - nKeyValue > 0 )
        	m_objRootNode.SetLeftNode(Delete( m_objRootNode.GetLeftNode(), nKeyValue ));
        else if( m_objRootNode.GetKeyValue() - nKeyValue < 0 )
        	m_objRootNode.SetRightNode(Delete(  m_objRootNode.GetRightNode(),nKeyValue ));
        else if( m_objRootNode.GetLeftNode() != null && m_objRootNode.GetRightNode() != null ) // Two children
        {
        	m_objRootNode.SetKeyValue(_findMin( m_objRootNode.GetRightNode() ).GetKeyValue());
            m_objRootNode.SetRightNode(Delete( m_objRootNode.GetRightNode(),m_objRootNode.GetKeyValue() ));
        }
        else
        	m_objRootNode = ( m_objRootNode.GetLeftNode() != null ) ? m_objRootNode.GetLeftNode() : m_objRootNode.GetRightNode();
        return m_objRootNode;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private BSTNode _findMin( BSTNode m_objRootNode )
    {
        if( m_objRootNode == null )
            return null;
        else if( m_objRootNode.GetLeftNode() == null )
            return m_objRootNode;
        return _findMin( m_objRootNode.GetLeftNode() );
    }
    public int GetMin( BSTNode m_objRootNode )
    {
        if( m_objRootNode == null )
            return 0;
        else if( m_objRootNode.GetLeftNode() == null )
            return m_objRootNode.GetKeyValue();
        return GetMin( m_objRootNode.GetLeftNode() );
    }
    public int GetMax( BSTNode m_objRootNode )
    {
        if( m_objRootNode == null )
            return 0;
        else if( m_objRootNode.GetRightNode() == null )
            return m_objRootNode.GetKeyValue();
        return GetMax( m_objRootNode.GetRightNode() );
    }
    public int GetHeight(BSTNode m_objRootNode)
    {
        if(m_objRootNode == null)
            return -1;

        int left = GetHeight(m_objRootNode.GetLeftNode());
        int right = GetHeight(m_objRootNode.GetRightNode());

        return left > right ? left + 1 : right + 1;
    }
    
    public int Getk()
    {
    	return k;
    }
    public void Setk(int k)
    {
    	this.k = k;
    }
    
}
