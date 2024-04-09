package SeleniumFrameworkDesign.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ShoppingCart.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent
{
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement click;
	
	@FindBy(xpath="//a[contains(@class,'action__submit')]")
	WebElement placeorder;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement clickelement;
	
	By result= By.cssSelector(".ta-results");
	
	public void goToCheckout(String countryName)
	{
		Actions action = new Actions(driver);
		action.sendKeys(country, countryName).build().perform();
		
		waitForElementToAppear(result);
//		waitForElementToClick(clickelement);
//		
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", click);
//		click.click();
		
	}
	public ConfirmationPage placeOrder()
	{
		JavascriptExecutor js1 = (JavascriptExecutor) driver;

		js1.executeScript("arguments[0].click();", placeorder);
//		placeorder.click();
		return new ConfirmationPage(driver);
	}
	
}
