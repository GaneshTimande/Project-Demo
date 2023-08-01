package com.qa.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.utilities.Base;



public class LoginPage2  extends Base{

	  

	@FindBy(xpath="//input[@name='emailId']") private WebElement email;
    
    @FindBy(xpath="//div[@fxflex='initial']//app-user-validation//form//button") private WebElement continuebutton;
    
    @FindBy(xpath="//input[@name='password']") private WebElement password;
    
    @FindBy(xpath="//form[@autocomplete='off']//button") private WebElement login;////1
    
    
   
	//Initializing the Page Objects:
	public LoginPage2(){
		PageFactory.initElements(driver, this);
	}

	//Actions:
	/*public String validateLandingPageTitle1() {
		// TODO Auto-generated method stub
		return driver.getTitle();

	}*/


    public  void login1(String Email, String Password) throws InterruptedException
    {
    	email.sendKeys(Email);
 	    Thread.sleep(2000);
 	    
 	   continuebutton.click();
 	    Thread.sleep(4000);
 	   password.sendKeys(Password);
	    Thread.sleep(2000);
	    login.click();
 
 	 
 	driver.findElement(By.xpath("//span[normalize-space()='View all apps']")).click();
   Thread.sleep(2000);
	
	String parent=driver.getWindowHandle();

	Set<String>s=driver.getWindowHandles();

	// Now iterate using Iterator
	Iterator<String> I1= s.iterator();

	while(I1.hasNext())
	{

		String child_window=I1.next();


	if(!parent.equals(child_window))
	{
		driver.switchTo().window(child_window);

			System.out.println(driver.switchTo().window(child_window).getTitle());
WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search filter']"));

			search.sendKeys("Vahana Automation test");
			search.sendKeys(Keys.ENTER);
			System.out.println("h");
		//	Actions act = new Actions(driver);

			//Locate WebElement to perform double click 
			WebElement btnElement = driver.findElement(By.xpath("//span[normalize-space()='Ganesh Timande']"));
			Actions act = new Actions(driver);
			//Double Click the button
			act.doubleClick(btnElement).perform();
			System.out.println("s");
		}
	}
	//return new Landingpage();

		

		
	}

	}





