package TestCaseProject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.SeleniumUtility;

public class VTiger extends SeleniumUtility {
	static ExtentTest test;
	static ExtentReports report;

	@BeforeTest
	public static void startTest() {
		report = new ExtentReports(".\\ExtentReport\\VTigerReport.html");
	}

	@Test
	public void TC1_Login() {
		test = report.startTest("LoginPage");
		WebDriver driver = setUp("chrome", "https://demo.vtiger.com/vtigercrm/index.php");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.name("password")).clear();
		typeInput(driver.findElement(By.id("username")), "Admin");
		driver.findElement(By.name("password")).sendKeys("Test@123", Keys.ENTER);
		test.log(LogStatus.PASS, "Login Page Done");
	}

	@Test
	public void TC2_Create() {
		test = report.startTest("Create Test Case");

		clickOnElement(driver.findElement(By.xpath("//div[@id='appnavigator']/div")));
		clickOnElement(driver.findElement(By.xpath("//div[@class='app-list row']/div[2]")));
		clickOnElement(driver.findElement(By.xpath("//a[@title='Leads']")));
		clickOnElement(driver.findElement(By.xpath("//button[@id='Leads_listView_basicAction_LBL_ADD_RECORD']")));

		// dropdown code
		WebElement salutationDD = driver.findElement(By.xpath("//select[@name='salutationtype']"));
		Select salutationDropDown = new Select(salutationDD);
		salutationDropDown.selectByIndex(3);

		typeInput(driver.findElement(By.xpath("//input[@name='firstname']")), "Shrikala");
		typeInput(driver.findElement(By.xpath("//input[@name='lastname']")), "Nayak");

		clickOnElement(driver.findElement(By.xpath("//div[@id='s2id_autogen11']")));
		clickOnElement(driver.findElement(By.xpath("//ul[@id='select2-results-12']/li[2]/ul/li[1]")));
		clickOnElement(driver.findElement(By.xpath("//button[text()='Save']")));

		test.log(LogStatus.PASS, "New Lead Created");
	}

	@Test
	public void TC3_Update() {
		test = report.startTest("Update Test Case");
		clickOnElement(driver.findElement(By.id("Leads_detailView_basicAction_LBL_EDIT")));
		typeInput(driver.findElement(By.id("Leads_editView_fieldName_phone")), "0123456789");
		clickOnElement(driver.findElement(By.xpath("//button[text()='Save']")));
		test.log(LogStatus.PASS, "New Lead Updated");
	}

	@Test
	public void TC4_Delete() {
		test = report.startTest("Delete Test Case");
		clickOnElement(driver.findElement(By.xpath("//h4[@class='module-title pull-left text-uppercase']")));
		clickOnElement(driver.findElement(By.xpath("//tbody[@class='overflow-y']/tr[1]/td[1]/div/span[1]/input")));
		clickOnElement(driver.findElement(By.xpath("//button[@title='Delete']")));
		clickOnElement(driver.findElement(By.xpath("//button[text()='Yes']")));
		test.log(LogStatus.PASS, "Lead Deleted");
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			test.log(LogStatus.FAIL, "Test Case Failed due to " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test Case Skipped due to " + result.getName());
		}
			report.endTest(test);
	}

	@AfterTest
	public static void endTest() {
		report.flush();
	}

	@AfterTest
	public void Cleanup() throws InterruptedException {
		Thread.sleep(5000 * 10);
		cleanUp();
	}

}
