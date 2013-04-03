package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import vEB.VEBTree;

public class VEBTreeTest 
{
	private VEBTree vEBTree;
	
	@Before
	public void setUp() throws Exception
	{
		vEBTree = VEBTree.createVEBTree(16);
		
		/* Insert Elements */
		vEBTree.insert(3);
		vEBTree.insert(5);
		vEBTree.insert(8);
		vEBTree.insert(10);
		vEBTree.insert(12);
		vEBTree.insert(13);
		vEBTree.insert(14);
		vEBTree.insert(15);
	}
	
	@Test
	public void testInsertSearch()
	{
		assertTrue("We inserted 3:", vEBTree.search(3));
		assertTrue("We inserted 5:", vEBTree.search(5));
		assertTrue("We inserted 8:", vEBTree.search(8));
		assertTrue("We inserted 10:", vEBTree.search(10));
		assertTrue("We inserted 12:", vEBTree.search(12));
		assertTrue("We inserted 13:", vEBTree.search(13));
		assertTrue("We inserted 14:", vEBTree.search(14));
		assertTrue("We inserted 15:", vEBTree.search(15));
		
		assertTrue("We DID NOT insert 0:", !vEBTree.search(0));
		assertTrue("We DID NOT insert 1:", !vEBTree.search(1));
		assertTrue("We DID NOT insert 1:", !vEBTree.search(2));
		assertTrue("We DID NOT insert 1:", !vEBTree.search(4));
		assertTrue("We DID NOT insert 1:", !vEBTree.search(6));
		assertTrue("We DID NOT insert 1:", !vEBTree.search(7));
		assertTrue("We DID NOT insert 1:", !vEBTree.search(9));
	}
	
	@Test
	public void testDeleteSearch()
	{
		vEBTree.delete(3);
		assertTrue("We deleted 3:", !vEBTree.search(3));
		
		vEBTree.delete(5);
		assertTrue("We deleted 5:", !vEBTree.search(5));
		
		vEBTree.delete(8);
		assertTrue("We deleted 8:", !vEBTree.search(8));
		
		vEBTree.delete(10);
		assertTrue("We deleted 10:", !vEBTree.search(10));
		
		vEBTree.delete(12);
		assertTrue("We deleted 12:", !vEBTree.search(12));
		
		vEBTree.delete(13);
		assertTrue("We deleted 13:", !vEBTree.search(13));
		
		vEBTree.delete(14);
		assertTrue("We deleted 14:", !vEBTree.search(14));
		
		vEBTree.delete(15);
		assertTrue("We deleted 15:", !vEBTree.search(15));
	}
	
	@Test
	public void testPredecessor()
	{
		assertEquals("3 has no predecessor:", -1, vEBTree.predecessor(3));
		
		assertEquals("Predecessor of 5 is 3:", 3, vEBTree.predecessor(5));
		
		assertEquals("Predecessor of 8 is 5:", 5, vEBTree.predecessor(8));
		
		assertEquals("Predecessor of 10 is 8:", 8, vEBTree.predecessor(10));
		
		assertEquals("Predecessor of 15 is 14:", 14, vEBTree.predecessor(15));
	}
	
	@Test
	public void testMin()
	{
		assertEquals("Min should be 3:", 3, vEBTree.min());
	}
	
	@Test
	public void testMax()
	{
		assertEquals("Max should be 15:", 15, vEBTree.max());
	}

}
