package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericUtilities.BaseClass;

public class AddUserTest extends BaseClass {
	@Test
	public void addUserTest() throws InterruptedException{
		SoftAssert soft=new SoftAssert();
		
		
		home.clickUsersTab();
		soft.assertTrue(users.getPageHeader().contains("Users"));
		
		users.clickNewButton();
		Thread.sleep(2000);
		soft.assertEquals(adduser.getPageHeader(),"Add New User");
		
//			Robot r=null;
//			try {
//				r = new Robot();
//			} catch (AWTException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			r.keyPress(KeyEvent.VK_ALT);
//			r.keyPress(KeyEvent.VK_F4);
//			r.keyRelease(KeyEvent.VK_ALT);
//			r.keyRelease(KeyEvent.VK_F4);
			
		Map<String,String>map=excel.readFromExcel1("Add User");
		
		adduser.setEmail(map.get("Email"));
		adduser.setPassword(map.get("Password"));
		adduser.setFirstname(map.get("FirstName"));
		adduser.setLastname(map.get("LastName"));
		adduser.setAddress(map.get("Address"));
		adduser.setContactInfo(map.get("Contact info"));
		adduser.uploadPhoto(map.get("photo"));
		
		adduser.clickSave();
		
		soft.assertEquals(users.getSuccessMessage(), "Success!");
		soft.assertAll();
		
		
	}
  
}
