package Academy.Inar;

import NewPojo.Order;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CreateAnOrder {
    public static void main(String[] args) {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me")
                .setContentType(ContentType.JSON).build();

        Order order = new Order();
        order.setCartId("q9NWYsrVRHsQB8LO0DemJ");
        order.setCustomerName("idris");

        RequestSpecification reqPost = given().when().spec(req).body(order).header("Authorization",
                "2d602514874869749c3bf3f0525afc12f61ef4ba8cd7841adb612cb086fe13c5");

        String response = reqPost.post("/orders").then().log().all().extract().response().asString();
    }
}
