package Pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewEventInfoPage {
	//Declaration 
		@FindBy(xpath = "//span[@class='IvHeaderText']")
		private WebElement pageHeader;
		
	//Initialization
	public NewEventInfoPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
	//Utilization
	public String getpageHeader() {
	return pageHeader.getText();
	}
}
