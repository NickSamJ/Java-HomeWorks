package telran.util;

public class MultiplyMatrix {
	
	public static void main(String[] args) {
		int[][] m1 = {
				{1, 0, 0 }, 
				{-1,0,3}
		};
		int[][] m2 = {
				{7,0,0}, 
				{0,0,0,},
				{0,0,1,}
		};
//		printMatrix(matrix1);
		multMatrixes(m1, m2);
		
	}
	public static void printMatrix(int[][] matrix) {
		for(int i = 0; i<matrix.length; i++) {
			for(int j =0; j<matrix[i].length; j++) {
				System.out.print(matrix[i][j] +"\t");
			}
			System.out.println();
		}
	}
	public static void multMatrixes(int[][] m1, int[][] m2) {
		int[][] res = new int[m1.length][m2[0].length] ;
		for (int i = 0; i < m1.length; i++) {
			for(int j =0; j<m1[i].length; j++) {
				for (int k = 0; k < m2.length; k++) {					
					res[i][j]+=m1[i][k]*m2[k][j];
				}
			}
		}
		printMatrix(res);
	}
	public static void shuffle(int[] ar1, int[] ar2) {
		int[] res = new int[ar1.length+ar2.length];
		int firstSymb = 0;
	}
	
}
