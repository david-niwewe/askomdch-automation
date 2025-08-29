package com.askomdch.automation.steps;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import static org.junit.Assert.*;

import com.askomdch.automation.pages.ProductListingPage;
// import com.askomdch.automation.pages.ProductDetailsPage;
import com.askomdch.automation.hooks.Hooks;
import com.askomdch.automation.utils.LoggerUtil;

import java.util.List;

public class ProductSteps {
    private WebDriver driver = Hooks.driver;
    private ProductListingPage listingPage = new ProductListingPage(driver);
    // private ProductDetailsPage detailsPage = new ProductDetailsPage(driver);

    @Given("I am on the product listing page")
    public void openListingPage() {
        try {
            Dotenv dotenv = Dotenv.load();
            String baseUrl = dotenv.get("BASE_URL");
            if (baseUrl == null || baseUrl.isEmpty()) {
                LoggerUtil.error("BASE_URL is not set in .env file.");
                fail("BASE_URL not defined.");
            }
            driver.get(baseUrl + "store");
            LoggerUtil.info("Navigated to product listing page: " + baseUrl + "store");
        } catch (Exception e) {
            LoggerUtil.exception(e);
            fail("Failed to open product listing page.");
        }
    }

    @Then("each product must display a product image")
    public void checkProductImages() {
        try {
            List<WebElement> products = listingPage.getProducts();
            if (products.isEmpty()) {
                LoggerUtil.warn("No products found on the listing page.");
                fail("No products available to check images.");
            }
            for (WebElement product : products) {
                if (!listingPage.isImageDisplayed(product)) {
                    LoggerUtil.error("Product image missing for product: " + listingPage.getProductName(product));
                    fail("Product image missing.");
                }
                LoggerUtil.info("Product image verified: " + listingPage.getProductName(product));
            }
        } catch (NoSuchElementException | TimeoutException e) {
            LoggerUtil.exception(e);
            fail("Error while verifying product images.");
        } catch (Exception e) {
            LoggerUtil.exception(e);
            fail("Unexpected error during product image verification.");
        }
    }

    @Then("each product must display a product name")
    public void checkProductNames() {
        try {
            List<WebElement> products = listingPage.getProducts();
            if (products.isEmpty()) {
                LoggerUtil.warn("No products found on the listing page.");
                fail("No products available to check names.");
            }
            for (WebElement product : products) {
                String name = listingPage.getProductName(product);
                if (name == null || name.isEmpty()) {
                    LoggerUtil.error("Product name missing for a product.");
                    fail("Product name missing.");
                }
                LoggerUtil.info("Product name verified: " + name);
            }
        } catch (NoSuchElementException | TimeoutException e) {
            LoggerUtil.exception(e);
            fail("Error while verifying product names.");
        } catch (Exception e) {
            LoggerUtil.exception(e);
            fail("Unexpected error during product name verification.");
        }
    }

    @When("I click on the product image")
    public void clickProductImage() {
        try {
            List<WebElement> products = listingPage.getProducts();
            if (products.isEmpty()) {
                LoggerUtil.warn("No products found to click on.");
                fail("Cannot click product image, no products available.");
            }
            products.get(0).click();
            LoggerUtil.info("Clicked on the first product image successfully.");
        } catch (IndexOutOfBoundsException e) {
            LoggerUtil.exception(e);
            fail("Product list is empty, cannot click on product image.");
        } catch (Exception e) {
            LoggerUtil.exception(e);
            fail("Unexpected error while clicking on product image.");
        }
    }

    @Then("the product details page for that product must open")
    public void verifyNavigation() {
        try {
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.contains("/product/")) {
                LoggerUtil.error("Navigation failed. Current URL: " + currentUrl);
                fail("Product details page did not open as expected.");
            }
            LoggerUtil.info("Successfully navigated to product details page: " + currentUrl);
        } catch (Exception e) {
            LoggerUtil.exception(e);
            fail("Error verifying navigation to product details page.");
        }
    }
}
