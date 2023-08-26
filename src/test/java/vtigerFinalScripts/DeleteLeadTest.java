package vtigerFinalScripts;

import java.util.List;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericLibraries.BaseClass;
import GenericLibraries.Iconstantpath;

public class DeleteLeadTest extends BaseClass {
	            
	@Test
	public void deleteLeadTest() {
		SoftAssert soft=new SoftAssert();
		home.clickLeads();
		soft.assertTrue(driver.getTitle().contains("Leads"));
		Map<String, String> map=excel.readFromExcel("LeadTestData", "Delete lead");
		lead.traverseToRequiredLead(web, map.get("Lead Name"));
		lead.clickDelete();
		
		web.handleAlert("ok");
		List<WebElement> leadNameList=lead.getLeadsNamesList();
		boolean status=false;
		for(WebElement leads:leadNameList) {
			if(!(leads.getText().equals("null16"))) {
				status=true;
			}
		}
		soft.assertTrue(status);
		if(status) 
			excel.writeToExcel("LeadTestData", "Delete lead", "Pass", Iconstantpath.EXCEL_PATH);
		else
			excel.writeToExcel("LeadTestData", "Delete lead", "Fail", Iconstantpath.EXCEL_PATH);
	
		soft.assertAll();
		
		
	}
          
}