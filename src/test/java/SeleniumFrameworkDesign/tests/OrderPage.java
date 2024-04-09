package SeleniumFrameworkDesign.tests;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShoppingCart.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent
{

		WebDriver driver;
		public OrderPage(WebDriver driver)
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(css="tr td:nth-child(3)")
		List<WebElement> yourOrders;

		
		public List<WebElement> OrderList()
		{
			return yourOrders;
		}
		
		public Boolean matchOrder(String orderName)
		{
			Boolean match =OrderList().stream().anyMatch(OrderList->OrderList.getText().equals(orderName));
			return match;
		}

}
