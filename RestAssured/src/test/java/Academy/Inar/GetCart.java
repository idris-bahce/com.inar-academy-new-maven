package Academy.Inar;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetCart {
    public static void main(String[] args) {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me")
                .build();

        RequestSpecification reqGet = given().log().all().spec(req);

        String response = reqGet.when().get("/carts/q9NWYsrVRHsQB8LO0DemJ").then().log().all().extract().asString();
    }
}
