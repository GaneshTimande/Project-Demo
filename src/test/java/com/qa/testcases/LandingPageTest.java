package com.qa.testcases;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.pages.Apppage;
import com.qa.pages.Landingpage;
import com.qa.pages.LoginPage;



import com.qa.utilities.Base;
import com.qa.utilities.Utilities;
import com.util.JiraPolicy;


public class LandingPageTest extends Base{
   
    LoginPage loginpage;
    Landingpage landingpage;
    Apppage     apppage ; 
    public LandingPageTest(){
        super();
    }
    
    public WebDriver driver;
    @BeforeClass
    public void setup() throws InterruptedException 
    {
        
    driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
    loginpage = new LoginPage();
    landingpage=loginpage.login(prop.getProperty("Email"), prop.getProperty("Password"));
   
    }
    
    @Test(priority=1)
    public void Apptest() throws InterruptedException {
    	 apppage= landingpage.Createapp();
    }
    
    
    
    
@Test(priority=2)
    public void verifyTitle() {
    Assert.assertTrue(landingpage.verifyApplicationLabel(), "Application");
}
   
   
@Test(priority=2)
public void gotovesigner() {
	landingpage.gotovesigner();
}


//    @AfterClass
//    public void tearDown() 
//    {
//        
//        driver.quit();
        
    
}