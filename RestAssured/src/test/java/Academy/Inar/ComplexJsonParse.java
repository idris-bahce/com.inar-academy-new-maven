package Academy.Inar;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.CoursePrice());

        int count = js.getInt("courses.size()");
        System.out.println(count);

        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

    //    String titleFirstCourse = js.getString("courses[2].title");
    //    System.out.println(titleFirstCourse);

//        for (int i = 0; i < count; i++) {
//            System.out.println(js.get("courses["+i+"].title"));
//            System.out.println(js.get("courses["+i+"].price"));
//        }

        for (int i = 0; i < count; i++) {
            if (js.get("courses["+i+"].title").equals("RPA")){
                System.out.println(js.get("courses["+i+"].copies"));
                break;
            }
        }
    }
}
