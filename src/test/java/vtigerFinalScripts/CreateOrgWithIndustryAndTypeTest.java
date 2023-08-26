package vtigerFinalScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericLibraries.BaseClass;
import GenericLibraries.Iconstantpath;

public class CreateOrgWithIndustryAndTypeTest extends BaseClass {
    @Test
public void createOrgWithTypeAndIndustryTest() {
	 SoftAssert soft=new SoftAssert();
	 home.clickOrganizatons();
	 soft.assertTrue(driver.getTitle().contains("organizations"));
	 org.clickPlusButton();
	 soft.assertTrue(createOrg.getpageHeader().equals("Creating New Organisation"));
	 Map<String, String> map=excel.readFromExcel("OrganisationTestdata","Create Organisation");
	 String orgName=map.get("Organisation Name")+jutil.generateRandomNum(100);
	 createOrg.setorgName(orgName);
	 createOrg.selectIndustry(web,map.get("Industry"));
	 createOrg.selectType(web, map.get("Type"));
	 createOrg.clickSaveButton();
	 
	 soft.assertTrue(newOrgInfo.getpageHeader().contains(orgName));
	 if(newOrgInfo.getpageHeader().contains(orgName))
		 excel.writeToExcel("OrganisationTestdata", "Create Organisation With Industry And Type", "pass", Iconstantpath.EXCEL_PATH);
	 else
		 excel.writeToExcel("OrganisationTestdata", "Create Organisation With Industry And Type", "Fail",Iconstantpath.EXCEL_PATH);
	 soft.assertAll();
	 

}
}



