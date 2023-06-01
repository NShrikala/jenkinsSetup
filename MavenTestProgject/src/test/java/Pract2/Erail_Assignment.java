package Pract2;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Erail_Assignment {
	 WebDriver driver;
	 String homeWinId;
	 WebDriverWait wait;	
  public void OpenBrowser() {
	  WebDriverManager.chromedriver().setup();
		driver =  new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://erail.in/");
		wait=  new WebDriverWait(driver, 20); 
		
		homeWinId=driver.getWindowHandle();//EC8129D18C193DDA926BAF64A0600F3C
		System.out.println("Home window id: "+homeWinId);
		
		}
  @Test
  public void OpenEcatering()
  {
	  OpenBrowser();
	  driver.findElement(By.xpath("//a[text()='eCatering']")).click();
	  Set<String> allWinIds=driver.getWindowHandles();
		//get child window id
		allWinIds.remove(homeWinId);
		System.out.println("After removing homeWinId from allWinIds, now allWinIds are "+allWinIds);//[210D71947F962864FADADCF89553CDF9]
		
		String childWinId=allWinIds.iterator().next();
		
		//once you get child window id, you can move your control from Home window to child window
		driver.switchTo().window(childWinId);
		System.out.println("Current URL "+ driver.getCurrentUrl());
		
		driver.findElement(By.xpath("//div[@class='mb-6 flex-shrink-0']")).click();
		driver.findElement(By.xpath("//input[@class='form-input pl-12  text-sm']")).sendKeys("12627");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'12627')]")));
		driver.findElement(By.xpath("//button[contains(text(),'12627')]")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Boarding Date']")).click();

		driver.findElement(By.xpath("//input[@placeholder='Boarding Date']")).sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER);
		
		WebElement boardingstn = driver.findElement(By.xpath("//select[@placeholder='Boarding Station']"));
		wait.until(ExpectedConditions.elementToBeClickable(boardingstn));
		
		boardingstn.click();
		boardingstn.sendKeys(Keys.PAGE_DOWN,Keys.PAGE_DOWN);
		List<WebElement> stnlist = driver.findElements(By.xpath("//select[@placeholder='Boarding Station']/option"));
     	wait.until(ExpectedConditions.elementToBeClickable(stnlist.get(27)));
		stnlist.get(27).click();
		
		
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'ITARSI')]")));		
//		Actions act = new Actions(driver);
//		act.moveToElement(driver.findElement(By.xpath("//option[contains(text(),'ITARSI')]"))).build().perform();

		//driver.findElement(By.xpath("//button[text()='FIND FOOD']")).click();
		
  }
  
}
