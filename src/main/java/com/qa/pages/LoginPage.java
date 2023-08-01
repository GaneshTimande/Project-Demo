package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.utilities.Base;



public class LoginPage  extends Base{



	@FindBy(xpath="//input[@name='emailId']") private WebElement email;
    
    @FindBy(xpath="//div[@fxflex='initial']//app-user-validation//form//button") private WebElement continuebutton;
    
    @FindBy(xpath="//input[@name='password']") private WebElement password;
    
    @FindBy(xpath="//form[@autocomplete='off']//button") private WebElement login;////1
    
    
   
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}

	//Actions:
	/*public String validateLandingPageTitle1() {
		// TODO Auto-generated method stub
		return driver.getTitle();

	}*/


    public  Landingpage login(String Email, String Password) throws InterruptedException
    {
    	email.sendKeys(Email);
 	    Thread.sleep(2000);
 	    
 	   continuebutton.click();
 	    Thread.sleep(4000);
 	   password.sendKeys(Password);
	    Thread.sleep(2000);
	    login.click();
 	  
 	    
	return new Landingpage();

		

		
	}

	

}



