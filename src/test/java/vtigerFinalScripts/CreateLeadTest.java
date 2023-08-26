package vtigerFinalScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericLibraries.BaseClass;
import GenericLibraries.Iconstantpath;

public class CreateLeadTest extends BaseClass
{
	@Test
	
	public void createLeadTest()
	{
		SoftAssert soft = new SoftAssert();
		home.clickLeads();
		soft.assertTrue(driver.getTitle().contains("Leads"));
		lead.clickPlusButton();
		soft.assertTrue(createLead.getpageHeader().equals("Creating New Lead"));
		Map<String,String> map=excel.readFromExcel("LeadTestdata", "Create lead");
		   String lastName=map.get("Last Name")+jutil.generateRandomNum(100);
		   excel.writeToExcel("LeadTestData",11,3, lastName,Iconstantpath.EXCEL_PATH);
		   createLead.setLastName(lastName);
		   createLead.setCompanyName(map.get("Company"));
		   createLead.clickSaveButton();
		   
		   
		   soft.assertTrue(newLeadInfo.getpageHeader().contains(lastName));
		   if(newLeadInfo.getpageHeader().contains(lastName))
			   excel.writeToExcel("LeadTestdata", "Create lead", "Pass", Iconstantpath.EXCEL_PATH);
		   else
			   excel.writeToExcel("LeadTestdata", "Create lead", "fail", Iconstantpath.EXCEL_PATH);
		   
		  soft.assertAll();


	}

}
