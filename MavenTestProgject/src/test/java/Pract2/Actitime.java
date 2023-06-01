package Pract2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utilities.SeleniumUtility;

public class Actitime extends SeleniumUtility {
	
  @Test
  public void LoginTask() {
	setUp("chrome", "https://demo.actitime.com/");
	  driver.findElement(By.name("username")).sendKeys("admin");
	  driver.findElement(By.name("pwd")).sendKeys("manager",Keys.ENTER);
	  
 	  clickOnElement(driver.findElement(By.xpath("//a[@class='content tasks']")));
	  clickOnElement(driver.findElement(By.className("addNewButton")));
	  clickOnElement(driver.findElement(By.xpath("//div[@class='item createNewTasks']")));
	  clickOnElement(driver.findElement(By.xpath("//div[@class='selectCustomerAndProject']/table/tbody/tr[1]/td/div/div")));
	  clickOnElement(driver.findElement(By.xpath("//div[@class='customerSelector customerOrProjectSelector selectorWithPlaceholderContainer']/div/div[2]/div/div[1]/div/div[contains(text(),'Big Bang')]")));
	  clickOnElement(driver.findElement(By.xpath("//div[@class='projectSelector customerOrProjectSelector selectorWithPlaceholderContainer']/div/div")));
	 
	   List<WebElement> projlist = driver.findElements(By.xpath("//div[@class='projectSelector customerOrProjectSelector selectorWithPlaceholderContainer']/div/div[2]/div/div[1]/div/div"));
	  
	   int ProjID;
	   ProjID = (int) ((Math.random() * (1000 - 100)) + 100);
		
	   if(projlist.size()>2)
	   {
		   projlist.get(projlist.size()-1).click();
		   System.out.println("Project Name :- "+projlist.get(projlist.size()-1).toString() );
	   }
	   else
	   {
		   projlist.get(0).click();
		   driver.findElement(By.xpath("//input[@class='newProject newCustomerProjectField inputFieldWithPlaceholder']"))
		   .sendKeys("Testing Project"+ProjID);
	   System.out.println("Project Name :-  Testing Project" +ProjID);
	   }
						   
			   
		driver.findElement(By.xpath("//div[@class='taskTableContainer']/table/tbody/tr[1]/td[1]/input")).sendKeys("Testing task"+ProjID);
//		driver.findElement(By.xpath("//div[@class='taskTableContainer']/table/tbody/tr[1]/td[5]")).click();
//		driver.findElement(By.xpath("//div[@class='taskTableContainer']/table/tbody/tr[1]/td[5]/div[1]/div/div/div/div/div[12]/div")).click();
		driver.findElement(By.xpath("//div[@class='taskTableContainer']/table/tbody/tr[1]/td[6]/label/span[2]")).click();
		driver.findElement(By.className("commitButtonPlaceHolder")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toastsContainer']")));
		System.out.println("Create Popup =  "+driver.findElement(By.xpath("//div[@class='toastsContainer']")).isDisplayed());

  }
}
