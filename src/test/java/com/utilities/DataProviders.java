package com.utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.base.BaseTest;

public class DataProviders extends BaseTest{


	@DataProvider(name="dp")
	public Object[][] getData(Method m) {
		
		String sheetName = m.getName();
		
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		System.out.println("In sheet "+sheetName+" rows are: "+rows +" and cols are: "+cols);
		
				
		Object[][] data = new Object[rows-1][1];
		
		Hashtable<String, String> table=null;
		
		for(int i=1; i<rows;i++) {
			table= new Hashtable<String, String>();
			for (int j=0; j<cols;j++) {
			//	System.out.println(excel.getCellData(sheetName, j, 0));
			//	System.out.println(excel.getCellData(sheetName, j, i));
				table.put(excel.getCellData(sheetName, j, 0), excel.getCellData(sheetName, j, i));
				data[i-1][0] = table; 
			}
		}
				
		return data;
		
	}
	
//	@DataProvider(name="dp")
//	public Object[][] getData(Method m) {
//		
//		String sheetName = m.getName();
//		
//		int rows = excel.getRowCount(sheetName);
//		int cols = excel.getColumnCount(sheetName);
//		
//		System.out.println("In sheet "+sheetName+" rows are: "+rows +" and cols are: "+cols);
//		
//				
//		Object[][] data = new Object[rows-1][cols];
//		
//		for(int i=1; i<rows;i++) {
//			for (int j=0; j<cols;j++) {
//				data[i-1][j] = excel.getCellData(sheetName, j, i);
//			}
//		}
//				
//		return data;
//		
//	}
	
}
