package com.base;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BaseCar {

	WebDriver driver;
	
	BaseCar(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id=\"root\"]/div[2]/div/div[2]/div/h1")
	WebElement cartype;
	
	public String getCarTitle() {
			return cartype.getText();
	}
	
	
	@FindBy(xpath="//div/div/div/a/h3")
	List<WebElement> carName;
	
	@FindBy(xpath="//li/div/div/div[1]/div[2]/span[1]")
	List<WebElement> carprice;
	
	public void printCars() {
		
		for(int i=0; i<carprice.size(); i++) {
			
			System.out.println("Car Name is: "+carName.get(i).getText()+" and price is: "+carprice.get(i).getText());
					
		}
		
		
	}
}
