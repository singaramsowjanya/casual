package vtigerFinalScripts;

import java.util.Map;


import org.testng.asserts.SoftAssert;

import GenericLibraries.BaseClass;
import GenericLibraries.Iconstantpath;


public class CreateEventTest extends BaseClass{
	
	public void createEventTest() {
	      
	SoftAssert soft=new SoftAssert();
	     Map<String, String> map=excel.readFromExcel("EventsTestData", "Create New Event");
	     home.selectFromQuickCreate(web,map.get("Quick Create"));
	     soft.assertEquals(createEvent.getPageHeader(),"Create To Do");
	     String subject=map.get("subject")+jutil.generateRandomNum(100);
	     createEvent.setSubject(subject);
	     createEvent.clickOnStartDate();
	     createEvent.chooseRequiredDate(web, map.get("Start Date"), jutil);
	     createEvent.clickOnDueDate();
	     createEvent.chooseRequiredDate(web, map.get("Due Date"), jutil);
	     createEvent.clickSaveButton();
	     soft.assertTrue(newEventInfo.getpageHeader().contains(subject));
	     if(newEventInfo.getpageHeader().contains(subject))
	    	 excel.writeToExcel("EventsTestData", "Create New Event", "Pass", Iconstantpath.EXCEL_PATH);
	     else
	    	 excel.writeToExcel("EventsTestData", "Create New Event", "Fail" ,Iconstantpath.EXCEL_PATH);
	     
	    soft.assertAll();	 
	     

}
}
