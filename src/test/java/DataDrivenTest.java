import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class DataDrivenTest {

    @BeforeTest
    public void setUp() {
        baseURI = "http://localhost:3000";
    }

    @Test(dataProvider = "DataSet")
    public void test1_post(String firstName, String lastName, int subjectId) {
            JSONObject request = new JSONObject();
            request.put("firstName", firstName);
            request.put("lastName", lastName);
            request.put("subjectId", subjectId);

            given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(request.toJSONString())
                    .post("/users")
                    .then()
                    .statusCode(201)
                    .log().all();
    }

//    @Test
    public void deleteUsers() {
        test_deleteAllUsers(11, 865);
    }

    public void test_deleteAllUsers(int firstId, int lastId) {
        for (int i = firstId; i <= lastId; i++) {
            try {
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .delete(format("/users/%s", i));
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @DataProvider(name = "DataSet")
    public Object[][] dataForPost() {
        Random rand = new Random();
        Faker faker = new Faker();
        Object[][] data = new Object[2][3];

        for (int i = 0; i < 2; i++) {
            data[i][0] = faker.name().firstName();
            data[i][1] = faker.name().lastName();
            data[i][2] = rand.nextInt(3) + 1;
        }
        return data;
    }
}
