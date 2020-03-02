package telran.util;

import java.util.Comparator;

public class ShufflingComparator implements Comparator<Integer>{

	@Override
	public int compare(Integer o1,Integer o2) {
		
		return (Math.random()<0.5) ? -1 : 1;
	}

}
