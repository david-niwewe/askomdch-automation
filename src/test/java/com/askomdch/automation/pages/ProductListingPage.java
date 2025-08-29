package com.askomdch.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductListingPage {
    WebDriver driver;

    By productCards = By.cssSelector(".product-card");
    By productImage = By.cssSelector(".product-card img");
    By productName  = By.cssSelector(".product-card .name");
    By productPrice = By.cssSelector(".product-card .price");
    By saleLabel    = By.cssSelector(".product-card .sale-label");

    public ProductListingPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getProducts() {
        return driver.findElements(productCards);
    }

    public boolean isImageDisplayed(WebElement product) {
        return product.findElement(productImage).isDisplayed();
    }

    public String getProductName(WebElement product) {
        return product.findElement(productName).getText();
    }

    public String getProductPrice(WebElement product) {
        return product.findElement(productPrice).getText();
    }

    public boolean hasSaleLabel(WebElement product) {
        return !product.findElements(saleLabel).isEmpty();
    }
}
