package org.travelagency.service;

import org.travelagency.model.Package;
import org.travelagency.model.User;
import org.travelagency.repository.UserRepository;

import java.util.List;

public class UserService
{
    private UserRepository userRepo = new UserRepository();

    public User register(User user)
    {
        List<User> u = userRepo.findByUsername(user.getUsername());
        if(u != null && u.size() > 0)
        {
            return null;
        }
        else
        {
            userRepo.addUser(user);
            return user;
        }
    }

    public User login(User user)
    {
        List<User> u = userRepo.findByUsernameAndPassword(user);
        if(u != null && u.size() > 0)
        {
            return u.get(0);
        }
        else
        {
            return null;
        }
    }

    public User addVacationToUser(User user, Package vacation)
    {
        user.getVacations().clear();
        user.getVacations().add(vacation);
        userRepo.updateUser(user);
        return user;
    }

    public List<Package> getVacations(User user)
    {
        return userRepo.getVacations(user);
    }
}
