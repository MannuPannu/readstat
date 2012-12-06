package src;

import src.statistics.Array;
import src.statistics.Statistics;
import src.statistics.ReadStat;

class Main 
{
  public static void main(String[] args) 
  {
    ReadStat read = new ReadStat();
    Array statArray = read.getStatistics();

    int arraySize = statArray.size;

    Statistics [] s = statArray.statObjArray;

    for(int i = 0; i < arraySize; i++)
	{
	    String fileName = s[i].fileName;
	    long[] psCounters = new long[s[i].psCountersSize];
	    psCounters = s[i].psCounters;

	    int[] psSizes = new int[s[i].psSizesSize];
	    psSizes = s[i].psSizes;

	    int[] psTimers = new int[s[i].psTimersSize];
	    psTimers = s[i].psTimers;

	    System.out.println("Statistics data for file: " + fileName);
	    for(int j = 0; j < s[i].psCountersSize; j++)
		{
		    System.out.println(psCounters[j]);
		}

	    for(int j = 0; j < s[i].psSizesSize; j++)
		{
		    System.out.println(psSizes[j]);
		}

	    for(int j = 0; j < s[i].psTimersSize; j++)
		{
		    System.out.println(psTimers[j]);
		}
	}
  }
}