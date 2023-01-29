package Files;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.given;

public class JiraTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://localhost:8080";

        SessionFilter session = new SessionFilter();

        String response = given().header("Content-Type", "application/json").body("{" +
                " \"username\": \"idrisbahce26\",\n" +
                " \"password\": \"123456\" }").log().all().filter(session).when().post("rest/auth/1/session")
                .then().extract().response().asString();


        given().pathParam("id", "10004").log().all().header("Content-Type", "application/json").body("{\n" +
                "    \"body\": \"Hey I am updating the existing comment.\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}").filter(session).when().post("/rest/api/2/issue/{id}/comment").then().assertThat()
                .statusCode(201);
    }
}
