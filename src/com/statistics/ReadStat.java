package com.statistics;

/* This class is a wrapper for the C++ module using libReadStat.so to 
 * 
 */

public class ReadStat {

    public native Array getStatistics();

    static 
    {
	    try {
		System.loadLibrary("ReadStat");  //libReadStat.so
	    } catch (UnsatisfiedLinkError e) {
		System.err.println("Native code library failed to load.\n" + e);
		System.exit(1);
	    }
    }
}
