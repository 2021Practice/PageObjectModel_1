package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.extentListeners.ExtentListeners;

public class NewCarPage extends BasePage {

	public NewCarPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[@title='Toyota']")
	WebElement toyota;
	
	public ToyotaCarPage gotoToyota() {
		ExtentListeners.test.log(Status.INFO,"Clicking on "+toyota.getText());
		toyota.click();
		return new ToyotaCarPage(driver);
	}

	@FindBy(xpath="(//a[@title='Tata'])[1]")
	WebElement tata;
	
	public TataCarPage gotoTata() {
		ExtentListeners.test.log(Status.INFO,"Clicking on "+tata.getText());
		tata.click();
		return new TataCarPage(driver);
	}

	@FindBy(xpath="//a[@title='Kia']")
	WebElement kia;
	
	public KiaCarPage gotoKia() {
		ExtentListeners.test.log(Status.INFO,"Clicking on "+kia.getText());
		kia.click();
		return new KiaCarPage(driver);
	}
}
