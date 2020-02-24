package telran.tests;

import java.util.Comparator;

public class SortOddEvenComparator implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		int oi1 = (Integer)o1;
		int oi2 = (Integer)o2;
		
		if(oi1%2!=0 || oi2%2!=0) {
			if (oi1<oi2) {				
				return 1;
			}else {
				return -1;
			}
		}else {
			if (oi1<oi2) {
				return -1;
			}else {
				return 1;
			}
		}
	}
}
