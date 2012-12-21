package com.model;

import java.util.List;

/*
  Keeps process data objects in an Array
 */
public class Statistics {
    
    private List<ProcessStat> procObjArray;

    public Statistics (List<ProcessStat> procObjArray) {

	this.procObjArray = procObjArray;
    }

    public List<ProcessStat> getProcessStats()
    {
	return this.procObjArray;
    }
}

