package com.nttdata.page;

import org.openqa.selenium.By;

public class StoreLoginPage {
    public static By emailInput = By.id("field-email");
    public static By passwordInput = By.id("field-password");
    public static By signInButton = By.id("submit-login");
    public static By loginErrorMessage = By.cssSelector("li.alert.alert-danger");
}
