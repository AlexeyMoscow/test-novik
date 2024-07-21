package tests.api;

import controllers.ApiControllers;
import models.User;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static controllers.ApiControllers.getUsers;
import static models.User.createNewUser;
import static org.junit.jupiter.api.Assertions.*;

public class ApiTests extends BaseTest{

    @Test
    void createNewUserTest (){
        User randomUser = createNewUser();
        ApiControllers.createUser(randomUser, true, HttpStatus.SC_OK);
        User createdUser = ApiControllers.getUserByUserName(randomUser.getUsername());

        assertEquals(randomUser, createdUser, "There is NO created user in list of all users" );
        assertNotNull(createdUser.getCreated_at(), "Field created_at not exist");
        assertNotNull(createdUser.getUpdated_at(), "Field updated_at not exist");
        assertEquals(createdUser.getId(), getUsers().size(), "User's ID is NOT last in users list");
    }

    @Test
    void getAllUsersTest (){
        Assertions.assertFalse(getUsers().isEmpty(), "No any created users");
    }

    @Test
    void createUserWithUsernameAlreadyExisted (){
        User randomUser = createNewUser();
        ApiControllers.createUser(randomUser, true, HttpStatus.SC_OK);

        User userWithSameUsername = createNewUser();
        userWithSameUsername.setUsername(randomUser.getUsername());

        ApiControllers.createUser(userWithSameUsername, false, HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void createUserWithEmailAlreadyExisted (){
        User randomUser = createNewUser();
        ApiControllers.createUser(randomUser, true, HttpStatus.SC_OK);

        User userWithSameEmail = createNewUser();
        userWithSameEmail.setEmail(randomUser.getEmail());

        ApiControllers.createUser(userWithSameEmail, false, HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void createUserWithNullUserName (){
        User randomUser = createNewUser();
        randomUser.setUsername(null);
        ApiControllers.createUser(randomUser, false, HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void createUserWithNullEmail (){
        User randomUser = createNewUser();
        randomUser.setEmail(null);
        ApiControllers.createUser(randomUser, false, HttpStatus.SC_BAD_REQUEST);
    }


    @Test
    void createUserWithNullPassword (){
        User randomUser = createNewUser();
        randomUser.setPassword(null);
        ApiControllers.createUser(randomUser, false, HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void verifyPasswordSavedAsCoded (){
        User randomUser = createNewUser();
        String passwordInRequest = randomUser.getPassword();
        ApiControllers.createUser(randomUser, true, HttpStatus.SC_OK);
        User createdUser = ApiControllers.getUserByUserName(randomUser.getUsername());

        assertNotEquals(passwordInRequest, createdUser.getPassword(), "Password is stored as NOT coded");
    }


}
