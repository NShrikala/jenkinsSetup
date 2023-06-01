package TestCaseProject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.SeleniumUtility;

public class OrangeHRM extends SeleniumUtility {
	static ExtentTest test;
	static ExtentReports report;
	int EmpId = (int) ((Math.random() * (1000 - 100)) + 100);

	@BeforeTest
	public static void startTest() {
		report = new ExtentReports(".\\ExtentReport\\OrangeHRMReport.html");
	}

	@Test
	public void TC1_Login() {
		test = report.startTest("LoginPage");
		WebDriver driver = setUp("chrome", "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("password")).clear();
		typeInput(driver.findElement(By.name("username")), "Admin");
		driver.findElement(By.name("password")).sendKeys("admin123", Keys.ENTER);
		test.log(LogStatus.PASS, "Login Page Done");

	}

	@Test
	public void TC2_CreatePIM() {
		test = report.startTest("Create PIM");
		clickOnElement(driver.findElement(By.xpath("//span[text()='PIM']")));
		// validate PIM page opened
		String PageHeading = driver.findElement(By.cssSelector("span[class='oxd-topbar-header-breadcrumb']>h6"))
				.getText();
		if (PageHeading.contains("PIM")) {
			clickOnElement(driver.findElement(By.linkText("Add Employee")));

			// add details
			typeInput(driver.findElement(By.name("firstName")), "QA");
			typeInput(driver.findElement(By.name("lastName")), "Tester");
			driver.findElement(By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']/div/div/div[2]/input"))
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, String.valueOf(EmpId));

			clickOnElement(driver.findElement(By.xpath("//button[@type='submit']")));

			// Create Employee Validation
			Boolean SuccessMsg = isElementExist(driver.findElement(By.id("oxd-toaster_1")));
			if (SuccessMsg == true) {
				test.log(LogStatus.PASS, "Employee Successfully added.");
			}
		}

	}

	@Test
	public void TC3_UpdatePIM() {
		test = report.startTest("Update PIM");

		// Search from EmpID
		clickOnElement(driver.findElement(By.xpath("//span[text()='PIM']")));
		typeInput(driver.findElement(By.xpath("//div[@class='oxd-form-row']/div[1]/div[2]/div[1]/div[2]/input")),
				String.valueOf(EmpId));

		clickOnElement(driver.findElement(By.xpath("//button[@type='submit']")));

		// Edit
		clickOnElement(driver.findElement(
				By.xpath("//div[div[2][div[text()='" + String.valueOf(EmpId) + "']]]/div[9]/div/button[2]/i")));

		clickOnElement(driver.findElement(By.xpath("//a[text()='Contact Details']")));

		driver.findElement(By.xpath("//form[@class='oxd-form']/div[1]/div/div[1]/div/div[2]/input")).sendKeys("Mumbai",
				Keys.ENTER);

		Boolean SuccessMsg1 = isElementExist(driver.findElement(By.id("oxd-toaster_1")));
		if (SuccessMsg1 == true) {
			test.log(LogStatus.PASS, "Employee Contact details Successfully updated..");
		}
	}

	@Test
	public void TC4_DeletePIM() throws InterruptedException {
		test = report.startTest("Delete PIM");
		// Search from EmpID
		clickOnElement(driver.findElement(By.xpath("//span[text()='PIM']")));
		typeInput(driver.findElement(By.xpath("//div[@class='oxd-form-row']/div[1]/div[2]/div[1]/div[2]/input")),
				String.valueOf(EmpId));
		clickOnElement(driver.findElement(By.xpath("//button[@type='submit']")));

		clickOnElement(driver.findElement(
				By.xpath("//div[div[2][div[text()='" + String.valueOf(EmpId) + "']]]/div[9]/div/button[1]/i")));
	
		System.out.println("//div[div[2][div[text()='" + String.valueOf(EmpId) + "']]]/div[9]/div/button[1]/i");
		clickOnElement(driver.findElement(By.xpath(
				"//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")));

		Boolean SuccessMsg1 = isElementExist(driver.findElement(By.id("oxd-toaster_1")));
		if (SuccessMsg1 == true) {
			test.log(LogStatus.PASS, "Employee  details Successfully deleted.");
		}
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			test.log(LogStatus.FAIL, "Test Case Failed due to " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test Case Skipped due to " + result.getName());
		}
		// ending test endTest(logger) : It ends the current test and prepares to create
		// HTML report
		report.endTest(test);
	}

	@AfterTest
	public static void endTest() {

		report.flush();

	}

}
