import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Test1_GET {

    @Test
    public void test_01_GET() {
        Response getUsersList = get("https://reqres.in/api/users?page=2");
        System.out.println(getUsersList.toString());
        System.out.println(getUsersList.getBody().toString());
        System.out.println(getUsersList.getStatusCode());
        System.out.println(getUsersList.getContentType());
        System.out.println("The end of the test");
    }

    @Test
    public void test_02_GET() {
        given().get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.id[0]",equalTo(7));
    }
}
