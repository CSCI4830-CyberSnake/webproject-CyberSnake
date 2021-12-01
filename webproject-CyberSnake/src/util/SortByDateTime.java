package util;

import java.util.Comparator;
import util.UtilTimeslot;
import datamodel.Timeslot;

public class SortByDateTime implements Comparator<Timeslot> {

	public int compare( Timeslot a, Timeslot b ) {
		
		int dateCompare = a.getDate().compareTo(b.getDate());
		
		if( dateCompare < 0 ) {
			return -1;
		}
		else if( dateCompare > 0) {
			return 1;
		}
		else {
			int timeA = UtilTimeslot.getIntTime(a.getStartTime());
			int timeB = UtilTimeslot.getIntTime(b.getStartTime());
			return timeA - timeB;
		}
	}
}
