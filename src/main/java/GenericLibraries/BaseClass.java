package GenericLibraries;
 
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import Pompages.ContactsPage;
import Pompages.CreateNewContactPage;
import Pompages.CreateNewEventPage;
import Pompages.CreateNewLeadPage;
import Pompages.CreateNewOrganizationPage;
import Pompages.DuplicatingLeadPage;
import Pompages.HomePage;
import Pompages.LeadsPage;
import Pompages.Loginpage;
import Pompages.NewContactInfoPage;
import Pompages.NewEventInfoPage;
import Pompages.NewLeadInfoPage;
import Pompages.NewOrgInfoPage;
import Pompages.Organizationspage;


public class BaseClass {
           
	      protected PropertiesUtility property;
	      protected ExcelUtility excel;
	      protected javaUtility jutil;
	      protected WebDriverUtility web;
	      protected WebDriver driver;
	      
	      protected Loginpage login;
	      protected HomePage home;
	      protected Organizationspage org;
	      protected ContactsPage contacts;
	      protected LeadsPage lead;
	      protected CreateNewOrganizationPage createOrg;
	      protected CreateNewContactPage createContact;
	      protected CreateNewEventPage createEvent;
	      protected CreateNewLeadPage createLead;
	      protected NewOrgInfoPage newOrgInfo;
	      protected NewContactInfoPage newContactInfo;
	      protected NewLeadInfoPage newLeadInfo;
	      protected NewEventInfoPage newEventInfo;
	      protected DuplicatingLeadPage duplicatingLead;
	      
	      public static WebDriver sdriver;
	      public static javaUtility sjutil;
	      
	      //@Beforesuite
	       //@BeforeTest
	      
	      @BeforeClass
	      public void classSetup() {
	    	  property=new PropertiesUtility();
	    	  excel=new ExcelUtility();
	    	  jutil=new javaUtility();
	    	  web=new WebDriverUtility();
	    	  
	    	  property.propretiesInitialization(Iconstantpath.PROPERTIES_PATH);
	    	  driver=web.launchBrowser(property.readFromProperties("browser"));
	    	  web.maximizeBrowser();
	    	  web.waitTillElementFound(Long.parseLong(property.readFromProperties("time")));
	    	  
	    	  sdriver=driver;
	    	  sjutil=jutil;
	    	  
	    	  
	    	  
	      }
	      @BeforeMethod
	      public void methodSetup() {
	    	  excel.excelInitialization(Iconstantpath.EXCEL_PATH);
	    	  
	    	  login=new Loginpage(driver);
	    	  home=new HomePage(driver);
	    	  org=new Organizationspage(driver);
	    	  contacts=new ContactsPage(driver);
	    	  lead=new LeadsPage(driver);
	    	  createOrg=new CreateNewOrganizationPage(driver);
	    	  createContact=new CreateNewContactPage(driver);
	    	  createEvent=new CreateNewEventPage(driver);
	    	  createLead=new CreateNewLeadPage(driver);
	    	  newOrgInfo=new NewOrgInfoPage(driver);
	    	  newContactInfo=new NewContactInfoPage(driver);
	    	  newLeadInfo=new NewLeadInfoPage(driver);
	    	  newEventInfo=new NewEventInfoPage(driver);
	    	  duplicatingLead=new DuplicatingLeadPage(driver);
	    	  
	    	  web.navigateToApp(property.readFromProperties("url"));
	    	  Assert.assertTrue(driver.getTitle().contains("vtiger"));
	    	  login.loginToApp(property.readFromProperties("username"),property.readFromProperties("password"));
	    	  Assert.assertTrue(driver.getTitle().contains("Home"));
	    	  }
	      
	      @AfterMethod
	      public void methodTearDown() {
	    	  home.signOutOfVtiger(web);
	    	  excel.closeExcel();
	      }
	      
	      @AfterClass
	      public void classTearDown() {
	    	  web.quitAllWindows();
	      }
	      
}
