package com.askomdch.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {
    WebDriver driver;

    By productName = By.cssSelector(".product-detail .name");
    By productPrice = By.cssSelector(".product-detail .price");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getName() {
        return driver.findElement(productName).getText();
    }

    public String getPrice() {
        return driver.findElement(productPrice).getText();
    }
}
