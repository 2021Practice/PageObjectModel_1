package com.base;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.extentListeners.ExtentListeners;

public class BaseCar {

	WebDriver driver;
	
	BaseCar(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id=\"root\"]/div[2]/div/div[2]/div/h1")
	WebElement cartype;
	
	public String getCarTitle() {
		ExtentListeners.test.log(Status.INFO, "Validating Brand of Cars: "+cartype.getText());
		return cartype.getText();
	}
	
	
	@FindBy(xpath="//div/div/div/a/h3")
	List<WebElement> carName;
	
	@FindBy(xpath="//li/div/div/div[1]/div[2]/span[1]")
	List<WebElement> carprice;
	
	public void printCars() {
		
		ExtentListeners.test.log(Status.INFO, "Received list of "+ carName.size()+" car names and "+carprice.size()+" car prices" );
				
		for(int i=0; i<carprice.size(); i++) {
			ExtentListeners.test.log(Status.INFO, "Car Name is: "+carName.get(i).getText()+" and price is: "+carprice.get(i).getText() );
			System.out.println("Car Name is: "+carName.get(i).getText()+" and price is: "+carprice.get(i).getText());
					
		}
		
		
	}
}
