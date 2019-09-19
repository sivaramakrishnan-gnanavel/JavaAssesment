/**
 * 
 */
package com.hpe.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * @author sivaramakrishnan gnanavel
 * @category Register POM
 */
public class Register_Page {
	WebDriver driver;
	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By email = By.id("input-email");
	By telephone = By.id("input-telephone"); 
	By address_1 = By.id("input-address-1");
	By city = By.id("input-city");
	By country = By.id("input-country");
	By zone = By.id("input-zone");
	By password = By.id("input-password");
	By confirmPassword = By.id("input-confirm");
	By acceptTermsAndConditions = By.xpath("//*[@id=\"System_nyHsmShk\"]/form/div/div[1]/label/input");
	By register = By.xpath("//*[@id=\"System_nyHsmShk\"]/form/div/div[2]/input");
	
	public Register_Page(WebDriver driver) {
		this.driver = driver;
	}
	public void enterFirstName(String fname) {
		driver.findElement(firstName).sendKeys(fname);
	}
	public void enterLastName(String lname) {
		driver.findElement(lastName).sendKeys(lname);	
		}
	public void enterEmail(String mail) {
		driver.findElement(email).sendKeys(mail);
	}
	public void enterTelephone(String telph) {
		driver.findElement(telephone).sendKeys(telph);
	}
	public void enterAddress(String addr) {
		driver.findElement(address_1).sendKeys(addr);
	}
	public void enterCity(String ct) {
		driver.findElement(city).sendKeys(ct);
	}
	
	public void enterCountry(String con) {
		Select dropdown = new Select(driver.findElement(country));
		dropdown.selectByVisibleText(con);
	}
	public void enterZone(String zn) {
		Select dropdown = new Select(driver.findElement(zone));
		dropdown.selectByVisibleText(zn);
	}
	public void enterPassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}
	public void confirmPassword(String cpwd) {
		driver.findElement(confirmPassword).sendKeys(cpwd);
	}
	public void tickAcceptConditions() {
		driver.findElement(acceptTermsAndConditions).click();
	}
	public void clickRegister() {
		driver.findElement(register).click();
	}
}
