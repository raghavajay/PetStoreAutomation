package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
public FileInputStream fis;
public FileOutputStream fos;
public XSSFWorkbook workbook;
public XSSFSheet sheet;
public XSSFRow row;
public XSSFCell cell;
public XSSFCellStyle style;
String path;

public XLUtility(String path) {
	this.path=path;
}
public int getRowCount(String sheetName) throws IOException {
	fis=new FileInputStream(path);
	workbook=new XSSFWorkbook(fis);
	sheet=workbook.getSheet(sheetName);
	int rowCount=sheet.getLastRowNum();
	workbook.close();
	fis.close();
	return rowCount;
}
public int getCellCount(String sheetName,int rownNum) throws IOException {
	fis=new FileInputStream(path);
	workbook=new XSSFWorkbook(fis);
	sheet=workbook.getSheet(sheetName);
	row=sheet.getRow(rownNum);
	int cellCount=row.getLastCellNum();
	workbook.close();
	fis.close();
	return cellCount;
}
public String getCellData(String sheetName,int rownNum,int column) throws IOException {
	fis=new FileInputStream(path);
	workbook=new XSSFWorkbook(fis);
	sheet=workbook.getSheet(sheetName);
	row=sheet.getRow(rownNum);
    cell=row.getCell(column);
    DataFormatter formatter=new DataFormatter();
    String data;
    data=formatter.formatCellValue(cell);
	workbook.close();
	fis.close();
	return data;
}
//public String setCellData(String sheetName,int rownNum,int column) throws IOException {
//	File xlFile=new File(path);
//	if(!xlFile.exists()) {
//		workbook=new XSSFWorkbook();	
//		fos=new FileOutputStream(path);
//		workbook.write(fos);
//	}
//	fis=new FileInputStream(path);
//	workbook=new XSSFWorkbook(fis);
//	
//	if(workbook.getSheetIndex(sheet)==-1) {  //if sheet does not exists
//			workbook.createSheet(sheetName);
//			sheet=workbook.getSheet(sheetName);
//	}
//	
//	if(sheet.getRow(rownNum)==null) {    //if row does not exists
//		sheet.createRow(rownNum);
//		row=sheet.getRow(rownNum);
//	}
//	
//	
//}
}
