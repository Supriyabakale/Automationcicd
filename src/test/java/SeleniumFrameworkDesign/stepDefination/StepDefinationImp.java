package SeleniumFrameworkDesign.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFrameworkDesign.testComponents.BaseTest;
import SeleniumFrameworkDesign.tests.CartPage;
import SeleniumFrameworkDesign.tests.CheckoutPage;
import SeleniumFrameworkDesign.tests.ConfirmationPage;
import SeleniumFrameworkDesign.tests.ProductCatalogue;
import ShoppingCart.pageobjects.LandingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImp extends BaseTest
{
	public LandingPage landingPage;
	public ProductCatalogue productcatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username,String password)
	{
		landingPage.LoginApplication(username,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName)
	{
		productcatalogue = new ProductCatalogue(driver);
		List<WebElement> products =  productcatalogue.getProductList();
		WebElement prod = productcatalogue.getProductByName(productName);
		productcatalogue.addItemToCart(productName);
		productcatalogue.goToCart();
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName)
	{
		
		CartPage cartPage = new CartPage(driver);
		List<WebElement> cartProducts = cartPage.cartProducts();
		
		Boolean match = cartPage.matchProduct(productName);;
		Assert.assertTrue(match);
		
		cartPage.matchProduct(productName);
		
		CheckoutPage checkoutPage = cartPage.submit();
		
		checkoutPage.goToCheckout("India");
		confirmationPage = checkoutPage.placeOrder();
	}
	
	@Then("{string} message displayed on confirmation")
	public void Message_displayed_on_confirmation(String string)
	{
		String confirmMessage = confirmationPage.getMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));	
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void Message_is_displayed(String string1)
	{
		Assert.assertEquals(string1, landingPage.getErrorMessage());
		driver.close();
	}
	
}
