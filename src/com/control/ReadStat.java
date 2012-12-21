package com.control;

import com.model.Statistics;

/* 
 * This class uses a native library implemented in C++ to get the statistics data 
 */

public class ReadStat {

    //    public native void getStatistics();

    public native void writedatatofile();

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
