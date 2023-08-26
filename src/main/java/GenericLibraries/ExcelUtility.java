package GenericLibraries;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;






public class ExcelUtility
{
	private Workbook workbook;
	private DataFormatter df;
	
	/**
	 * This method is used to initialize excel
	 * @param excelpath
	 */
 public void excelInitialization(String excelpath) {
	FileInputStream fis=null;
	try{
		fis=new FileInputStream(excelpath);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	try {
		workbook=WorkbookFactory.create(fis);
	}catch (EncryptedDocumentException | IOException e) {
	     e.printStackTrace();  
	}
	}


 /**
  * This method is used to read single data from excel
  * @param sheetName
  * @param rowNum
  * @param cellNum
  * @return
  */
public String readFromExcel(String sheetName, int rowNum,  int cellNum ) 
{
	df=new DataFormatter();
	return df.formatCellValue(workbook.getSheet(sheetName).getRow(rowNum).getCell(cellNum));
}
/**
 * This method is used to read data of specified test script at a time
 * @param sheetName
 * @param expectedTestName
 * @return
 */
public Map<String,String> readFromExcel(String sheetName,String expectedTestName)
{
Map<String,String>map=new HashMap<String,String>();
df=new DataFormatter();
      Sheet sheet=workbook.getSheet(sheetName);


for(int i=0;i<=sheet.getLastRowNum();i++) {
	if(df.formatCellValue(sheet.getRow(i).getCell(1)).equals(expectedTestName)) {
		for(int j=1;j<=sheet.getLastRowNum();j++) {
			map.put(df.formatCellValue(sheet.getRow(j).getCell(2)),
					df.formatCellValue(sheet.getRow(j).getCell(3)));
			if(df.formatCellValue(sheet.getRow(j).getCell(2)).equals("####"))
				break;
		}
		break;
	}
}
return map;
}

/*
 * This method is used to write data to excel
 * @param sheetName
 * @param expectedTestName
 * @param status
 * @param excelpath
 */
public void writeToExcel(String sheetName, String expectedTestName, String status, String excelpath) {
	Sheet sheet=workbook.getSheet(sheetName);
	
	
	for(int i=0;i<=sheet.getLastRowNum();i++) {
		if(df.formatCellValue(sheet.getRow(i).getCell(1)).equals(expectedTestName)) {
		  Cell cell=sheet.getRow(i).createCell(4);
		   cell.setCellValue(status);
		   break;
		}
	}
	FileOutputStream fos=null;
	try {
		fos=new FileOutputStream(excelpath);
	}catch(FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		workbook.write(fos);
	}catch(IOException e) {
		e.printStackTrace();
	}
}
/*This method is used to write data from excel
 * @param sheetName
 * @param rowNum
 * @param cellNum
 * @param value
 * @param excelpath
 */
public void writeToExcel(String sheetName,int rowNum,int cellNum, String value,String excelpath) {
	Sheet sheet=workbook.getSheet(sheetName);
	sheet.getRow(rowNum).createCell(cellNum).setCellValue(value);
	FileOutputStream fos=null;
	try {
		fos=new FileOutputStream(excelpath);
	}catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		workbook.write(fos);
		}catch(IOException e) {
			e.printStackTrace();
		}
}

/*
 * This method is used to close excel
 */
public void closeExcel() {
	try {
		workbook.close();
		}catch(IOException e) {
			e.printStackTrace();
		}

}
}
