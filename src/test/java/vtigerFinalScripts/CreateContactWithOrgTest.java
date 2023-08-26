package vtigerFinalScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericLibraries.BaseClass;
import GenericLibraries.Iconstantpath;

public class CreateContactWithOrgTest extends BaseClass
{ 
	@Test
	
public void createContactWithOrgTest()
{
	SoftAssert soft = new SoftAssert();
	home.clickContacts();
	soft.assertTrue(driver.getTitle().contains("Contacts"));
	contacts.clickPlusButton();
	soft.assertTrue(createContact.getpageHeader().equals("Creating New Contact"));
	Map<String,String> map=excel.readFromExcel("ContactTestdata", "Create Contact");
	   String lastName=map.get("Last Name")+jutil.generateRandomNum(100);
	   createContact.setlastName(lastName);
	   createContact.selectExistingOrg(web,map.get("Organisation Name"));
	   createContact.clickSaveButton();
	   
	   
	   soft.assertTrue(newContactInfo.getpageHeader().contains(lastName));
	   if(newContactInfo.getpageHeader().contains(lastName))
		   excel.writeToExcel("ContactTestdata", "Create Contact with Organization ", "Pass", Iconstantpath.EXCEL_PATH);
	   else
		   excel.writeToExcel("ContactTestdata", "Create Contact with Organization ", "fail", Iconstantpath.EXCEL_PATH);
	   
	  soft.assertAll();

	}
}
