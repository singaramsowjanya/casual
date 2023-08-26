package vtigerFinalScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericLibraries.BaseClass;
import GenericLibraries.Iconstantpath;

public class CreateContactTest extends BaseClass
{
	@Test
	public void createContactTest()
	{
	SoftAssert soft = new SoftAssert();
	home.clickContacts();
	soft.assertTrue(driver.getTitle().contains("Contacts"));
	contacts.clickPlusButton();
	soft.assertTrue(createContact.getpageHeader().equals("Creating New Contact"));
	Map<String,String> map=excel.readFromExcel("ContactTestdata", "Create Contact");
	   String lastName=map.get("Last Name")+jutil.generateRandomNum(100);
	   createContact.setlastName(lastName);
	   createContact.clickSaveButton();
	   
	   
	   soft.assertTrue(newContactInfo.getpageHeader().contains(lastName));
	   if(newContactInfo.getpageHeader().contains(lastName))
		   excel.writeToExcel("ContactTestdata", "Create Contact ", "Pass", Iconstantpath.EXCEL_PATH);
	   else
		   excel.writeToExcel("ContactTestdata", "Create Contact ", "fail", Iconstantpath.EXCEL_PATH);
	   
	  soft.assertAll();

}
}
