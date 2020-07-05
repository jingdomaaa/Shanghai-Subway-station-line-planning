package Shanghai-Subway-station-line-planning;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ShortestPathFloyd {
    /** �ڽӾ��� */
    private int[][] matrix;
    /** ��ʾ������ */
    private int MAX_WEIGHT = 9999;
    /**·������*/
    private int[][] pathMatirx;
    /**ǰ����*/
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
     * ����ͼ
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
    	
        //·������D������ʾ���㵽��������·��Ȩֵ֮�͵ľ��󣬳�ʼʱ������ͼ���ڽӾ���
        pathMatirx = new int[matrix.length][matrix.length];
        //ǰ����P����P[m][n] ��ֵΪ m��n�����·����ǰ�����㣬�����ֱ����ֵΪn��Ҳ���ǳ�ʼֵ
        preTable = new int[matrix.length][matrix.length];
        
        //��ʼ��D,P
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                pathMatirx[i][j] = matrix[i][j];
                preTable[i][j] = j;
            }
        }
        
        //ѭ�� �м侭������
        for (int k = 0; k < matrix.length; k++) {
            //ѭ������·��
            for (int m = 0; m < matrix.length; m++) {
                for (int n = 0; n < matrix.length; n++) {
                    int mn = pathMatirx[m][n];
                    int mk = pathMatirx[m][k];
                    int kn = pathMatirx[k][n];
                    int addedPath = (mk == MAX_WEIGHT || kn == MAX_WEIGHT)? MAX_WEIGHT : mk + kn;
                    if (mn > addedPath) {
                        //�������k����·����ԭ����·�����̣��������Ȩֵ��Ϊ��С��һ��
                        pathMatirx[m][n] = addedPath;
                        //ǰ������Ϊ�����±�Ϊk�Ķ���
                        preTable[m][n] = preTable[m][k];
                    }
                }
            }
        }
        
    }
    
    /**
     * ��ӡ �������·��
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
     * ��ӡ Ŀ�����·��
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