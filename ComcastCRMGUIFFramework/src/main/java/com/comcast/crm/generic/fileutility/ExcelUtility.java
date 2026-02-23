package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	public String getDataFromExcel(String sheetName,int rowNum,int celNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis1=new FileInputStream("./testdata/TestScriptData.xlsx");
		
		Workbook wb=WorkbookFactory.create(fis1);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		wb.close();
		return data;
	}
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis2=new FileInputStream("./testdata/TestScriptData.xlsx");
		
		Workbook wb=WorkbookFactory.create(fis2);
		int rowCount=wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowCount;
	}
	
	public void setDataIntoExcel(String sheetName,int rowNum,int celNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis3=new FileInputStream("./testdata/TestScriptData.xlsx");
		
		Workbook wb=WorkbookFactory.create(fis3);
		wb.getSheet(sheetName).getRow(rowNum).createCell(celNum);
		
		FileOutputStream fos=new FileOutputStream("./testdata/TestScriptData.xlsx");
		wb.write(fos);
		wb.close();
	}
}
