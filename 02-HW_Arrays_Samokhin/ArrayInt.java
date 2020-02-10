package telran.util;

public class ArrayInt {
	/**
	 * Search an index of given number in array
	 * @param ar - array of numbers
	 * @param number - number for search
	 * @return
	 */
	public static int search(int[] ar, int number) {		
		for(int i=0; i<ar.length; i++) {
			// Alternative way
			
//			if(ar[i]==number) {
//				index = i;
//				continue;
//			}
			
			if (ar[i]==number) {return i;};
		}
		return -1;
		
	}
	/**
	 * Sorting of numbers
	 * @param arr - array of numbers
	 */
	public static void sort(int[] arr) {
//		boolean anychanges = false;
//        
//        for(int i=0; i<arr.length-1; i++){
//            int temp = 0;
//            if(arr[i]>arr[i+1]){
//                anychanges=true;
//                
//                temp=(int)arr[i];
//                arr[i]=arr[i+1];
//                arr[i+1]=temp;
//            }
//        }
//        if(anychanges){
//            sort(arr);
//        }
		
		boolean sorted = false ;
		int length = arr.length;
		do {
			sorted = true;
			length--;
			for(int i=0; i<length; i++){
				if(arr[i]>=arr[i+1]) {
					int tmp = arr[i];
					arr[i]=arr[i+1];
					arr[i+1]=tmp;
					sorted=false;
				}
			}
		}while(!sorted);
	}
	
	/**
	 * Search an index of given number in a sorted array
	 * @param ar - array of numbers
	 * @param number - number for search
	 * @return
	 */
	public static int binarySearch(int[] arr, int number) {
		
		int left = 0;
		int right =  arr.length-1;
		int middle  = (left+right)/2;
		while(left<=right && arr[middle]!=number) {
			if(number<arr[middle]) {
				right = middle-1;
			}else {
				left = middle+1;
			}
			middle = (right+left)/2;
		}
		
		int index = left;
		
		int result  = -index-1;

		
		if(arr[middle]==number) {
			result = middle;
		}
		return result;
	}
}
