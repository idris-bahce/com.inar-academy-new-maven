package Academy.Inar;

import NewPojo.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class GetAllProductions {
    public static void main(String[] args) throws JsonProcessingException {

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me")
                .build();
        RequestSpecification reqGet = given().log().all().spec(req);
        Product[] product = reqGet.when().get("/products").then().log().all().extract().response().as(Product[].class);
        System.out.println(product[1].getCategory());

    }
}
