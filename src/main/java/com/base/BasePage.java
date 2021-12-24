package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	public static WebDriver driver;
	public static BaseCar baseCar;
	public BasePage(WebDriver driver) {
		
		this.driver = driver;
		baseCar = new BaseCar(driver);
		PageFactory.initElements(driver, this);
	}
	
}
