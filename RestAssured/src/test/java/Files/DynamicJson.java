package Files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle){
        RestAssured.baseURI="http://216.10.245.166";
        String response = given().header("Content-Type","application/json").body(Payload.addBook(isbn,aisle))
                .when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReusableMethods.rowToJson(response);
        String ID = js.get("ID");
        System.out.println(ID);
    }
    @DataProvider(name = "BooksData")
    public Object[][] getData(){

        return new Object[][]{{"olkjdfkad","652415"},{"lsdfskdcm","1635252"},{"lsdcjfskn","26845455"}};
    }
}
