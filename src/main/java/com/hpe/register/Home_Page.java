/**
 * 
 */
package com.hpe.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author sivaramakrishnan gnanavel
 * @category Home POM
 */
public class Home_Page {
	WebDriver driver;
	public Home_Page(WebDriver driver) {
		this.driver = driver;
	}
	public void hoverLogin() {		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Actions action = new Actions(driver);
		WebElement dropDown = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Menu_Wmt3OMY3\"]/nav/ul/li[2]/a")));
		action.moveToElement(dropDown).perform();	
	}
	public void clickLoginOrRegister() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement login = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Menu_Wmt3OMY3\"]/nav/ul/li[2]/ul/li[1]/a")));
		login.click();	
		}
	public void clickRegister() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement register = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"System_nyHsmShk\"]/div/div[1]/div/div[2]/div/a")));
		register.click();
	}
	public void logout() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement logout = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Menu_Wmt3OMY3\"]/nav/ul/li[2]/ul/li[4]/a")));
		logout.click();
	}
}
