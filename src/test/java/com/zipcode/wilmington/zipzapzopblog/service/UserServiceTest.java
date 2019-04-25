package com.zipcode.wilmington.zipzapzopblog.service;

import com.zipcode.wilmington.zipzapzopblog.model.User;
import com.zipcode.wilmington.zipzapzopblog.repository.UserRepo;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Test
    public void createTest(){
        //given
        User user = new User("m@gmail.com", "23434", "mbrahma","m","b" );

        //mock
        UserRepo userRepo = mock(UserRepo.class);

        //expected data
        Long expectedId = 5l;
        User userToReturn = new User("m@gmail.com", "23434", "mbrahma","m","b" );
        userToReturn.setId(expectedId);

        // when the method save is called with the user
        // then return the created user
        when(userRepo.save(user)).thenReturn(userToReturn);

        // create class to test
        UserService userService = new UserService(userRepo);

        // call the method to test
        User actual = userService.save(user);

        //verify the result
        Assert.assertEquals(expectedId, actual.getId());

    }

    @Test
    public void updateTest() throws SQLException {
        //given
        User userToReturn = new User("m@gmail.com", "23434", "mbrahma","m","b" );
        userToReturn.setId(1L);
        User updatedUser = new User();

        //mock
        UserRepo userRepo = mock(UserRepo.class);

        // when actions
        when(userRepo.findById(1L)).thenReturn(Optional.of(userToReturn));
        // create class to test
        UserService userService = new UserService(userRepo);
        User user = userRepo.findById(1L).get();


        // call the method to test
        User actual = userService.update(user);

        // Make sure the method save got call with the person
        verify(userRepo).save(user);
    }

    @Test
    public void showTest(){
        //given
        User userToReturn = new User("m@gmail.com", "23434", "mbrahma","m","b" );
        userToReturn.setId(1L);
        //mock
        UserRepo userRepo = mock(UserRepo.class);

        // when actions
        when(userRepo.findById(1L)).thenReturn(Optional.of(userToReturn));
        // create class to test
        UserService userService = new UserService(userRepo);
        User user = userService.show(1l).get();

        // confirm that
        Assert.assertEquals(userToReturn,user);
    }

    @Test
    public void deleteTestPostive() throws SQLException{
        //mock
        Boolean expectedBool = true;
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(50L)).thenReturn(Optional.of(new User("mctina@gmail.com","hamburglar","mctina","Tina","SmallTock")));

        // create class to test
        UserService userService = new UserService(userRepo);

        // method call
        Boolean actual = userService.delete(50L);
        // verify
        verify(userRepo, times(1)).deleteById(50L);

        // boolean is returned
        Assert.assertEquals(expectedBool,actual);
    }

    @Test(expected = SQLException.class)
    public void deleteTestNegative() throws SQLException{
        //mock
        Boolean expectedBool = true;
        UserRepo userRepo = mock(UserRepo.class);

        // create class to test
        UserService userService = new UserService(userRepo);

        // method call
        Boolean actual = userService.delete(50L);
        // verify
        verify(userRepo, times(1)).deleteById(50L);

        // boolean is returned
        Assert.assertEquals(expectedBool,actual);
    }

    @Test
    public void showAllTest(){
        //given
        User user1 = new User("m@gmail.com", "23434", "mbrahma","m","b" );
        User user2 = new User("c@gmail.com","1235","cmcclintock","c","m");
        List<User> userList = Arrays.asList(user1,user2);

        //mock
        UserRepo userRepo = mock(UserRepo.class);

        // when actions
        when(userRepo.findAll()).thenReturn(userList);

        // create class to test
        UserService userService = new UserService(userRepo);

        // call method
        List<User> result = userService.showAll();

        // confirm method is functioning correctly
        Assert.assertEquals(userList,result);
    }

    @Test
    public void findByUsernameTest(){
        //given
        String username = "mbrahma";
        User userToReturn = new User("m@gmail.com", "23434", username,"m","b" );
        //mock
        UserRepo userRepo = mock(UserRepo.class);

        // when actions
        when(userRepo.findByUsername(username)).thenReturn(Optional.of(userToReturn));
        // create class to test
        UserService userService = new UserService(userRepo);
        User user = userService.findByUsername(username).get();

        // confirm that
        Assert.assertEquals(userToReturn,user);
    }

    @Test
    public void findByEmailTest(){
        //given
        String email = "m@gmail.com";
        User userToReturn = new User("m@gmail.com", "23434", "mbrahma","m","b" );
        //mock
        UserRepo userRepo = mock(UserRepo.class);

        // when actions
        when(userRepo.findByEmail(email)).thenReturn(Optional.of(userToReturn));
        // create class to test
        UserService userService = new UserService(userRepo);
        User user = userService.findByEmail(email).get();

        // confirm that
        Assert.assertEquals(userToReturn,user);
    }





}
