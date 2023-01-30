package Files;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://localhost:8080";

        //Login Scenario
        SessionFilter session = new SessionFilter();

        String response = given().header("Content-Type", "application/json").body("{" +
                " \"username\": \"idrisbahce26\",\n" +
                " \"password\": \"123456\" }").log().all().filter(session).when().post("rest/auth/1/session")
                .then().extract().response().asString();


        String expectedMessage="Hi How are you?";
        //Add comment
        String addCommentResponse = given().pathParam("id", "10004").log().all().
                header("Content-Type", "application/json").body("{\n" +
                "    \"body\": \""+expectedMessage+"\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}").filter(session).when().post("/rest/api/2/issue/{id}/comment").then().assertThat()
                .statusCode(201).extract().response().asString();
        JsonPath js = new JsonPath(addCommentResponse);
        String commentID = js.getString("id");


        //Add attachment
        given().header("X-Atlassian-Token","no-check").filter(session).pathParam("id", "10004")
                .queryParam("fields","comment").header("Content-Type","multipart/form-data")
                .multiPart("file",new File("jira.txt")).when().
                post("rest/api/2/issue/{id}/attachments").then().log().all().assertThat().statusCode(200)
                ;

        //Get Issue
        String issueDetails = given().filter(session).pathParam("id", "10004").log().all().when().
                get("/rest/api/2/issue/{id}").then().log().all().extract().response().asString();

        System.out.println(issueDetails);
        JsonPath js2 = new JsonPath(issueDetails);
        int commentsCounts = js2.get("fields.comment.comments.size()");
        for (int i = 0; i < commentsCounts; i++) {
            String commentIDIssue = js2.get("fields.comment.comments["+i+"].id").toString();
            if (commentIDIssue.equalsIgnoreCase(commentID)){
                String message = js2.get("fields.comment.comments["+i+"].body").toString();
                System.out.println(message);
                Assert.assertEquals(message,expectedMessage);
            }
        }
    }
}
