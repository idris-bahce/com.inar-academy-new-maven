package Academy.Inar;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import NewPojo.Products;


import static io.restassured.RestAssured.given;
public class GetAllProductions {//Couldn't solve the exception problem!
    public static void main(String[] args) {

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me").build();

        RequestSpecification reqGet = given().log().all().spec(req);


        Products products = reqGet.when().get("/products").then().log().all().extract().response().as(Products.class);

        System.out.println(products.getProducts().get(1).getCategory());

    }
}
