package dazuoye;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {
	public static int[][] save(String fileName)
    {
        int[][] les = null;
        try
        {
            Scanner scanner = new Scanner(new File(fileName));
            int i = 0;
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine().trim();
                if(i == 0)
                {
                    int num = Integer.parseInt(line);
                    les = new int[num][num];
                }
                else
                {
                    for(int j = 0; j < line.length(); j++)
                    {
                        les[i - 1][j] = Integer.parseInt(line.charAt(j) + "");
                    }
                }
                i++;
            }
            scanner.close();
            return les;
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
 
    public static void main(String[] args)
    {
        int[][] LES = save("C:\\Users\\qzz\\Desktop\\Matrix1.txt");
        System.out.println(Arrays.deepToString(LES));
    }
}
