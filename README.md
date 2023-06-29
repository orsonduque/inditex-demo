# INDITEX-DEMO

Este documento proporciona información sobre la aplicación/servicio en SpringBoot que permite consultar tarifas de productos. La aplicación utiliza una base de datos en memoria (H2) y se inicializa con los datos de ejemplo proporcionados.

# Configuración y Ejecución

Para configurar y ejecutar la aplicación, siga los pasos a continuación:

1. Clone el repositorio o descargue el código fuente de la aplicación.
2. Para lanzar la aplicación, ejecutar:

```
 ./gradlew
```

3. Usuario:admin, Contraseña:admin
4. URL de acceso: http://localhost:8080/
5. URL de acceso swagger: http://localhost:8080/admin/docs

## Testing

Para lanzar las pruebas de integración de la aplicación, ejecutar:

```
./gradlew test integrationTest
```

## Consideraciones

Los test solicitados se realizaron parametrizados en la clase PriceResourceIT

```
@ParameterizedTest
@CsvSource(
{
"2020-06-14T10:00:00+02:00, 1, 35455", // Test 1
"2020-06-14T16:00:00+02:00, 1, 35455", // Test 2
"2020-06-14T21:00:00+02:00, 1, 35455", // Test 3
"2020-06-15T10:00:00+02:00, 1, 35455", // Test 4
"2020-06-16T21:00:00+02:00, 1, 35455", // Test 5
}
)void testGetPrice(ZonedDateTime dateTime, Long brandId, Long productId) throws Exception {
Optional<PriceOutputDTO> optionalPriceOutputDTO = priceService.findOneByDateAndBrandIdAndProductId(dateTime, brandId, productId);
PriceOutputDTO priceOutputDTO = optionalPriceOutputDTO.orElseThrow(() -> new AssertionError("priceOutput is null"));

        restPriceMockMvc
            .perform(get(ENTITY_API_URL + "/brand/{brandId}/product/{productId}/date/{date}", brandId, productId, dateTime))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.brandId").value(priceOutputDTO.getBrandId()))
            .andExpect(jsonPath("$.productId").value(priceOutputDTO.getProductId()))
            .andExpect(jsonPath("$.priceList").value(priceOutputDTO.getPriceList()))
            .andExpect(jsonPath("$.priceValue").value(priceOutputDTO.getPriceValue()));
    }
```

Mediante Sonar fueron detectados los siguientes detalles:

1. Inheritance tree of classes should not be too deep: Las clases BadRequestAlertException, EmailAlreadyUsedException, InvalidPasswordException y LoginAlreadyUsedException son generadas por JHipster y siguen las mejores prácticas de desarrollo de software. Por lo tanto, no es necesario tomar ninguna acción en este caso.
2. Make sure disabling Spring Security's CSRF protection is safe here: En la clase SecurityConfiguration, la protección CSRF está desactivada por defecto. Sin embargo, en entornos de producción, es importante configurar adecuadamente el token CSRF para garantizar la seguridad de la aplicación. Dado que este es un examen y no se considera parte del alcance, se ha omitido la configuración del token CSRF. Es importante tener en cuenta que en un entorno de producción real, se debe implementar la configuración adecuada del token CSRF para proteger contra ataques CSRF..

```
@Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
   // @formatter:off
   http
   .csrf()
   .disable()
```
