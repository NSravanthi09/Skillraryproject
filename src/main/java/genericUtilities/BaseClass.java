package genericUtilities;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pomPages.AddNewCategoryPage;
import pomPages.AddNewCousrePage;
import pomPages.AddNewUserPage;
import pomPages.CategoryPage;
import pomPages.CourseListPage;
import pomPages.HomePage;
import pomPages.LoginPage;
import pomPages.Userspage;

public class BaseClass {
	
  protected	WebDriver driver;
  protected	 propertiesUtility property;
  protected	JavaUtility jutil;
  protected	webDriverUtility web;
  protected	ExcelUtility excel;
   
  protected LoginPage login;
  protected HomePage home;
  protected Userspage users;
  protected CourseListPage courseList;
  protected CategoryPage category;
  protected AddNewUserPage adduser;
  protected AddNewCousrePage addCourse;
  protected AddNewCategoryPage addCategory;
  
  public static WebDriver sdriver;
  public static JavaUtility sjutil;
  
	
	//@BeforeSuite
	//@BeforeTest
	
	@BeforeClass
	public void classSetup() {
		web=new webDriverUtility();
		jutil=new JavaUtility();
		property=new propertiesUtility();
		excel=new ExcelUtility();
		
		property.propertiesInit(IConstantPath.PROPERTIES_FILE_PATH);
		
		driver=web.launchBrowser(property.readFromProperties("browser"));
		web.waitTillElementFound(Long.parseLong(property.readFromProperties("timeouts")));
		sdriver=driver;
		sjutil=jutil;
		
	}
	
	@BeforeMethod
	public void methodsSetup() {
		login=new LoginPage(driver);
		home=new HomePage(driver);
		users=new Userspage(driver);
		courseList=new CourseListPage(driver);
		category=new CategoryPage(driver);
		adduser=new AddNewUserPage(driver);
		addCourse=new AddNewCousrePage(driver);
		addCategory=new AddNewCategoryPage(driver);
		 excel.excelInit(IConstantPath.EXCEL_PATH,"Sheet1");
		 
		 web.navigateToApp(property.readFromProperties("url"));
		 Assert.assertEquals(login.getPageHeader(), "Login");
		 login.loginToApp(property.readFromProperties("username"), property.readFromProperties("password"));
		 Assert.assertEquals(home.getPageHeader(), "Home");
				 
		}
	
	@AfterMethod
	public void methodTearDown() {
		excel.closeExcel();
		home.signOutOfApp();
	  
	}
	@AfterClass
	public void classTearDown() {
		web.quitAllWindows();

	}
	//@AfterTest
	//@AfterSuite

}
