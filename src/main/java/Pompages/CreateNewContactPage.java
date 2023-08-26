package Pompages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericLibraries.WebDriverUtility;

public class CreateNewContactPage {
	//Declaration 
			@FindBy(xpath = "//span[@class='lvtHeaderText']")
			private WebElement pageHeader;
			@FindBy(name="lastname")
			private WebElement lastNameTF;
			@FindBy (xpath="//img[contains(@onclick,'Accounts&action')]")
			private WebElement orgPlusButton;
			@FindBy (xpath="//div[@id='ListViewContents']/descendant::table[@cellspacing='1']/descendant::div")
			private List<WebElement> orgList;
			@FindBy (xpath = "//input[normalize-space(@value)='Save']")
			private WebElement saveButton;
			
			
		      //Initialization
			public CreateNewContactPage(WebDriver driver) {
				PageFactory.initElements(driver, this);
			}
			//Utilization
			public String getpageHeader() {
				return pageHeader.getText();
				}
			public void setlastName(String lastName) {
				lastNameTF.sendKeys(lastName);
			}
			public void selectExistingOrg(WebDriverUtility web,String requiredOrgName)
			{
				String parentID=web.getparentWindowID();
				web.switchToChildWindow();
				for(int i=1; i < orgList.size();i++)
			{
				if(orgList.get(i).getText().equals(requiredOrgName))
				{
					System.out.println(orgList.get(i).getText());
				    orgList.get(i).click();
				    break;
				}
			}
				web.switchToWindow(parentID);
			}

			public void clickSaveButton() {
				saveButton.click();
			}

}
