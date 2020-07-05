package dazuoye;
  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.PrintWriter;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;

public class GetExcelInfo {  
	
    public static void main(String[] args) {  
    	
        GetExcelInfo obj = new GetExcelInfo();  
        File file = new File("C:\\Users\\qzz\\Desktop\\Matrix(1).xlsx");  
        obj.readExcelWrite2TXT(file);
        
    }  
    
    // ȥ��Excel�ķ���readExcel���÷�������ڲ���Ϊһ��File����  
    public void readExcelWrite2TXT(File file) {  
    	
        // �����ļ������  
        FileWriter fw = null;  
        PrintWriter out = null;  
        
        try {  
            // ָ������txt���ļ�·��  
            String fileName = file.getName().replace(".xlsx", "");  
            fw = new FileWriter(file.getParent() + "/" + fileName + ".txt");  
            out = new PrintWriter(fw);  
            // ��������������ȡExcel  
            InputStream is = new FileInputStream(file.getAbsolutePath());  
            // jxl�ṩ��Workbook��  
            Workbook wb = Workbook.getWorkbook(is);  
            // Excel��ҳǩ����  
            int sheet_size = wb.getNumberOfSheets();  
            
            for (int index = 0; index < sheet_size; index++) {  
                // ÿ��ҳǩ����һ��Sheet����  
                Sheet sheet = wb.getSheet(index);  
                // sheet.getRows()���ظ�ҳ��������  
                
                for (int i = 0; i < sheet.getRows(); i++) {  
                    // sheet.getColumns()���ظ�ҳ��������  
                    for (int j = 0; j < sheet.getColumns(); j++) {  
                        String cellinfo = sheet.getCell(j, i).getContents();  
                        // ����Excel�ж�ȡ������д�뵽txt��  
                        out.println(cellinfo);  
                        
                    }  
                }  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (BiffException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
        	
            try {  
                // �ǵùر���  
                out.close();  
                fw.close();  
                // ���ڴ˴��õ��˻�������������������󣬲�����flush������ĳЩ���ݽ�����  
                // �������ڴ��ж�����д���ļ���������һ��Ҫע��  
                out.flush();  
            } catch (IOException e) {  
                e.printStackTrace();  
                
            }  
        }  
    }  
}  