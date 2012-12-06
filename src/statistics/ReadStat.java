package src.statistics;

public class ReadStat {

    public native Array getStatistics();

    static 
    {
	    try {
		System.loadLibrary("ReadStat");
	    } catch (UnsatisfiedLinkError e) {
		System.err.println("Native code library failed to load.\n" + e);
		System.exit(1);
	    }
    }


    /*
    public Array getStatistics()
    {
	System.out.println("Get stat");

	long[] psCounters = new long[1];
	psCounters[0] = 12;

	int[] psSizes = new int[1];
	psSizes[0] = 7;

	int[] psTimers = new int[1];
	psTimers[0] = 3;

	Statistics s = new Statistics("Hej!", psCounters, 1, psSizes, 1, psTimers, 1);

	Statistics[] sa = new Statistics[1];
	sa[0] = s;

	Array a = new Array(sa, 1);

	return a;
	}*/
}
