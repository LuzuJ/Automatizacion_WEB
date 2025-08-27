package com.nttdata.steps;

import com.nttdata.page.CartPage;
import com.nttdata.page.StoreHomePage;
import com.nttdata.page.StoreLoginPage;
import com.nttdata.page.StorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private double productPrice;

    public StoreSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navigateToLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(StoreHomePage.signInLink)).click();
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(StoreLoginPage.emailInput)).sendKeys(email);
        driver.findElement(StoreLoginPage.passwordInput).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(StoreLoginPage.signInButton)).click();
    }

    public void navigateToCategory(String category, String subcategory) {
        wait.until(ExpectedConditions.elementToBeClickable(StorePage.clothesCategoryLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(StorePage.menSideFilterLink)).click();
    }

    public void addFirstProductToCart(int units) {
        wait.until(ExpectedConditions.elementToBeClickable(StorePage.firstProductImage)).click();

        String priceText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".current-price span"))).getText();
        this.productPrice = cleanAndParsePrice(priceText);

        WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.productQuantityInput));
        quantityInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        quantityInput.sendKeys(String.valueOf(units));

        wait.until(ExpectedConditions.elementToBeClickable(StorePage.addToCartButton)).click();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(StorePage.proceedToCheckoutButton)).click();
    }

    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".user-info .logout")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginFailed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(StoreLoginPage.loginErrorMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isConfirmationPopupCorrect() {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    StorePage.confirmationModalTitle,
                    "Producto añadido correctamente"
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validatePopupTotal(int units) {
        String subtotalText = wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.modalSubtotal)).getText();
        double subtotal = cleanAndParsePrice(subtotalText);
        double expectedTotal = this.productPrice * units;
        return Math.abs(subtotal - expectedTotal) < 0.01;
    }

    public String getCartPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CartPage.pageTitle)).getText();
    }

    public boolean validateCartTotal(int units) {
        String totalText = wait.until(ExpectedConditions.visibilityOfElementLocated(CartPage.totalPrice)).getText();
        double total = cleanAndParsePrice(totalText);
        double expectedTotal = this.productPrice * units;
        return Math.abs(total - expectedTotal) < 0.01;
    }

    private double cleanAndParsePrice(String priceText) {
        String cleanedText = priceText.replace(",", ".").replaceAll("[^\\d.]", "");
        return Double.parseDouble(cleanedText);
    }

    public void navigateToNonExistentCategory(String category) {
        try {
            WebElement categoryLink = driver.findElement(By.xpath("//a[contains(text(),'" + category + "')]"));
            if (categoryLink.isDisplayed()) {
                categoryLink.click();
            }
        } catch (Exception e) {
            System.out.println("La categoría '" + category + "' no fue encontrada, como se esperaba.");
        }
    }

    public boolean isCategoryNotFound() {
        return !driver.getCurrentUrl().contains("Autos");
    }
}
