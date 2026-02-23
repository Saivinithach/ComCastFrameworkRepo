package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass1;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * test class for contact module
 * @author vinitha
 * 
 */

public class SearchContactTest extends BaseClass1
{
	/**
	 * scenario : login()==> navigateContact==>createContatct()==>verify
	 */
	
	@Test
	public void searchContact()
	{
		/*Step 1: login to app*/
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
}
