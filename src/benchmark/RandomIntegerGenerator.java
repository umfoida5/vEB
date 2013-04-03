package benchmark;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class RandomIntegerGenerator
{
	public static void main(String[] args)
	{
		BufferedWriter bufferedWriter = null;
		String fileName = "randomInts16MB.txt";
		int numsToGenerate = BenchmarkDictionary.universeSize;
		
		try
		{
			bufferedWriter = new BufferedWriter(new FileWriter(fileName));
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			for(int i = 0; i < numsToGenerate; i=i+2)
			{
				list.add(i);
			}
			
			Collections.shuffle(list);
			Collections.shuffle(list);
			Collections.shuffle(list);
			Collections.shuffle(list);
			Collections.shuffle(list);
			
			for(int i = 1; i <= numsToGenerate/2; i++)
			{
				if(i % 100 == 0)
				{
					bufferedWriter.newLine();
				}
				bufferedWriter.write(list.get(i-1) + " ");
			}
			
			bufferedWriter.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
