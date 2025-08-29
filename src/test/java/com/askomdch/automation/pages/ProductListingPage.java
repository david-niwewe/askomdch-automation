package com.askomdch.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductListingPage {
    WebDriver driver;

    By productCards = By.xpath("//*[@id='main']/div/ul/li");

    By productImage = By.xpath(".//div[1]/a/img");
    By productName  = By.xpath(".//div[2]/a/h2");
    By productPrice = By.cssSelector(".//div[2]/span");
    By saleLabel    = By.cssSelector(".sale-label");

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
