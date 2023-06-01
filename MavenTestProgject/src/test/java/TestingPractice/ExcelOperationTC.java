package TestingPractice;

import utilities.*;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class ExcelOperationTC extends SeleniumUtility {
	String[] appUrl = new String[6];
	String[] userName = new String[6];	
	String[] password = new String[6];
	String[] ExpectedTitle = new String[6];
	
	String filepath1 = ".\\src\\test\\resources\\AppTest.xlsx";

	@BeforeTest
	public void getData() {
	
		for (int i = 1; i <= 6; i++) {
			appUrl[i - 1] = ExcelUtility.getCellValue(filepath1, "TC", i, 0);
			System.out.println(appUrl[i - 1]);
		}
		for (int i = 1; i <= 6; i++) {
			userName[i - 1] = ExcelUtility.getCellValue(filepath1, "TC", i, 1);
			System.out.println(userName[i - 1]);
		}
		for (int i = 1; i <= 6; i++) {
			password[i - 1] = ExcelUtility.getCellValue(filepath1, "TC", i, 2);
			System.out.println(password[i - 1]);
		}
		for (int i = 1; i <= 6; i++) {
			ExpectedTitle[i - 1] = ExcelUtility.getCellValue(filepath1, "TC", i, 3);
			System.out.println(password[i - 1]);
		}
		
	}

	public void PerformOperation(String appUrl, String userName, String password,String ExpectedTitle,int row) {
		WebDriver driver = setUp("chrome", appUrl);
		String currenttitle;
		if (appUrl.contains("actitime")) {
			
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.name("pwd")).clear();
			
			driver.findElement(By.id("username")).sendKeys(userName);
			driver.findElement(By.name("pwd")).sendKeys(password);
			driver.findElement(By.id("loginButton")).click();
			
			
			} else if (appUrl.contains("vtiger")) {
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.name("password")).clear();			
			driver.findElement(By.id("username")).sendKeys(userName);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.xpath("//button[@class='button buttonBlue']")).click();
			
			} else {
			driver.findElement(By.name("username")).clear();
			driver.findElement(By.name("password")).clear();				
			driver.findElement(By.name("username")).sendKeys(userName);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.className("orangehrm-login-button")).click();
			
		}
		
	//	System.out.println("URL  :- "+ row + "   --  " + driver.getCurrentUrl());
		
		if(getCurrentTitleOfApplication().equals(ExpectedTitle)) {
			ExcelUtility.updateExcelContent(filepath1, "TC", row,5,"Passed");
		}else {
			ExcelUtility.updateExcelContent(filepath1, "TC", row, 5,"failed");
		}
	//	Assert.assertEquals(getCurrentTitleOfApplication(), ExpectedTitle);

	}

	@Test
	public void actitimeLogin() {
		for(int i = 0; i< appUrl.length;i++)
		{
		PerformOperation(appUrl[i],userName[i], password[i],ExpectedTitle[i],i+1);
		}
		
			}

	@AfterTest
	public void cleanUp1() {
		cleanUp();
	}

}
