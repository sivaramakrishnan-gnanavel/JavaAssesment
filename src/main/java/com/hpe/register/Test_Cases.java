/**
 * 
 */
package com.hpe.register;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * @author sivaramakrishnan gnanavel
 */
public class Test_Cases {
	
	@Test
	public void registerAutomation(){
		
		Utility_File utility = new Utility_File();
		WebDriver driver = utility.establishConnection();
		Home_Page homepage = new Home_Page(driver);
		Register_Page registerpage = new Register_Page(driver);		
		
		XSSFWorkbook workbook = utility.getExcelFile(new File(System.getProperty("user.dir")+""+File.separator+"registerUserDetails.xlsx"));
		List<Map<String, String>> excelData = utility.getExcelList(workbook,"FirstName,LastName,Email,Telephone,Address,City,Country,Zone,Password,ConfirmPassword","Test");
		
		driver.get("http://tasyah.com/");
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
		
		/**
		 *  Loop to Enter User Details Register Page 
		 *  Update the UserDetails in Excel File - Below ForEach Loop will 
		 *  register the number of excel records
		 *  
		 *  */
		
			for(int rows = 0; rows < excelData.size() ;rows++ ){
				Map<String , String> row = excelData.get(rows);
				try {
					
					homepage.hoverLogin();
					homepage.clickLoginOrRegister();
					homepage.clickRegister();
					
					registerpage.enterFirstName(row.get("FirstName"));
					registerpage.enterLastName(row.get("LastName"));
					registerpage.enterEmail(row.get("Email"));
					registerpage.enterTelephone(row.get("Telephone"));
					registerpage.enterAddress(row.get("Address"));
					registerpage.enterCity(row.get("City"));
					registerpage.enterCountry(row.get("Country"));
					registerpage.enterZone(row.get("Zone"));
					registerpage.enterPassword(row.get("Password"));
					registerpage.confirmPassword(row.get("ConfirmPassword"));
					
					registerpage.tickAcceptConditions();
					registerpage.clickRegister();
					
					homepage.hoverLogin();
					utility.takeScreenShots(driver, System.getProperty("user.dir")+""+File.separator+"ScreenShots"+""+File.separator+""+(rows+1)+"User.png");
					homepage.logout();
					
				}catch(Exception e) {
					System.out.println("Possible Errors : \nDatas/Emails Already Registered,Hence current row is Skipped");
					System.out.println("Possible Solutions : Try enter new Datas/Emails\ncheckout "+(rows+1)+"User.png"+" ScreenShots for Clarification");
					driver.get("http://tasyah.com/");
				}
			}
				driver.quit();	
	}
}
