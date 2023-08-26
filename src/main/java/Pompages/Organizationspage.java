package Pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organizationspage {
//Declaration
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement PlusButton;
	
	//Initialization
	public Organizationspage(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
	//Utilization
	public void clickPlusButton() {
		PlusButton.click();
	}
}
