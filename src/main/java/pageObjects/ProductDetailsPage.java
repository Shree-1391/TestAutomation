package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.optimus.BaseClass;

public class ProductDetailsPage extends BaseClass
{
	public WebDriver driver;
	
	@FindBy(xpath="//div[@class='product-form__item product-form__item--submit product-form__item--payment-button']/button")	
	private WebElement addToCartBtn;
	
	@FindBy(xpath="//div[@class='product-form__item product-form__item--submit product-form__item--payment-button']/button/span[1]")
	private WebElement unavailableBtn;

	@FindBy(xpath="//div[@class='product-single__meta']/h1")
	private WebElement selectedProductName;
	
	@FindBy(xpath="//*[@id='CartPopupHeading']")
	private WebElement popUpHeading;
	
	@FindBy(xpath="//div[@class='cart-popup-item__title']")
	private WebElement addedProductname;
	
	@FindBy(xpath="//span[@class='price-item price-item--regular']")
	private WebElement addedProductPrice;
	
	@FindBy(xpath="//*[@class='product-details']/li[1]")
	private WebElement addedColor;
	
	@FindBy(xpath="//*[@class='product-details']/li[2]")
	private WebElement addedSize;
	
	@FindBy(xpath="//div[@class='cart-popup__header']/button")
	private WebElement closePopUp;
	
	@FindBy(xpath="//div[@class='cart-popup-item__quantity']/span[3]")
	private WebElement qtyPopup;
	
	@FindBy(xpath="//*[@id='SingleOptionSelector-0']")
	private WebElement colorDropdown;
	
	@FindBy(xpath="//*[@id='SingleOptionSelector-1']")
	private WebElement sizeDropdown;
	
	
	public ProductDetailsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getAddToCartBtn() {
		return addToCartBtn;
	}

	public void setAddToCartBtn(WebElement addToCartBtn) {
		this.addToCartBtn = addToCartBtn;
	}

	public WebElement getSelectedProductName() {
		return selectedProductName;
	}

	public void setSelectedProductName(WebElement selectedProductName) {
		this.selectedProductName = selectedProductName;
	}

	public WebElement getPopUpHeading() {
		return popUpHeading;
	}

	public void setPopUpHeading(WebElement popUpHeading) {
		this.popUpHeading = popUpHeading;
	}
	
	
	public WebElement getAddedProductname() {
		return addedProductname;
	}

	public void setAddedProductname(WebElement addedProductname) {
		this.addedProductname = addedProductname;
	}

	public WebElement getAddedProductPrice() {
		return addedProductPrice;
	}

	public void setAddedProductPrice(WebElement addedProductPrice) {
		this.addedProductPrice = addedProductPrice;
	}

	public WebElement getAddedColor() {
		return addedColor;
	}

	public void setAddedColor(WebElement addedColor) {
		this.addedColor = addedColor;
	}

	public WebElement getAddedSize() {
		return addedSize;
	}

	public void setAddedSize(WebElement addedSize) {
		this.addedSize = addedSize;
	}

	public WebElement getClosePopUp() {
		return closePopUp;
	}

	public void setClosePopUp(WebElement closePopUp) {
		this.closePopUp = closePopUp;
	}

	public WebElement getQtyPopup() {
		return qtyPopup;
	}

	public void setQtyPopup(WebElement qtyPopup) {
		this.qtyPopup = qtyPopup;
	}

	public WebElement getColorDropdown() {
		return colorDropdown;
	}

	public void setColorDropdown(WebElement colorDropdown) {
		this.colorDropdown = colorDropdown;
	}

	public WebElement getSizeDropdown() {
		return sizeDropdown;
	}

	public void setSizeDropdown(WebElement sizeDropdown) {
		this.sizeDropdown = sizeDropdown;
	}

	public WebElement getUnavailableBtn() {
		return unavailableBtn;
	}

	public void setUnavailableBtn(WebElement unavailableBtn) {
		this.unavailableBtn = unavailableBtn;
	}
	
}
