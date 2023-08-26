package Pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericLibraries.WebDriverUtility;

public class HomePage {

	//Declaration
	@FindBy(xpath ="//a[text()='Leads' and contains(@href,'action=index')]")
	private WebElement leadsTab;
	@FindBy(xpath = "//a[text()='Organizations' and contains(@href,'action=index')]")
	private WebElement organizationsTab;
	
	@FindBy(xpath ="//a[text()='Contacts' and contains(@href,'action=index')]" )
	private WebElement contactsTab;
	
	@FindBy(id="qccombo")
	private WebElement quickCreateDropdown;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminIcon;
	
	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement signOutButton;
	//Initialization
		public HomePage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		//Utilization
		public void clickLeads() {
			leadsTab.click();
		}
		public void clickOrganizatons() {
			organizationsTab.click();
		}
		public void clickContacts() {
			contactsTab.click();
		}
		public void selectFromQuickCreate(WebDriverUtility web, String value) {
			web.selectFromDropdown(quickCreateDropdown, value);
		}
		public void signOutOfVtiger(WebDriverUtility web) {
			web.mouseHover(adminIcon);
			signOutButton.click();
}
}