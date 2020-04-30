package stepDefinitions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.Reporter;

import com.main.optimus.BaseClass;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import pageObjects.HomePage;
import pageObjects.ProductDetailsPage;
import pageObjects.YourCartPage;
import pageObjects.LoginPage;

public class StepDefinition extends BaseClass
{
	@Before 
	public void login() throws IOException, InterruptedException
	{
		prop = initializeProperty();
		driver = setupDriver();
	
		String url = prop.getProperty("url");
		driver.get(url);
		
		driver.manage().window().maximize();
		
		LoginPage lp= new LoginPage(driver);
		// Click on EnterUsing Password
		 lp.getEnterUsingPw().click();
		 
		 // Enter the Password
		 String PassWord=  prop.getProperty("Password");
		 lp.getEnterPw().sendKeys(PassWord);
		 
		 // Click on Submit button
		 lp.getSubmitBtn().click();
		 
		 // Validate the Browser
		 System.out.println(driver.getCurrentUrl());
		 String actaulUrl ="https://ecom-optimus.myshopify.com/";
		 Assert.assertEquals(actaulUrl, driver.getCurrentUrl());
		 Reporter.log("User Logged into application Successfully", true);
		 
	}
	
	@After
	public void tear()
	{
		driver.close();
	}
	
	@Given("^User should be in Home page$")
    public void user_should_be_in_home_page() throws Throwable {
		
		HomePage _HP = new HomePage(driver);
		_HP.getHomeLlink().click();
		
		 System.out.println(driver.getCurrentUrl());
		 String actaulUrl ="https://ecom-optimus.myshopify.com/";
		 Assert.assertEquals(actaulUrl, driver.getCurrentUrl());
		 Reporter.log("User is in Home Page", true);
    }
	
	@Given("^User search for Product \"([^\"]*)\"$")
	public void user_search_for_Product(String SearchText) throws Throwable {
		HomePage _HP = new HomePage(driver);
		//  User clicks on the Search icon
		_HP.getSearchBtn().click(); 
		// Enter the Search text
		_HP.getSearchEnter().sendKeys(SearchText);
		// Select the product
		try
		{
			_HP.getProductResult().get(0).click();
			 Reporter.log("User searched product Successfully", true);
		}
		catch (StaleElementReferenceException e)
		{
			System.out.println("Captured "+e+" Exception");
			
		}
		
	}

	@When("^User add the product to the Cart$")
	public void user_add_the_product_to_the_Cart() throws Throwable {
		 ProductDetailsPage _PDP= new ProductDetailsPage(driver);
		 // Click on Add to Cart button
		 JavascriptExecutor js = (JavascriptExecutor)driver;
	     js.executeScript("arguments[0].click();", _PDP.getAddToCartBtn());
		 // Click on Close on Pop up
	     _PDP.getClosePopUp().click();      
	}

	@And("^user clicks on cart icon$")
	public void user_clicks_on_cart_icon() throws Throwable {
		HomePage _HP = new HomePage(driver);
		// Cart count should be incremented by 1
		Reporter.log("Cart count is: "+ _HP.getCartCount().getText(), true);
		Reporter.log("User added " + _HP.getCartCount().getText() + " product to the cart Successfully", true); 
		// Click on cart icon
		_HP.getCartBtn().click();
	}

	@Then("^User should navigate to Your cart Page$")
	public void user_should_navigate_to_Your_cart_Page() throws Throwable {
	   YourCartPage _YCP = new YourCartPage(driver);
	   // get the Title of the page
	   Reporter.log("Title of the page: "+ _YCP.getYourCartTitle().getText(), true);
	}

	@Then("^Added product should be available in Your cart page$")
	public void added_product_should_be_available_in_Your_cart_page() throws Throwable {
		YourCartPage _YCP = new YourCartPage(driver);
		// Get  the Product name and details added
		List<WebElement> rows= _YCP.getRows();
    	for(int i=0;i<rows.size();i++)
    	{
    		 Reporter.log("Added Product Details: "+ _YCP.getProductDetails().get(i).getText(), true);
    		 Reporter.log("Product Price is: "+_YCP.getProductPrice().get(i).getText(), true);
    		 Reporter.log("Product Quantity: "+_YCP.getProductQty().get(i).getAttribute("value"), true);
    		 Reporter.log("Product Total is: "+_YCP.getProductTotal().get(i).getText(), true);
    	}
    	Reporter.log("Sub Total is: "+_YCP.getSubTotal().getText(), true);
	}

	@Given("^User navigate to Featured Collection$")
	public void user_navigate_to_Featured_Collection() throws Throwable {
	   // User navigates to Feature collection
		HomePage _HP = new HomePage(driver);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].scrollIntoView();", _HP.getFeaturedCollectionTitle());
	}

    @And("^User clicks on the \"([^\"]*)\" product$")
    public void user_clicks_on_the_something_product(String product) throws Throwable
    {
    	int num = Integer.parseInt(product);
    	// selects the first product
    	HomePage _HP = new HomePage(driver);
    	_HP.getFeaturedCollectionProducts().get(num-1).click();
    	
    }

	@When("^increase the quantity by \"([^\"]*)\"$")
	public void increase_the_quantity_by(String qty) throws Throwable {
		double productTotal=0;
		double productPrice=0;
		
		YourCartPage _YCP = new YourCartPage(driver);
		// Get the product details
		List<WebElement> rows= _YCP.getRows();
    	for(int i=0;i<rows.size();i++)
    	{
    		 Reporter.log("Added Product Details: "+ _YCP.getProductDetails().get(i).getText(), true);
    		 Reporter.log("Product Price is: "+_YCP.getProductPrice().get(i).getText(), true);
    		 Reporter.log("Product Quantity: "+_YCP.getProductQty().get(i).getAttribute("value"), true);
    		 Reporter.log("Product Total is: "+_YCP.getProductTotal().get(i).getText(), true);
    		
    		int _qty = Integer. parseInt(qty);
    		
     		String sProductPrice = _YCP.getProductPrice().get(i).getText();
     		sProductPrice= sProductPrice.substring(4);
     		productPrice= DecimalFormat.getNumberInstance().parse(sProductPrice).doubleValue();
     		
     		productTotal = productPrice*_qty;
    		String sTotal = String.valueOf(productTotal);
    		
    		// Increase the product quantity
    		_YCP.getProductQty().get(i).sendKeys(Keys.CONTROL,"a",Keys.DELETE);
    		_YCP.getProductQty().get(i).sendKeys(qty,Keys.TAB);
    	
    		Thread.sleep(3000);
    		
    		String sProductTotal = _YCP.getProductTotal().get(i).getText();
     		sProductTotal= sProductTotal.substring(4);
     		double dProductTotal= DecimalFormat.getNumberInstance().parse(sProductTotal).doubleValue();
    
    		Reporter.log("Expected Product Total is: "+sTotal, true);	
    		Assert.assertEquals(dProductTotal, productTotal);
    	}
	}

	 @Then("^User should see the change in the quantity$")
	 public void user_should_see_the_change_in_the_quantity() throws Throwable {
		YourCartPage _YCP = new YourCartPage(driver);
		// Get the product details
		List<WebElement> rows= _YCP.getRows();
		for(int i=0;i<rows.size();i++)
    	{
			 Reporter.log("----------------------------------------------------", true);
			 Reporter.log("Increased Product Quantity: "+_YCP.getProductQty().get(i).getAttribute("value"), true);
    	}
    }

	@Then("^Product Total and Subtotal should match$")
	public void product_Total_and_Subtotal_should_match() throws Throwable {
		YourCartPage _YCP = new YourCartPage(driver);
		// Get the product details
		List<WebElement> rows= _YCP.getRows();
		for(int i=0;i<rows.size();i++)
		{
			// Changed Product total and Sub Total
			Reporter.log("Changed Product Total: "+ _YCP.getProductTotal().get(i).getText(), true);
			Reporter.log("Sub Total is: "+_YCP.getSubTotal().getText(), true);
			Assert.assertEquals(_YCP.getProductTotal().get(i).getText(), _YCP.getSubTotal().getText());
			Reporter.log("Product Total and Sub Total Matched ", true);
		}
	}

	@When("^user clicks on Remove link$")
	public void user_clicks_on_Remove_link() throws Throwable {
		YourCartPage _YCP = new YourCartPage(driver);
		List<WebElement> remove = _YCP.getRemoveLink();
		for (int i=0;i<remove.size();i++)
		{
			remove.get(i).click();
		}
		Reporter.log("Clicked on Remove link successfully ", true);
	}

	@Then("^Product should be deleted from the Cart$")
	public void product_should_be_deleted_from_the_Cart() throws Throwable {
		YourCartPage _YCP = new YourCartPage(driver);
		
		String cartMsg1 = _YCP.getCartEmptyMsg().getText();
		String cartMsg = _YCP.getXpath();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cartMsg)));
		
		Thread.sleep(1000);
		
		Reporter.log(cartMsg1, true);
		Reporter.log("Product deleted successfully from cart ", true);	
	}

	@And("^User clicks on the all the products and added to cart$")
    public void user_clicks_on_the_all_the_products_and_added_to_cart() throws Throwable {
		HomePage _HP = new HomePage(driver);
		ProductDetailsPage _PDP = new ProductDetailsPage(driver);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].scrollIntoView();", _HP.getFeaturedCollectionTitle());
	    
	   List<WebElement> featuredProducts = _HP.getFeaturedCollectionProducts();
	 
	   for(int i=0;i<featuredProducts.size();i++)
	   {
		   js.executeScript("arguments[0].scrollIntoView();", _HP.getFeaturedCollectionTitle());
		   featuredProducts.get(i).click();
		   js.executeScript("arguments[0].click();", _PDP.getAddToCartBtn());
		   _HP.getHomeLlink().click();
	   }
	
	}

	@When("^User select the different sizes from Size drop down$")
	public void user_select_the_different_sizes_from_Size_drop_down() throws Throwable {
		// Select the multiple sizes and add to cart
		ProductDetailsPage _PDP= new ProductDetailsPage(driver);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement sizeDropDown = _PDP.getSizeDropdown();
		
		Select selectSize = new Select(sizeDropDown);
		List<WebElement> size = selectSize.getOptions();
		
		for(int i=1;i<size.size();i++)
		{
			Reporter.log(size.get(i).getText(), true);
			size.get(i).click();
			 // Click on Add to Cart button
			js.executeScript("arguments[0].click();", _PDP.getAddToCartBtn());
			 // Click on Close on Pop up
		     _PDP.getClosePopUp().click(); 
		} 
	}
	
	@And("^User select the different colors from color drop down$")
    public void user_select_the_different_colors_from_color_drop_down() throws Throwable {
		// Select the multiple colors and add to cart
		ProductDetailsPage _PDP= new ProductDetailsPage(driver);
		System.out.println("control in color");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement colorDropDown = _PDP.getColorDropdown();
		
		System.out.println(colorDropDown);
				
		Select selectColor = new Select(colorDropDown);
		List<WebElement> color = selectColor.getOptions();
				
		for(int i=1;i<color.size();i++)
		{
			Reporter.log(color.get(i).getText(), true);
			color.get(i).click();
			if(_PDP.getUnavailableBtn().getText().equalsIgnoreCase("Add to cart"))
			{
				// Click on Add to Cart button
				js.executeScript("arguments[0].click();", _PDP.getAddToCartBtn());
				// Click on Close on Pop up
				_PDP.getClosePopUp().click(); 
			}
			
		} 
    }
}
