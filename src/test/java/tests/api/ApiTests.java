package tests.api;

import controllers.ApiControllers;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static controllers.ApiControllers.*;
import static models.User.createNewUser;

public class ApiTests extends BaseTest{

    @Test
    void getAllUsersTest (){
        List<User> users = getUsers();
        Assertions.assertFalse(users.isEmpty(), "No any created users");
    }

    @Test
    void createNewUserTest (){
        User randomUser = createNewUser();
        ApiControllers.createUser(randomUser);
        User createdUser = ApiControllers.getUserByUserName(randomUser.getUsername());

        Assertions.assertEquals(randomUser, createdUser, "There is NO created user in list of all users" );
    }
}
