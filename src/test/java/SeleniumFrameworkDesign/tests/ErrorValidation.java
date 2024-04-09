package SeleniumFrameworkDesign.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import SeleniumFrameworkDesign.testComponents.BaseTest;
import ShoppingCart.pageobjects.LandingPage;

public class ErrorValidation extends BaseTest{

		@Test
		public void ErrorValidationTests() throws IOException
		{
			
			landingPage.LoginApplication("crazyadmin@gmail.com", "Icecrm@1");
			Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		}
		
		@Test(retryAnalyzer=SeleniumFrameworkDesign.testComponents.Retry.class)
		public void productErrorValidation()
		{
			LandingPage landingPage = new LandingPage(driver);
			landingPage.Goto();
			landingPage.LoginApplication("crazyadmin@gmai.com", "Icecream@123");
			
			String productName = "ZARA COAT 3";
			
			ProductCatalogue productcatalogue = new ProductCatalogue(driver);

			List<WebElement> products =  productcatalogue.getProductList();

			WebElement prod = productcatalogue.getProductByName(productName);
			
			productcatalogue.addItemToCart(productName);
			
			productcatalogue.goToCart();
			
			CartPage cartPage = new CartPage(driver);
			List<WebElement> cartProducts = cartPage.cartProducts();
			
			Boolean match = cartPage.matchProduct("ZARA COAT 33");;
			Assert.assertFalse(match);
		}

}
