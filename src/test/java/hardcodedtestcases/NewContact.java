package hardcodedtestcases;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class NewContact {

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
		
		
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		if(driver.getTitle().contains("Contacts"))
			System.out.println(" contacts page is displayed");
		else
			System.out.println("contacts page is displayed");
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		WebElement createcon=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")); 
		if(createcon.getText().equals("Creating New Contact"))
			System.out.println("create new contacts page is displayed");
		else
			System.out.println("create new contacts page not is displayed");
		Random random=new Random();
		int randomNum=random.nextInt(100);
		String conName="sowju"+randomNum;
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(conName);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		Thread.sleep(2000);

		String newconInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(newconInfo.contains(conName))
			System.out.println("contact is created succesfully");
		else
			System.out.println("contact is not created succesfully");
		
		WebElement adminIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(adminIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
	}

}
