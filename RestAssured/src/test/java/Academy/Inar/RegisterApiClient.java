package Academy.Inar;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RegisterApiClient {
    public static void main(String[] args) {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me")
                .setContentType(ContentType.JSON).build();

        RequestSpecification reqPost = given().when().spec(req)
                .body("{\n" +
                "   \"clientName\": \"idris\",\n" +
                "   \"clientEmail\": \"idris@example.com\"\n" +
                "}");

        String response = reqPost.when().post("/api-clients").then().log().all().extract().response().asString();
    }
}
