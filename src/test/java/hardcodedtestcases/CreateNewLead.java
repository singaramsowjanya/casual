package hardcodedtestcases;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateNewLead {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		if(driver.getTitle().contains("vtiger"))
			System.out.println("login page is displayed");
		else
			System.out.println("login page is not displayed");
		
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("Sowju@1996");
		driver.findElement(By.id("submitButton")).submit();
		if(driver.getTitle().contains("Home"))
			System.out.println("home page is displayed");
		else
			System.out.println("home page is not displayed");
		
		
		driver.findElement(By.xpath("//a[text()='Leads' and contains(@href,'action=index')]")).click();
		if(driver.getTitle().contains("Leads"))
			System.out.println(" Leads page is displayed");
		else
			System.out.println("Leads page is not displayed");
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		WebElement createlead=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")); 
		if(createlead.getText().equals("Creating New Lead"))
			System.out.println("create new leads page is displayed");
		else
			System.out.println("create new leads page not is displayed");
		Random random=new Random();
		int randomNum=random.nextInt(100);
		String leadName="nani"+randomNum;
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(leadName);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='company']")).sendKeys("TCS");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		String newleadInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(newleadInfo.contains(leadName))
			System.out.println("lead is created succesfully");
		else
			System.out.println("lead is not created succesfully");
		WebElement adminIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(adminIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
	}

}
