package vEB;

public class VEBNode
{
	public static int INVALID_VALUE = -1;
	
	public int universeSize;
	public int min;
	public int max;
	public VEBNode summary;
	public VEBNode[] cluster;
	
	public VEBNode()
	{
		universeSize = 0;
		min = 0;
		max = 0;
		summary = null;
		cluster = null;
	}
	
	public VEBNode(int size, int min, int max, VEBNode summary, VEBNode[] cluster)
	{
		this.universeSize = size;
		this.min = min;
		this.max = max;
		this.summary = summary;
		this.cluster = cluster;
	}
}
