package obs.selenium;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtility {
        static FileInputStream f;
        static XSSFWorkbook w;
        static XSSFSheet s;
        public static String readLoginEmail(int i, int j) throws IOException
        {
            f=new FileInputStream("C:\\Users\\Lenovo\\IdeaProjects\\seleniumbasics\\src\\main\\resources\\TestData.xlsx");
            w=new XSSFWorkbook(f);
            s=w.getSheet("Login");
            Row row=s.getRow(i);
            Cell cell=row.getCell(j);
            return cell.getStringCellValue();
        }
    public static String readLoginPassword(int i, int j) throws IOException
    {
        f=new FileInputStream("C:\\Users\\Lenovo\\IdeaProjects\\seleniumbasics\\src\\main\\resources\\TestData.xlsx");
        w=new XSSFWorkbook(f);
        s=w.getSheet("Login");
        Row row=s.getRow(i);
        Cell cell=row.getCell(j);
        return cell.getStringCellValue();
    }
   /* public static void main(String[] args) throws IOException {
        String UserName=ExcelUtility.readLoginEmail(1,0);
        System.out.println(UserName);
        String password=ExcelUtility.readLoginPassword(1,1);
        System.out.println(password);
    }*/



}
