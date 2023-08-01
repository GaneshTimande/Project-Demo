package com.qa.testcases;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.pages.Landingpage;
import com.qa.pages.LoginPage;



import com.qa.utilities.Base;
import com.qa.utilities.Utilities;
import com.util.JiraPolicy;


public class LoginPageTest extends Base{
   
    LoginPage loginpage;
    Landingpage landingpage;
    
    public LoginPageTest(){
        super();
    }
    
    public WebDriver driver;
    @BeforeClass
    public void setup() 
    {
        
    driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
 
    }
    

    @JiraPolicy(logTicketReady=true)
    @Test(priority=1, dataProvider="excelData",enabled=true)
    public void loginTest(String Email, String Password) throws InterruptedException{
        
    	loginpage = new LoginPage();
        landingpage=loginpage.login(Email, Password); 
        
    
       
    }
    @DataProvider(name = "excelData")
	public Object[][] supplyTestData() {
		
		Object[][] data =  Utilities.getTestDataFromExcel("Login");
		return data;
	}
    
    
    
   
   
    @AfterClass
    public void tearDown() 
    {
        
        driver.quit();
        
    }

}