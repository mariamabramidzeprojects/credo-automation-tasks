package api;

import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WireMockTests {

    @Test
    public void getUsersReturns200() {

        configureFor("localhost", 8080);

        stubFor(get(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("Users fetched successfully")));

        given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/users")
                .then()
                .statusCode(200);
    }

    @Test
    public void getUsersReturns500() {

        configureFor("localhost", 8080);

        stubFor(get(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withBody("Internal Server Error")));

        given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/users")
                .then()
                .statusCode(500);
    }
}