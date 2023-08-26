
	package testng;

	import java.time.Duration;


	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.Test;

	public class DependsOnMethodsTest {
		@Test
	public void navigateToActitime() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://demo.actitime.com/login.do");
		
		WebElement logo =driver.findElement(By.className("atLogoImg"));
		//Assert.assertTrue(logo.isDisplayed());
		Assert.fail();
		driver.quit();

		}
		@Test(dependsOnMethods="navigateToActitime",alwaysRun = true)
		public void loginToActitime()
		{
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://demo.actitime.com/login.do");
			driver.findElement(By.id("username")).sendKeys("admin"+Keys.TAB+"manager"+Keys.ENTER);
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			
			
			wait.until(ExpectedConditions.titleContains("Enter Time-Track"));
			driver.quit();
			
		}
		
	}


