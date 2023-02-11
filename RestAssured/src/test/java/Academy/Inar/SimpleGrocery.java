package Academy.Inar;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class SimpleGrocery {
    public static void main(String[] args) {

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me").build();


        RequestSpecification reqGet = given().log().all().spec(req);

        String response = reqGet.get("/status").asString();

        System.out.println(response);
    }
}
