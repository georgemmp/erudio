package br.com.erudio.integration.swagger;

import br.com.erudio.config.TestConfig;
import br.com.erudio.testcontainer.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void shouldDisplaySwaggerUiPage() {
        var content = given().basePath("/swagger-ui/index.html")
                .port(TestConfig.SERVER_PORT)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .asString();

        assertTrue(content.contains("Swagger UI"));
    }
}
