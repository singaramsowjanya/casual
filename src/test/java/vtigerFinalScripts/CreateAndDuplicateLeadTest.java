package vtigerFinalScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericLibraries.BaseClass;
import GenericLibraries.Iconstantpath;

public class CreateAndDuplicateLeadTest extends BaseClass
{ 
	@Test
	
	public void createAndDuplicateLeadTest()
	{
		//WE are creating and duplicating the lead
		SoftAssert soft = new SoftAssert();
		home.clickLeads();
		soft.assertTrue(driver.getTitle().contains("Leads"));
		lead.clickPlusButton();
		soft.assertEquals(createLead.getpageHeader(),"Creating New Lead");
		Map<String,String> map=excel.readFromExcel("LeadTestdata", "Create and Duplicate Lead");
		   String lastName=map.get("Last Name")+jutil.generateRandomNum(100);
		   createLead.setLastName(lastName);
		   createLead.setCompanyName(map.get("Company"));
		   createLead.clickSaveButton();
		     
		   soft.assertTrue(newLeadInfo.getpageHeader().contains(lastName));
		   newLeadInfo.clickDuplicateButton();
		   soft.assertTrue(duplicatingLead.getpageHeader().contains("Duplicating"));
		   String newLastName=map.get("New Last Name")+jutil.generateRandomNum(100);
           duplicatingLead.setLastName(newLastName);
           duplicatingLead.clicksaveButton();
		   if(newLeadInfo.getpageHeader().contains(newLastName))
			   excel.writeToExcel("LeadTestdata", "Create and Duplicate Lead", "Pass", Iconstantpath.EXCEL_PATH);
		   else
			   excel.writeToExcel("LeadTestdata", "Create and Duplicate Lead", "fail", Iconstantpath.EXCEL_PATH);
		   
		  soft.assertAll();
	}

}
