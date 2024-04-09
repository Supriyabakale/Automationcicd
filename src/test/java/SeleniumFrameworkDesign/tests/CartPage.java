package SeleniumFrameworkDesign.tests;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage 
{

	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//li[@class='totalRow'][3]/button")
	WebElement Submit;
	
	public List<WebElement> cartProducts()
	{
		return cartProducts;
	}
	
	public Boolean matchProduct(String productName)
	{
		Boolean match =cartProducts().stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
		return match;
	}
	
	public CheckoutPage submit()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", Submit);
		return new CheckoutPage(driver);
	}
}
