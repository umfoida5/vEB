package benchmark;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

import vEB.VEBTree;

public class BenchmarkDictionary
{
	public static int universeSize = 4194304;
	public static String filename = "randomInts16MB.txt";
	
	public static void main(String[] args) 
	{
		benchmarchDataStructures();
	}

	
	public static void benchmarchDataStructures()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> deleteIndexes = new ArrayList<Integer>();
		
		for(int i = 0; i < universeSize; i=i+2)
		{
			list.add(i);
			deleteIndexes.add(i/2);
		}
		
		Collections.shuffle(list);
		Collections.shuffle(list);
		Collections.shuffle(list);
		Collections.shuffle(list);
		Collections.shuffle(list);
		
		Collections.shuffle(deleteIndexes);
		Collections.shuffle(deleteIndexes);
		Collections.shuffle(deleteIndexes);
		Collections.shuffle(deleteIndexes);
		Collections.shuffle(deleteIndexes);
		
		benchmarkVEBTree(list, deleteIndexes);
		
		//Hashtable<Integer, Integer> hashTable;
		//TreeSet<Integer> binaryTree;
		//LinkedList<Integer> linkedList;
		//int[] sortedArray;
		
	}
	
	public static void benchmarkVEBTree(ArrayList<Integer> arrayList, ArrayList<Integer> deleteIndexes)
	{		
		System.out.println("*** vEB Benchmark for universe of size "+universeSize+" ***");
		
		/* initialize data structure */
		long startTime = System.currentTimeMillis();
		VEBTree vEBTree =  VEBTree.createVEBTree(universeSize);
		System.out.println("Init time = "+(System.currentTimeMillis()-startTime));
		/* ************************* */
		
		/* insert into data structure */
		startTime = System.currentTimeMillis();
		for(int i = 0; i < arrayList.size(); i++)
		{
			vEBTree.insert(i);
		}
		System.out.println("Insert time = "+(System.currentTimeMillis()-startTime));
		/* ************************** */
		
		/* search in the data structure */
		startTime = System.currentTimeMillis();
		for(int i = 0; i < universeSize; i++)
		{
			vEBTree.search(i);
		}
		System.out.println("Search time = "+(System.currentTimeMillis()-startTime));
		/* **************************** */
		
		/* predecessor benchmark */
		startTime = System.currentTimeMillis();
		for(int i = 0; i < universeSize; i++)
		{
			vEBTree.predecessor(i);
		}
		System.out.println("Predecessor time = "+(System.currentTimeMillis()-startTime));
		/* ********************* */
		
		/* delete 1/4 of elements */
		startTime = System.currentTimeMillis();
		for(int i = 0; i < deleteIndexes.size(); i++)
		{
			vEBTree.delete(arrayList.get(deleteIndexes.get(i)));
		}
		System.out.println("Delete time = "+(System.currentTimeMillis()-startTime));
		/* ********************** */
	}
}




