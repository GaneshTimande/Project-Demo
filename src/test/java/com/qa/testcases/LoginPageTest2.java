package com.qa.testcases;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.pages.Landingpage;
import com.qa.pages.LoginPage;
import com.qa.pages.LoginPage2;
import com.qa.utilities.Base;
import com.qa.utilities.Utilities;
import com.util.JiraPolicy;


public class LoginPageTest2 extends Base{
   
    LoginPage2 loginpage2;
    Landingpage landingpage;
    
    public LoginPageTest2(){
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
        
    	loginpage2 = new LoginPage2();
    loginpage2.login1(Email, Password); 
        
    
       
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