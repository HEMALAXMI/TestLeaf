package org.selfPractice.PracticeFramework.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataLibrary {	
	
	public static Object[][] getData(String sheetName,String testcaseName)  throws IOException {
		
		String filepath = "./data/TestData.xlsx";
		File file = new File(filepath);
		FileInputStream fis = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		short colCount = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[1][1];
		
		for(int i = 1; i <= rowCount ; i++) {
			
			Map<String, String> map = new HashMap<>();
			String TCName = sheet.getRow(i).getCell(0).toString();
			
			if(TCName.equals(testcaseName)) {
				
				System.out.println("the test case found in data sheet" + testcaseName + "   " +TCName);
				int currentRow = i;
				XSSFRow row = sheet.getRow(currentRow);
				XSSFRow firstRow = sheet.getRow(0);
				
				for(int j = 0; j < colCount; j++) {
					XSSFCell cell = row.getCell(j);
					XSSFCell headerCell = firstRow.getCell(j);
					
					String header = headerCell.getStringCellValue();
					String cellValue = cell.getStringCellValue();
					
					map.put(header, cellValue);
				}
				data[0][0] = map;
			}	
		}
		return data;
		
	}
	
}

//2D array working code
/*Object[][] data = new Object[1][colCount];

for(int i = 1; i <= rowCount ; i++) {	
	String TCName = sheet.getRow(i).getCell(0).toString();
	
	if(TCName.equals(testcaseName)) {
		
		System.out.println("the test case found in data sheet" + testcaseName + "   " +TCName);
		int currentRow = i;
		XSSFRow row = sheet.getRow(currentRow);
		
		
		for(int j = 0; j < colCount; j++) {
			XSSFCell cell = row.getCell(j);
			String cellValue = cell.getStringCellValue();
			data[0][j] = cellValue;
			System.out.println(cellValue);
		}
	}	
	
}*/