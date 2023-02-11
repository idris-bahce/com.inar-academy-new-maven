package Academy.Inar;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import NewPojo.CartCreateResponse;

import static io.restassured.RestAssured.given;

public class CreateCart {
    public static void main(String[] args) {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me").build();

        RequestSpecification reqPost = given().log().all().spec(req);

        CartCreateResponse response = reqPost.when().post("/carts").then().log().all().extract()
                .response().as(CartCreateResponse.class);

        System.out.println(response.getCartId());
    }
}
