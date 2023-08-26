package vtigerFinalScripts;


import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericLibraries.BaseClass;
import GenericLibraries.Iconstantpath;

public class CreateOrganizationTest extends BaseClass {
	
	@Test
	public void createOrgTest()
	{
		SoftAssert soft = new SoftAssert();
		home.clickOrganizatons();
		soft.assertTrue(driver.getTitle().contains("Organisation"));
		org.clickPlusButton();
		soft.assertTrue(createOrg.getpageHeader().equals("Creating New Organisation"));
		Map<String,String> map=excel.readFromExcel("OrganisationTestdata", "Create Organisation");
		   String orgName=map.get("Organisation Name")+jutil.generateRandomNum(100);
		   createOrg.setorgName(orgName);
		   createOrg.clickSaveButton();
		   
		   
		   soft.assertTrue(newOrgInfo.getpageHeader().contains(orgName));
		   if(newOrgInfo.getpageHeader().contains(orgName))
			   excel.writeToExcel("OrganisationTestdata", "Create Organisation", "Pass", Iconstantpath.EXCEL_PATH);
		   else
			   excel.writeToExcel("OrganisationTestdata", "Create Organisation", "fail", Iconstantpath.EXCEL_PATH);
		   
		  //soft.assertAll();
	}
}
