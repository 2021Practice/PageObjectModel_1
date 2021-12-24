package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.extentListeners.ExtentListeners;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath = "//*[@id=\"root\"]/div[1]/header/div/nav/ul/li[1]/div")
	WebElement newCarsMenu;

	@FindBy(xpath = "//div[contains(text(),'Find New Cars')]")
	WebElement findNewCar;

	public NewCarPage goToFindNewCar() {
		ExtentListeners.test.log(Status.INFO, "Moving to "+newCarsMenu.getText());
		new Actions(driver).moveToElement(newCarsMenu).perform();
		
		ExtentListeners.test.log(Status.INFO, "Clicking on "+findNewCar.getText());
		findNewCar.click();
		
		return new NewCarPage(driver);
	}

	@FindBy(xpath = "//div[contains(text(),'New Car Loan')]")
	WebElement newCarLoan;

	public void goToNewCarLoan() {
		ExtentListeners.test.log(Status.INFO, "Clicking on "+newCarLoan.getText());
		newCarLoan.click();
		
	}

	@FindBy(xpath = "//div[contains(text(),'Compare Cars')]")
	WebElement compareCars;

	public void goToComapreCars() {
		ExtentListeners.test.log(Status.INFO, "Clicking on "+compareCars.getText());
		compareCars.click();
	}

	@FindBy(xpath = "")
	WebElement usedCar;

	public void goToUsedCar() {
		ExtentListeners.test.log(Status.INFO, "Moving to "+usedCar.getText());
		new Actions(driver).moveToElement(usedCar).perform();
	}

}
