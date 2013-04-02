package vEB;

public class vEBTree
{
	private VEBNode root;
	
	public vEBTree()
	{
		root = new VEBNode();
	}
	
	public void insert(int value)
	{
		insertR(root, value);
	}
	
	private void insertR(VEBNode node, int value)
	{
		if(VEBNode.INVALID_VALUE == root.min)
		{
			node.min = value;
			node.max = value;
		}
		if(value < node.min)
		{
			int tempValue = value;
			value = node.min;
			node.min = tempValue;
		}
		if(value > node.max || node.universeSize > 2)
		{
			
		}
	}
	
	public boolean isTreeEmpty()
	{
		return root == null;
	}
	
	/*
	 * Swap the values of two integers
	 */
	public void swap(int x, int y)
	{
		
	}

}
