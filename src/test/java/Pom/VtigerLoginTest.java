package Pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Pompages.Loginpage;


public class VtigerLoginTest {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8888/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Create an object because it is a non-static method
         Loginpage login=new Loginpage(driver);
         login.loginToApp("admin", "Sowju@1996");
      
      if(driver.getTitle().contains("vtiger"))
    	  System.out.println("Login passed");
      else
    	  System.out.println("Login Failed");
      
      driver.quit();
	}

}