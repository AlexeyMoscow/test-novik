package controllers;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import models.User;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static utils.Constants.CREATE_USER;
import static utils.Constants.GET_USER;

public class ApiControllers {

    public static List<User> getUsers() {
        JsonPath jsonPath = given()
                .when()
                .get(GET_USER)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath();
        return jsonPath.getList("", User.class);
    }

    public static void createUser(User user, boolean successStatus, int code) {
        given().urlEncodingEnabled(true)
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .param("email", user.getEmail())
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post(CREATE_USER)
                .then()
                .statusCode(code)
                .body("success", is(successStatus));
    }

    public static User getUserByUserName(String username) {
        return getUsers().stream().filter(element -> element.getUsername().equals(username)).toList().getFirst();
    }

}
