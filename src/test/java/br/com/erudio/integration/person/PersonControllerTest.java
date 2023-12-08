package br.com.erudio.integration.person;

import br.com.erudio.config.IntegrationTest;
import br.com.erudio.config.TestConfig;
import br.com.erudio.domain.entities.person.Person;
import br.com.erudio.infrastrucutre.dtos.person.PersonRequestDTO;
import br.com.erudio.infrastrucutre.dtos.person.PersonResponseDTO;
import br.com.erudio.mocks.PersonDTOFactory;
import br.com.erudio.testcontainer.AbstractIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
public class PersonControllerTest extends AbstractIntegrationTest {
    private static ObjectMapper objectMapper;
    private static RequestSpecification specification;

    @Test
    @Order(1)
    public void shouldSavePerson() throws IOException {
        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .addHeader(HttpHeaders.ORIGIN, "http://localhost:8081")
                .addHeader(HttpHeaders.AUTHORIZATION, token)
                .setBasePath("/api/v1/people")
                .setPort(TestConfig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        PersonRequestDTO mockRequest = PersonDTOFactory.createPersonRequestDTO().build();
        PersonResponseDTO mockResponse = PersonDTOFactory.createPersonResponseDTO().build();

        var content = given()
                .spec(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(mockRequest)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .body()
                .asString();

        Person createdPerson = objectMapper.readValue(content, Person.class);

        assertEquals(mockResponse.getAddress(), createdPerson.getAddress());
        assertEquals(mockResponse.getFirstName(), createdPerson.getFirstName());
        assertEquals(mockResponse.getLastName(), createdPerson.getLastName());
        assertEquals(mockResponse.getGender(), createdPerson.getGender());
    }

    @Test
    @Order(2)
    public void shouldUpdatePersonById() throws IOException {
        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .addHeader(HttpHeaders.ORIGIN, "http://localhost:8081")
                .addHeader(HttpHeaders.AUTHORIZATION, token)
                .setBasePath("/api/v1/people")
                .setPort(TestConfig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        PersonRequestDTO mockRequest = PersonDTOFactory.createPersonRequestDTO().build();
        PersonResponseDTO mockResponse = PersonDTOFactory.createPersonResponseDTO().build();

        var content = given()
                .spec(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", mockResponse.getId())
                .body(mockRequest)
                .when()
                .patch("{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .asString();

        Person createdPerson = objectMapper.readValue(content, Person.class);

        assertEquals(mockResponse.getAddress(), createdPerson.getAddress());
        assertEquals(mockResponse.getFirstName(), createdPerson.getFirstName());
        assertEquals(mockResponse.getLastName(), createdPerson.getLastName());
        assertEquals(mockResponse.getGender(), createdPerson.getGender());
    }

    @Test
    @Order(3)
    public void shouldFindPersonById() throws IOException {
        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .addHeader(HttpHeaders.ORIGIN, "http://localhost:8081")
                .addHeader(HttpHeaders.AUTHORIZATION, token)
                .setBasePath("/api/v1/people")
                .setPort(TestConfig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        PersonResponseDTO mockResponse = PersonDTOFactory.createPersonResponseDTO().build();

        var content = given()
                .spec(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", mockResponse.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .asString();

        Person person = objectMapper.readValue(content, Person.class);

        assertEquals(mockResponse.getAddress(), person.getAddress());
        assertEquals(mockResponse.getFirstName(), person.getFirstName());
        assertEquals(mockResponse.getLastName(), person.getLastName());
        assertEquals(mockResponse.getGender(), person.getGender());
    }

    @Test
    @Order(4)
    public void shouldFindAll() throws IOException {
        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .addHeader(HttpHeaders.ORIGIN, "http://localhost:8081")
                .addHeader(HttpHeaders.AUTHORIZATION, token)
                .setBasePath("/api/v1/people")
                .setPort(TestConfig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        PersonResponseDTO mockResponse = PersonDTOFactory.createPersonResponseDTO().build();

        given()
                .spec(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get()
                .then()
                .assertThat()
                .body("[0].firstName", equalTo(mockResponse.getFirstName()))
                .body("[0].lastName", equalTo(mockResponse.getLastName()))
                .body("[0].address", equalTo(mockResponse.getAddress()))
                .body("[0].gender", equalTo(mockResponse.getGender().name()))
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .asString();
    }

    @Test
    @Order(5)
    public void shouldDeleteById() throws IOException {
        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .addHeader(HttpHeaders.ORIGIN, "http://localhost:8081")
                .addHeader(HttpHeaders.AUTHORIZATION, token)
                .setBasePath("/api/v1/people")
                .setPort(TestConfig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        PersonResponseDTO mockResponse = PersonDTOFactory.createPersonResponseDTO().build();

        var content = given()
                .spec(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", mockResponse.getId())
                .when()
                .delete("{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .extract()
                .body()
                .asString();

        assertNotNull(content);
    }
}
