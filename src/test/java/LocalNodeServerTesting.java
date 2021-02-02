import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class LocalNodeServerTesting {

    @BeforeTest
    public void setUp() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void test1_get() {
        given()
                .get("/users")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void test2_get() {
        given()
                .param("name", "Superhero")
                .get("/subjects")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void test3_post() {
        Random rand = new Random();
        int subjectId = rand.nextInt(3);

        Faker faker = new Faker();

        JSONObject request = new JSONObject();
        request.put("firstName", faker.name().firstName());
        request.put("lastName", faker.name().lastName());
        request.put("subjectId", ++subjectId);

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
    public void test4_delete() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .delete("/users/7")
                .then()
                .statusCode(200)
                .log().all();
    }
}
