package Academy.Inar;

import Files.Payload;
import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Basics {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Payload.addPlace() ).when().post("/maps/api/place/add/json").then().assertThat()
                .statusCode(200).body("scope",equalTo("APP")).header("server","Apache/2.4.41 (Ubuntu)")
                .extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String placeID = js.getString("place_id");
        System.out.println(placeID);

        String newAddress = "70 Summer walk, USA";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").when().put("/maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
                .body("msg",equalTo("Address successfully updated"));

        String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeID)
                .when().get("/maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract()
                .asString();

        JsonPath js1 = ReusableMethods.rowToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress,newAddress);
    }
}
