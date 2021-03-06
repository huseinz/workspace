
public class BinarySearchTrees 
{
	
	static BST m_objBST = new BST();
	
	public static void main(String[] args) 
	{
		m_objBST.Insert(10);
		m_objBST.Insert(50);
		m_objBST.Insert(20);
		m_objBST.Insert(10000);

		m_objBST.print_inorder(m_objBST.m_objRootNode);
		System.out.println(m_objBST.FindRank(m_objBST.m_objRootNode, 10));
		m_objBST.Delete(20);
		m_objBST.print_inorder(m_objBST.m_objRootNode);
		System.out.println(m_objBST.GetMin(m_objBST.m_objRootNode));
		System.out.println(m_objBST.GetMax(m_objBST.m_objRootNode));
		System.out.println(m_objBST.GetSubTreeSize(m_objBST.m_objRootNode));
		System.out.println(m_objBST.GetHeight(m_objBST.m_objRootNode));
	}
	
}
