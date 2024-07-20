package tests.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

import static utils.Constants.BASE_URL;

public class BaseTest {
    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }
}
