package com.askomdch.automation.steps;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;

import com.askomdch.automation.pages.ProductListingPage;
import com.askomdch.automation.hooks.Hooks;
import com.askomdch.automation.pages.ProductDetailsPage;

public class ProductSteps {
    WebDriver driver = Hooks.driver;
    ProductListingPage listingPage = new ProductListingPage(driver);
    ProductDetailsPage detailsPage = new ProductDetailsPage(driver);

    @Given("I am on the product listing page")
    public void openListingPage() {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("BASE_URL");
        driver.get(url+"store");
    }

    @Then("each product must display a product image")
    public void checkProductImages() {
        for (WebElement product : listingPage.getProducts()) {
            assertTrue("Image missing", listingPage.isImageDisplayed(product));
        }
    }

    @Then("each product must display a product name")
    public void checkProductNames() {
        for (WebElement product : listingPage.getProducts()) {
            assertFalse("Product name missing", listingPage.getProductName(product).isEmpty());
        }
    }

    @When("I click on the product image")
    public void clickProductImage() {
        listingPage.getProducts().get(0).click();  // First product
    }

    @Then("the product details page for that product must open")
    public void verifyNavigation() {
        assertTrue(driver.getCurrentUrl().contains("/product/"));
    }
}
