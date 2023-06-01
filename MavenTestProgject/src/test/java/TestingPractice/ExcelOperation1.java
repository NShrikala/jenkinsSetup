package TestingPractice;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelOperation1 {
 
	@Test(enabled = false)
	public void testCase() {
		// step1: file location using FileInputStream class
		// step2: create an instance of required workbook class and upcast it to Workbook interface
		// step3: you can get Sheet related information from workbook instance, also you can get specific sheet as well
		// step4: once you get specific sheet, from the sheet you can all row related information and also get specific row
		// step5: once you get specific row, from row you can get value of all the cell and also get specific cell
	}

	@Test(enabled = false)
	public void readSheetDetails() throws IOException {
		// Create instance of FileInputStream class and pass the required excel file location to its constructor
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\AppTest.xlsx");
		// create an instance of required workbook class and pass FileInputStream instance to its constructor
		Workbook workbook = new XSSFWorkbook(fis);
		// get the number of sheets present in the excel
		int sheetCount = workbook.getNumberOfSheets();
		System.out.println("Sheet count: " + sheetCount);
		// print all the sheets name from the excel
		for (int i = 0; i < sheetCount; i++) {
			String sheetName = workbook.getSheetName(i);
			System.out.println("Sheet name is: " + sheetName);
		}
		// get the required sheet from excel
		Sheet sheet = workbook.getSheet("Sheet1");
	}
	
	@Test(enabled = false)
	public void getDiffTypeOfCellData() throws IOException {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\AppTest.xlsx");
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet("Sheet1");
		
		Row row = sheet.getRow(2);
		int cellCount = row.getLastCellNum();
		System.out.println("Cell count on row3 : " + cellCount);
		for (int i = 0; i < cellCount; i++) {
			Cell cell = row.getCell(i);
			switch (cell.getCellType()) {
			case (Cell.CELL_TYPE_STRING):
				System.out.println("Cell Data: " + cell.getStringCellValue());
				break;
			case (Cell.CELL_TYPE_NUMERIC):
				System.out.println("Cell Data: " + cell.getNumericCellValue());
				break;
			case (Cell.CELL_TYPE_BOOLEAN):
				System.out.println("Cell Data: " + cell.getBooleanCellValue());
				break;
			case (Cell.CELL_TYPE_BLANK):
				break;
			default:
				System.out.println("Invalid cell details");
				break;
			}
		}
	}

	@Test
	public void readAllDataFromSheet() throws IOException {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\AppTest.xlsx");
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet("Sheet1");
		
		Row row;
		for(int i = 1; i<=sheet.getLastRowNum();i++)
		{
			row = sheet.getRow(i);
			System.out.println("Row -: "+ i);
			for(int j = 0; j< row.getLastCellNum(); j++)
			{
				Cell cell = row.getCell(j);
				switch (cell.getCellType()) {
				case (Cell.CELL_TYPE_STRING):
					System.out.println("Cell Data: " + cell.getStringCellValue());
					break;
				case (Cell.CELL_TYPE_NUMERIC):
					System.out.println("Cell Data: " + cell.getNumericCellValue());
					break;
				case (Cell.CELL_TYPE_BOOLEAN):
					System.out.println("Cell Data: " + cell.getBooleanCellValue());
					break;
				case (Cell.CELL_TYPE_BLANK):
					break;
				default:
					System.out.println("Invalid cell details");
					break;
				}
			}
			
		}
	}
}
