package TestCaseProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.SeleniumUtility;

public class ActiTime extends SeleniumUtility {
	static ExtentTest test;
	static ExtentReports report;
	int ProjID = (int) ((Math.random() * (1000 - 100)) + 100);
	JavascriptExecutor js;

	@BeforeTest
	public static void startTest() {
		report = new ExtentReports(".\\ExtentReport\\ActitimeReport.html");
	}

	@Test
	public void TC1_Login() {
		test = report.startTest("LoginPage");
		WebDriver driver = setUp("chrome", "https://demo.actitime.com/");
		js = (JavascriptExecutor) driver;
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("pwd")).clear();

		typeInput(driver.findElement(By.name("username")), "admin");
		driver.findElement(By.name("pwd")).sendKeys("manager", Keys.ENTER);
		test.log(LogStatus.PASS, "Login Page Done");
	}

	@Test
	public void TC2_CreateTask() {
		test = report.startTest("Create New Task");

		// select new task
		clickOnElement(driver.findElement(By.xpath("//a[@class='content tasks']")));
		clickOnElement(driver.findElement(By.className("addNewButton")));
		clickOnElement(driver.findElement(By.xpath("//div[@class='item createNewTasks']")));

		// for dropdown
		DropDownSelection();

		// entering task details

		typeInput(driver.findElement(By.xpath("//div[@class='taskTableContainer']/table/tbody/tr[1]/td[1]/input")),
				("Testing task" + ProjID));

		clickOnElement(driver
				.findElement(By.xpath("//div[@class='taskTableContainer']/table/tbody/tr[1]/td[6]/label/span[2]")));
		clickOnElement(driver.findElement(By.className("commitButtonPlaceHolder")));

		boolean popup = isElementExist(driver.findElement(By.xpath("//div[@class='toastsContainer']")));

		test.log(LogStatus.INFO, "is New Task Created confirmation popup displayed :- " + popup);
	}

	@Test
	public void TC3_UpdateCurrentTask() {
		test = report.startTest("Update Current Task");

		clickOnElement(driver.findElement(By.xpath("//table[@class='createdTasksRowsTable']/tbody/tr/td[3]/div/div")));
		clickOnElement(driver.findElement(By.xpath(
				"//div[@class='taskRowsTableContent']/div[3]/div/div/div[1]/div[1]/div[2]/div[1]//div[3]/div[3]")));
		boolean popup = isElementExist(driver.findElement(By.xpath("//div[@class='toastsContainer']")));

		test.log(LogStatus.INFO, "is Updation of Task Confirmation popup displayed :- " + popup);

	}

	
	@Test()
	public void TC4_DeleteTask() throws InterruptedException {
		test = report.startTest("Delete Task");
		refreshPage();
		String Taskname = "Testing task" + ProjID;
		clickOnElement(driver.findElement(By.xpath("//tr[td[div[div[div[div[@title='"+Taskname+"']]]]]]/td[1]/div")));
		System.out.println("//tr[td[div[div[div[div[@title='"+Taskname+"']]]]]]/td[1]/div");
		Boolean downPopup = isElementExist(driver.findElement(By.xpath(
				"//div[@class='animatedVisibilityContainer tasklist_components_bulkOperationsPanelContainer']")));
		if (downPopup == true) {
			clickOnElement(driver.findElement(By.xpath("//div[@class='delete button']")));

			clickOnElement(driver.findElement(
					By.xpath("//div[@class='dialogWithPointerWrapper deleteDialog']/div[1]/div[1]/div/div[5]/div[1]")));

			Boolean Deletepopup = isElementExist(driver.findElement(By.xpath("//div[@class='toastsContainer']")));
			test.log(LogStatus.INFO, "is Delete Popup displayed? - " + Deletepopup);
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

	

	public void DropDownSelection() {
		WebElement element = driver.findElement(
				By.xpath("//div[@class='selectCustomerAndProject']/table/tbody/tr[1]/td/div/div/div[1]/div[3]"));
		js.executeScript("arguments[0].click();", element);

//		clickOnElement(driver.findElement(
//				By.xpath("//div[@class='selectCustomerAndProject']/table/tbody/tr[1]/td/div/div/div[1]/div[3]")));

		List<WebElement> searchitemlist = driver.findElements(By.xpath(
				"//div[@class='customerSelector customerOrProjectSelector selectorWithPlaceholderContainer']/div/div[2]/div/div[1]/div/div"));
		System.out.println("**** " + searchitemlist.size());

		if (searchitemlist.size() >= 2) {
			try {

				clickOnElement(driver.findElement(By.xpath(
						"//div[@class='customerSelector customerOrProjectSelector selectorWithPlaceholderContainer']/div/div[2]/div/div[1]/div/div[@class='itemRow cpItemRow selected']")));
				System.out.println("Selected ***");

			} catch (NoSuchElementException e) {
				System.out.println("Exception :- " + e);
				searchitemlist.get(2).click();
			}

			// projectlist
			clickOnElement(driver.findElement(By.xpath(
					"//div[@class='projectSelector customerOrProjectSelector selectorWithPlaceholderContainer']/div/div")));
			List<WebElement> projlist = driver.findElements(By.xpath(
					"//div[@class='projectSelector customerOrProjectSelector selectorWithPlaceholderContainer']/div/div[2]/div/div[1]/div/div"));

			if (projlist.size() > 3) {
				projlist.get(projlist.size() - 1).click();

			} else {
				projlist.get(0).click();

				typeInput(
						driver.findElement(By.xpath(
								"//input[@class='newProject newCustomerProjectField inputFieldWithPlaceholder']")),
						("Testing Project" + ProjID));

				System.out.println("Project Name :-  Testing Project" + ProjID);
			}

		} else {
			searchitemlist.get(0).click();
			typeInput(driver.findElements(By.xpath("//input[@placeholder='Enter Customer Name']")).get(1),
					"CompanyTC" + ProjID);

			typeInput(driver.findElements(By.xpath("//input[@placeholder='Enter Project Name']")).get(1),
					"Project" + ProjID);

		}

	}
}
