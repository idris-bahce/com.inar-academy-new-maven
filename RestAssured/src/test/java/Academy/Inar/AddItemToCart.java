package Academy.Inar;

import NewPojo.AddItem;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class AddItemToCart {
    public static void main(String[] args) {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me")
                .setContentType(ContentType.JSON).build();

        AddItem addItem = new AddItem();
        addItem.setProductId("4646");

        RequestSpecification reqPost = given().log().all().spec(req).body(addItem);
        String response = reqPost.when().post("/carts/q9NWYsrVRHsQB8LO0DemJ/items/").then().log().all()
                .extract().response().asString();

    }
}
