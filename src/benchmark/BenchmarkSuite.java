package benchmark;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.TreeSet;

import vEB.VEBTree;

public class BenchmarkSuite
{
	private VEBTree vEBTree;
	private Hashtable<Integer, Integer> hashTable;
	private TreeSet<Integer> binaryTree;
	private LinkedList<Integer> linkedList;
	private int[] sortedArray;

	public BenchmarkSuite()
	{
	}
	
	/*
	 * Universe must be a size that is a power of 2.
	 */
	public void benchmarkAll(int universeSize)
	{
		vEBTree = VEBTree.createVEBTree(universeSize);
		hashTable = new Hashtable<Integer, Integer>(universeSize);
		binaryTree = new TreeSet<Integer>();
		linkedList = new LinkedList<Integer>();
		sortedArray = new int[universeSize];
	}
	
	private void benchmarkInsert()
	{
		
	}
	
	private void benchmarkSearch()
	{
		
	}
	
	private void benchmarkMax()
	{
		
	}
	
	private void benchmarkMin()
	{
		
	}
	
	private void benchmarkPredecessor()
	{
		
	}
	
	private void benchmarkDelete()
	{
		
	}
	
}
