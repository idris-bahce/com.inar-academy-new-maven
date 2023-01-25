package Academy.Inar;

import Files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
    @Test
    public void sumOfCourses(){
        JsonPath js = new JsonPath(Payload.CoursePrice());
        int sum = js.getInt("dashboard.purchaseAmount");

        int count = js.getInt("courses.size()");

        int sumOfPrices = 0;
        for (int i = 0; i < count; i++) {
            int priceOfCourses = js.getInt("courses["+i+"].price");
            int amountOfCourses = js.getInt("courses["+i+"].copies");
            sumOfPrices += priceOfCourses*amountOfCourses;
        }
        System.out.println(sumOfPrices);
        Assert.assertEquals(sum,sumOfPrices);
    }
}
