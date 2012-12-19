package com.statistics;

public class Statistics {
    
    public String fileName; //File name of the process
    public long[] psCounters;
    public int psCountersSize;
    public int[] psSizes;
    public int psSizesSize;
    public int[] psTimers;
    public int psTimersSize;

    public Statistics (String fileName, long[] psCounters, int psCountersSize, int[] psSizes, 
		       int psSizesSize, int[] psTimers, int psTimersSize){

	this.fileName = fileName;

	this.psCounters = new long[psCountersSize];
        System.arraycopy(psCounters, 0, this.psCounters, 0, psCountersSize);
	this.psCountersSize = psCountersSize;

	this.psSizes = new int[psSizesSize];
        System.arraycopy(psSizes, 0, this.psSizes, 0, psSizesSize);
	this.psSizesSize = psSizesSize;

	this.psTimers = new int[psTimersSize];
        System.arraycopy(psTimers, 0, this.psTimers, 0, psTimersSize);
	this.psTimersSize = psTimersSize;
    }
}

