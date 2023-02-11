package Academy.Inar;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class DeleteItemFromCart {
    public static void main(String[] args) {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me")
                .setContentType(ContentType.JSON).build();

        RequestSpecification reqDelete = given().log().all().spec(req)
                .body("{\n" +
                " \"customerName\": \"idris\",\n" +
                " \"comment\": \"I need this at 2.00 am.\"\n" +
                "}");

        String response = reqDelete.delete("/carts/q9NWYsrVRHsQB8LO0DemJ/items/332121345").then()
                .log().all().extract().response().asString();
    }
}
