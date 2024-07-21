package utils;

import com.github.javafaker.Faker;
import java.util.Locale;

public class RandomUtils {
    static String firstName, lastName, userEmail;

    static Faker faker = new Faker(new Locale("en"));

    public static String getFirstName() {
        return firstName = faker.name().firstName();
    }

    public static String getPassword() { return lastName = faker.internet().password(); }

    public static String getEmail() {
        return userEmail = faker.internet().emailAddress();
    }

}
