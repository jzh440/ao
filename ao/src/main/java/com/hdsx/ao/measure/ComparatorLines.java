package com.hdsx.ao.measure;

import java.util.Comparator;

public class ComparatorLines implements Comparator<HDLine>{

	public int compare(HDLine o1, HDLine o2) {
		if(o1.getEndPos()>o2.getEndPos())
			return 1;
		else
			return -1;
	}

}
