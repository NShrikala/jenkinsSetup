package Pract2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Jquery {
	 WebDriver driver;
	 String homeWinId;
	 WebDriverWait wait;	
  public void OpenBrowser() {
	  WebDriverManager.chromedriver().setup();
		driver =  new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://jqueryui.com/");
		wait=  new WebDriverWait(driver, 20); 
		
		homeWinId=driver.getWindowHandle();//EC8129D18C193DDA926BAF64A0600F3C
		System.out.println("Home window id: "+homeWinId);
		
		}
  @Test
  public void Testcase1() throws InterruptedException
  {
	  OpenBrowser();
	  
	  driver.findElement(By.xpath("//a[text()='Sortable']")).click();
	  
	  WebElement frame = driver.findElement(By.className("demo-frame"));
	  driver.switchTo().frame(frame);
	  
	  List <WebElement> sortlist = driver.findElements(By.xpath("//ul[@id='sortable']/li"));
	  
	  Actions act = new Actions(driver);
	  
	  for(int i=sortlist.size()-1; i>=0;i--)
	  {
		  act.dragAndDrop(sortlist.get(i),sortlist.get(0)).build().perform();
	  }
  }
  
  
 
}
