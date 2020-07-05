package dazuoye;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Demo {
	
	public static void main(String [] args) throws FileNotFoundException {

		int index = 417;
		File file = new File("C:\\Users\\qzz\\Desktop\\Matrix1.txt");
		Scanner in = new Scanner(file);
		int[][] matrix = new int[index][index];
		
		for(int i = 0; i < index; i++) {
			for(int j = 0; j < index; j++) {
				matrix[i][j] = in.nextInt();
			}
		}
		System.out.println(matrix);
		in.close();
	}
	
}
