package com.zipcode.wilmington.zipzapzopblog.service;

import com.zipcode.wilmington.zipzapzopblog.model.User;
import com.zipcode.wilmington.zipzapzopblog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public Optional<User>  show(Long id){
        return userRepo.findById(id);
    }

    public List<User> showAll(){ return userRepo.findAll();}

    public User save(User user){
        return userRepo.save(user);
    }

    public Boolean delete(Long id) throws SQLException{
        Optional<User> user = show(id);
        if(user.isPresent()){
            userRepo.deleteById(id);
            return true;
        }
        else{
            throw new SQLException();
        }

    }

    public User update(User user) throws SQLException{
        Optional<User> optional = userRepo.findById(user.getId());
        if(optional.isPresent()) {
            User originalUser = optional.get();
            originalUser.setEmail(user.getEmail());
            originalUser.setFirstName(user.getFirstName());
            originalUser.setLastName(user.getLastName());
            return userRepo.save(originalUser);
        }else{
            throw new SQLException();
        }
    }

    public Optional<User> findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    }

}
