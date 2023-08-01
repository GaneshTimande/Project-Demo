package com.qa.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.utilities.Base;

public class Landingpage extends Base { 


	@FindBy(xpath="//span[normalize-space()='View all apps']") private WebElement viewapp;

	@FindBy(xpath="//input[@placeholder='Search filter']") private WebElement searchapp;

	@FindBy(xpath="//input[@name='password']") private WebElement password;

	@FindBy(xpath="//form[@autocomplete='off']//button") private WebElement login;////1

	@FindBy(xpath="//h2[normalize-space()='Applications']") private WebElement VahanaHome;////1

	@FindBy(xpath="//input[@placeholder='Search filter']") private WebElement searchapp1;
	
	 @FindBy(xpath="/html[1]/body[1]/vcp-app[1]/vcp-outlet[1]/div[1]/div[1]/div[1]/vcp-application-development[1]/vcp-develop[1]/div[1]/perfect-scrollbar[1]/div[1]/div[1]/div[1]/section[1]/vcp-develop-thumbnail-view[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]") private WebElement testapp;////1




	//Initializing the Page Objects:
	public Landingpage(){
		PageFactory.initElements(driver, this);
	}


	public Boolean verifyApplicationLabel() {
		// TODO Auto-generated method stub
		return VahanaHome.isDisplayed();

	}


	public  Apppage Createapp() throws InterruptedException{

		viewapp.click();

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


				searchapp1=driver.findElement(By.xpath(""));
				searchapp1.sendKeys("Vahana Automation test");
				searchapp1.sendKeys(Keys.ENTER);
				
			Thread.sleep(3000);




			}




		}
		return new Apppage();

	
	
	}
	

	public void gotovesigner() {
		WebElement dynamicElement = (new WebDriverWait(driver, 20))
			    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@fxlayout='row']")));
Actions act= new Actions(driver);
		//Double click on element
RemoteWebElement ele = (RemoteWebElement) driver.findElement(By.xpath("//div[@class='card-content']//div[@fxlayout='row']")); 
		
		act.doubleClick(ele).build().perform();
		System.out.println("d");
	}
}



