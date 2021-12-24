package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.base.BasePage;

public class NewCarPage extends BasePage {

	public NewCarPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[@title='Toyota']")
	WebElement toyota;
	
	public ToyotaCarPage gotoToyota() {
		toyota.click();
		return new ToyotaCarPage(driver);
	}

	@FindBy(xpath="(//a[@title='Tata'])[1]")
	WebElement tata;
	
	public TataCarPage gotoTata() {
		tata.click();
		return new TataCarPage(driver);
	}

	@FindBy(xpath="//a[@title='Kia']")
	WebElement kia;
	
	public KiaCarPage gotoKia() {
		kia.click();
		return new KiaCarPage(driver);
	}
}
