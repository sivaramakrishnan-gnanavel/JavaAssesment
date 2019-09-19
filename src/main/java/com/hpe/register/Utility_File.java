/**
 * 
 */
package com.hpe.register;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author sivaramakrishnan
 * @category Utility
 */
public class Utility_File {
	/**
	 * Connect to the WebDriver Component
	 * 
	 */
	public WebDriver establishConnection() {	
		System.setProperty("webdriver.chrome.driver", 
					"C:\\Users\\gnanaves\\Downloads\\Driver\\chromedriver_win32\\chromedriver.exe");
		return new ChromeDriver();
		}
	/**
	 * Takes Screenshots and store it in a File
	 * 
	 */
	public void takeScreenShots(WebDriver driver, String ss) {
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
		FileUtils.copyFile(src, new File(ss));
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}	
	}
	/**
	 * Get the ExcelFiles as WorkBook
	 * 
	 */
	public XSSFWorkbook getExcelFile(File file){
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			return wb;
		} catch (FileNotFoundException e) {
			System.out.println("file not found in the directory");
		} catch (IOException e) {
			System.out.println("exception occured while reading from excel");
		}
		return null;
	}
	/**
	 * Get the Excel Data's as List 
	 * 
	 */
	public List<Map<String, String>> getExcelList(XSSFWorkbook wb,String headerData,String SheetName){		
		  List<Map<String,String>> tempList = new ArrayList<Map<String,String>>();
		  Map<Integer,String> header = new HashMap<Integer,String>();
		  String[] arg =headerData.split(",");	
		  for(int i=0;i<arg.length; i++){
			 if(!arg[i].equals(""))
			   header.put(i, arg[i]);
		  }
		  for( Row row : wb.getSheet(SheetName.trim()) ) {
			  Map<String,String>  tempMap = new HashMap<String,String>();
			   for(Cell cell :row){
				   if(header.get(cell.getColumnIndex()) != null){
			   		   switch( cell.getCellType()) {
		                case Cell.CELL_TYPE_STRING :
		                    tempMap.put( header.get(cell.getColumnIndex()),cell.getRichStringCellValue().getString());
		                    break;
		                case Cell.CELL_TYPE_NUMERIC :
		                    if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell))
		                    tempMap.put(  header.get(cell.getColumnIndex()),cell.getDateCellValue().toString());
		                    else
		                    	tempMap.put(  header.get(cell.getColumnIndex()),Integer.toString((int)cell.getNumericCellValue()));
		                    break;
		                case Cell.CELL_TYPE_FORMULA :
		                		tempMap.put(  header.get(cell.getColumnIndex()),cell.getCellFormula());
		                		break;
			   		   }
				   }
			   }
			   tempList.add(tempMap);
		  }
		  if(tempList.size() > 0){
		  	tempList.remove(0);
		  } 
		  return tempList;
		}
}
