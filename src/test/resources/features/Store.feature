# language: es
@Regression
Característica: Compra de Productos en la Tienda

  Como cliente de la tienda en línea,
  quiero poder navegar, seleccionar y validar productos en mi carrito
  para asegurar que el flujo de compra funcione correctamente.

  @CompraExitosa
  Esquema del escenario: Validación del precio de un producto con usuario válido
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "<usuario>" y clave "<clave>"
    Cuando navego a la categoria "<categoria>" y subcategoria "<subcategoria>"
    Y agrego <unidades> unidades del primer producto al carrito
    Entonces valido en el popup la confirmación del producto agregado
    Y valido en el popup que el monto total sea calculado correctamente
    Cuando finalizo la compra
    Entonces valido el titulo de la pagina del carrito
    Y vuelvo a validar el calculo de precios en el carrito

    Ejemplos:
      | usuario               | clave         | categoria | subcategoria | unidades |
      | jonathan123@gmail.com | H0l@C0m0_Test | CLOTHES   | Men          | 2        |

  @LoginFallido
  Esquema del escenario: Validación de login con credenciales inválidas
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "<usuario>" y clave "<clave>"
    Entonces valido que el login falle

    Ejemplos:
      | usuario              | clave         |
      | jonathan321@test.com | N0Es_Clave123 |

  @CategoriaInexistente
  Esquema del escenario: Validación de categoría inexistente
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "<usuario>" y clave "<clave>"
    Cuando intento navegar a una categoria inexistente "<categoria>"
    Entonces valido que la categoría no se encuentre

    Ejemplos:
      | usuario               | clave         | categoria |
      | jonathan123@gmail.com | H0l@C0m0_Test | Autos     |