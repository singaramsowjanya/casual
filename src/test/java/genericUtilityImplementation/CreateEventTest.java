package genericUtilityImplementation;

import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import GenericLibraries.Iconstantpath;
import GenericLibraries.ExcelUtility;
import GenericLibraries.javaUtility;
import GenericLibraries.PropertiesUtility;
import GenericLibraries.WebDriverUtility;

public class CreateEventTest {
public static void main(String[] args) {
	ExcelUtility excel = new ExcelUtility();
	PropertiesUtility property=new PropertiesUtility();
	javaUtility javaUtil = new javaUtility();
	WebDriverUtility web = new WebDriverUtility();

	property.propretiesInitialization(Iconstantpath.PROPERTIES_PATH);
	excel.excelInitialization(Iconstantpath.EXCEL_PATH);
	
	WebDriver driver=web.launchBrowser(property.readFromProperties("browser"));
	web.maximizeBrowser();
	web.navigateToApp(property.readFromProperties("url"));
	web.waitTillElementFound(Long.parseLong(property.readFromProperties("time")));
	
			if(driver.getTitle().contains("vtiger"))
		System.out.println("Login page displayed");
	else
		System.out.println("Login page not found");
	
	driver.findElement(By.name("user_name")).sendKeys(property.readFromProperties("username"));
	driver.findElement(By.name("user_password")).sendKeys(property.readFromProperties("password"));
	driver.findElement(By.id("submitButton")).submit();
	
	if (driver.getTitle().contains("Home"))
		System.out.println("Home page is displayed");
	else
		System.out.println("Home page not found");

	Map<String, String> map = excel.readFromExcel("EventsTestData", "Create New Event");
	WebElement quickCreate = driver.findElement(By.id("qccombo"));
	web.selectFromDropdown(map.get("Quick Create"), quickCreate);
	String header = driver.findElement(By.xpath("//td[@class='mailSubHeader']/b")).getText();
	if (header.contains("Create To Do"))
		System.out.println("Create To Do is displayed");
	else
		System.out.println("Create To Do not found");

	String subject = map.get("Subject") + javaUtil.generateRandomNum(100);
	driver.findElement(By.name("subject")).sendKeys(subject);
	driver.findElement(By.id("jscal_trigger_date_start")).click();

	String currentMonthYear = driver
			.findElement(By
					.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
			.getText();

	String[] str = currentMonthYear.split(",");

	int currentMonthInNum = javaUtil.convertMonthToInt(str[0]);
	int currentYearInNum = Integer.parseInt(str[1].trim());
	String requiredStartDate = map.get("Start Date");
	String[] date = requiredStartDate.split("-");
	int requiredYear = Integer.parseInt(date[0]);
	int requiredMonth = Integer.parseInt(date[1]);
	int requiredDate = Integer.parseInt(date[2]);

	while (currentYearInNum < requiredYear) {
		driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[.='»']"))
				.click();
		currentMonthYear = driver
				.findElement(By.xpath(
						"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
				.getText();
		str = currentMonthYear.split(",");
		currentMonthInNum = javaUtil.convertMonthToInt(str[0]);
		currentYearInNum = Integer.parseInt(str[1].trim());

		if (currentYearInNum == requiredYear) {
			while (currentMonthInNum < requiredMonth) {
				driver.findElement(
						By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[.='›']"))
						.click();
				currentMonthYear = driver.findElement(By.xpath(
						"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
						.getText();
				str = currentMonthYear.split(",");
				currentMonthInNum = javaUtil.convertMonthToInt(str[0]);
			}
			while (currentMonthInNum > requiredMonth) {
				driver.findElement(
						By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[.='‹']"))
						.click();
		currentMonthYear = driver.findElement(By.xpath("//div[@class='calendar' and contains"
				+ "(@style,'block')]/descendant::td[@class='title']")).getText();
				str = currentMonthYear.split(",");
				currentMonthInNum = javaUtil.convertMonthToInt(str[0]);
			}
		}
	}

	driver.findElement(By.xpath(
			"//div[@class='calendar' and contains(@style,'block')]/descendant::td[.='" + requiredDate + "']"))
			.click();
	
	WebElement dueDate = driver.findElement(By.id("jscal_field_due_date"));
	dueDate.clear();
	dueDate.sendKeys(map.get("Due Date"));
	driver.findElement(By.xpath("//input[@value='  Save']")).click();
	String eventInfoPageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
	if(eventInfoPageHeader.contains(subject)) {
		System.out.println("Pass");
		excel.writeToExcel("EventsTestData","Create New Event", "Pass", Iconstantpath.EXCEL_PATH);
	}
	else {
		System.out.println("Fail");
		excel.writeToExcel("Create New Event", "Fail", Iconstantpath.EXCEL_PATH, "EventsTestData");
	}

	WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	web.mouseHover(administratorIcon);

	driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	//web.closeWindows();
	//excel.closeWorkbook();
	web.quitAllWindows();
	excel.closeExcel();
	
}
	
}
	