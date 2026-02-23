package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest 
{
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String book,String bookName)
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		
		//search product
		driver.findElement(By.partialLinkText("Books")).click();
		String price=driver.findElement(By.xpath("//a[text()='"+bookName+"']/ancestor::div[@class='product-item']/descendant::div[8]/span[2]")).getText();
		System.out.println(price);
		
		driver.quit();
	}
	
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		ExcelUtility eu=new ExcelUtility();
		int rowCount=eu.getRowCount("Sheet4");

		
		Object[][] objArr=new Object[rowCount][2];
		
		for(int i=0;i<rowCount;i++)
		{
			objArr[i][0]=eu.getDataFromExcel("Sheet4", i+1, 0);
			objArr[i][1]=eu.getDataFromExcel("Sheet4", i+1, 1);
		}
		
		return objArr;
	}
}
