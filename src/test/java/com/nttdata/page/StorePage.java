package com.nttdata.page;

import org.openqa.selenium.By;

public class StorePage {
    // Localizador principal para el enlace de la categoría "Clothes"
    public static By clothesCategoryLink = By.id("category-3");

    // LOCALIZADOR  PARA "MEN"
    public static By menSideFilterLink = By.xpath("//div[@id='left-column']//a[contains(text(),'Men')]");

    public static By firstProductImage = By.cssSelector("article.product-miniature:first-child .thumbnail");
    public static By productQuantityInput = By.id("quantity_wanted");
    public static By addToCartButton = By.cssSelector("button.add-to-cart");
    public static By confirmationModalTitle = By.id("myModalLabel");
    public static By modalProductName = By.cssSelector(".product-name");
    public static By modalProductPrice = By.cssSelector(".modal .product-price");
    public static By modalQuantity = By.cssSelector("div.modal-body .col-md-6 strong");
    public static By modalSubtotal = By.cssSelector(".cart-content p:nth-child(2) span.value");
    public static By proceedToCheckoutButton = By.xpath("//div[@class='cart-content-btn']/a[contains(.,'Finalizar compra')]");
}
