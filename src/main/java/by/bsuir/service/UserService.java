package by.bsuir.contract;

import by.bsuir.model.Employer;
import by.bsuir.model.User;
import by.bsuir.model.UsersEntity;

import java.util.List;

public interface UserService {

    public List<User> getAllUsersBything(Integer thingId);

    public List<User> getAllemployerUsers(Integer employerId);

    public List<String> getAllUsersCities();

    public UsersEntity getUserByLogin(String login);

    public UsersEntity getUserById(Integer id);

    public User getUserrrById(Integer id);

    public Employer getemployerByLogin(String login);

    public User gettUserrrByLogin(String login);

    public UsersEntity saveUser(UsersEntity user);

    public List<Employer> getemployersList();

    public List<User> getUsersList();

    public Employer getemployerById(Integer id);

    public Employer getFullemployerById(Integer id);

    public boolean delete(UsersEntity usersEntity);

    public boolean updateUseremployerthing(Integer userId, Integer employerId, Integer thingId);

    public Integer useremployerthing(Integer userId, Integer employerId);

}
