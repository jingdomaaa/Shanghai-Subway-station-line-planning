package Shanghai-Subway-station-line-planning;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ShortestPathFloyd {
    /** 邻接矩阵 */
    private int[][] matrix;
    /** 表示正无穷 */
    private int MAX_WEIGHT = 9999;
    /**路径矩阵*/
    private int[][] pathMatirx;
    /**前驱表*/
    private int[][] preTable;

    Readfile rf = new Readfile();
    
    public static void main(String[] args) throws IOException {
        ShortestPathFloyd floyd = new ShortestPathFloyd();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        
        floyd.floyd(417);
        
        floyd.print(n,m);
    }

    /**
     * 创建图
     * @throws FileNotFoundException 
     * @throws IOException 
     */
    private void floyd(int index) throws FileNotFoundException {
    	
		File file = new File("C:\\Users\\qzz\\Desktop\\Matrix1.txt");
		Scanner in = new Scanner(file);
		matrix = new int[index][index];
		for(int i = 0; i < index; i++) {
			for(int j = 0; j < index; j++) {
				matrix[i][j] = in.nextInt();
			}
		}
		in.close();
    	
        //路径矩阵（D），表示顶点到顶点的最短路径权值之和的矩阵，初始时，就是图的邻接矩阵。
        pathMatirx = new int[matrix.length][matrix.length];
        //前驱表（P），P[m][n] 的值为 m到n的最短路径的前驱顶点，如果是直连，值为n。也就是初始值
        preTable = new int[matrix.length][matrix.length];
        
        //初始化D,P
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                pathMatirx[i][j] = matrix[i][j];
                preTable[i][j] = j;
            }
        }
        
        //循环 中间经过顶点
        for (int k = 0; k < matrix.length; k++) {
            //循环所有路径
            for (int m = 0; m < matrix.length; m++) {
                for (int n = 0; n < matrix.length; n++) {
                    int mn = pathMatirx[m][n];
                    int mk = pathMatirx[m][k];
                    int kn = pathMatirx[k][n];
                    int addedPath = (mk == MAX_WEIGHT || kn == MAX_WEIGHT)? MAX_WEIGHT : mk + kn;
                    if (mn > addedPath) {
                        //如果经过k顶点路径比原两点路径更短，将两点间权值设为更小的一个
                        pathMatirx[m][n] = addedPath;
                        //前驱设置为经过下标为k的顶点
                        preTable[m][n] = preTable[m][k];
                    }
                }
            }
        }
        
    }
    
    /**
     * 打印 所有最短路径
     */
    public void print() {
    	
        for (int m = 0; m < matrix.length; m++) {
            for (int n = m + 1; n < matrix.length; n++) {
                int k = preTable[m][n];
                System.out.print("(" + m + "," + n + ")" + pathMatirx[m][n] + ":  ");
                System.out.print(m);
                
                while (k != n) {
                    System.out.print("->" + k);
                    k = preTable[k][n];
                }
                
                System.out.println("->" + n);
            }
            System.out.println();
        }
        
    }
    
    /**
     * 打印 目标最短路径
     */
    public void print(int m,int n) {
    	
    	int k = preTable[m][n];
        System.out.print("(" + m + "," + n + ")" + pathMatirx[m][n] + ":  ");
        System.out.print(m);
        
        while (k != n) {
            System.out.print("->" + k);
            k = preTable[k][n];
        }
        
        System.out.println("->" + n);
        
    }
    
}