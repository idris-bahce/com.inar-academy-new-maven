package Academy.Inar;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class DeleteOrder {
    public static void main(String[] args) {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me")
                .build();

        RequestSpecification reqDelete = given().log().all().spec(req).header("Authorization",
                "2d602514874869749c3bf3f0525afc12f61ef4ba8cd7841adb612cb086fe13c5");

        String response = reqDelete.when().delete("/orders/vgu3lHRW4o8n6Z8JK7TvQ").then().log().all()
                .extract().response().asString();
    }
}
