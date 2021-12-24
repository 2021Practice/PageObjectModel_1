package com.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.base.BaseTest;
import com.pages.HomePage;
import com.pages.NewCarPage;
import com.utilities.DataProviders;

public class FindNewCarTest extends BaseTest {

	@Test(dataProviderClass=DataProviders.class, dataProvider="dp")
	public void findNewCarTest(Hashtable<String, String> data) {
		
		if(data.get("RunMode").equalsIgnoreCase("N")){
			throw new SkipException("Skipping testcase as runmode is NO");
		}
		
		System.out.println(data.get("browser"));
		setUp(data.get("browser"));
		
		HomePage page = new HomePage(driver);
		System.out.println(driver.getTitle());
		
		NewCarPage nc =page.goToFindNewCar();
		
		if(data.get("Brand").equalsIgnoreCase("tata")) {
			nc.gotoTata();
		}else if(data.get("Brand").equalsIgnoreCase("kia")) {
			nc.gotoKia();
		}else if(data.get("Brand").equalsIgnoreCase("toyota")) {
			nc.gotoToyota();
		}
		
		System.out.println(BasePage.baseCar.getCarTitle());
		Assert.assertEquals(BasePage.baseCar.getCarTitle(), data.get("expectedTitle"), "Title did not match");	
		
		BasePage.baseCar.printCars();
		
		
		
//		
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
