package com.nttdata.page;

import org.openqa.selenium.By;

public class CartPage {
    public static By pageTitle = By.cssSelector("h1");

    public static By totalPrice = By.cssSelector("div.cart-summary-line.cart-total span.value");
}
