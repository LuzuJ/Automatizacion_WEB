package com.nttdata.stepsdefinitions;

import com.nttdata.core.DriverManager;
import com.nttdata.steps.StoreSteps;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class StoreStepsDef {

    private WebDriver driver;
    private StoreSteps storeSteps;
    private int units;

    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
        storeSteps = new StoreSteps(driver);
    }

    @Dado("estoy en la página de la tienda")
    public void estoyEnLaPáginaDeLaTienda() {
        driver.get("https://qalab.bensg.com/store/");
        storeSteps.navigateToLoginPage();
    }

    @Y("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String user, String pass) {
        storeSteps.login(user, pass);
        if (user.equals("jonathan123@gmail.com")) {
            Assertions.assertTrue(storeSteps.isLoginSuccessful(), "El inicio de sesión falló para un usuario válido.");
        } else {
            Assertions.assertTrue(storeSteps.isLoginFailed(), "No se mostró el mensaje de error para un usuario inválido.");
        }
    }

    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String category, String subcategory) {
        storeSteps.navigateToCategory(category, subcategory);
    }

    @Y("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(Integer units) {
        this.units = units;
        storeSteps.addFirstProductToCart(units);
    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        Assertions.assertTrue(storeSteps.isConfirmationPopupCorrect(), "El popup de confirmación no apareció o su texto es incorrecto.");
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        Assertions.assertTrue(storeSteps.validatePopupTotal(this.units), "El cálculo del subtotal en el popup es incorrecto.");
    }

    @Cuando("finalizo la compra")
    public void finalizoLaCompra() {
        storeSteps.proceedToCheckout();
    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        Assertions.assertEquals("CARRITO", storeSteps.getCartPageTitle());
    }

    @Y("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        Assertions.assertTrue(storeSteps.validateCartTotal(this.units), "El cálculo del total en la página del carrito es incorrecto.");
    }
}
