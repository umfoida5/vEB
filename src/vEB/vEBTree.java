package vEB;

public class VEBTree
{
	public static int BASE_SIZE = 2; /* Base vEB Node size */
	public static int NULL = -1; /* Initial min and max values */
	
	private VEBNode root;
	
	/*
	 * Creates and returns an instance of a van Emde Boas Tree.
	 */
	public static VEBTree createVEBTree(int universeSize)
	{
		if(isPowerOf2(universeSize))
		{
			return new VEBTree(universeSize);
		}
		else
		{
			return null;
		}
	}	
	
	
	/*
	 * Insert x into the tree.
	 */
	public void insert(int x)
	{
		insertR(root, x);
	}
	
	
	/*
	 * TODO. Delete x from the tree.
	 */
	public void delete(int x)
	{
		
	}
	
	
	/*
	 * Returns true if x is in the tree, false otherwise.
	 */
	public boolean search(int x)
	{
		return searchR(root, x);
	}
	
	
	/*
	 * Returns the predecessor of x, or -1 if x is the minimum.
	 */
	public int predecessor(int x)
	{
		return predecessorR(root, x);
	}
	
	
	public int predecessorR(VEBNode node, int x)
	{
		int highOfX;
		if(NULL == node.min || x < node.min)
		{
			return NULL;
		}
		else if(x >= node.max)
		{
			return node.max;
		}
		else if(BASE_SIZE == node.universeSize)
		{
			return 0;
		}
		else if(NULL != node.cluster[highOfX = high(node, x)].min && low(node, x) >= node.cluster[highOfX].min)
		{
			return index(node, highOfX, predecessorR(node.cluster[highOfX], low(node, x)));
		}
		else
		{
			int summaryPred = predecessorR(node.summary, highOfX - 1);
			return index(node, summaryPred, node.cluster[summaryPred].max);
		}
	}
	
	
	/*
	 * Returns the minimum value in the tree or -1 if the tree is empty.
	 */
	public int min()
	{
		return root.min;
	}
	
	
	/*
	 * Returns the maximum value in the tree or -1 if the tree is empty.
	 */
	public int max()
	{
		return root.max;
	}
	
	
	/*
	 * Creates the tree structure with a universize of size
	 * universeSize.
	 */
	private VEBTree(int universeSize)
	{
		/* 
		 * This node will handle creating all the other nodes,
		 * and the full tree will be built.
		 */
		root = new VEBNode(universeSize);
	}
	
	
	private void insertR(VEBNode node, int x)
	{
		/* This node is empty */
		if(NULL == node.min)
		{
			node.min = x;
			node.max = x;
		}
		if(x < node.min)
		{
			int tempValue = x;
			x = node.min;
			node.min = tempValue;
		}
		if(x > node.min || node.universeSize > BASE_SIZE)
		{
			int highOfX = high(node, x);
			int lowOfX = low(node, x);
			
			/* Case when the cluster is non-empty*/
			if(NULL != node.cluster[highOfX].min)
			{
				/* Insert into the cluster recursively */
				insertR(node.cluster[highOfX], lowOfX);
			}
			else
			{
				/* Insert into the summary recursively */
				insertR(node.summary, highOfX);
				node.cluster[highOfX].min = lowOfX;
				node.cluster[highOfX].max = lowOfX;
			}
		}
		if(x > node.max)
		{
			node.max = x;
		}
	}
	
	
	private boolean searchR(VEBNode node, int x)
	{
		if(x == node.min || x == node.max)
		{
			return true;
		}
		else if(BASE_SIZE == node.universeSize)
		{
			return false;
		}
		else
		{
			return searchR(node.cluster[high(node, x)], low(node, x));
		}
	}
	
	
	/*
	 * Returns the integer value of the first half of the bits of x.
	 */
	private int high(VEBNode node, int x)
	{
		return (int)Math.floor(x / lowerSquareRoot(node));
	}
	
	
	/*
	 * Returns the value of the most significant bits of x.
	 */
	private double higherSquareRoot(VEBNode node)
	{
		return Math.pow(2, Math.ceil((Math.log10(node.universeSize) / Math.log10(2)) / 2));
	}
	
	
	/*
	 * Returns the integer value of the second half of the bits of x.
	 */
	private int low(VEBNode node, int x)
	{
		return x % (int)lowerSquareRoot(node);
	}
	
	
	/*
	 * Returns the value of the least significant bits of x.
	 */
	private double lowerSquareRoot(VEBNode node)
	{
		/* Change bases to 2 since java api does not support this. */
		return Math.pow(2, Math.floor((Math.log10(node.universeSize) / Math.log10(2)) / 2));
	}
	
	
	/*
	 * Returns the index in the tree of the given value.
	 */
	private int index(VEBNode node, int x, int y)
	{
		return (int)(x * lowerSquareRoot(node) + y);
	}
	
	
	/*
	 * Returns true if x is a power of 2, false otherwise.
	 */
	private static boolean isPowerOf2(int x)
	{
		if(0 == x)
		{
			return false;
		}
		
		while(x % 2 == 0)
		{
			x = x / 2;
		}
		
		if(x > 1)
		{
			return false;
		}
		
		return true;
	}
}
