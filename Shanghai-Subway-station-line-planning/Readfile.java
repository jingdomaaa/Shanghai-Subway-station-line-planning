package Shanghai-Subway-station-line-planning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
  
public class Readfile {
	
	public int[][] txtString(FileReader file) throws IOException{
		
        BufferedReader br = new BufferedReader(file);//读取文件
        
            String line = br.readLine();//读取一行数据
            int lines = Integer.parseInt(line);//将数据转化为int类型
            System.out.println(lines);

            String [] sp = null;
            String [][] c = new String[lines][lines];
            int [][] cc = new int[lines][lines];
            int count=0;
            
            while((line=br.readLine())!=null) {//按行读取
                sp = line.split(" ");//按空格进行分割
                for(int i=0;i<sp.length;i++){
                    c[count][i] = sp[i];
                }
                count++;
            }
            
            for(int i=0;i<lines;i++){
                for(int j=0;j<lines;j++){
                    cc[i][j] = Integer.parseInt(c[i][j]);
                }
            }
            return cc;
        
    }
  
    /**
     * 读取文件
     * @param filePath
     * @return
     */
    public  List  readTxtFile(String filePath) {
        List<String> list = new ArrayList<String>();
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (!lineTxt.startsWith("#"))
                        list.add(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到文件");
            }
        } catch (Exception e) {
            System.out.println("出错了");
            e.printStackTrace();
        }
        return list;
  
    }
      
    /**
     * 创建二维数组
     * @param list
     * @return
     */
    public String[][] createArray(String filePath){
        List<String> list = readTxtFile(filePath);
        String array[][] = new String[list.size()][list.size()];
        for(int i=0;i<list.size();i++){
            array[i] = new String[list.size()];
            String linetxt=list.get(i);
            String[] myArray = linetxt.replaceAll("\\s+", "@").split("@");
            for(int j=0;j<myArray.length;j++){
                if(j<list.size()){
                    array[i][j]=myArray[j];
                }
            }
        }
        return array;
    }
      
    /**
     * 打印数组
     * @param array
     */
    public void printArray(String array[][]){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                if(j!=array[i].length-1){
                    System.out.print("array["+i+"]["+j+"]="+array[i][j]+",");
                }
                else{
                    System.out.print("array["+i+"]["+j+"]="+array[i][j]);
                }
                  
            }
            System.out.println();
        }
    }
  
  
//    public static void main(String args[]) {
//        String array[][] = createArray("C:\\Users\\qzz\\Desktop\\Matrix1.txt");
//        printArray(array);
//    }
      
      
}