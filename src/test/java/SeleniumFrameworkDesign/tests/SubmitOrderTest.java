package SeleniumFrameworkDesign.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.testComponents.BaseTest;
import ShoppingCart.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData")
	public void submitOrderTest(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		landingPage.LoginApplication(input.get("email"), input.get("password"));
		
		String countryName = "India";
		
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);

		List<WebElement> products =  productcatalogue.getProductList();

		WebElement prod = productcatalogue.getProductByName(productName);
		
		productcatalogue.addItemToCart(productName);
		
		productcatalogue.goToCart();
		
		CartPage cartPage = new CartPage(driver);
		List<WebElement> cartProducts = cartPage.cartProducts();
		
		Boolean match = cartPage.matchProduct(productName);;
		Assert.assertTrue(match);
		
		cartPage.matchProduct(productName);
		
		
		
		CheckoutPage checkoutPage = cartPage.submit();
		
		checkoutPage.goToCheckout(countryName);
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();

		String confirmMessage = confirmationPage.getMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
		
	}
	
	@Test(dependsOnMethods={"submitOrderTest"})
	public void OrderHistory()
	{
//		landingPage.LoginApplication("crazyadmin@gmail.com", "Icecream@123");
		OrderPage orderPage = new OrderPage(driver);
		orderPage.goToOredersPage();
		Assert.assertTrue(orderPage.matchOrder(productName));
	}
	

//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"crazyadmin@gmail.com", "Icecream@123"}};
//	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "crazyadmin@gmail.com");
//		map.put("password", "Icecream@123");
//		
//		return new Object[][] {{map}};
		
		
		List<HashMap<String, String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumFrameworkDesign\\Data\\PurchaseOrder.json");

		return new Object[][] {{data.get(0)}};
	}
}
