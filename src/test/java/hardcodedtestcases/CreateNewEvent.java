package hardcodedtestcases;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateNewEvent {

	public static void main(String[] args) {
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
		WebElement quickCreateDropdown=driver.findElement(By.id("qccombo"));
		Select s=new Select(quickCreateDropdown);
		s.selectByValue("Events");
		
		WebElement createToDo=driver.findElement(By.xpath("//b[text()='Create To Do']"));
		if(createToDo.isDisplayed() )
		System.out.println("create to do window is displayed");
		else
		System.out.println("create to do window is not displayed");
		
		Random random=new Random();
				int randomNum=random.nextInt(100);
				String subject="Meet"+randomNum;
				
				driver.findElement(By.xpath("subject")).sendKeys(subject);
				driver.findElement(By.id("jscal_trigger_date_start")).click();
				
				String reqDate="19";
				int reqMonth=10;
				int reqYear=2025;
				
				String commonpath="//div[@class='calendar' and contains(@style,'block')]/descendant::td[%s]";
				String actMonthYear=driver.findElement(By.xpath(formatpath(commonpath,"@class='title'"))).getText();
				System.out.println(actMonthYear);
				
				String[] str =actMonthYear.split(",");
				int actYear=Integer.parseInt(str[1]);
				
				while(actYear < reqYear)
				{
					driver.findElement(By.xpath(formatpath(commonpath,"text()='>>'"))).click();
					actMonthYear=driver.findElement(By.xpath(formatpath(commonpath,"@class='title'"))).getText();
					System.out.println(actMonthYear);
					
					str=actMonthYear.split(",");
					actYear=Integer.parseInt(str[1]);
				}
				int actMonth=DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0]).get(ChronoField.MONTH_OF_YEAR);
				System.out.println(actMonth);
				
				while(actYear < reqYear) {
					driver.findElement(By.xpath(formatpath(commonpath,"text()='>'"))).click();
					actMonthYear=driver.findElement(By.xpath(formatpath(commonpath,"@class='title'"))).getText();
					System.out.println(actMonth);
					
					str=actMonthYear.split(",");
					actMonth=DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0]).get(ChronoField.MONTH_OF_YEAR);
					System.out.println(actMonth);
					
					
				}
				while(actYear > reqYear) {
					driver.findElement(By.xpath(formatpath(commonpath,"text()='<'"))).click();
					actMonthYear=driver.findElement(By.xpath(formatpath(commonpath,"@class='title'"))).getText();
					System.out.println(actMonth);
				
		str=actMonthYear.split(",");
		 actMonth=DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0]).get(ChronoField.MONTH_OF_YEAR);
		System.out.println(actMonth);
}
driver.findElement(By.xpath(formatpath(commonpath,"text()='"+reqDate+","))).click();
}
	
	public static String formatpath(String commonpath,String replaceData)
	{
		return String.format(commonpath, replaceData) ;
	}
		
		}


